package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesFabric;
import net.x_j0nnay_x.simpeladd.menu.FabricCropGrowthMenu;
import net.x_j0nnay_x.simpeladd.util.data.CropGrowthData;

public class FabricCropGrowthBlockEntity extends Abst_CropGrowthBlockEntity implements ExtendedScreenHandlerFactory<CropGrowthData> {

    public FabricCropGrowthBlockEntity(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesFabric.CROP_GROWTH, $$1, $$2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new FabricCropGrowthMenu(i, inventory, this, this.data);
    }

    @Override
    public void cropGrowthTick(ServerLevel pLevel, BlockPos pPos, BlockState pState) {
        super.cropGrowthTick(pLevel, pPos, pState);
    }

    @Override
    public CropGrowthData getScreenOpeningData(ServerPlayer player) {
        return new CropGrowthData(true);
    }
}
