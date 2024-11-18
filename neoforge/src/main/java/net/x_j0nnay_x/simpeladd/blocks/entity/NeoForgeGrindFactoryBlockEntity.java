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
import net.x_j0nnay_x.simpeladd.menu.NeoForgeGrindFactoryMenu;

public class NeoForgeGrindFactoryBlockEntity extends Abst_GrindFactoryBlockEntity  {

    public NeoForgeGrindFactoryBlockEntity(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesNeoForge.GRIND_FACTORY.get(), $$1, $$2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new NeoForgeGrindFactoryMenu(i, inventory, this, this.data);
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
    public void grindFactoryTick(Level pLevel, BlockPos pPos, BlockState pState) {
        super.grindFactoryTick(pLevel, pPos, pState);
    }
}
