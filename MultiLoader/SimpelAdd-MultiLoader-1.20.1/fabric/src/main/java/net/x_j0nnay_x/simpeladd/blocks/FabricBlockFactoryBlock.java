package net.x_j0nnay_x.simpeladd.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.x_j0nnay_x.simpeladd.blocks.entity.FabricBlockFactoryBlockEntity;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesFabric;
import org.jetbrains.annotations.Nullable;

public class FabricBlockFactoryBlock extends Abst_BlockFactoryBlock {
    public FabricBlockFactoryBlock(Properties $$0) {
        super($$0);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new FabricBlockFactoryBlockEntity(blockPos, blockState);
    }
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        if (!pLevel.isClientSide()){
            BlockEntity entity = pLevel.getBlockEntity(pPos);

            if(entity instanceof FabricBlockFactoryBlockEntity){
               pPlayer.openMenu((MenuProvider)entity);

            }else {
                throw  new IllegalStateException("Block Factory Container Provider is missing");
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        if(level.isClientSide()){
            return null;
        }
        return createTickerHelper(blockEntityType, ModBlockEntitiesFabric.BLOCK_FACTORY,
                ((pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.blockFactoryTick(pLevel1, pPos, pState1)));
    }
}
