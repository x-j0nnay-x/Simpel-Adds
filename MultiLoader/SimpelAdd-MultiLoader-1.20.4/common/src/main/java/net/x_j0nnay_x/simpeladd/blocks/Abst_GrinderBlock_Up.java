package net.x_j0nnay_x.simpeladd.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.x_j0nnay_x.simpeladd.blocks.entity.Abst_GrinderBlockEntity_Up;

public abstract class Abst_GrinderBlock_Up extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WORKING = BooleanProperty.create("working");

    protected Abst_GrinderBlock_Up(Properties $$0) {
        super($$0);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WORKING, Boolean.valueOf(false)));
    }


    @Override
    public void onRemove(BlockState $$0, Level $$1, BlockPos $$2, BlockState $$3, boolean $$4) {
        if (!$$0.is($$3.getBlock())) {
            BlockEntity $$5 = $$1.getBlockEntity($$2);
            if ($$5 instanceof Abst_GrinderBlockEntity_Up) {
                if ($$1 instanceof ServerLevel) {
                    Containers.dropContents($$1, $$2, (Abst_GrinderBlockEntity_Up)$$5);
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
        $$0.add(FACING, WORKING);
    }
    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (!blockState.getValue(WORKING).booleanValue()) {
            return;
        }
        double d = (double)blockPos.getX() + 0.5;
        double e = blockPos.getY();
        double f = (double)blockPos.getZ() + 0.5;
        if (randomSource.nextDouble() < 0.1) {
            level.playLocalSound(d, e, f, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 0.35f, 0.35f, false);
        }
        Direction direction = blockState.getValue(FACING);
        Direction.Axis axis = direction.getAxis();
        double g = 0.52;
        double h = randomSource.nextDouble() * 0.6 - 0.3;
        double i = axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52 : h;
        double j = randomSource.nextDouble() * 6.0 / 16.0;
        double k = axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52 : h;
        level.addParticle(ParticleTypes.SMOKE, d + i, e + j, f + k, 0.0, 0.0, 0.0);
        level.addParticle(ParticleTypes.ASH, d + i, e + j, f + k, 0.0, 0.0, 0.0);
    }
}