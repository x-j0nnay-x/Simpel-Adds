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
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesForge;
import net.x_j0nnay_x.simpeladd.menu.ForgeChillerMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ForgeChillerBlockEntity extends Abst_ChillerBlockEntity {
    private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());
    private final FluidTank fluidTank = new FluidTank(10000) {
        @Override
        public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
            return stack.getFluid() == Fluids.WATER;
        }
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
            ForgeChillerBlockEntity.this.sendUpdate();
        }
    };

    private final LazyOptional<FluidTank> fluidOptional = LazyOptional.of(() -> this.fluidTank);

    public ForgeChillerBlockEntity(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesForge.CHILLER.get(), $$1, $$2);
    }
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER)
            return handlers[facing.ordinal()].cast();


        if(capability == ForgeCapabilities.FLUID_HANDLER) {
            if(facing == Direction.NORTH || facing == Direction.EAST || facing == Direction.WEST || facing == Direction.SOUTH){
                return  this.fluidOptional.cast();}
        }

        return super.getCapability(capability, facing);
    }
    @Override
    public void load(CompoundTag $$0) {
        super.load($$0);
        this.fluidTank.readFromNBT($$0.getCompound(SimpelAddMod.MODCUSTOM +"chiller_FluidTankW"));
    }

    @Override
    protected void saveAdditional(CompoundTag $$0) {
        super.saveAdditional($$0);
        $$0.put(SimpelAddMod.MODCUSTOM +"chiller_FluidTankW", this.fluidTank.writeToNBT(new CompoundTag()));
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new ForgeChillerMenu(i, inventory, this, this.data);
    }

    @Override
    public void chillerTick(Level pLevel, BlockPos pPos, BlockState pState) {
        super.chillerTick(pLevel, pPos, pState);

    }

    @Override
    public void fillWater() {
        if(canFillWater()){
            LazyOptional<IFluidHandlerItem> fluidHandler = this.stacks.get(WATERSLOT).getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM);
            fluidHandler.ifPresent(iFluidHandlerItem -> {
                int amountToDrain = this.fluidTank.getCapacity() - this.fluidTank.getFluidAmount();
                int amount = iFluidHandlerItem.drain(amountToDrain, IFluidHandler.FluidAction.SIMULATE).getAmount();
                if(amount > 0) {
                    this.fluidTank.fill(iFluidHandlerItem.drain(amountToDrain, IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);

                    if(amount <= amountToDrain) {
                        this.stacks.set(WATERSLOT, iFluidHandlerItem.getContainer());

                    }
                }
            });}
    }

    @Override
    public boolean canFillWater() {
        if (this.fluidTank.getFluidAmount() < this.fluidTank.getCapacity()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void setWaterUese() {
        if(this.fluidTank.getFluid().getAmount() >= 1000){
            this.fluidTank.drain(1000, IFluidHandler.FluidAction.EXECUTE);
            this.waterUese = 10;
        }
    }

    @Override
    public boolean hasWater() {
        return this.fluidTank.getFluidAmount() > 0 || this.waterUese > 0;
    }
    public FluidTank getFluidTank() {
        return this.fluidTank;
    }
    public FluidStack getFluidStack() {
        return this.fluidTank.getFluid();
    }
    public LazyOptional<FluidTank> getFluidOptional() {
        return this.fluidOptional;
    }

}
