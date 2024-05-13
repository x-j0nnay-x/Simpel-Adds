package net.x_j0nnay_x.simpeladdmod.screen;

import net.minecraft.world.item.ItemStack;

import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.CapabilityHooks;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class FluidContainerSlot extends SlotItemHandler {
    public FluidContainerSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return stack.getCapability(Capabilities.FluidHandler.ITEM).equals(true);
    }

    public boolean mayPlaceFluid(@NotNull ItemStack stack, @NotNull FluidStack fluidStack)
    {
        return stack.getCapability(Capabilities.FluidHandler.ITEM).equals(true);

    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}