package x_j0nnay_x.simpeladdmod.block;


import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;
import x_j0nnay_x.simpeladdmod.block.entitys.Harvester_Block_Entity;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;


public class Harvester_Block extends BaseEntityBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final MapCodec<Harvester_Block> CODEC = simpleCodec(Harvester_Block::new);

    public Harvester_Block(Properties properties) {
        super(properties);
    }

    /* FACING */
    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(POWERED, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    /* BLOCK ENTITY */
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new Harvester_Block_Entity(worldPosition, blockState);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, ItemStack toolStack, boolean willHarvest, FluidState fluid) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof Harvester_Block_Entity blockEntity1) {
            blockEntity1.drops();
        }
        return super.onDestroyedByPlayer(state, level, pos, player, toolStack, willHarvest, fluid);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if(entity instanceof Harvester_Block_Entity blockEntity) {
                player.openMenu(new SimpleMenuProvider(blockEntity, Component.translatable("block.simpeladdmod.harvester")), pos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, block, orientation, movedByPiston);
        BlockPos _pos = BlockPos.containing(pos.getX(), pos.getY(), pos.getZ());
        BlockState _bs = level.getBlockState(_pos);

        if (level.hasNeighborSignal(pos)){
            if (Boolean.FALSE.equals(_bs.getValue(this.POWERED))) {

                level.setBlock(_pos, _bs.setValue(this.POWERED, true), 3);
            }
        } else if (level.getBestNeighborSignal(pos) < 1 ) {
            if (Boolean.TRUE.equals(_bs.getValue(this.POWERED))){

                level.setBlock(_pos, _bs.setValue(this.POWERED, false), 6);
            }
        }
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        if(level.isClientSide()) {
            return null;
        }

        return createTickerHelper(type, Mod_Registrys.Mod_Block_Entity.HARVESTER_BLOCK_ENTITY.get(), (level1, pos, state, entity) ->
                entity.tick((ServerLevel) level1, pos, state));
    }

}