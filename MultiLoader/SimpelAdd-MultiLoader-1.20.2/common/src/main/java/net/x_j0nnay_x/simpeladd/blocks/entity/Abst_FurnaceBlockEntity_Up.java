package net.x_j0nnay_x.simpeladd.blocks.entity;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.blocks.Abst_FurnaceBlock_Up;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import org.jetbrains.annotations.Nullable;
import static net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity.getFuel;

import java.util.Optional;

public abstract class Abst_FurnaceBlockEntity_Up extends RandomizableContainerBlockEntity implements WorldlyContainer {

    protected NonNullList<ItemStack> stacks = NonNullList.withSize(11, ItemStack.EMPTY);
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
    public  static int XPBOTTLESLOT = 10;;
    private static final int[] SLOTS_FOR_UP = new int[]{FUELSLOT};
    private static final int[] SLOTS_FOR_DOWN = new int[]{OUTPUTSLOT1, OUTPUTSLOT2, OUTPUTSLOT3, OUTPUTSLOT4, XPBOTTLESLOT};
    private static final int[] SLOTS_FOR_SIDES = new int[]{INPUTSLOT1, INPUTSLOT2, INPUTSLOT3, INPUTSLOT4};
    protected final ContainerData data;
    private int progress1 = 0;
    private int progress2 = 0;
    private int progress3 = 0;
    private int progress4 = 0;
    private int maxProgress;
    private int fuelLevel = 0 ;
    private int fueluse = 0;
    private int storedXP = 0;
    private int maxXP = 10000;


    protected Abst_FurnaceBlockEntity_Up(BlockEntityType<?> $$0, BlockPos $$1, BlockState $$2) {
        super($$0, $$1, $$2);

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
    public void load(CompoundTag $$0) {
        super.load($$0);
        this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems($$0, this.stacks);
        this.progress1 = $$0.getInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_progress1");
        this.progress2 = $$0.getInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_progress2");
        this.progress3 = $$0.getInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_progress3");
        this.progress4 = $$0.getInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_progress4");
        this.fuelLevel = $$0.getInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_fuel_left");
        this.storedXP = $$0.getInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_stored_xp");
       }

    @Override
    protected void saveAdditional(CompoundTag $$0) {
        super.saveAdditional($$0);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_progress1", progress1);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_progress2", progress2);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_progress3", progress3);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_progress4", progress4);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_fuel_left", fuelLevel);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"upgraded_furnace_stored_xp", storedXP);
        ContainerHelper.saveAllItems($$0, this.stacks);
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack var2, @Nullable Direction direction) {
        return (
                (direction == Direction.EAST || direction == Direction.WEST || direction == Direction.SOUTH || direction == Direction.NORTH) &&
                        (index == INPUTSLOT1 || index == INPUTSLOT2 || index == INPUTSLOT3 || index == INPUTSLOT4)||
                        direction == Direction.UP && (index == FUELSLOT)
        );
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack var2, Direction direction) {
        return (direction == Direction.DOWN && (index == OUTPUTSLOT1 || index == OUTPUTSLOT2 || index == OUTPUTSLOT3 || index == OUTPUTSLOT4) || (index == FUELSLOT && var2.is(Items.BUCKET)));
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
        boolean $$3 = !var2.isEmpty() && ItemStack.isSameItemSameTags($$2, var2);
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
    public void sendUpdate() {
        setChanged();

        if (this.level != null)
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }

//Processing

    public void upFurnaceTick(Level pLevel, BlockPos pPos, BlockState pState) {
        makeXPBottle();
        addFuel();
        setFuleUse();
        if (this.stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_1)) {
            this.maxProgress = 20;
        }if (this.stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_2)) {
            this.maxProgress = 12;
        }if (this.stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_3)) {
            this.maxProgress = 5;
        }if (this.stacks.get(UPGRADESLOT).isEmpty()){
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

        pState = pState.setValue(Abst_FurnaceBlock_Up.WORKING, Boolean.valueOf(isWorking()));
        pLevel.setBlock(pPos, pState, 3);
        if(fuelLevel >= fueluse && fuelLevel > 0){
            if(hasRecipe1()){
                increaseCraftingProgress1();
                if(hasProgressFinished1()){
                    useFuel();
                    craftItem1();
                    resetProgress1();
                }
            }
            if(hasRecipe2()){
                increaseCraftingProgress2();
                if(hasProgressFinished2()){
                    useFuel();
                    craftItem2();
                    resetProgress2();
                }
            }
            if(hasRecipe3()){
                increaseCraftingProgress3();
                if(hasProgressFinished3()){
                    useFuel();
                    craftItem3();
                    resetProgress3();
                }
            }
            if(hasRecipe4()){
                increaseCraftingProgress4();
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
            fuelLevel += this.getFuelTime(this.stacks.get(FUELSLOT)) / 200;
            if(this.stacks.get(FUELSLOT).is(Items.LAVA_BUCKET)){
                this.stacks.remove(FUELSLOT);
                this.stacks.set(FUELSLOT, Items.BUCKET.getDefaultInstance());
            }else {
                this.removeItem(FUELSLOT, 1);
            }
        }
    }
    private void  setFuleUse(){
        if(!this.stacks.get(INPUTSLOT1).isEmpty()) {
            fueluse += 1;}
        if(!this.stacks.get(INPUTSLOT2).isEmpty()){
            fueluse += 1;}
        if(!this.stacks.get(INPUTSLOT3).isEmpty()){
            fueluse += 1;}
        if(!this.stacks.get(INPUTSLOT4).isEmpty()){
            fueluse += 1;}
        if(this.stacks.get(INPUTSLOT1).isEmpty()) {
            fueluse -= 1;}
        if(this.stacks.get(INPUTSLOT2).isEmpty()){
            fueluse -= 1;}
        if(this.stacks.get(INPUTSLOT3).isEmpty()){
            fueluse -= 1;}
        if(this.stacks.get(INPUTSLOT4).isEmpty()){
            fueluse -= 1;}
        else{
            fueluse = 0;
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
        if (canWork() && this.fuelLevel > 0){
            return true;
        }
        return false;
    }
    private void resetProgressAll() {
        this.progress1 = 0;
        this.progress2 = 0;
        this.progress3 = 0;
        this.progress4 = 0;
    }
    private void resetProgress1() {
        this.progress1 = 0;
    }
    private void resetProgress2() {
        this.progress2 = 0;
    }
    private void resetProgress3() {
        this.progress3 = 0;
    }
    private void resetProgress4() {
        this.progress4 = 0;
    }
    private void increaseCraftingProgress1() {
        this.progress1++;
    }
    private void increaseCraftingProgress2() {
        this.progress2++;
    }
    private void increaseCraftingProgress3() {
        this.progress3++;
    }
    private void increaseCraftingProgress4() {
        this.progress4++;
    }

    private boolean hasProgressFinished1() {
        return this.progress1 >= this.maxProgress;
    }
    private boolean hasProgressFinished2() {
        return this.progress2 >= this.maxProgress;
    }
    private boolean hasProgressFinished3() {
        return this.progress3 >= this.maxProgress;
    }
    private boolean hasProgressFinished4() {
        return this.progress4 >= this.maxProgress;
    }
    private void craftItem1() {
        Optional<RecipeHolder<SmeltingRecipe>> recipe = getCurrentRecipe1();
        ItemStack result = recipe.get().value().getResultItem(null);
        this.removeItem(INPUTSLOT1, 1);
        if(this.storedXP < this.maxXP){
            this.storedXP += Math.round(recipe.get().value().getExperience());
        }
        this.stacks.set(OUTPUTSLOT1, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT1).getCount() + result.getCount()));


    }
    private void craftItem2() {
        Optional<RecipeHolder<SmeltingRecipe>> recipe = getCurrentRecipe2();
        ItemStack result = recipe.get().value().getResultItem(null);
        this.removeItem(INPUTSLOT2, 1);
        if(this.storedXP < this.maxXP){
            this.storedXP += Math.round(recipe.get().value().getExperience());
        }
        this.stacks.set(OUTPUTSLOT2, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT2).getCount() + result.getCount()));

    }
    private void craftItem3() {
        Optional<RecipeHolder<SmeltingRecipe>> recipe = getCurrentRecipe3();
        ItemStack result = recipe.get().value().getResultItem(null);
        this.removeItem(INPUTSLOT3, 1);
        if(this.storedXP < this.maxXP){
            this.storedXP += Math.round(recipe.get().value().getExperience());
        }
        this.stacks.set(OUTPUTSLOT3, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT3).getCount() + result.getCount()));

    }
    private void craftItem4() {
        Optional<RecipeHolder<SmeltingRecipe>> recipe = getCurrentRecipe4();
        ItemStack result = recipe.get().value().getResultItem(null);
        this.removeItem(INPUTSLOT4, 1);
        if(this.storedXP < this.maxXP){
            this.storedXP += Math.round(recipe.get().value().getExperience());
        }
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