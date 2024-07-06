package net.x_j0nnay_x.simpeladd.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.x_j0nnay_x.simpeladd.blocks.entity.ForgeNetheriteCrafterBlockEntity;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesForge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ForgeNetheriteCraftingBlock extends Abst_NetheriteCrafterBlock {
    public ForgeNetheriteCraftingBlock(Properties $$0) {
        super($$0);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ForgeNetheriteCrafterBlockEntity(blockPos, blockState);
    }
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
        BlockEntity be = pLevel.getBlockEntity(pPos);

        if (!(be instanceof ForgeNetheriteCrafterBlockEntity blockEntity))
            return InteractionResult.PASS;

        if (pLevel.isClientSide())
            return InteractionResult.SUCCESS;

        // open screen
        if(pPlayer instanceof ServerPlayer sPlayer) {
            sPlayer.openMenu(blockEntity, pPos);
        }else {
            throw  new IllegalStateException("Netherite Crafter Container Provider is missing");
        }

        return InteractionResult.CONSUME;

    }



    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()){
            return null;
        }
        return createTickerHelper(pBlockEntityType, ModBlockEntitiesForge.NETHERITE_CRAFTER.get(),
                ((pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.netheriteCrafterTick(pLevel1, pPos, pState1)));
    }
}
