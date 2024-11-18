package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesNeoForge;
import net.x_j0nnay_x.simpeladd.menu.NeoForgeFurnaceMenu_up;

public class NeoForgeFurnaceBlockEntity_Up extends Abst_FurnaceBlockEntity_Up  {

    public NeoForgeFurnaceBlockEntity_Up(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesNeoForge.UPGRADED_FURNACE.get(), $$1, $$2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new NeoForgeFurnaceMenu_up(i, inventory, this, this.data);
    }

    public static boolean isFuel(ItemStack itemStack) {
        return itemStack.getBurnTime(RecipeType.SMELTING) > 0;
    }

    protected int getFuelTime(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        } else {
            Item item = fuel.getItem();
            return (int)fuel.getBurnTime(RecipeType.SMELTING);
        }
    }

    @Override
    public void upFurnaceTick(Level pLevel, BlockPos pPos, BlockState pState) {
        super.upFurnaceTick(pLevel, pPos, pState);
    }
}
