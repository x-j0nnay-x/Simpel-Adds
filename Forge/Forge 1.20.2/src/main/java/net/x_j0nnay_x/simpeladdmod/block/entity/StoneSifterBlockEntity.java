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
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.x_j0nnay_x.simpeladdmod.block.ModBlockEntities;

import net.x_j0nnay_x.simpeladdmod.block.custom.StoneSifterBlock;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.screen.StoneSifter.StoneSifterMenu;

import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

public class StoneSifterBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    private final ItemStackHandler itemHandler = new ItemStackHandler(8);
    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(8, ItemStack.EMPTY);
    private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());
    public static int GRINDERSLOT = 0;
    public static int INPUTSLOT = 1;
    public static int COPPERSLOT = 2;
    public static int IRONSLOT = 3;
    public static int GOLDSLOT = 4;
    public static int REDSTONESLOT = 5;
    public static int QUARTZSLOT = 6;
    public static int DIMOANDSLOT = 7;
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 60;

    public StoneSifterBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.STONE_SIFTER.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> StoneSifterBlockEntity.this.progress;
                    case 1 -> StoneSifterBlockEntity.this.maxProgress;

                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> StoneSifterBlockEntity.this.progress = pValue;
                    case 1 -> StoneSifterBlockEntity.this.maxProgress = pValue;

                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.simpeladdmod.stone_sifter_block");
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return  new StoneSifterMenu(pContainerId,pInventory, this, this.data);
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
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return (direction == Direction.EAST  && (index == INPUTSLOT) ||
                direction == Direction.WEST && (index == INPUTSLOT) ||
                direction == Direction.SOUTH && (index == INPUTSLOT) ||
                direction == Direction.NORTH && (index == INPUTSLOT) ||
                direction == Direction.UP && (index == GRINDERSLOT));
    }
    @Override
    public boolean canTakeItemThroughFace(int slotIndex, ItemStack itemStack, Direction direction) {
        // Only allow the down direction and only for the result slot.
        return (direction == Direction.DOWN && (slotIndex == COPPERSLOT || slotIndex == IRONSLOT || slotIndex == GOLDSLOT
        || slotIndex == REDSTONESLOT || slotIndex == QUARTZSLOT || slotIndex == DIMOANDSLOT));
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
        progress = compound.getInt("sifter_progress");
        if (!this.tryLoadLootTable(compound))
            this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compound, this.stacks);
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("inventory", itemHandler.serializeNBT());
        compound.putInt("sifter_progress", progress);
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
        if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER)
            return handlers[facing.ordinal()].cast();
        return super.getCapability(capability, facing);
    }


// processing

    public void tick(Level pLevel, BlockPos pPos, BlockState pState){
        pState = pState.setValue(StoneSifterBlock.WORKING, Boolean.valueOf(isWorking()));
        pLevel.setBlock(pPos, pState, 3);
        if(hasRecipe()){
                increaseCraftingProgress();
                setChanged(pLevel, pPos, pState);
                if(hasProgressFinished()){
                    usestone();
                    useGrind();
                    craftItem();
                    resetProgress();
                }

        }else {

            resetProgress();
        }
    }

    private void useGrind() {
        if (stacks.get(GRINDERSLOT).is(ModItems.GRINDERHEAD.get())) {
            if (stacks.get(GRINDERSLOT).getDamageValue() >= stacks.get(GRINDERSLOT).getMaxDamage()) {
                stacks.set(GRINDERSLOT, ItemStack.EMPTY);
            } else {
                stacks.get(GRINDERSLOT).setDamageValue(stacks.get(GRINDERSLOT).getDamageValue() + 1);

            }

        }
    }
    private void usestone() {
        stacks.get(INPUTSLOT).setCount(stacks.get(INPUTSLOT).getCount() - 1);
    }

    private boolean canWork(){
        if( !isCopperFull() || !isIronFull() || !isGoldFull() || !isRedstoneFull() || !isQaruzFull() || !isDiamondFull()){
            return true;
        }
        return false;
    }
    private boolean isWorking() {
        return canWork() && hasRecipe();
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
        var d = Math.random();
        if(d < 0.3) {
            var c = Math.random();
            if (c < 0.4 && !isCopperFull()) {
                this.stacks.set(COPPERSLOT, new ItemStack(ModItems.COPPERDUST.get(), this.stacks.get(COPPERSLOT).getCount() + 1));
            }
            else if (c < 0.5 && !isIronFull()) {
                this.stacks.set(IRONSLOT, new ItemStack(ModItems.IRONDUST.get(), this.stacks.get(IRONSLOT).getCount() +1));
            }
            else if (c < 0.6 && !isGoldFull()) {
                this.stacks.set(GOLDSLOT, new ItemStack(ModItems.GOLDDUST.get(), this.stacks.get(GOLDSLOT).getCount() +1));
            }
            else if (c < 0.7 && !isRedstoneFull()) {
                this.stacks.set(REDSTONESLOT, new ItemStack(Items.REDSTONE, this.stacks.get(REDSTONESLOT).getCount() +1));
            }
            else if (c < 0.8 && !isQaruzFull()) {
                this.stacks.set(QUARTZSLOT, new ItemStack(Items.QUARTZ, this.stacks.get(QUARTZSLOT).getCount() +1));
            }
            else if (c < 0.9 && !isDiamondFull()) {
                this.stacks.set(DIMOANDSLOT, new ItemStack(Items.DIAMOND, this.stacks.get(DIMOANDSLOT).getCount() +1));
            }

        }

    }
    private boolean hasRecipe() {
       if (stacks.get(INPUTSLOT).is(Items.COBBLESTONE) && stacks.get(GRINDERSLOT).is(ModItems.GRINDERHEAD.get())){
           return true;
       }
       return false;
}

    private boolean isCopperFull(){
        if (stacks.get(COPPERSLOT).getCount() < 64){
            return false;
        }
        return true;
    }
    private boolean isIronFull(){
        if (stacks.get(IRONSLOT).getCount() < 64){
            return false;
        }
        return true;
    }
    private boolean isGoldFull(){
        if (stacks.get(GOLDSLOT).getCount() < 64){
            return false;
        }
        return true;
    }
    private boolean isRedstoneFull(){
        if (stacks.get(REDSTONESLOT).getCount() < 64){
            return false;
        }
        return true;
    }
    private boolean isQaruzFull(){
        if (stacks.get(QUARTZSLOT).getCount() < 64){
            return false;
        }
        return true;
    }
    private boolean isDiamondFull(){
        if (stacks.get(DIMOANDSLOT).getCount() < 64){
            return false;
        }
        return true;
    }
}