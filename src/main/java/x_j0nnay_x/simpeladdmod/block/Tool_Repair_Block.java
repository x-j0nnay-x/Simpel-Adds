package x_j0nnay_x.simpeladdmod.block;


import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;
import x_j0nnay_x.simpeladdmod.block.entitys.Grinder_Block_Entity;
import x_j0nnay_x.simpeladdmod.block.entitys.Tool_Repair_Block_Entity;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;


public class Tool_Repair_Block extends BaseEntityBlock {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final MapCodec<Tool_Repair_Block> CODEC = simpleCodec(Tool_Repair_Block::new);
    private static final VoxelShape BASE = Block.box((double)2.0F, (double)0.0F, (double)2.0F, (double)14.0F, (double)4.0F, (double)14.0F);;
    private static final VoxelShape X_LEG1 = Block.box((double)3.0F, (double)4.0F, (double)4.0F, (double)13.0F, (double)5.0F, (double)12.0F);
    private static final VoxelShape X_LEG2 = Block.box((double)4.0F, (double)5.0F, (double)6.0F, (double)12.0F, (double)10.0F, (double)10.0F);
    private static final VoxelShape X_TOP = Block.box((double)0.0F, (double)10.0F, (double)3.0F, (double)16.0F, (double)16.0F, (double)13.0F);
    private static final VoxelShape Z_LEG1 = Block.box((double)4.0F, (double)4.0F, (double)3.0F, (double)12.0F, (double)5.0F, (double)13.0F);
    private static final VoxelShape Z_LEG2 = Block.box((double)6.0F, (double)5.0F, (double)4.0F, (double)10.0F, (double)10.0F, (double)12.0F);
    private static final VoxelShape Z_TOP = Block.box((double)3.0F, (double)10.0F, (double)0.0F, (double)13.0F, (double)16.0F, (double)16.0F);
    private static final VoxelShape X_AXIS_AABB = Shapes.or(BASE, new VoxelShape[]{X_LEG1, X_LEG2, X_TOP});;
    private static final VoxelShape Z_AXIS_AABB = Shapes.or(BASE, new VoxelShape[]{Z_LEG1, Z_LEG2, Z_TOP});;

    public Tool_Repair_Block(Properties properties) {
        super(properties);
    }

    /* FACING */
    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getClockWise());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    /* BLOCK ENTITY */
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new Tool_Repair_Block_Entity(worldPosition, blockState);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, ItemStack toolStack, boolean willHarvest, FluidState fluid) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof Tool_Repair_Block_Entity blockEntity1) {
            blockEntity1.drops();
        }
        return super.onDestroyedByPlayer(state, level, pos, player, toolStack, willHarvest, fluid);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if(entity instanceof Tool_Repair_Block_Entity blockEntity) {
                player.openMenu(new SimpleMenuProvider(blockEntity, Component.translatable("block.simpeladdmod.grinder")), pos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        if(level.isClientSide()) {
            return null;
        }

        return createTickerHelper(type, Mod_Registrys.Mod_Block_Entity.TOOL_REPAIR_BLOCK_ENTTY.get(), (level1, pos, state, entity) ->
                entity.tick(level1, pos, state));
    }

    @Override
    public RenderShape getRenderShape(BlockState $$0) {
        return RenderShape.MODEL;
    }

    public VoxelShape getShape(BlockState $$0, BlockGetter $$1, BlockPos $$2, CollisionContext $$3) {
        Direction $$4 = (Direction)$$0.getValue(FACING);
        return $$4.getAxis() == Direction.Axis.X ? X_AXIS_AABB : Z_AXIS_AABB;
    }
}