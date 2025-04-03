package net.x_j0nnay_x.simpeladd.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.x_j0nnay_x.simpeladd.blocks.entity.FabricCropGrowthBlockEntity;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesFabric;
import org.jetbrains.annotations.Nullable;

public class FabricCropGrowthBlock extends Abst_CropGrowthBlock {
    public static final MapCodec<FabricCropGrowthBlock> CODEC = simpleCodec(FabricCropGrowthBlock::new);

    public FabricCropGrowthBlock(Properties $$0) {
        super($$0);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        if (!pLevel.isClientSide()){
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof FabricCropGrowthBlockEntity){
                pPlayer.openMenu((MenuProvider)entity);
            }else {
                throw  new IllegalStateException("Crop Growth Block Container Provider is missing");
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new FabricCropGrowthBlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()){
            return null;
        }
        return createTickerHelper(pBlockEntityType, ModBlockEntitiesFabric.CROP_GROWTH,
                ((pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.cropGrowthTick((ServerLevel) pLevel1, pPos, pState1)));
    }
}
