package net.x_j0nnay_x.simpeladd.blocks.entity;


import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
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
    private int fueluse = 0;
    private int storedXP = 0;
    private int maxXP = 10000;
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
        BlockEntity blockEntity = this.level.getBlockEntity(pPos);
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
        }if (this.stacks.get(XPBOOSTSLOT).isEmpty()){
            this.xpBoost = 1;
        }if (!this.stacks.get(XPBOOSTSLOT).isEmpty()){
            this.xpBoost = 4;
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
            RecipeHolder<? extends AbstractCookingRecipe> irecipe1 = this.getRecipeNonCached(this.stacks.get(INPUTSLOT1));
            RecipeHolder<? extends AbstractCookingRecipe> irecipe2 = this.getRecipeNonCached(this.stacks.get(INPUTSLOT2));
            RecipeHolder<? extends AbstractCookingRecipe> irecipe3 = this.getRecipeNonCached(this.stacks.get(INPUTSLOT3));
            RecipeHolder<? extends AbstractCookingRecipe> irecipe4 = this.getRecipeNonCached(this.stacks.get(INPUTSLOT4));

            if(hasRecipe(irecipe1, INPUTSLOT1)){
                increaseCraftingProgress1();
                if(hasProgressFinished1()){
                    useFuel();
                    craftItem(irecipe1, INPUTSLOT1);
                    this.storedXP += Math.round(irecipe1.value().getExperience() * xpBoost);
                    resetProgress1();
                }
            }
            if(hasRecipe(irecipe2, INPUTSLOT2)){
                increaseCraftingProgress2();
                if(hasProgressFinished2()){
                    useFuel();
                    craftItem(irecipe2, INPUTSLOT2);
                    this.storedXP += Math.round(irecipe2.value().getExperience()* xpBoost);
                    resetProgress2();
                }
            }
            if(hasRecipe(irecipe3, INPUTSLOT3)){
                increaseCraftingProgress3();
                if(hasProgressFinished3()){
                    useFuel();
                    craftItem(irecipe3, INPUTSLOT3);
                    this.storedXP += Math.round(irecipe3.value().getExperience()* xpBoost);
                    resetProgress3();
                }
            }
            if(hasRecipe(irecipe4, INPUTSLOT4)){
                increaseCraftingProgress4();
                if(hasProgressFinished4()){
                    useFuel();
                    craftItem(irecipe4, INPUTSLOT4);
                    this.storedXP += Math.round(irecipe4.value().getExperience() * xpBoost);
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


    private boolean isWorking() {
        if (this.progress1 > 0 || this.progress2 > 0 || this.progress3 > 0 || this.progress4 > 0 && this.fuelLevel > 0){
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