package net.x_j0nnay_x.simpeladdmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
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
import net.x_j0nnay_x.simpeladdmod.block.custom.BlockFactoryBlock;
import net.x_j0nnay_x.simpeladdmod.block.custom.NetheriteCrafterBlock;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.screen.NetheriteCrafter.NetheriteCrafterMenu;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;


public class NetheriteCrafterBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
     private final ItemStackHandler itemHandler = new ItemStackHandler(5);
    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(5, ItemStack.EMPTY);
    private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());
    public static int SCRAPSLOT = 0;
    public static int GOLDSLOT = 1;
    public static int BLAZESLOT = 2;
    public static int OUTPUTSLOT = 3;
    public static int UPGRADESLOT = 4;
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 90;
    private int blazeUse = 0;
    private int  maxBlazeuse = 4;

    public NetheriteCrafterBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.NETHERITE_CRAFTER.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> NetheriteCrafterBlockEntity.this.progress;
                    case 1 -> NetheriteCrafterBlockEntity.this.maxProgress;
                    case 2 -> NetheriteCrafterBlockEntity.this.blazeUse;
                    case 3 -> NetheriteCrafterBlockEntity.this.maxBlazeuse;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> NetheriteCrafterBlockEntity.this.progress = pValue;
                    case 1 -> NetheriteCrafterBlockEntity.this.maxProgress = pValue;
                    case 2 -> NetheriteCrafterBlockEntity.this.blazeUse = pValue;
                    case 3 -> NetheriteCrafterBlockEntity.this.maxBlazeuse = pValue;
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
        return Component.translatable("block.simpeladdmod.netherite_crafter_block");
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
        return new NetheriteCrafterMenu(pContainerId, pInventory, this, this.data);
    }


    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider p_335192_) {
        super.saveAdditional(compound, p_335192_);
        compound.put("inventory", itemHandler.serializeNBT());
        compound.putInt("nether_crafter_progress", progress);
        compound.putInt("netherite_crafter_blaze", blazeUse);
        if (!this.trySaveLootTable(compound)) {
            ContainerHelper.saveAllItems(compound, this.stacks, p_335192_);
        }
    }

    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider p_329555_) {
        super.loadAdditional(compound, p_329555_);
        itemHandler.deserializeNBT(compound.getCompound("inventory"));
        progress = compound.getInt("nether_crafter_progress");
        blazeUse = compound.getInt("netherite_crafter_blaze");
        if (!this.tryLoadLootTable(compound))
            this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compound, this.stacks, p_329555_);
    }
    @Override
    public int[] getSlotsForFace(Direction pSide) {
        return IntStream.range(0, this.getContainerSize()).toArray();
    }
    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        if (index == SCRAPSLOT && stack.is(Items.NETHERITE_SCRAP))
            return true;
        if (index == GOLDSLOT && stack.is(Items.GOLD_INGOT))
            return true;
        if (index == BLAZESLOT && stack.is(Items.BLAZE_ROD))
            return true;
        return false;
    }
    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return (direction == Direction.EAST  && (index == GOLDSLOT || index == SCRAPSLOT|| index == BLAZESLOT) ||
                direction == Direction.WEST && (index == GOLDSLOT || index == SCRAPSLOT|| index == BLAZESLOT) ||
                direction == Direction.SOUTH && (index == GOLDSLOT || index == SCRAPSLOT|| index == BLAZESLOT) ||
                direction == Direction.NORTH && (index == GOLDSLOT || index == SCRAPSLOT|| index == BLAZESLOT) ||
                direction == Direction.UP && (index == GOLDSLOT || index == SCRAPSLOT|| index == BLAZESLOT));
    }
    @Override
    public boolean canTakeItemThroughFace(int slotIndex, ItemStack itemStack, Direction direction) {
        // Only allow the down direction and only for the result slot.
        return (direction == Direction.DOWN && (slotIndex == OUTPUTSLOT));
    }
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider p_329179_) {
        return super.getUpdateTag(p_329179_);
    }

    @Override
    public int getContainerSize() {
        return stacks.size();
    }
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER)
            return handlers[facing.ordinal()].cast();
        return super.getCapability(capability, facing);
    }


    //process
    public void tick(Level pLevel, BlockPos pPos, BlockState pState){
        if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_1.get())) {
            this.maxProgress = 60;
        }if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_2.get())) {
            this.maxProgress = 36;
        }if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_3.get())) {
            this.maxProgress = 15;
        }if (stacks.get(UPGRADESLOT).isEmpty()){
            this.maxProgress = 90;
        }
        pState = pState.setValue(NetheriteCrafterBlock.WORKING, Boolean.valueOf(isWorking()));
        pLevel.setBlock(pPos, pState, 3);
            if(hasRecipe()) {
                if (hasSpace() && blazeUse > 0) {
                    increaseCraftingProgress();
                    if (hasProgressFinished()) {
                        useContent();
                        craftItem();
                        resetProgress();
                    }

                }else {
                    refillBlaze();
                }
        }else{
            resetProgress();
        }

    }
    private void  useContent(){
        this.removeItem(SCRAPSLOT, 1);
        this.removeItem(GOLDSLOT, 1);
        blazeUse --;
    }
    private void  refillBlaze(){
        if(this.stacks.get(BLAZESLOT).is(Items.BLAZE_ROD)) {
            this.removeItem(BLAZESLOT, 1);
            blazeUse = maxBlazeuse;
        }else{
            blazeUse = 0;
        }


    }
    private boolean isWorking(){
        if(hasRecipe() && blazeUse > 0){
            return true;
        }
        return false;
    }
    private boolean hasRecipe(){
        if(this.stacks.get(SCRAPSLOT).is(Items.NETHERITE_SCRAP) && this.stacks.get(GOLDSLOT).is(Items.GOLD_INGOT)){
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
    private void craftItem() {
        ItemStack result = new ItemStack(Items.NETHERITE_INGOT, 1);
        this.stacks.set(OUTPUTSLOT, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT).getCount() + result.getCount()));
    }
    private boolean hasSpace(){
        return this.stacks.get(OUTPUTSLOT).getCount() < 64;
    }


}
