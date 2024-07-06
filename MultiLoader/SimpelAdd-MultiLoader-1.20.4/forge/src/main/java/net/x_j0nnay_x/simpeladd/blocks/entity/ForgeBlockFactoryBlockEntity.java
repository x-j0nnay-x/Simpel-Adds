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
    private final FluidTank fluidTankW = new FluidTank(6000) {
        @Override
        public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
            return stack.getFluid() == Fluids.WATER;
        }
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
            ForgeBlockFactoryBlockEntity.this.sendUpdate();
        }
    };
    private final FluidTank fluidTankL = new FluidTank(6000) {
        @Override
        public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
            return stack.getFluid() == Fluids.LAVA;
        }
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
            ForgeBlockFactoryBlockEntity.this.sendUpdate();
        }
    };
    private final LazyOptional<FluidTank> fluidOptionalW = LazyOptional.of(() -> this.fluidTankW);
    private final LazyOptional<FluidTank> fluidOptionalL = LazyOptional.of(() -> this.fluidTankL);
    public ForgeBlockFactoryBlockEntity(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesForge.BLOCK_FACTORY.get(), $$1, $$2);
    }
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER)
            return handlers[facing.ordinal()].cast();


        if(capability == ForgeCapabilities.FLUID_HANDLER) {
            if(facing == Direction.NORTH || facing == Direction.EAST){
            return  this.fluidOptionalL.cast();}
            if(facing == Direction.SOUTH || facing == Direction.WEST){
                return  this.fluidOptionalW.cast();}
        }

        return super.getCapability(capability, facing);
    }
    @Override
    public void load(CompoundTag $$0) {
        super.load($$0);
        this.fluidTankW.readFromNBT($$0.getCompound(SimpelAddMod.MODCUSTOM +"blockfactory_FluidTankW"));
        this.fluidTankL.readFromNBT($$0.getCompound(SimpelAddMod.MODCUSTOM +"blockfactory_FluidTankL"));
    }

    @Override
    protected void saveAdditional(CompoundTag $$0) {
        super.saveAdditional($$0);
        $$0.put(SimpelAddMod.MODCUSTOM +"blockfactory_FluidTankW", this.fluidTankW.writeToNBT(new CompoundTag()));
        $$0.put(SimpelAddMod.MODCUSTOM +"blockfactory_FluidTankL", this.fluidTankL.writeToNBT(new CompoundTag()));
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new ForgeBlockFactoryMenu(i, inventory, this, this.data);
    }

    @Override
    public void blockFactoryTick(Level pLevel, BlockPos pPos, BlockState pState) {
        super.blockFactoryTick(pLevel, pPos, pState);

    }

    @Override
    public void resteLavaUses() {
        if(this.fluidTankL.getFluid().getAmount() >= 1000){
            this.fluidTankL.drain(1000, IFluidHandler.FluidAction.EXECUTE );
            this.lavaUses = this.maxLavaUses;
        }else{
            this.lavaUses = 0;
        }
    }

    @Override
    public boolean hasLiquid() {
        if(this.fluidTankW.getFluid().getAmount() >= 1000 && this.fluidTankL.getFluid().getAmount() >= 1000 || this.lavaUses > 0){
            return true;
        }else {
            return false;
        }
    }

    public void syncWater(){
        this.waterLevel = this.fluidTankW.getFluidAmount();
    }
    public void syncLava(){
        this.lavaLevel = this.fluidTankL.getFluidAmount();
    }
    @Override
    public boolean canFillLava() {
        if (this.fluidTankW.getFluidAmount() < this.fluidTankW.getCapacity()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean canFillWater() {
        if (this.fluidTankL.getFluidAmount() < this.fluidTankL.getCapacity()){
            return true;
        }else{
        return false;}
    }

    @Override
    public void fillLava() {

        LazyOptional<IFluidHandlerItem> fluidHandler = this.stacks.get(LAVASLOT).getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM);
        fluidHandler.ifPresent(iFluidHandlerItem -> {
            int amountToDrain = this.fluidTankL.getCapacity() - this.fluidTankL.getFluidAmount();
            int amount = iFluidHandlerItem.drain(amountToDrain, IFluidHandler.FluidAction.SIMULATE).getAmount();
            if(amount > 0) {
                this.fluidTankL.fill(iFluidHandlerItem.drain(amountToDrain, IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);

                if(amount <= amountToDrain) {
                    this.stacks.set(LAVASLOT, iFluidHandlerItem.getContainer());
                }
            }
        });
    }

    @Override
    public void fillWater() {
        LazyOptional<IFluidHandlerItem> fluidHandler = this.stacks.get(WATERSLOT).getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM);
        fluidHandler.ifPresent(iFluidHandlerItem -> {
            int amountToDrain = this.fluidTankW.getCapacity() - this.fluidTankW.getFluidAmount();
            int amount = iFluidHandlerItem.drain(amountToDrain, IFluidHandler.FluidAction.SIMULATE).getAmount();
            if(amount > 0) {
                this.fluidTankW.fill(iFluidHandlerItem.drain(amountToDrain, IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);

                if(amount <= amountToDrain) {
                    this.stacks.set(WATERSLOT, iFluidHandlerItem.getContainer());

                }
            }
        });
    }
    public FluidStack getFluidWStack() {
        return this.fluidTankW.getFluid();
    }
    public FluidStack getFluidLStack() {
        return this.fluidTankL.getFluid();
    }
    public LazyOptional<FluidTank> getFluidOptionalW() {
        return this.fluidOptionalW;
    }
    public FluidTank getFluidTankW() {
        return this.fluidTankW;
    }
    public LazyOptional<FluidTank> getFluidOptionalL() {
        return this.fluidOptionalL;
    }
    public FluidTank getFluidTankL() {
        return this.fluidTankL;
    }
}
