package net.x_j0nnay_x.simpeladdmod.block.entity;



import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.x_j0nnay_x.simpeladdmod.block.ImplementedInventory;
import net.x_j0nnay_x.simpeladdmod.block.ModBlockEntities;
import net.x_j0nnay_x.simpeladdmod.block.custom.Upgrade_Furnace;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.screen.Furnace_Up.FurnaceMenu_up;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static net.minecraft.block.entity.AbstractFurnaceBlockEntity.createFuelTimeMap;


public class Upgrade_Furnace_BlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory, SidedInventory {
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(11, ItemStack.EMPTY);

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
    public  static int XPBOTTLESLOT = 10;
    protected final PropertyDelegate data;
    private int progress1 = 0;
    private int progress2 = 0;
    private int progress3 = 0;
    private int progress4 = 0;
    private int maxProgress;
    private int fuelLevel = 0 ;
    private int fueluse = 0;
    private int storedXP = 0;
    private final int maxXP = 10000;

    public Upgrade_Furnace_BlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.UPGRADED_FURNACE, pPos, pBlockState);

        this.data = new PropertyDelegate() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> Upgrade_Furnace_BlockEntity.this.progress1;
                    case 1 -> Upgrade_Furnace_BlockEntity.this.maxProgress;
                    case 2 -> Upgrade_Furnace_BlockEntity.this.fuelLevel;
                    case 3 -> Upgrade_Furnace_BlockEntity.this.progress2;
                    case 4 -> Upgrade_Furnace_BlockEntity.this.progress3;
                    case 5 -> Upgrade_Furnace_BlockEntity.this.progress4;
                    case 6 -> Upgrade_Furnace_BlockEntity.this.storedXP;
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
                    case 6 -> Upgrade_Furnace_BlockEntity.this.storedXP = pValue;

                }
            }

            @Override
            public int size() {
                return 7;
            }
        };
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.simpeladdmod.upgraded_furnace");
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return  new FurnaceMenu_up(syncId, playerInventory, this, this.data);
    }
    @Override
    public void markDirty() {
        world.updateListeners(this.pos, getCachedState(), getCachedState(), 11);
        super.markDirty();
    }
    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void readNbt(NbtCompound compound) {
        super.readNbt(compound);
        this.inventory = DefaultedList.ofSize(11, ItemStack.EMPTY);
        Inventories.readNbt(compound, inventory);
        this.progress1 = compound.getInt("upgraded_furnace_progress1");
        this.progress2 = compound.getInt("upgraded_furnace_progress2");
        this.progress3 = compound.getInt("upgraded_furnace_progress3");
        this.progress4 = compound.getInt("upgraded_furnace_progress4");
        this.fuelLevel = compound.getInt("upgraded_furnace_fuel_left");
        this.storedXP = compound.getInt("upgraded_furnace_stored_xp");
    }

    @Override
    public void writeNbt(NbtCompound compound) {
        super.writeNbt(compound);
        Inventories.writeNbt(compound, inventory);
        compound.putInt("upgraded_furnace_progress1", this.progress1);
        compound.putInt("upgraded_furnace_progress2", this.progress2);
        compound.putInt("upgraded_furnace_progress3", this.progress3);
        compound.putInt("upgraded_furnace_progress4", this.progress4);
        compound.putInt("upgraded_furnace_fuel_left", this.fuelLevel);
        compound.putInt("upgraded_furnace_stored_xp", this.storedXP);
    }
    @Override
    public boolean canInsert(int index, ItemStack stack, @Nullable Direction direction) {
        return (
                (direction == Direction.EAST || direction == Direction.WEST || direction == Direction.SOUTH || direction == Direction.NORTH) &&
                        (index == INPUTSLOT1 || index == INPUTSLOT2 || index == INPUTSLOT3 || index == INPUTSLOT4) ||
                        direction == Direction.UP && index == FUELSLOT
        );
    }
    @Override
    public boolean canExtract(int index, ItemStack stack, Direction direction) {
        return (direction == Direction.DOWN && (index == OUTPUTSLOT1 || index == OUTPUTSLOT2 || index == OUTPUTSLOT3 || index == OUTPUTSLOT4)|| (index == FUELSLOT && stack.isOf(Items.BUCKET)));
    }
    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if (slot == FUELSLOT) {
            return AbstractFurnaceBlockEntity.canUseAsFuel(stack);
        }
        if (slot == INPUTSLOT1 || slot == INPUTSLOT2 || slot == INPUTSLOT3 || slot == INPUTSLOT4) {
            return  true;
        }

        return false;
    }


// processing

    public void tick(World pLevel, BlockPos pPos, BlockState pState){
        makeXPBottle();
        addFuel();
        setFuleUse();
        if (inventory.get(UPGRADESLOT).isOf(ModItems.SPEEDUPGRADE_1)) {
            this.maxProgress = 20;
        }if (inventory.get(UPGRADESLOT).isOf(ModItems.SPEEDUPGRADE_2)) {
            this.maxProgress = 12;
        }if (inventory.get(UPGRADESLOT).isOf(ModItems.SPEEDUPGRADE_3)) {
            this.maxProgress = 5;
        }if (inventory.get(UPGRADESLOT).isEmpty()){
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

        pState = (BlockState)pState.with(Upgrade_Furnace.WORKING, isWorking());
        pLevel.setBlockState(pPos, pState, Block.NOTIFY_ALL);
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
    protected int getFuelTime(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        } else {
            Item item = fuel.getItem();
            return (Integer)createFuelTimeMap().getOrDefault(item, 0);
        }
    }
    private void useFuel(){
        fuelLevel--;
    }

    private void addFuel() {
        if(!inventory.get(FUELSLOT).isEmpty() && !inventory.get(FUELSLOT).isOf(Items.BUCKET)){
            fuelLevel += this.getFuelTime(inventory.get(FUELSLOT)) / 200;

                  //  ForgeHooks.getBurnTime(this.inventory.get(FUELSLOT), RecipeType.SMELTING) / 200;
            if(inventory.get(FUELSLOT).isOf(Items.LAVA_BUCKET)){
               this.setStack(FUELSLOT, this.inventory.get(FUELSLOT).getRecipeRemainder());
            }else {
                this.removeStack(FUELSLOT, 1);
            }
        }
    }
    private void  setFuleUse(){
        if(!inventory.get(INPUTSLOT1).isEmpty()) {
            fueluse += 1;}
        if(!inventory.get(INPUTSLOT2).isEmpty()){
            fueluse += 1;}
        if(!inventory.get(INPUTSLOT3).isEmpty()){
            fueluse += 1;}
        if(!inventory.get(INPUTSLOT4).isEmpty()){
            fueluse += 1;}
        if(inventory.get(INPUTSLOT1).isEmpty()) {
            fueluse -= 1;}
        if(inventory.get(INPUTSLOT2).isEmpty()){
            fueluse -= 1;}
        if(inventory.get(INPUTSLOT3).isEmpty()){
            fueluse -= 1;}
        if(inventory.get(INPUTSLOT4).isEmpty()){
            fueluse -= 1;}
        else{
            fueluse = 0;
        }

    }
    private boolean canMakeBottleXP(){
        return this.storedXP >= 200;//200
    }
    private void makeXPBottle(){
        if (canMakeBottleXP() && this.inventory.get(XPBOTTLESLOT).getCount() <= 63) {
            this.storedXP -= 200;//200
            this.inventory.set(XPBOTTLESLOT, new ItemStack(Items.EXPERIENCE_BOTTLE.asItem(), this.inventory.get(XPBOTTLESLOT).getCount() + 1));
        }

    }
    private boolean hasItemInFirtsSlot(){
        return this.inventory.get(INPUTSLOT1).getCount() >= 2;
    }
    private boolean hasItemInSecondSlot(){
        return this.inventory.get(INPUTSLOT2).getCount() >= 2;
    }
    private boolean hasItemInThirdSlot(){
        return this.inventory.get(INPUTSLOT3).getCount() >= 2;
    }

    private boolean areStackEqual1to2(){
        return this.inventory.get(INPUTSLOT1).getCount() <= this.inventory.get(INPUTSLOT2).getCount();
    }
    private boolean areStackEqual2to3(){
        return this.inventory.get(INPUTSLOT2).getCount() <= this.inventory.get(INPUTSLOT3).getCount();
    }
    private boolean areStackEqual3to4(){
        return this.inventory.get(INPUTSLOT3).getCount() <= this.inventory.get(INPUTSLOT4).getCount();
    }
    private boolean isItemSameSlot2(){
        return this.inventory.get(INPUTSLOT1).getItem() == this.inventory.get(INPUTSLOT2).getItem() || this.inventory.get(INPUTSLOT2).isEmpty();
    }
    private boolean isItemSameSlot3(){
        return this.inventory.get(INPUTSLOT2).getItem() == this.inventory.get(INPUTSLOT3).getItem() || this.inventory.get(INPUTSLOT3).isEmpty();
    }

    private boolean isItemSameSlot4(){
        return this.inventory.get(INPUTSLOT3).getItem() == this.inventory.get(INPUTSLOT4).getItem() || this.inventory.get(INPUTSLOT4).isEmpty();
    }
    private void moveItemFrom1to2(){
        if(isItemSameSlot2()){
        ItemStack item = this.inventory.get(INPUTSLOT1);
        this.removeStack(INPUTSLOT1, 1);
        this.inventory.set(INPUTSLOT2, new ItemStack(item.getItem(),
                this.inventory.get(INPUTSLOT2).getCount() + 1));}

    }
    private void moveItemFrom2to3(){
        if(isItemSameSlot3()){
            ItemStack item = this.inventory.get(INPUTSLOT2);
            this.removeStack(INPUTSLOT2, 1);
            this.inventory.set(INPUTSLOT3, new ItemStack(item.getItem(),
                    this.inventory.get(INPUTSLOT3).getCount() + 1));}

    }

    private void moveItemFrom3to4(){
        if(isItemSameSlot4()){
        ItemStack item = this.inventory.get(INPUTSLOT3);
        this.removeStack(INPUTSLOT3, 1);
        this.inventory.set(INPUTSLOT4, new ItemStack(item.getItem(),
                this.inventory.get(INPUTSLOT4).getCount() + 1));}
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
        Optional<RecipeEntry<SmeltingRecipe>> recipe = getCurrentRecipe1();
        this.removeStack(INPUTSLOT1, 1);
        if(this.storedXP < this.maxXP){
            this.storedXP += Math.round(recipe.get().value().getExperience());
        }
        this.setStack(OUTPUTSLOT1, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(OUTPUTSLOT1).getCount() + recipe.get().value().getResult(null).getCount()));

    }
    private void craftItem2() {
        Optional<RecipeEntry<SmeltingRecipe>> recipe = getCurrentRecipe2();
        this.removeStack(INPUTSLOT2, 1);
        if(this.storedXP < this.maxXP){
            this.storedXP += Math.round(recipe.get().value().getExperience());
        }
        this.setStack(OUTPUTSLOT2, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(OUTPUTSLOT2).getCount() + recipe.get().value().getResult(null).getCount()));

    }
    private void craftItem3() {
        Optional<RecipeEntry<SmeltingRecipe>> recipe = getCurrentRecipe3();
        this.removeStack(INPUTSLOT3, 1);
        if(this.storedXP < this.maxXP){
            this.storedXP += Math.round(recipe.get().value().getExperience());
        }
        this.setStack(OUTPUTSLOT3, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(OUTPUTSLOT3).getCount() + recipe.get().value().getResult(null).getCount()));

    }
    private void craftItem4() {
        Optional<RecipeEntry<SmeltingRecipe>> recipe = getCurrentRecipe4();
        this.removeStack(INPUTSLOT4, 1);
        if(this.storedXP < this.maxXP){
            this.storedXP += Math.round(recipe.get().value().getExperience());
        }
        this.setStack(OUTPUTSLOT4, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(OUTPUTSLOT4).getCount() + recipe.get().value().getResult(null).getCount()));
    }
    public boolean hasRecipe1() {
        Optional<RecipeEntry<SmeltingRecipe>> recipe = getCurrentRecipe1();

        return recipe.isPresent() && canInsertOutputAmount1(recipe.get().value().getResult(null))
                && canInsertOutputItem1(recipe.get().value().getResult(null).getItem());
}
    public boolean hasRecipe2() {
        Optional<RecipeEntry<SmeltingRecipe>> recipe = getCurrentRecipe2();

        return recipe.isPresent() && canInsertOutputAmount2(recipe.get().value().getResult(null))
                && canInsertOutputItem2(recipe.get().value().getResult(null).getItem());
    }
    public boolean hasRecipe3() {
        Optional<RecipeEntry<SmeltingRecipe>> recipe = getCurrentRecipe3();

        return recipe.isPresent() && canInsertOutputAmount3(recipe.get().value().getResult(null))
                && canInsertOutputItem3(recipe.get().value().getResult(null).getItem());
    }
    public boolean hasRecipe4() {
        Optional<RecipeEntry<SmeltingRecipe>> recipe = getCurrentRecipe4();

        return recipe.isPresent() && canInsertOutputAmount4(recipe.get().value().getResult(null))
                && canInsertOutputItem4(recipe.get().value().getResult(null).getItem());
    }

    private Optional<RecipeEntry<SmeltingRecipe>> getCurrentRecipe1() {
        SimpleInventory inventory = new SimpleInventory(this.inventory.get(INPUTSLOT1));
        return getWorld().getRecipeManager().getFirstMatch(RecipeType.SMELTING, inventory, getWorld());
    }
    private Optional<RecipeEntry<SmeltingRecipe>> getCurrentRecipe2() {
        SimpleInventory inventory = new SimpleInventory(this.inventory.get(INPUTSLOT2));
        return getWorld().getRecipeManager().getFirstMatch(RecipeType.SMELTING, inventory, getWorld());
    }
    private Optional<RecipeEntry<SmeltingRecipe>> getCurrentRecipe3() {
        SimpleInventory inventory = new SimpleInventory(this.inventory.get(INPUTSLOT3));
        return getWorld().getRecipeManager().getFirstMatch(RecipeType.SMELTING, inventory, getWorld());
    }

    private Optional<RecipeEntry<SmeltingRecipe>> getCurrentRecipe4() {
        SimpleInventory inventory = new SimpleInventory(this.inventory.get(INPUTSLOT4));
        return getWorld().getRecipeManager().getFirstMatch(RecipeType.SMELTING, inventory, getWorld());
    }



    private boolean canInsertOutputItem1(Item item) {
        return this.getStack(OUTPUTSLOT1).getItem() == item || this.getStack(OUTPUTSLOT1).isEmpty();
    }

    private boolean canInsertOutputAmount1(ItemStack result) {
        return this.getStack(OUTPUTSLOT1).getCount() + result.getCount() <= getStack(OUTPUTSLOT1).getMaxCount();
    }
    private boolean canInsertOutputItem2(Item item) {
        return this.getStack(OUTPUTSLOT2).getItem() == item || this.getStack(OUTPUTSLOT2).isEmpty();
    }

    private boolean canInsertOutputAmount2(ItemStack result) {
        return this.getStack(OUTPUTSLOT2).getCount() + result.getCount() <= getStack(OUTPUTSLOT2).getMaxCount();
    }
    private boolean canInsertOutputItem3(Item item) {
        return this.getStack(OUTPUTSLOT3).getItem() == item || this.getStack(OUTPUTSLOT3).isEmpty();
    }

    private boolean canInsertOutputAmount3(ItemStack result) {
        return this.getStack(OUTPUTSLOT3).getCount() + result.getCount() <= getStack(OUTPUTSLOT3).getMaxCount();
    }
    private boolean canInsertOutputItem4(Item item) {
        return this.getStack(OUTPUTSLOT4).getItem() == item || this.getStack(OUTPUTSLOT4).isEmpty();
    }

    private boolean canInsertOutputAmount4(ItemStack result) {
        return this.getStack(OUTPUTSLOT4).getCount() + result.getCount() <= getStack(OUTPUTSLOT4).getMaxCount();
    }
}