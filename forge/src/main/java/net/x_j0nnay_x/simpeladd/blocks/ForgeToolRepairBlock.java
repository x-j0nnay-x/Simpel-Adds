package net.x_j0nnay_x.simpeladd.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraftforge.network.NetworkHooks;
import net.x_j0nnay_x.simpeladd.blocks.entity.ForgeToolRepairBlockEntity;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesForge;
import org.jetbrains.annotations.Nullable;

public class ForgeToolRepairBlock extends Abst_ToolRepairBlock{

    public ForgeToolRepairBlock(Properties $$0) {
        super($$0);
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ForgeToolRepairBlockEntity(blockPos, blockState);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()){
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof ForgeToolRepairBlockEntity){
                NetworkHooks.openScreen(((ServerPlayer) pPlayer), (ForgeToolRepairBlockEntity)entity, pPos);
            }else {
                throw  new IllegalStateException("Repair Tool Container Provider is missing");
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
        return createTickerHelper(pBlockEntityType, ModBlockEntitiesForge.TOOL_REPAIR.get(),
                ((pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick((ServerLevel) pLevel1, pPos)));
    }
}
