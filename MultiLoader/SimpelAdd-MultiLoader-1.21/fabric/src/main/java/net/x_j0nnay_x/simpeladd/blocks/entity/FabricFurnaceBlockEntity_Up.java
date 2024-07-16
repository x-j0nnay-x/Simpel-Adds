package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesFabric;
import net.x_j0nnay_x.simpeladd.menu.FabricFurnaceMenu_up;


public class FabricFurnaceBlockEntity_Up extends Abst_FurnaceBlockEntity_Up implements ExtendedScreenHandlerFactory  {

    public FabricFurnaceBlockEntity_Up(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesFabric.UPGRADED_FURNACE, $$1, $$2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new FabricFurnaceMenu_up(i, inventory, this, this.data);
    }

    @Override
    public void upFurnaceTick(Level pLevel, BlockPos pPos, BlockState pState) {
        super.upFurnaceTick(pLevel, pPos, pState);
    }


    @Override
    public Object getScreenOpeningData(ServerPlayer player) {
        return this.data;
    }
}
