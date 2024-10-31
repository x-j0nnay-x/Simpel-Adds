package net.x_j0nnay_x.simpeladd.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.x_j0nnay_x.simpeladd.blocks.entity.ForgeChillerBlockEntity;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesForge;
import org.jetbrains.annotations.Nullable;

public class ForgeChillerBlock extends Abst_ChillerBlock {
    public ForgeChillerBlock(Properties $$0) {
        super($$0);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ForgeChillerBlockEntity(blockPos, blockState);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        if (!pLevel.isClientSide()){
            BlockEntity entity = pLevel.getBlockEntity(pPos);

            if(entity instanceof ForgeChillerBlockEntity){

                if(pPlayer.getMainHandItem().is(Items.WATER_BUCKET)){
                    if (((ForgeChillerBlockEntity) entity).canFillWater()){
                        ((ForgeChillerBlockEntity) entity).fillWater();
                        pPlayer.getMainHandItem().getCraftingRemainingItem();
                    }
                }
                NetworkHooks.openScreen(((ServerPlayer) pPlayer), (ForgeChillerBlockEntity)entity, pPos);
            }else {
                throw  new IllegalStateException("Chiller Block Container Provider is missing");
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
        return createTickerHelper(pBlockEntityType, ModBlockEntitiesForge.CHILLER.get(),
                ((pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.chillerTick(pLevel1, pPos, pState1)));
    }
}
