package net.x_j0nnay_x.simpeladdmod.screen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderOwner;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class FluidContainerSlot extends SlotItemHandler {
    public FluidContainerSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent();
    }

    public boolean mayPlaceFluid(@NotNull ItemStack stack, @NotNull FluidStack fluidStack)
    {
        return stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent();
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}