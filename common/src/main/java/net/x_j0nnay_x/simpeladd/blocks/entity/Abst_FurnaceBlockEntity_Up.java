package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.blocks.Abst_FurnaceBlock_Up;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import org.jetbrains.annotations.Nullable;
import static net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity.getFuel;

public abstract class Abst_FurnaceBlockEntity_Up extends RandomizableContainerBlockEntity implements WorldlyContainer {

    private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCookingRecipe> recipeCheckSmelting;
    protected NonNullList<ItemStack> stacks = NonNullList.withSize(12, ItemStack.EMPTY);
    public static int FUELSLOT = 0;
    public static int INPUTSLOT1 = 1;
    public static int INPUTSLOT2 = 2;
    public static int INPUTSLOT3 = 3;
    public static int INPUTSLOT4 = 4;
    public static int OUTPUTSLOT1 = 5;
    public static int OUTPUTSLOT2 = 6;
    public static int OUTPUTSLOT3 = 7;
    public static int OUTPUTSLOT4 = 8;
    public static int UPGRADESLOT = 9;
    public static int XPBOTTLESLOT = 10;
    public static int XPBOOSTSLOT = 11;
    private static final int[] SLOTS_FOR_UP = new int[]{FUELSLOT};
    private static final int[] SLOTS_FOR_DOWN = new int[]{FUELSLOT, OUTPUTSLOT1, OUTPUTSLOT2, OUTPUTSLOT3, OUTPUTSLOT4};
    private static final int[] SLOTS_FOR_SIDES = new int[]{INPUTSLOT1, INPUTSLOT2, INPUTSLOT3, INPUTSLOT4, XPBOTTLESLOT};
    protected final ContainerData data;
    private int progress1 = 0;
    private int progress2 = 0;
    private int progress3 = 0;
    private int progress4 = 0;
    private int maxProgress;
    private int fuelLevel = 0 ;
    private int storedXP = 0;
    private int xpBoost;

    protected Abst_FurnaceBlockEntity_Up(BlockEntityType<?> $$0, BlockPos $$1, BlockState $$2) {
        super($$0, $$1, $$2);
        this.recipeCheckSmelting = RecipeManager.createCheck(RecipeType.SMELTING);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> Abst_FurnaceBlockEntity_Up.this.progress1;
                    case 1 -> Abst_FurnaceBlockEntity_Up.this.maxProgress;
                    case 2 -> Abst_FurnaceBlockEntity_Up.this.fuelLevel;
                    case 3 -> Abst_FurnaceBlockEntity_Up.this.progress2;
                    case 4 -> Abst_FurnaceBlockEntity_Up.this.progress3;
                    case 5 -> Abst_FurnaceBlockEntity_Up.this.progress4;
                    case 6 -> Abst_FurnaceBlockEntity_Up.this.storedXP;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> Abst_FurnaceBlockEntity_Up.this.progress1 = pValue;
                    case 1 -> Abst_FurnaceBlockEntity_Up.this.maxProgress = pValue;
                    case 2 -> Abst_FurnaceBlockEntity_Up.this.fuelLevel = pValue;
                    case 3 -> Abst_FurnaceBlockEntity_Up.this.progress2 = pValue;
                    case 4 -> Abst_FurnaceBlockEntity_Up.this.progress3 = pValue;
                    case 5 -> Abst_FurnaceBlockEntity_Up.this.progress4 = pValue;
                    case 6 -> Abst_FurnaceBlockEntity_Up.this.storedXP = pValue;
                }
            }

            @Override
            public int getCount() {
                return 7;
            }
        };
    }

    @Override
    public void loadAdditional(CompoundTag $$0, HolderLookup.Provider pRegistries) {
        super.loadAdditional($$0, pRegistries);
        this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems($$0, this.stacks, pRegistries);
        this.progress1 = $$0.getInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_progress1");
        this.progress2 = $$0.getInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_progress2");
        this.progress3 = $$0.getInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_progress3");
        this.progress4 = $$0.getInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_progress4");
        this.fuelLevel = $$0.getInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_fuel_left");
        this.storedXP = $$0.getInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_stored_xp");
    }

    @Override
    protected void saveAdditional(CompoundTag $$0, HolderLookup.Provider pRegistries) {
        super.saveAdditional($$0, pRegistries);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_progress1", progress1);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_progress2", progress2);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_progress3", progress3);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_progress4", progress4);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_fuel_left", fuelLevel);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_stored_xp", storedXP);
        ContainerHelper.saveAllItems($$0, this.stacks, pRegistries);
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack var2, @Nullable Direction direction) {
        if(direction == Direction.EAST || direction == Direction.WEST || direction == Direction.SOUTH || direction == Direction.NORTH){
            if(index == INPUTSLOT1 || index == INPUTSLOT2 || index == INPUTSLOT3 || index == INPUTSLOT4){
                return true;
            }
            return false;
        }
        if(direction == Direction.UP && index == FUELSLOT && isFuel(var2)){
            return true;
        }
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack var2, Direction direction) {
        if(direction == Direction.EAST || direction == Direction.WEST || direction == Direction.SOUTH || direction == Direction.NORTH){
            if(index == XPBOTTLESLOT){
                return true;
            }
            return false;
        }
        if(direction == Direction.DOWN){
            if(index == OUTPUTSLOT1 || index == OUTPUTSLOT2 || index == OUTPUTSLOT3 || index == OUTPUTSLOT4){
                return true;
            }
            if(index == FUELSLOT && var2.is(Items.BUCKET)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getContainerSize() {
        return this.stacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.stacks)
            if (!itemstack.isEmpty())
                return false;
        return true;
    }

    @Override
    public ItemStack getItem(int var1) {
        return this.stacks.get(var1);
    }

    @Override
    public ItemStack removeItem(int var1, int var2) {
        return ContainerHelper.removeItem(this.stacks, var1, var2);
    }

    @Override
    public ItemStack removeItemNoUpdate(int var1) {
        return ContainerHelper.takeItem(this.stacks, var1);
    }

    @Override
    public void setItem(int var1, ItemStack var2) {
        ItemStack $$2 = this.stacks.get(var1);
        boolean $$3 = !var2.isEmpty() && ItemStack.isSameItem($$2, var2);
        this.stacks.set(var1, var2);
        if (var2.getCount() > this.getMaxStackSize()) {
            var2.setCount(this.getMaxStackSize());
        }
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.stacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        this.stacks = nonNullList;
    }

    @Override
    public int[] getSlotsForFace(Direction var1) {
        if (var1 == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        } else {
            return var1 == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
        }
    }
    @Override
    public void clearContent() {
        this.stacks.clear();
    }
    @Override
    public boolean stillValid(Player $$0) {
        return Container.stillValidBlockEntity(this, $$0);
    }
    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.simpeladdmod.upgraded_furnace");
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return this.saveWithFullMetadata(pRegistries);
    }
    //Processing
    public void upFurnaceTick(Level pLevel, BlockPos pPos, BlockState pState) {
        makeXPBottle();
        addFuel();
        splitStack();
        resetCheck();
        setUpGrades();
        pState = pState.setValue(Abst_FurnaceBlock_Up.WORKING, Boolean.valueOf(isWorking()));
        pLevel.setBlock(pPos, pState, 3);
        if(fuelLevel > 0){
            for (int i = 0; i < 4; i ++) {
                int slot = INPUTSLOT1 + i;
                RecipeHolder<? extends AbstractCookingRecipe> irecipe = this.getRecipeNonCached(this.stacks.get(slot));
                if (hasRecipe(irecipe, slot)) {
                    incresseProgress(slot);
                    if (hasProgressFinished(slot)) {
                        useFuel();
                        craftItem(irecipe, slot);
                        this.storedXP += Math.round(irecipe.value().getExperience() * xpBoost);
                        resetProgress(slot);
                    }
                }
            }
        }
    }

    private void setUpGrades(){
        if (this.stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_1)) {
            this.maxProgress = 20;
        }if (this.stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_2)) {
            this.maxProgress = 12;
        }if (this.stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_3)) {
            this.maxProgress = 5;
        }if (this.stacks.get(UPGRADESLOT).isEmpty()){
            this.maxProgress = 30;
        }if (this.stacks.get(XPBOOSTSLOT).isEmpty()){
            this.xpBoost = 1;
        }if (!this.stacks.get(XPBOOSTSLOT).isEmpty()){
            this.xpBoost = 4;
        }
    }

    private boolean isblockEmpty(){
        return isSlotEmpty(INPUTSLOT1) && isSlotEmpty(INPUTSLOT2) && isSlotEmpty(INPUTSLOT3) && isSlotEmpty(INPUTSLOT4) ;
    }

    public static boolean isFuel(ItemStack pStack) {
        return getFuel().containsKey(pStack.getItem());
    }

    protected int getFuelTime(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        } else {
            Item item = fuel.getItem();
            return (int)getFuel().getOrDefault(item, 0);
        }
    }

    private void useFuel(){
        fuelLevel--;
    }

    private void addFuel() {
        if(!this.stacks.get(FUELSLOT).isEmpty() && !this.stacks.get(FUELSLOT).is(Items.BUCKET)){
            fuelLevel +=  (this.getFuelTime(this.stacks.get(FUELSLOT)) / 200);
            if (this.stacks.get(FUELSLOT).getItem() == (Items.LAVA_BUCKET)) {
                this.removeItem(FUELSLOT, 1);
                this.stacks.set(FUELSLOT, new ItemStack(Items.BUCKET));
            }
            else {
                this.removeItem(FUELSLOT, 1);
            }
        }
    }

    private void resetCheck(){
        for (int i = 0; i < 4; i ++) {
            int slot = INPUTSLOT1 + i;
            if (isSlotEmpty(slot) || fuelLevel == 0) {
                resetProgress(slot);
            }
        }
        if(fuelLevel < 0){
            fuelLevel = 0;
        }
    }

    private boolean canMakeBottleXP(){
        return this.storedXP >= 200;//200
    }

    private void makeXPBottle(){
        if (canMakeBottleXP() && this.stacks.get(XPBOTTLESLOT).getCount() <= 63) {
            this.storedXP -= 200;//200
            this.stacks.set(XPBOTTLESLOT, new ItemStack(Items.EXPERIENCE_BOTTLE.asItem(), this.stacks.get(XPBOTTLESLOT).getCount() + 1));
        }

    }

    private boolean hasEnoughtToMove(int slot, int count){
        return this.stacks.get(slot).getCount() >= count;
    }

    private boolean areStacksSplit(int checkSlot, int currentSlot){
        return this.stacks.get(checkSlot).getCount() <= this.stacks.get(currentSlot).getCount();
    }

    private boolean areStacksSame(int checkSlot, int currentSlot){
        return this.stacks.get(checkSlot).getItem() == this.stacks.get(currentSlot).getItem() || this.stacks.get(currentSlot).isEmpty();
    }

    private void moveItem(int slotToSplit, int slotToFill, int count){
        if (hasEnoughtToMove(slotToSplit, count)) {
            if (areStacksSame(slotToSplit, slotToFill)) {
                if (!areStacksSplit(slotToSplit, slotToFill)) {
                    ItemStack item = this.stacks.get(slotToSplit);
                    this.removeItem(slotToSplit, count);
                    this.stacks.set(slotToFill, new ItemStack(item.getItem(),
                            this.stacks.get(slotToFill).getCount() + count));
                }
            }
        }
    }

    private void splitStack(){
        int slotCount = 1;
        for (int i = 0; i < 3; i ++) {
            if (areStacksSame(INPUTSLOT1 + i, INPUTSLOT2 + i)) {
                slotCount++;
            }
        }
        for (int i = 0; i < 3; i ++) {
            int count = this.stacks.get(INPUTSLOT1+ i).getCount() / slotCount;
            moveItem(INPUTSLOT1 + i, INPUTSLOT2 + i, count);
        }

    }

    private boolean isSlotEmpty(int slot){
        return  this.stacks.get(slot).isEmpty();
    }

    private boolean isWorking() {
        if (this.progress1 > 0 || this.progress2 > 0 || this.progress3 > 0 || this.progress4 > 0 && this.fuelLevel > 0 && !isblockEmpty()){
            return true;
        }
        return false;
    }

    private void resetProgress(int slot){
        if(slot == INPUTSLOT1){
            this.progress1 = 0;
        }
        if(slot == INPUTSLOT2){
            this.progress2 = 0;
        }
        if(slot == INPUTSLOT3){
            this.progress3 = 0;
        }
        if(slot == INPUTSLOT4){
            this.progress4 = 0;
        }
    }
    private void incresseProgress(int slot){
        if(slot == INPUTSLOT1){
            this.progress1 ++;
        }
        if(slot == INPUTSLOT2){
            this.progress2 ++;
        }
        if(slot == INPUTSLOT3){
            this.progress3 ++;
        }
        if(slot == INPUTSLOT4){
            this.progress4 ++;
        }
    }

    private boolean hasProgressFinished(int slot){
        if(slot == INPUTSLOT1){
            return this.progress1 >= this.maxProgress;
        }
        if(slot == INPUTSLOT2){
            return this.progress2 >= this.maxProgress;
        }
        if(slot == INPUTSLOT3){
            return this.progress3 >= this.maxProgress;
        }
        if(slot == INPUTSLOT4){
            return this.progress4 >= this.maxProgress;
        }
        return false;
    }

    private RecipeHolder<? extends AbstractCookingRecipe> getRecipeNonCached(ItemStack itemStack) {
        return this.recipeCheckSmelting.getRecipeFor(new SingleRecipeInput(itemStack), this.level).orElse(null);
    }

    private  void craftItem(@Nullable RecipeHolder<?> recipe, int slot){
        int outputSlot = slot + 4;
        if (recipe != null && this.hasRecipe(recipe, slot)) {
            ItemStack itemstack = this.getItem(slot);
            ItemStack itemstack1 = recipe.value().getResultItem(RegistryAccess.EMPTY);
            ItemStack itemstack2 = this.getItem(outputSlot);
            if (itemstack2.isEmpty()) {
                this.setItem(outputSlot, itemstack1.copy());
            } else if (itemstack2.getItem() == itemstack1.getItem()) {
                itemstack2.grow(itemstack1.getCount());
            }
            itemstack.shrink(1);
        }
    }

    public  boolean hasRecipe(@Nullable RecipeHolder<?> recipe, int slot){
        int outputslot = slot + 4;
        if(!this.getItem(slot).isEmpty() && recipe != null){
            ItemStack recipeOutput = recipe.value().getResultItem(RegistryAccess.EMPTY);
            if (!recipeOutput.isEmpty()){
                ItemStack output = this.getItem(outputslot);
                if (output.isEmpty()) return true;
                else if (!ItemStack.isSameItemSameComponents(output, recipeOutput)) return false;
                else return output.getCount() + recipeOutput.getCount() <= output.getMaxStackSize();
            }
        }
        return false;
    }
}