package net.x_j0nnay_x.simpeladd.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.x_j0nnay_x.simpeladd.blocks.entity.Abst_CropGrowthBlockEntity;

public abstract class Abst_CropGrowthBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WORKING = BooleanProperty.create("working");

    protected Abst_CropGrowthBlock(Properties $$0) {
        super($$0);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WORKING, Boolean.valueOf(false)));
    }

    @Override
    public void onRemove(BlockState $$0, Level $$1, BlockPos $$2, BlockState $$3, boolean $$4) {
        if (!$$0.is($$3.getBlock())) {
            BlockEntity $$5 = $$1.getBlockEntity($$2);
            if ($$5 instanceof Abst_CropGrowthBlockEntity) {
                if ($$1 instanceof ServerLevel) {
                    Containers.dropContents($$1, $$2, (Abst_CropGrowthBlockEntity)$$5);
                    Containers.dropItemStack($$1, $$2.getX(), $$2.getY(), $$2.getZ(), ((Abst_CropGrowthBlockEntity)$$5).getCopperOutput());
                }
                $$1.updateNeighbourForOutputSignal($$2, this);
            }
            super.onRemove($$0, $$1, $$2, $$3, $$4);
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState $$0) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> $$0) {
        $$0.add(FACING,WORKING);
    }
}