package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesForge;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.menu.ForgeBlockFactoryMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ForgeBlockFactoryBlockEntity extends Abst_BlockFactoryBlockEntity {
    private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());

    public ForgeBlockFactoryBlockEntity(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesForge.BLOCK_FACTORY.get(), $$1, $$2);
    }
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER)
            return handlers[facing.ordinal()].cast();


        return super.getCapability(capability, facing);
    }


    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new ForgeBlockFactoryMenu(i, inventory, this, this.data);
    }

    @Override
    public void blockFactoryTick(Level pLevel, BlockPos pPos, BlockState pState) {
        super.blockFactoryTick(pLevel, pPos, pState);

    }


}
