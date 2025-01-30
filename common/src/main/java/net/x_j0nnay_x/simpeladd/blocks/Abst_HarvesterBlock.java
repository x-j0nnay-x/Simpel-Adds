package net.x_j0nnay_x.simpeladd.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.x_j0nnay_x.simpeladd.blocks.entity.Abst_HavesterBlockEntity;


public abstract class Abst_HarvesterBlock extends BaseEntityBlock {

    public static final BooleanProperty POWERED = BooleanProperty.create("powered");

    protected Abst_HarvesterBlock(Properties $$0) {
        super($$0);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, Boolean.valueOf(false)));
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(POWERED, context.getLevel().hasNeighborSignal(context.getClickedPos()));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> $$0) {
        $$0.add(POWERED);
    }

    @Override
    protected void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
        super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
        BlockPos _pos = BlockPos.containing(pos.getX(), pos.getY(), pos.getZ());
        BlockState _bs = world.getBlockState(_pos);

        if (world.getBestNeighborSignal(pos) > 0 ){
            if (Boolean.FALSE.equals(_bs.getValue(this.POWERED))) {

                world.setBlock(_pos, _bs.setValue(this.POWERED, true), 3);
            }
        } else if (world.getBestNeighborSignal(pos) < 1 ) {
            if (Boolean.TRUE.equals(_bs.getValue(this.POWERED))){

                world.setBlock(_pos, _bs.setValue(this.POWERED, false), 6);
            }

        }
    }

    @Override
    public void onRemove(BlockState $$0, Level $$1, BlockPos $$2, BlockState $$3, boolean $$4) {
        if (!$$0.is($$3.getBlock())) {
            BlockEntity $$5 = $$1.getBlockEntity($$2);
            if ($$5 instanceof Abst_HavesterBlockEntity) {
                if ($$1 instanceof ServerLevel) {
                    Containers.dropContents($$1, $$2, (Abst_HavesterBlockEntity)$$5);
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
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
    }

}
