package net.x_j0nnay_x.simpeladdmod.block.entity;


import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeEntry;
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
import net.x_j0nnay_x.simpeladdmod.block.custom.GrinderBlock;
import net.x_j0nnay_x.simpeladdmod.block.custom.GrinderBlock_upgrade;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.recipe.GrinderRecipe;

import net.x_j0nnay_x.simpeladdmod.screen.grinder.GrinderMenu;
import net.x_j0nnay_x.simpeladdmod.screen.grinder_up.GrinderMenu_up;
import net.x_j0nnay_x.simpeladdmod.until.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.stream.IntStream;

public class GrinderBlockEntity_upgrade extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory, SidedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(11, ItemStack.EMPTY);

    public static int GRINDERSLOT = 0;
    public static int INPUTSLOT1 = 1;
    public static int INPUTSLOT2 = 2;
    public static int INPUTSLOT3 = 3;
    public static int INPUTSLOT4 = 4;
    public static int OUTPUTSLOT1 = 5;
    public static int OUTPUTSLOT2 = 6;
    public static int OUTPUTSLOT3 = 7;
    public static int OUTPUTSLOT4 = 8;
    public  static int UPGRADESLOT = 9;
    public  static int BOOSTSLOT = 10;

    protected final PropertyDelegate data;
    private int progress1 = 0;
    private int progress2 = 0;
    private int progress3 = 0;
    private int progress4 = 0;
    private int maxProgress;
    private int grindsleft = 0 ;
    private int maxGrinds = 20;
    private int grindEff = 5;
    private int hasBoost = 0;
    public GrinderBlockEntity_upgrade(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.GRINDER_UP, pPos, pBlockState);
        this.data = new PropertyDelegate() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> GrinderBlockEntity_upgrade.this.progress1;
                    case 1 -> GrinderBlockEntity_upgrade.this.maxProgress;
                    case 2 -> GrinderBlockEntity_upgrade.this.grindsleft;
                    case 3 -> GrinderBlockEntity_upgrade.this.maxGrinds;
                    case 4 -> GrinderBlockEntity_upgrade.this.grindEff;
                    case 5 -> GrinderBlockEntity_upgrade.this.hasBoost;
                    case 6 -> GrinderBlockEntity_upgrade.this.progress2;
                    case 7 -> GrinderBlockEntity_upgrade.this.progress3;
                    case 8 -> GrinderBlockEntity_upgrade.this.progress4;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> GrinderBlockEntity_upgrade.this.progress1 = pValue;
                    case 1 -> GrinderBlockEntity_upgrade.this.maxProgress = pValue;
                    case 2 -> GrinderBlockEntity_upgrade.this.grindsleft = pValue;
                    case 3 -> GrinderBlockEntity_upgrade.this.maxGrinds = pValue;
                    case 4 -> GrinderBlockEntity_upgrade.this.grindEff = pValue;
                    case 5 -> GrinderBlockEntity_upgrade.this.hasBoost = pValue;
                    case 6 -> GrinderBlockEntity_upgrade.this.progress2 = pValue;
                    case 7 -> GrinderBlockEntity_upgrade.this.progress3 = pValue;
                    case 8 -> GrinderBlockEntity_upgrade.this.progress4 = pValue;

                }
            }

            @Override
            public int size() {
                return 9;
            }
        };
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.simpeladdmod.grinder_block_up");
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return  new GrinderMenu_up(syncId, playerInventory, this, this.data);
    }
    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 11);
        super.markDirty();
    }
    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
    @Override
    public void readNbt(NbtCompound compound) {
        super.readNbt(compound);
        Inventories.readNbt(compound, inventory);
        progress1 = compound.getInt("grinder_progress1");
        progress2 = compound.getInt("grinder_progress2");
        progress3 = compound.getInt("grinder_progress3");
        progress4 = compound.getInt("grinder_progress4");
        grindsleft = compound.getInt("grinder_grinds_left");
        grindEff = compound.getInt("grinder_effec");

    }

    @Override
    public void writeNbt(NbtCompound compound) {
        super.writeNbt(compound);
        Inventories.writeNbt(compound, inventory);
        compound.putInt("grinder_progress1", progress1);
        compound.putInt("grinder_progress2", progress2);
        compound.putInt("grinder_progress3", progress3);
        compound.putInt("grinder_progress4", progress4);
        compound.putInt("grinder_grinds_left", grindsleft);
        compound.putInt("grinder_effec", grindEff);

    }
    @Override
    public boolean canInsert(int index, ItemStack stack, @Nullable Direction direction) {
        return (
                (direction == Direction.EAST || direction == Direction.WEST || direction == Direction.SOUTH || direction == Direction.NORTH) &&
                        (index == INPUTSLOT1 || index == INPUTSLOT2 || index == INPUTSLOT3 || index == INPUTSLOT4) ||
                direction == Direction.UP && index == GRINDERSLOT
        );
    }

    @Override
    public boolean canExtract(int index, ItemStack stack, Direction direction) {
        return (direction == Direction.DOWN && (index == OUTPUTSLOT1 || index == OUTPUTSLOT2 || index == OUTPUTSLOT3 || index == OUTPUTSLOT4));
    }
    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if (slot == GRINDERSLOT) {
            return stack.getItem() == ModItems.GRINDERHEAD;
        }
        if (slot == INPUTSLOT1 || slot == INPUTSLOT2 || slot == INPUTSLOT3 || slot == INPUTSLOT4) {
            return  stack.isIn(ModTags.Items.CANGRIND);
        }

        return false;
    }


// processing

    public void tick(World pLevel, BlockPos pPos, BlockState pState){
        if(inventory.get(BOOSTSLOT).isOf(ModItems.BOOSTUPGRADE)){
            this.hasBoost = 1;
        }
        if (inventory.get(BOOSTSLOT).isEmpty()){
            this.hasBoost = 0;
        }
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
        pState = (BlockState)pState.with(GrinderBlock_upgrade.WORKING, isWorking());
        pLevel.setBlockState(pPos, pState, Block.NOTIFY_ALL);
        if(grindsleft > 0){
                if(hasRecipe1()){
                    increaseCraftingProgress1();
                        if(hasProgressFinished1()){
                            useGrind();
                            craftItem1();
                            resetProgress1();
                        }
                }if(hasRecipe2()){
                increaseCraftingProgress2();
                if(hasProgressFinished2()){
                    useGrind();
                    craftItem2();
                    resetProgress2();
                }
            }if(hasRecipe3()){
                increaseCraftingProgress3();
                if(hasProgressFinished3()){
                    useGrind();
                    craftItem3();
                    resetProgress3();
                }
            }if(hasRecipe4()){
                increaseCraftingProgress4();
                if(hasProgressFinished4()){
                    useGrind();
                    craftItem4();
                    resetProgress4();
                }
            }
        }else{
            resetGrinds();
            resetProgressAll();
            }
    }
    private void resetGrindEff(){
        grindEff = 5;
    }
    private void useGrind(){
        if (inventory.get(BOOSTSLOT).isOf(ModItems.BOOSTUPGRADE)){
            if (grindEff > 0) {
                grindEff--;
            }else {
                grindsleft--;
                resetGrindEff();
            }
        }else {
            grindsleft--;
        }
    }
    private void resetGrinds() {
        if(inventory.get(GRINDERSLOT).isOf(ModItems.GRINDERHEAD)){
            if(inventory.get(GRINDERSLOT).getDamage() >= inventory.get(GRINDERSLOT).getMaxDamage()){
                inventory.set(GRINDERSLOT, ItemStack.EMPTY);
            }else{
                inventory.get(GRINDERSLOT).setDamage(inventory.get(GRINDERSLOT).getDamage() + 1);
                grindsleft = maxGrinds;
            }
        }else {
            grindsleft = 0;
        }
    }
    private boolean hasItemInFirtsSlot(){
        return this.getStack(INPUTSLOT1).getCount() >= 2;
    }
    private boolean hasItemInSecondSlot(){
        return this.getStack(INPUTSLOT2).getCount() >= 2;
    }
    private boolean hasItemInThirdSlot(){
        return this.getStack(INPUTSLOT3).getCount() >= 2;
    }

    private boolean areStackEqual1to2(){
        return this.getStack(INPUTSLOT1).getCount() <= this.getStack(INPUTSLOT2).getCount();
    }
    private boolean areStackEqual2to3(){
        return this.getStack(INPUTSLOT2).getCount() <= this.getStack(INPUTSLOT3).getCount();
    }
    private boolean areStackEqual3to4(){
        return this.getStack(INPUTSLOT3).getCount() <= this.getStack(INPUTSLOT4).getCount();
    }
    private boolean isItemSameSlot2(){
        return this.getStack(INPUTSLOT1).getItem() == this.getStack(INPUTSLOT2).getItem() || this.getStack(INPUTSLOT2).isEmpty();
    }
    private boolean isItemSameSlot3(){
        return this.getStack(INPUTSLOT2).getItem() == this.getStack(INPUTSLOT3).getItem() || this.getStack(INPUTSLOT3).isEmpty();
    }

    private boolean isItemSameSlot4(){
        return this.getStack(INPUTSLOT3).getItem() == this.getStack(INPUTSLOT4).getItem() || this.getStack(INPUTSLOT4).isEmpty();
    }
    private void moveItemFrom1to2(){
        if(isItemSameSlot2()){
            ItemStack item = this.getStack(INPUTSLOT1);
            this.removeStack(INPUTSLOT1, 1);
            this.setStack(INPUTSLOT2, new ItemStack(item.getItem(),
                    this.getStack(INPUTSLOT2).getCount() + 1));}

    }
    private void moveItemFrom2to3(){
        if(isItemSameSlot3()){
            ItemStack item = this.getStack(INPUTSLOT2);
            this.removeStack(INPUTSLOT2, 1);
            this.setStack(INPUTSLOT3, new ItemStack(item.getItem(),
                    this.getStack(INPUTSLOT3).getCount() + 1));}

    }

    private void moveItemFrom3to4(){
        if(isItemSameSlot4()){
            ItemStack item = this.getStack(INPUTSLOT3);
            this.removeStack(INPUTSLOT3, 1);
            this.setStack(INPUTSLOT4, new ItemStack(item.getItem(),
                    this.getStack(INPUTSLOT4).getCount() + 1));}
    }
    private boolean canWork(){
        return  hasRecipe1()||hasRecipe2()||hasRecipe3()||hasRecipe4();
    }
    private boolean isWorking() {
        if (canWork()){
            if(grindsleft > 0 || inventory.get(GRINDERSLOT).isOf(ModItems.GRINDERHEAD)){
                return true;
            }
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
        Optional<RecipeEntry<GrinderRecipe>> recipe = getCurrentRecipe1();
        this.removeStack(INPUTSLOT1, 1);

        this.setStack(OUTPUTSLOT1, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(OUTPUTSLOT1).getCount() + recipe.get().value().getResult(null).getCount()));
    }
    private void craftItem2() {
        Optional<RecipeEntry<GrinderRecipe>> recipe = getCurrentRecipe2();
        this.removeStack(INPUTSLOT2, 1);

        this.setStack(OUTPUTSLOT2, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(OUTPUTSLOT2).getCount() + recipe.get().value().getResult(null).getCount()));
    }
    private void craftItem3() {
        Optional<RecipeEntry<GrinderRecipe>> recipe = getCurrentRecipe3();
        this.removeStack(INPUTSLOT3, 1);

        this.setStack(OUTPUTSLOT3, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(OUTPUTSLOT3).getCount() + recipe.get().value().getResult(null).getCount()));

    }
    private void craftItem4() {
        Optional<RecipeEntry<GrinderRecipe>> recipe = getCurrentRecipe4();
        this.removeStack(INPUTSLOT4, 1);

        this.setStack(OUTPUTSLOT4, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(OUTPUTSLOT4).getCount() + recipe.get().value().getResult(null).getCount()));

    }
    private boolean hasRecipe1() {
        Optional<RecipeEntry<GrinderRecipe>> recipe = getCurrentRecipe1();

        return recipe.isPresent() && canInsertOutputAmount1(recipe.get().value().getResult(null))
                && canInsertOutputItem1(recipe.get().value().getResult(null).getItem());
}
    private boolean hasRecipe2() {
        Optional<RecipeEntry<GrinderRecipe>> recipe = getCurrentRecipe2();

        return recipe.isPresent() && canInsertOutputAmount2(recipe.get().value().getResult(null))
                && canInsertOutputItem2(recipe.get().value().getResult(null).getItem());
    }
    private boolean hasRecipe3() {
        Optional<RecipeEntry<GrinderRecipe>> recipe = getCurrentRecipe3();

        return recipe.isPresent() && canInsertOutputAmount3(recipe.get().value().getResult(null))
                && canInsertOutputItem3(recipe.get().value().getResult(null).getItem());
    }
    private boolean hasRecipe4() {
        Optional<RecipeEntry<GrinderRecipe>> recipe = getCurrentRecipe4();

        return recipe.isPresent() && canInsertOutputAmount4(recipe.get().value().getResult(null))
                && canInsertOutputItem4(recipe.get().value().getResult(null).getItem());
    }
    private Optional<RecipeEntry<GrinderRecipe>> getCurrentRecipe1() {
        SimpleInventory inventory = new SimpleInventory(this.inventory.get(INPUTSLOT1));
        return getWorld().getRecipeManager().getFirstMatch(GrinderRecipe.Type.INSTANCE, inventory, getWorld());
    }
    private Optional<RecipeEntry<GrinderRecipe>> getCurrentRecipe2() {
        SimpleInventory inventory = new SimpleInventory(this.inventory.get(INPUTSLOT2));
        return getWorld().getRecipeManager().getFirstMatch(GrinderRecipe.Type.INSTANCE, inventory, getWorld());
    }
    private Optional<RecipeEntry<GrinderRecipe>> getCurrentRecipe3() {
        SimpleInventory inventory = new SimpleInventory(this.inventory.get(INPUTSLOT3));
        return getWorld().getRecipeManager().getFirstMatch(GrinderRecipe.Type.INSTANCE, inventory, getWorld());
    }
    private Optional<RecipeEntry<GrinderRecipe>> getCurrentRecipe4() {
        SimpleInventory inventory = new SimpleInventory(this.inventory.get(INPUTSLOT4));
        return getWorld().getRecipeManager().getFirstMatch(GrinderRecipe.Type.INSTANCE, inventory, getWorld());
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