package net.x_j0nnay_x.simpeladd.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.x_j0nnay_x.simpeladd.blocks.entity.Abst_ToolRepairBlockEntity;


public abstract class Abst_ToolRepairBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final VoxelShape BASE = Block.box((double)2.0F, (double)0.0F, (double)2.0F, (double)14.0F, (double)4.0F, (double)14.0F);;
    private static final VoxelShape X_LEG1 = Block.box((double)3.0F, (double)4.0F, (double)4.0F, (double)13.0F, (double)5.0F, (double)12.0F);
    private static final VoxelShape X_LEG2 = Block.box((double)4.0F, (double)5.0F, (double)6.0F, (double)12.0F, (double)10.0F, (double)10.0F);
    private static final VoxelShape X_TOP = Block.box((double)0.0F, (double)10.0F, (double)3.0F, (double)16.0F, (double)16.0F, (double)13.0F);
    private static final VoxelShape Z_LEG1 = Block.box((double)4.0F, (double)4.0F, (double)3.0F, (double)12.0F, (double)5.0F, (double)13.0F);
    private static final VoxelShape Z_LEG2 = Block.box((double)6.0F, (double)5.0F, (double)4.0F, (double)10.0F, (double)10.0F, (double)12.0F);
    private static final VoxelShape Z_TOP = Block.box((double)3.0F, (double)10.0F, (double)0.0F, (double)13.0F, (double)16.0F, (double)16.0F);
    private static final VoxelShape X_AXIS_AABB = Shapes.or(BASE, new VoxelShape[]{X_LEG1, X_LEG2, X_TOP});;
    private static final VoxelShape Z_AXIS_AABB = Shapes.or(BASE, new VoxelShape[]{Z_LEG1, Z_LEG2, Z_TOP});;

    protected Abst_ToolRepairBlock(Properties $$0) {
        super($$0);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public void onRemove(BlockState $$0, Level $$1, BlockPos $$2, BlockState $$3, boolean $$4) {
        if (!$$0.is($$3.getBlock())) {
            BlockEntity $$5 = $$1.getBlockEntity($$2);
            if ($$5 instanceof Abst_ToolRepairBlockEntity) {
                if ($$1 instanceof ServerLevel) {
                    Containers.dropContents($$1, $$2, (Abst_ToolRepairBlockEntity)$$5);
                    Containers.dropItemStack($$1, $$2.getX(), $$2.getY(), $$2.getZ(), ((Abst_ToolRepairBlockEntity)$$5).getCopperOutput());
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

    public VoxelShape getShape(BlockState $$0, BlockGetter $$1, BlockPos $$2, CollisionContext $$3) {
        Direction $$4 = (Direction)$$0.getValue(FACING);
        return $$4.getAxis() == Direction.Axis.X ? X_AXIS_AABB : Z_AXIS_AABB;
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
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getClockWise());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> $$0) {
        $$0.add(FACING);
    }


}