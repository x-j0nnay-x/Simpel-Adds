package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesNeoForge;
import net.x_j0nnay_x.simpeladd.menu.NeoForgeCropGrowthMenu;

public class NeoForgeCropGrowthBlockEntity extends Abst_CropGrowthBlockEntity  {

    public NeoForgeCropGrowthBlockEntity(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesNeoForge.CROP_GROWTH.get(), $$1, $$2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new NeoForgeCropGrowthMenu(i, inventory, this, this.data);
    }

    @Override
    public void cropGrowthTick(ServerLevel pLevel, BlockPos pPos, BlockState pState) {
        super.cropGrowthTick(pLevel, pPos, pState);
    }
}
