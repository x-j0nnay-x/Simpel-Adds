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
import net.x_j0nnay_x.simpeladdmod.block.entity.NetheriteCrafterBlockEntity;
import net.x_j0nnay_x.simpeladdmod.block.entity.NetheriteCrafterBlockEntity;
import org.jetbrains.annotations.Nullable;

public class NetheriteCrafterBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final MapCodec<NetheriteCrafterBlock> CODEC = createCodec(NetheriteCrafterBlock::new);

    public static DirectionProperty FACING = DirectionProperty.of("facing",
            Direction.EAST,
            Direction.WEST,
            Direction.NORTH,
            Direction.SOUTH);
    public static final BooleanProperty WORKING = BooleanProperty.of("working");
    public NetheriteCrafterBlock(Settings pProperties) {
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
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return this.stateManager.getDefaultState().with(FACING, rotation.rotate(state.get(FACING)));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(WORKING);
    }
    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return  this.stateManager.getDefaultState().rotate(mirror.getRotation(state.get(FACING)));
    }
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        BlockEntity blockEntity;
        if ((blockEntity = world.getBlockEntity(pos)) instanceof NetheriteCrafterBlockEntity) {
            ((NetheriteCrafterBlockEntity)blockEntity).getDisplayName();
        }
    }



    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof NetheriteCrafterBlockEntity) {
                ItemScatterer.spawn(world, pos, (NetheriteCrafterBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = ((NetheriteCrafterBlockEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new NetheriteCrafterBlockEntity(pos, state);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.NETHERITE_CRAFTER,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }
}