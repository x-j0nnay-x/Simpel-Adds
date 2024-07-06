package net.x_j0nnay_x.simpeladdmod.block.entity;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;


import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.capabilities.Capabilities;
import net.neoforged.neoforge.common.capabilities.Capability;
import net.neoforged.neoforge.common.util.LazyOptional;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.x_j0nnay_x.simpeladdmod.block.ModBlockEntities;
import net.x_j0nnay_x.simpeladdmod.block.custom.Upgrade_Furnace;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;


import net.x_j0nnay_x.simpeladdmod.screen.Furnace_Up.FurnaceMenu_up;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.stream.IntStream;

public class Upgrade_Furnace_BlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    private final ItemStackHandler itemHandler = new ItemStackHandler(10);
    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(10, ItemStack.EMPTY);
    private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());

    public static int FUELSLOT = 0;
    public static int INPUTSLOT1 = 1;
    public static int INPUTSLOT2 = 2;
    public static int INPUTSLOT3 = 3;
    public static int INPUTSLOT4 = 4;
    public static int OUTPUTSLOT1 = 5;
    public static int OUTPUTSLOT2 = 6;
    public static int OUTPUTSLOT3 = 7;
    public static int OUTPUTSLOT4 = 8;
    public  static int UPGRADESLOT = 9;

    protected final ContainerData data;
    private int progress1 = 0;
    private int progress2 = 0;
    private int progress3 = 0;
    private int progress4 = 0;
    private int maxProgress;
    private int fuelLevel = 0 ;
    private int fueluse = 0;

    public Upgrade_Furnace_BlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.UPGRADED_FURNACE.get(), pPos, pBlockState);

        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> Upgrade_Furnace_BlockEntity.this.progress1;
                    case 1 -> Upgrade_Furnace_BlockEntity.this.maxProgress;
                    case 2 -> Upgrade_Furnace_BlockEntity.this.fuelLevel;
                    case 3 -> Upgrade_Furnace_BlockEntity.this.progress2;
                    case 4 -> Upgrade_Furnace_BlockEntity.this.progress3;
                    case 5 -> Upgrade_Furnace_BlockEntity.this.progress4;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> Upgrade_Furnace_BlockEntity.this.progress1 = pValue;
                    case 1 -> Upgrade_Furnace_BlockEntity.this.maxProgress = pValue;
                    case 2 -> Upgrade_Furnace_BlockEntity.this.fuelLevel = pValue;
                    case 3 -> Upgrade_Furnace_BlockEntity.this.progress2 = pValue;
                    case 4 -> Upgrade_Furnace_BlockEntity.this.progress3 = pValue;
                    case 5 -> Upgrade_Furnace_BlockEntity.this.progress4 = pValue;


                }
            }

            @Override
            public int getCount() {
                return 6;
            }
        };
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.simpeladdmod.upgraded_furnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return  new FurnaceMenu_up(pContainerId,pInventory, this, this.data);
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
        return (
                (direction == Direction.EAST || direction == Direction.WEST || direction == Direction.SOUTH || direction == Direction.NORTH) &&
                        (index == INPUTSLOT1 || index == INPUTSLOT2 || index == INPUTSLOT3 || index == INPUTSLOT4)||
                direction == Direction.UP && (index == FUELSLOT)
        );
    }
    @Override
    public boolean canTakeItemThroughFace(int slotIndex, ItemStack itemStack, Direction direction) {
        // Only allow the down direction and only for the result slot.
        return (direction == Direction.DOWN && (slotIndex == OUTPUTSLOT1 || slotIndex == OUTPUTSLOT2 || slotIndex == OUTPUTSLOT3 || slotIndex == OUTPUTSLOT4) || (slotIndex == FUELSLOT && itemStack.is(Items.BUCKET)));
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
        progress1 = compound.getInt("upgraded_furnace_progress1");
        progress2 = compound.getInt("upgraded_furnace_progress2");
        progress3 = compound.getInt("upgraded_furnace_progress3");
        progress4 = compound.getInt("upgraded_furnace_progress4");
        fuelLevel = compound.getInt("upgraded_furnace_fuel_left");
        if (!this.tryLoadLootTable(compound))
            this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compound, this.stacks);
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("inventory", itemHandler.serializeNBT());
        compound.putInt("upgraded_furnace_progress1", progress1);
        compound.putInt("upgraded_furnace_progress2", progress2);
        compound.putInt("upgraded_furnace_progress3", progress3);
        compound.putInt("upgraded_furnace_progress4", progress4);
        compound.putInt("upgraded_furnace_fuel_left", fuelLevel);
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
        return super.getCapability(capability, facing);
    }


// processing

    public void tick(Level pLevel, BlockPos pPos, BlockState pState){

        addFuel();
        setFuleUse();
        if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_1.get())) {
            this.maxProgress = 20;
        }if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_2.get())) {
            this.maxProgress = 12;
        }if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_3.get())) {
            this.maxProgress = 5;
        }if (stacks.get(UPGRADESLOT).isEmpty()){
            this.maxProgress = 30;
        }

        if(hasItemInFirtsSlot() && !areStackEqual1to2()){
            moveItemFrom1to2();
        }

        if(hasItemInSecondSlot() && !areStackEqual2to3()){
            moveItemFrom2to3();
        }
        if(hasItemInThirdSlot() && !areStackEqual3to4()){
            moveItemFrom3to4();
        }

        pState = pState.setValue(Upgrade_Furnace.WORKING, Boolean.valueOf(isWorking()));
        pLevel.setBlock(pPos, pState, 3);
        if(fuelLevel >= fueluse && fuelLevel > 0){
            if(hasRecipe1()){
                increaseCraftingProgress1();
                setChanged(pLevel, pPos, pState);
                if(hasProgressFinished1()){
                    useFuel();
                    craftItem1();
                    resetProgress1();
                }
            }
            if(hasRecipe2()){
                increaseCraftingProgress2();
                setChanged(pLevel, pPos, pState);
                if(hasProgressFinished2()){
                    useFuel();
                    craftItem2();
                    resetProgress2();
                }
            }
            if(hasRecipe3()){
                increaseCraftingProgress3();
                setChanged(pLevel, pPos, pState);
                if(hasProgressFinished3()){
                    useFuel();
                    craftItem3();
                    resetProgress3();
                }
            }
            if(hasRecipe4()){
                increaseCraftingProgress4();
                setChanged(pLevel, pPos, pState);
                if(hasProgressFinished4()){
                    useFuel();
                    craftItem4();
                    resetProgress4();
                }
            }
        }else{
            resetProgressAll();
            }
    }
    public static boolean isFuel(ItemStack pStack) {
        return CommonHooks.getBurnTime(pStack, RecipeType.SMELTING) > 0;
    }
    private void useFuel(){
        fuelLevel--;
    }
    private void addFuel() {
        if(!stacks.get(FUELSLOT).isEmpty() && !stacks.get(FUELSLOT).is(Items.BUCKET)){
            fuelLevel += CommonHooks.getBurnTime(this.stacks.get(FUELSLOT), RecipeType.SMELTING) / 200;
            if(stacks.get(FUELSLOT).is(Items.LAVA_BUCKET)){
               this.stacks.set(FUELSLOT, this.stacks.get(FUELSLOT).getCraftingRemainingItem());
            }else {
                this.removeItem(FUELSLOT, 1);
            }
        }
    }
    private void  setFuleUse(){
        if(!stacks.get(INPUTSLOT1).isEmpty()) {
            fueluse += 1;}
        if(!stacks.get(INPUTSLOT2).isEmpty()){
            fueluse += 1;}
        if(!stacks.get(INPUTSLOT3).isEmpty()){
            fueluse += 1;}
        if(!stacks.get(INPUTSLOT4).isEmpty()){
            fueluse += 1;}
        if(stacks.get(INPUTSLOT1).isEmpty()) {
            fueluse -= 1;}
        if(stacks.get(INPUTSLOT2).isEmpty()){
            fueluse -= 1;}
        if(stacks.get(INPUTSLOT3).isEmpty()){
            fueluse -= 1;}
        if(stacks.get(INPUTSLOT4).isEmpty()){
            fueluse -= 1;}
        else{
            fueluse = 0;
        }

    }
    private boolean hasItemInFirtsSlot(){
        return this.stacks.get(INPUTSLOT1).getCount() >= 2;
    }
    private boolean hasItemInSecondSlot(){
        return this.stacks.get(INPUTSLOT2).getCount() >= 2;
    }
    private boolean hasItemInThirdSlot(){
        return this.stacks.get(INPUTSLOT3).getCount() >= 2;
    }

    private boolean areStackEqual1to2(){
        return this.stacks.get(INPUTSLOT1).getCount() <= this.stacks.get(INPUTSLOT2).getCount();
    }
    private boolean areStackEqual2to3(){
        return this.stacks.get(INPUTSLOT2).getCount() <= this.stacks.get(INPUTSLOT3).getCount();
    }
    private boolean areStackEqual3to4(){
        return this.stacks.get(INPUTSLOT3).getCount() <= this.stacks.get(INPUTSLOT4).getCount();
    }
    private boolean isItemSameSlot2(){
        return this.stacks.get(INPUTSLOT1).getItem() == this.stacks.get(INPUTSLOT2).getItem() || this.stacks.get(INPUTSLOT2).isEmpty();
    }
    private boolean isItemSameSlot3(){
        return this.stacks.get(INPUTSLOT2).getItem() == this.stacks.get(INPUTSLOT3).getItem() || this.stacks.get(INPUTSLOT3).isEmpty();
    }

    private boolean isItemSameSlot4(){
        return this.stacks.get(INPUTSLOT3).getItem() == this.stacks.get(INPUTSLOT4).getItem() || this.stacks.get(INPUTSLOT4).isEmpty();
    }
    private void moveItemFrom1to2(){
        if(isItemSameSlot2()){
        ItemStack item = this.stacks.get(INPUTSLOT1);
        this.removeItem(INPUTSLOT1, 1);
        this.stacks.set(INPUTSLOT2, new ItemStack(item.getItem(),
                this.stacks.get(INPUTSLOT2).getCount() + 1));}

    }
    private void moveItemFrom2to3(){
        if(isItemSameSlot3()){
            ItemStack item = this.stacks.get(INPUTSLOT2);
            this.removeItem(INPUTSLOT2, 1);
            this.stacks.set(INPUTSLOT3, new ItemStack(item.getItem(),
                    this.stacks.get(INPUTSLOT3).getCount() + 1));}

    }

    private void moveItemFrom3to4(){
        if(isItemSameSlot4()){
        ItemStack item = this.stacks.get(INPUTSLOT3);
        this.removeItem(INPUTSLOT3, 1);
        this.stacks.set(INPUTSLOT4, new ItemStack(item.getItem(),
                this.stacks.get(INPUTSLOT4).getCount() + 1));}
    }
    private boolean canWork(){
        return  hasRecipe1()||hasRecipe2()||hasRecipe3()||hasRecipe4();
    }
    private boolean isWorking() {
        if (canWork() && fuelLevel > 0){
            return true;
        }
        return false;
    }
    private void resetProgressAll() {
        progress1 = 0;
        progress2 = 0;
        progress3 = 0;
        progress4 = 0;
    }
    private void resetProgress1() {
        progress1 = 0;
    }
    private void resetProgress2() {
        progress2 = 0;
    }
    private void resetProgress3() {
        progress3 = 0;
    }
    private void resetProgress4() {
        progress4 = 0;
    }
    private void increaseCraftingProgress1() {

        progress1++;
    }
    private void increaseCraftingProgress2() {

        progress2++;
    }
    private void increaseCraftingProgress3() {

        progress3++;
    }
    private void increaseCraftingProgress4() {

        progress4++;
    }

    private boolean hasProgressFinished1() {

            return progress1 >= maxProgress;

    }
    private boolean hasProgressFinished2() {

        return progress2 >= maxProgress;

    }
    private boolean hasProgressFinished3() {

        return progress3 >= maxProgress;

    }
    private boolean hasProgressFinished4() {

        return progress4 >= maxProgress;

    }

    private void craftItem1() {
        Optional<RecipeHolder<SmeltingRecipe>> recipe = getCurrentRecipe1();
        ItemStack result = recipe.get().value().getResultItem(null);
        this.removeItem(INPUTSLOT1, 1);
        this.stacks.set(OUTPUTSLOT1, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT1).getCount() + result.getCount()));

    }
    private void craftItem2() {
        Optional<RecipeHolder<SmeltingRecipe>> recipe = getCurrentRecipe2();
        ItemStack result = recipe.get().value().getResultItem(null);
        this.removeItem(INPUTSLOT2, 1);
        this.stacks.set(OUTPUTSLOT2, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT2).getCount() + result.getCount()));

    }
    private void craftItem3() {
        Optional<RecipeHolder<SmeltingRecipe>> recipe = getCurrentRecipe3();
        ItemStack result = recipe.get().value().getResultItem(null);
        this.removeItem(INPUTSLOT3, 1);
        this.stacks.set(OUTPUTSLOT3, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT3).getCount() + result.getCount()));

    }
    private void craftItem4() {
        Optional<RecipeHolder<SmeltingRecipe>> recipe = getCurrentRecipe4();
        ItemStack result = recipe.get().value().getResultItem(null);
        this.removeItem(INPUTSLOT4, 1);
        this.stacks.set(OUTPUTSLOT4, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT4).getCount() + result.getCount()));

    }
    public boolean hasRecipe1() {
        Optional<RecipeHolder<SmeltingRecipe>> recipe = getCurrentRecipe1();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().value().getResultItem(getLevel().registryAccess());

        return canInsertOutputAmount1(result.getCount()) && canInsertOutputItem1(result.getItem());
}
    public boolean hasRecipe2() {
        Optional<RecipeHolder<SmeltingRecipe>> recipe = getCurrentRecipe2();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().value().getResultItem(getLevel().registryAccess());

        return canInsertOutputAmount2(result.getCount()) && canInsertOutputItem2(result.getItem());
    }
    public boolean hasRecipe3() {
        Optional<RecipeHolder<SmeltingRecipe>> recipe = getCurrentRecipe3();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().value().getResultItem(getLevel().registryAccess());

        return canInsertOutputAmount3(result.getCount()) && canInsertOutputItem3(result.getItem());
    }
    public boolean hasRecipe4() {
        Optional<RecipeHolder<SmeltingRecipe>> recipe = getCurrentRecipe4();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().value().getResultItem(getLevel().registryAccess());

        return canInsertOutputAmount4(result.getCount()) && canInsertOutputItem4(result.getItem());
    }

    private Optional<RecipeHolder<SmeltingRecipe>> getCurrentRecipe1() {
        SimpleContainer inventory = new SimpleContainer(this.stacks.get(INPUTSLOT1));
        return this.level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, inventory, level);
    }
    private Optional<RecipeHolder<SmeltingRecipe>> getCurrentRecipe2() {
        SimpleContainer inventory = new SimpleContainer(this.stacks.get(INPUTSLOT2));
        return this.level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, inventory, level);
    }
    private Optional<RecipeHolder<SmeltingRecipe>> getCurrentRecipe3() {
        SimpleContainer inventory = new SimpleContainer(this.stacks.get(INPUTSLOT3));
        return this.level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, inventory, level);
    }
    private Optional<RecipeHolder<SmeltingRecipe>> getCurrentRecipe4() {
        SimpleContainer inventory = new SimpleContainer(this.stacks.get(INPUTSLOT4));
        return this.level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, inventory, level);
    }


    private boolean canInsertOutputItem1(Item item) {
        return this.stacks.get(OUTPUTSLOT1).isEmpty() || this.stacks.get(OUTPUTSLOT1).is(item);
    }

    private boolean canInsertOutputAmount1(int count) {
        return this.stacks.get(OUTPUTSLOT1).getCount() + count <= this.stacks.get(OUTPUTSLOT1).getMaxStackSize();
    }
    private boolean canInsertOutputItem2(Item item) {
        return this.stacks.get(OUTPUTSLOT2).isEmpty() || this.stacks.get(OUTPUTSLOT2).is(item);
    }

    private boolean canInsertOutputAmount2(int count) {
        return this.stacks.get(OUTPUTSLOT2).getCount() + count <= this.stacks.get(OUTPUTSLOT2).getMaxStackSize();
    }
    private boolean canInsertOutputItem3(Item item) {
        return this.stacks.get(OUTPUTSLOT3).isEmpty() || this.stacks.get(OUTPUTSLOT3).is(item);
    }

    private boolean canInsertOutputAmount3(int count) {
        return this.stacks.get(OUTPUTSLOT3).getCount() + count <= this.stacks.get(OUTPUTSLOT3).getMaxStackSize();
    }
    private boolean canInsertOutputItem4(Item item) {
        return this.stacks.get(OUTPUTSLOT4).isEmpty() || this.stacks.get(OUTPUTSLOT4).is(item);
    }

    private boolean canInsertOutputAmount4(int count) {
        return this.stacks.get(OUTPUTSLOT4).getCount() + count <= this.stacks.get(OUTPUTSLOT4).getMaxStackSize();
    }
    
}