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
import net.x_j0nnay_x.simpeladd.blocks.entity.Abst_ChillerBlockEntity;


public abstract class Abst_ChillerBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WORKING = BooleanProperty.create("working");

    protected Abst_ChillerBlock(Properties $$0) {
        super($$0);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WORKING, Boolean.valueOf(false)));
    }


    @Override
    public void onRemove(BlockState $$0, Level $$1, BlockPos $$2, BlockState $$3, boolean $$4) {
        if (!$$0.is($$3.getBlock())) {
            BlockEntity $$5 = $$1.getBlockEntity($$2);
            if ($$5 instanceof Abst_ChillerBlockEntity) {
                if ($$1 instanceof ServerLevel) {
                    Containers.dropContents($$1, $$2, (Abst_ChillerBlockEntity)$$5);
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
    public void animateTick(BlockState $$0, Level $$1, BlockPos $$2, RandomSource $$3) {
        if (!$$0.getValue(WORKING).booleanValue()) {
            return;
        }
        if ($$3.nextInt(100) == 0) {
            $$1.playLocalSound((double)$$2.getX() + 0.5, (double)$$2.getY() + 0.5, (double)$$2.getZ() + 0.5, SoundEvents.SNOW_STEP, SoundSource.BLOCKS, 0.5F, $$3.nextFloat() * 0.07F + 0.15F, false);
        }

        for(int $$4 = 0; $$4 < 4; ++$$4) {
            double $$5 = (double)$$2.getX() + $$3.nextDouble();
            double $$6 = (double)$$2.getY() + $$3.nextDouble();
            double $$7 = (double)$$2.getZ() + $$3.nextDouble();
            double $$8 = ((double)$$3.nextFloat() - 0.5) * 0.5;
            double $$9 = ((double)$$3.nextFloat() - 0.5) * 0.5;
            double $$10 = ((double)$$3.nextFloat() - 0.5) * 0.5;
            int $$11 = $$3.nextInt(2) * 2 - 1;
            if (!$$1.getBlockState($$2.west()).is(this) && !$$1.getBlockState($$2.east()).is(this)) {
                $$5 = (double)$$2.getX() + 0.5 + 0.25 * (double)$$11;
                $$8 = (double)($$3.nextFloat() * 0.25F * (float)$$11);
            } else {
                $$7 = (double)$$2.getZ() + 0.5 + 0.25 * (double)$$11;
                $$10 = (double)($$3.nextFloat() * 0.25F * (float)$$11);
            }

            $$1.addParticle(ParticleTypes.SNOWFLAKE, $$5, $$6, $$7, $$8, $$9, $$10);
            $$1.addParticle(ParticleTypes.ASH, $$5, $$6, $$7, $$8, $$9, $$10);
        }

    }


}