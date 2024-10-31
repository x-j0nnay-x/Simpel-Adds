package net.x_j0nnay_x.simpeladdmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
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
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.x_j0nnay_x.simpeladdmod.block.ModBlockEntities;
import net.x_j0nnay_x.simpeladdmod.screen.Chiller.ChillerMenu;
import net.x_j0nnay_x.simpeladdmod.until.ModTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.stream.IntStream;


public class ChillerBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
     private final ItemStackHandler itemHandler = new ItemStackHandler(3);
    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
    private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());
    private final FluidTank fluidTank = new FluidTank(10000) {
        @Override
        public boolean isFluidValid(int tank, @NotNull FluidStack stack) {

            return stack.getFluid() == Fluids.WATER;

        }

        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
            ChillerBlockEntity.this.sendUpdate();
        }
    };
    private final LazyOptional<FluidTank> fluidOptional = LazyOptional.of(() -> this.fluidTank);
    public static int CHILLINGSLOT = 0;
    public static int WATERSLOT = 1;
    public static int OUTPUTSLOT = 2;
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 60;
    private int snowLevel = 0;

    private int waterUese = 0;
    public ChillerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.CHILLER.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> ChillerBlockEntity.this.progress;
                    case 1 -> ChillerBlockEntity.this.maxProgress;
                    case 2 -> ChillerBlockEntity.this.snowLevel;
                    case 3 -> ChillerBlockEntity.this.waterUese;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> ChillerBlockEntity.this.progress = pValue;
                    case 1 -> ChillerBlockEntity.this.maxProgress = pValue;
                    case 2 -> ChillerBlockEntity.this.snowLevel = pValue;
                    case 3 -> ChillerBlockEntity.this.waterUese = pValue;

                }
            }

            @Override
            public int getCount() {
                return 5;
            }
        };
    }
    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.simpeladdmod.chiller_block");
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
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return new ChillerMenu(pContainerId, pInventory, this, this.data);
    }


    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("chiller_progress", progress);
        pTag.putInt("chiller_snow", snowLevel);
        pTag.put("FluidTank", this.fluidTank.writeToNBT(new CompoundTag()));
        if (!this.trySaveLootTable(pTag)) {
            ContainerHelper.saveAllItems(pTag, this.stacks);
        }
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("chiller_progress");
        snowLevel = pTag.getInt("chiller_snow");
        this.fluidTank.readFromNBT(pTag.getCompound("FluidTank"));
        if (!this.tryLoadLootTable(pTag))
            this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(pTag, this.stacks);
    }
    @Override
    public int[] getSlotsForFace(Direction pSide) {
        return IntStream.range(0, this.getContainerSize()).toArray();
    }
    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        if (index == WATERSLOT && stack.is(Items.WATER_BUCKET))
            return true;
        if (index == CHILLINGSLOT && stack.is(ModTags.Items.CHILLING))
            return true;
        return false;
    }
    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return (
                (direction == Direction.EAST || direction == Direction.WEST || direction == Direction.SOUTH || direction == Direction.NORTH) &&
                (index == CHILLINGSLOT) ||
                direction == Direction.UP && (index == WATERSLOT)
        );
    }
    @Override
    public boolean canTakeItemThroughFace(int slotIndex, ItemStack itemStack, Direction direction) {
        // Only allow the down direction and only for the result slot.
        return (direction == Direction.DOWN && (slotIndex == OUTPUTSLOT ||
                slotIndex == WATERSLOT && itemStack.is(Items.BUCKET)) );
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
    public int getContainerSize() {
        return stacks.size();
    }
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER)
            return handlers[facing.ordinal()].cast();

        if(capability == ForgeCapabilities.FLUID_HANDLER)
            return this.fluidOptional.cast();
        return super.getCapability(capability, facing);
    }


    //process
    public void tick(Level pLevel, BlockPos pPos, BlockState pState){
        if(canFillWater()){
            fillWater();
        }
        if(canFillSnow()){
            fillSnow();
        }
        if(waterUese == 0){
            setWaterUese();
        }
        if(hasSnow()){
            if(hasWater()) {
                if (hasSpace()) {
                    increaseCraftingProgress();
                    if (hasProgressFinished()) {
                        useContent();
                        craftItem();
                        resetProgress();
                    }
                }
            }
        }else{
            resetProgress();
        }

    }
    private void  useContent(){
        this.waterUese --;
        this.snowLevel --;
    }
    public void  fillSnow(){
        if (canFillSnow()){
            if (snowLevel < 20) {
                if (this.stacks.get(CHILLINGSLOT).is(Items.SNOWBALL)) {
                    this.removeItem(CHILLINGSLOT, 1);
                    snowLevel += 1;
                }
            }
            if(snowLevel <= 16){
                if(this.stacks.get(CHILLINGSLOT).is(Items.SNOW_BLOCK)){
                    this.removeItem(CHILLINGSLOT, 1);
                    snowLevel += 4;
                }
            }
            if (snowLevel <= 12){
                if(this.stacks.get(CHILLINGSLOT).is(Items.ICE)){
                    this.removeItem(CHILLINGSLOT, 1);
                    snowLevel += 8;
                }
            }
            if (snowLevel <= 8){
                if(this.stacks.get(CHILLINGSLOT).is(Items.PACKED_ICE)){
                    this.removeItem(CHILLINGSLOT, 1);
                    snowLevel += 12;
                }
            }
            if (snowLevel <= 1){
                if(this.stacks.get(CHILLINGSLOT).is(Items.BLUE_ICE)){
                    this.removeItem(CHILLINGSLOT, 1);
                    snowLevel += 19;
                }
            }
        }
    }
    private void  fillWater(){
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
    private boolean canFillWater() {
        if (this.fluidTank.getFluidAmount() < this.fluidTank.getCapacity()){
            return true;
        }else{
            return false;
        }
    }
    private boolean canFillSnow() {
        return this.snowLevel < 20;
    }
    private void setWaterUese(){
        if(this.fluidTank.getFluid().getAmount() >= 1000){
            this.fluidTank.drain(1000, IFluidHandler.FluidAction.EXECUTE);
            this.waterUese = 10;
        }

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
    private void craftItem() {
        ItemStack result = new ItemStack(Items.ICE, 1);
        this.stacks.set(OUTPUTSLOT, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT).getCount() + result.getCount()));
    }
    private boolean hasSpace(){
        return this.stacks.get(OUTPUTSLOT).getCount() < 64;
    }
    private boolean hasSnow() {
        return this.snowLevel >0;
    }
    private boolean hasWater() {
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

    private void sendUpdate() {
        setChanged();

        if (this.level != null)
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }

}
