package net.x_j0nnay_x.simpeladd.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;



import net.x_j0nnay_x.simpeladd.blocks.entity.ForgeTickAcceleratorBlockEntity;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesForge;
import org.jetbrains.annotations.Nullable;

public class ForgeTickAcceleratorBlock extends Abst_TickAcceleratorBlock{

    public static final MapCodec<ForgeTickAcceleratorBlock> CODEC = simpleCodec(ForgeTickAcceleratorBlock::new);

    public ForgeTickAcceleratorBlock(Properties $$0) {
        super($$0);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ForgeTickAcceleratorBlockEntity(blockPos, blockState);
    }
    @Override
    public InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        BlockEntity be = pLevel.getBlockEntity(pPos);
        if (!(be instanceof ForgeTickAcceleratorBlockEntity blockEntity))
            return InteractionResult.PASS;
        if (pLevel.isClientSide())
            return InteractionResult.SUCCESS;
        // open screen
        if(pPlayer instanceof ServerPlayer sPlayer) {
            sPlayer.openMenu(blockEntity, pPos);
        }else {
            throw  new IllegalStateException("Tick Accelerator Container Provider is missing");
        }
        return InteractionResult.CONSUME;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()){
            return null;
        }
        return createTickerHelper(pBlockEntityType, ModBlockEntitiesForge.TICK_ACCELERATOR.get(),
                ((pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick((ServerLevel) pLevel1, pPos)));
    }
}
