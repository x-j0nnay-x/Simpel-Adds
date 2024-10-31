package net.x_j0nnay_x.simpeladd.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
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

import net.neoforged.neoforge.network.NetworkHooks;
import net.x_j0nnay_x.simpeladd.blocks.entity.NeoForgeBlockFactoryBlockEntity;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesNeoForge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NeoForgeBlockFactoryBlock extends Abst_BlockFactoryBlock {
    public NeoForgeBlockFactoryBlock(Properties $$0) {
        super($$0);
    }



    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new NeoForgeBlockFactoryBlockEntity(blockPos, blockState);
    }
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
        if (!pLevel.isClientSide()){
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof NeoForgeBlockFactoryBlockEntity){
                NetworkHooks.openScreen(((ServerPlayer) pPlayer), (NeoForgeBlockFactoryBlockEntity)entity, pPos);
            }else {
                throw  new IllegalStateException("Block Factory Container Provider is missing");
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());

    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()){
            return null;
        }
        return createTickerHelper(pBlockEntityType, ModBlockEntitiesNeoForge.BLOCK_FACTORY.get(),
                ((pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.blockFactoryTick(pLevel1, pPos, pState1)));
    }


}
