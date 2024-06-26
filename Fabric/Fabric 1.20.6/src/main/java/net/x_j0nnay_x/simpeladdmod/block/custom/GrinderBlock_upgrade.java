package net.x_j0nnay_x.simpeladdmod.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.x_j0nnay_x.simpeladdmod.block.ModBlockEntities;
import net.x_j0nnay_x.simpeladdmod.block.entity.GrinderBlockEntity;
import net.x_j0nnay_x.simpeladdmod.block.entity.GrinderBlockEntity_upgrade;
import org.jetbrains.annotations.Nullable;

public class GrinderBlock_upgrade extends BlockWithEntity implements BlockEntityProvider {
    public static final MapCodec<GrinderBlock_upgrade> CODEC = createCodec(GrinderBlock_upgrade::new);

    public static DirectionProperty FACING = DirectionProperty.of("facing",
            Direction.EAST,
            Direction.WEST,
            Direction.NORTH,
            Direction.SOUTH);
    public static final BooleanProperty WORKING = BooleanProperty.of("working");
    public GrinderBlock_upgrade(Settings pProperties) {
        super(pProperties);

    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(WORKING, false);
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(WORKING);
    }
    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return this.stateManager.getDefaultState().with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return  this.stateManager.getDefaultState().rotate(mirror.getRotation(state.get(FACING)));
    }
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        BlockEntity blockEntity;
        if ((blockEntity = world.getBlockEntity(pos)) instanceof GrinderBlockEntity_upgrade) {
            ((GrinderBlockEntity_upgrade)blockEntity).getDisplayName();
        }
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GrinderBlockEntity_upgrade(pos, state);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof GrinderBlockEntity_upgrade) {
                ItemScatterer.spawn(world, pos, (GrinderBlockEntity_upgrade)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = ((GrinderBlockEntity_upgrade) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.GRINDER_UP,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

}
