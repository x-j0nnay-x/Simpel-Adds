package net.x_j0nnay_x.simpeladdmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

import net.neoforged.neoforge.common.capabilities.Capabilities;
import net.neoforged.neoforge.common.capabilities.Capability;
import net.neoforged.neoforge.common.util.LazyOptional;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.fluids.capability.templates.FluidHandlerItemStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.x_j0nnay_x.simpeladdmod.block.ModBlockEntities;
import net.x_j0nnay_x.simpeladdmod.block.custom.BlockFactoryBlock;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.screen.BlockFactory.BlockFactoryMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.stream.IntStream;


public class BlockFactoryBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {

    private final ItemStackHandler itemHandler = new ItemStackHandler(7);
    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(7, ItemStack.EMPTY);
    private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());

    private final FluidTank fluidTankW = new FluidTank(6000) {
        @Override
        public boolean isFluidValid(int tank, @NotNull FluidStack stack) {

            return stack.getFluid() == Fluids.WATER;

        }

        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
            BlockFactoryBlockEntity.this.sendUpdate();
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
            BlockFactoryBlockEntity.this.sendUpdate();
        }
    };

    private final LazyOptional<FluidTank> fluidOptionalW = LazyOptional.of(() -> this.fluidTankW);
    private final LazyOptional<FluidTank> fluidOptionalL = LazyOptional.of(() -> this.fluidTankL);

    public static int WATERSLOT = 5;
    public static int LAVASLOT = 6;

    public static int GRINDERSLOT = 0;
    public static int COBBLESLOT = 1;
    public static int GRAVALSLOT = 2;
    public static int SANDSLOT = 3;
    public static int OBSIDIANSLOT = 4;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 35;
    private int lavaUses = 0 ;
    private int maxLavaUses = 4;
    private int grindsleft = 0;
    private int maxGrinds = 3;
    public BlockFactoryBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.BLOCK_FACTORY.get(), pPos, pBlockState);

        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> BlockFactoryBlockEntity.this.progress;
                    case 1 -> BlockFactoryBlockEntity.this.maxProgress;
                    case 2 -> BlockFactoryBlockEntity.this.grindsleft;
                    case 3 -> BlockFactoryBlockEntity.this.lavaUses;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> BlockFactoryBlockEntity.this.progress = pValue;
                    case 1 -> BlockFactoryBlockEntity.this.maxProgress = pValue;
                    case 2 -> BlockFactoryBlockEntity.this.grindsleft = pValue;
                    case 3 -> BlockFactoryBlockEntity.this.lavaUses = pValue;

            }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.simpeladdmod.blockfactory_block");
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return  new BlockFactoryMenu(pContainerId,pInventory, this, this.data);
    }


    @Override
    public int[] getSlotsForFace(Direction pSide) {
        return IntStream.range(0, this.getContainerSize()).toArray();
    }
    @Override
    public int getMaxStackSize() {
        return 64;
    }
    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        if (index == COBBLESLOT)
            return false;
        if (index == GRAVALSLOT)
            return false;
        if (index == SANDSLOT)
            return false;
        if (index == OBSIDIANSLOT)
            return false;
        if (index == GRINDERSLOT && stack.is(ModItems.GRINDERHEAD.get()))
            return true;
        if (index == LAVASLOT && stack.is(Items.LAVA_BUCKET))
            return true;
        if (index == WATERSLOT && stack.is(Items.WATER_BUCKET))
            return true;
        return false;
    }
    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return ((direction == Direction.EAST || direction == Direction.WEST || direction == Direction.SOUTH || direction == Direction.NORTH) &&
                (index == LAVASLOT) && stack.is(Items.LAVA_BUCKET) ||
                (index == WATERSLOT) && stack.is(Items.WATER_BUCKET) ||
                direction == Direction.UP && (index == GRINDERSLOT));
    }
    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return (direction == Direction.DOWN && (
                        index == WATERSLOT && stack.is(Items.BUCKET)||
                        index == LAVASLOT && stack.is(Items.BUCKET)));
    }

    @Override
    public int getContainerSize() {
        return stacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.stacks)
            if (!itemstack.isEmpty())
                return false;
        return true;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.stacks;
    }

    @Override
    protected void setItems( NonNullList<ItemStack> stacks) {
        this.stacks = stacks;
    }
    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        itemHandler.deserializeNBT(compound.getCompound("inventory"));
        progress = compound.getInt("blockfactroy_progress");
        grindsleft = compound.getInt("blockfactroy_grinds_left");
        this.fluidTankW.readFromNBT(compound.getCompound("FluidTankW"));
        this.fluidTankL.readFromNBT(compound.getCompound("FluidTankL"));
        if (!this.tryLoadLootTable(compound))
            this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compound, this.stacks);
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("inventory", itemHandler.serializeNBT());
        compound.putInt("blockfactroy_progress", progress);
        compound.putInt("blockfactroy_grinds_left", grindsleft);
        compound.put("FluidTankW", this.fluidTankW.writeToNBT(new CompoundTag()));
        compound.put("FluidTankL", this.fluidTankL.writeToNBT(new CompoundTag()));
        if (!this.trySaveLootTable(compound)) {
            ContainerHelper.saveAllItems(compound, this.stacks);
        }
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == Capabilities.ITEM_HANDLER)
            return handlers[facing.ordinal()].cast();
        if(capability == Capabilities.FLUID_HANDLER) {
            return  this.fluidOptionalL.cast();
        }
        return super.getCapability(capability, facing);
    }


    //processing
    public void tick(Level pLevel, BlockPos pPos, BlockState pState){
        if (canFillWater()){
            fillWater();
        }
        if (ccanFillLava()){
            fillLava();
        }
        pState = pState.setValue(BlockFactoryBlock.WORKING, Boolean.valueOf(isWorking()));
        pLevel.setBlock(pPos, pState, 3);
        if(hasLiquid()) {
            if (!isFull()){
                increaseCraftingProgress();
                setChanged(pLevel, pPos, pState);
                if (CobbleSpace()) {
                    if (hasProgressFinished()) {
                        makeCobble();
                        resetProgress();
                    }
                }
                if (grindsleft > 0) {
                    if (GravalSpace() && !CobbleSpace()) {
                            if (hasProgressFinished()) {
                                useGrind();
                                makeGraval();
                                resetProgress();
                            }
                    }
                    if (SandSpace() && !GravalSpace() && !CobbleSpace()) {
                        if (hasProgressFinished()) {
                            useGrind();
                            makeSand();
                            resetProgress();
                        }
                    }
                }else{
                    resetGrinds();
                }
                if (ObslidanSpace() && !SandSpace() && !GravalSpace() && !CobbleSpace()){
                    if(lavaUses > 0){
                        if (hasProgressFinished()) {
                            makeObsidain();
                            useLava();
                            resetProgress();
                        }
                    }else{
                        resteLavaUses();
                    }
                }
                if(!ObslidanSpace() && !SandSpace() && !GravalSpace() && !CobbleSpace()){
                    resetProgress();
                }
                if (grindsleft == 0 && !isFull() && !CobbleSpace()){
                    resetProgress();
                }
            }
        }
    }


    private void useGrind(){
        grindsleft --;
    }
    private void useLava(){
        lavaUses --;
    }
    private void resteLavaUses(){
        if(this.fluidTankL.getFluid().getAmount() >= 1000){
            this.fluidTankL.drain(1000, FluidHandlerItemStack.FluidAction.EXECUTE );
            lavaUses = maxLavaUses;
        }else{
            lavaUses = 0;
        }
    }
    private void resetGrinds() {
        if(stacks.get(GRINDERSLOT).is(ModItems.GRINDERHEAD.get())){
            if(stacks.get(GRINDERSLOT).getDamageValue() >= stacks.get(GRINDERSLOT).getMaxDamage()){
                stacks.set(GRINDERSLOT, ItemStack.EMPTY);
            }else{
                stacks.get(GRINDERSLOT).setDamageValue(stacks.get(GRINDERSLOT).getDamageValue() + 1);
                grindsleft = maxGrinds;
            }
        }else {
            grindsleft = 0;
        }
    }
   
    private boolean isFull(){
        if (!CobbleSpace() && !GravalSpace() && !SandSpace() && !ObslidanSpace()){
            return true;
        }
        return false;
    }
    private boolean isWorking() {
        if(hasLiquid() && !isFull()){

            return true;
        }
        return false;
    }

    private void resetProgress() {
        progress = 0;
    }
    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }
    private void makeCobble() {
        ItemStack result = new ItemStack(Items.COBBLESTONE, 1);
        this.stacks.set(COBBLESLOT, new ItemStack(result.getItem(),
                this.stacks.get(COBBLESLOT).getCount() + result.getCount()));
    }
    private void makeGraval() {
        ItemStack result = new ItemStack(Items.GRAVEL, 1);
        this.stacks.set(GRAVALSLOT, new ItemStack(result.getItem(),
                this.stacks.get(GRAVALSLOT).getCount() + result.getCount()));
    }
    private void makeSand() {
        ItemStack result = new ItemStack(Items.SAND, 1);
        this.stacks.set(SANDSLOT, new ItemStack(result.getItem(),
                this.stacks.get(SANDSLOT).getCount() + result.getCount()));
    }
    private void makeObsidain() {
        ItemStack result = new ItemStack(Items.OBSIDIAN, 1);
        this.stacks.set(OBSIDIANSLOT, new ItemStack(result.getItem(),
                this.stacks.get(OBSIDIANSLOT).getCount() + result.getCount()));
    }
    private boolean hasLiquid() {
        if(this.fluidTankW.getFluid().getAmount() >= 1000 && this.fluidTankL.getFluid().getAmount() >= 1000 || lavaUses > 0){
            return true;
        }else {
            return false;
        }
    }
    private boolean CobbleSpace(){
        return this.stacks.get(COBBLESLOT).isEmpty() || this.stacks.get(COBBLESLOT).getCount() < 64;
    }
    private boolean GravalSpace(){
        return this.stacks.get(GRAVALSLOT).isEmpty() || this.stacks.get(GRAVALSLOT).getCount() < 64;
    }
    private boolean SandSpace(){
        return this.stacks.get(SANDSLOT).isEmpty() || this.stacks.get(SANDSLOT).getCount() < 64;
    }
    private boolean ObslidanSpace(){
        return this.stacks.get(OBSIDIANSLOT).isEmpty() || this.stacks.get(OBSIDIANSLOT).getCount() < 64;
    }
    private boolean canFillWater() {
        if (this.fluidTankW.getFluidAmount() < this.fluidTankW.getCapacity()){
            return true;
        }else{
            return false;
        }
    }
    private boolean ccanFillLava() {
        if (this.fluidTankL.getFluidAmount() < this.fluidTankL.getCapacity()){
            return true;
        }else{
            return false;
        }
    }
    private void  fillWater(){
        LazyOptional<IFluidHandlerItem> fluidHandler = this.stacks.get(WATERSLOT).getCapability(Capabilities.FLUID_HANDLER_ITEM);
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
    private void  fillLava(){
        LazyOptional<IFluidHandlerItem> fluidHandler = this.stacks.get(LAVASLOT).getCapability(Capabilities.FLUID_HANDLER_ITEM);
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
    private void sendUpdate() {
        setChanged();

        if (this.level != null)
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }



}
