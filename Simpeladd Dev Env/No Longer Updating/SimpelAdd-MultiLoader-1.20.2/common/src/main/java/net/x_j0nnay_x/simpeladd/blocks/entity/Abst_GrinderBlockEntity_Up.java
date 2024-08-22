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
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.blocks.Abst_GrinderBlock_Up;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import net.x_j0nnay_x.simpeladd.recipe.GrinderRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class Abst_GrinderBlockEntity_Up extends RandomizableContainerBlockEntity implements WorldlyContainer {

    protected NonNullList<ItemStack> stacks = NonNullList.withSize(11, ItemStack.EMPTY);

    public static int GRINDERSLOT = 0;
    public static int INPUTSLOT1 = 1;
    public static int INPUTSLOT2 = 2;
    public static int INPUTSLOT3 = 3;
    public static int INPUTSLOT4 = 4;
    public static int OUTPUTSLOT1 = 5;
    public static int OUTPUTSLOT2 = 6;
    public static int OUTPUTSLOT3 = 7;
    public static int OUTPUTSLOT4 = 8;
    public static int UPGRADESLOT = 9;
    public static int BOOSTSLOT = 10;
    private static final int[] SLOTS_FOR_UP = new int[]{GRINDERSLOT};
    private static final int[] SLOTS_FOR_DOWN = new int[]{OUTPUTSLOT1, OUTPUTSLOT2, OUTPUTSLOT3, OUTPUTSLOT4};
    private static final int[] SLOTS_FOR_SIDES = new int[]{INPUTSLOT1, INPUTSLOT2, INPUTSLOT3, INPUTSLOT4};
    protected final ContainerData data;
    private int progress1 = 0;
    private int progress2 = 0;
    private int progress3 = 0;
    private int progress4 = 0;
    private int maxProgress;
    private int grindsleft = 0 ;
    private int maxGrinds = 3;
    private int grindEff = 5;
    private int hasBoost = 0;


    protected Abst_GrinderBlockEntity_Up(BlockEntityType<?> $$0, BlockPos $$1, BlockState $$2) {
        super($$0, $$1, $$2);

        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> Abst_GrinderBlockEntity_Up.this.progress1;
                    case 1 -> Abst_GrinderBlockEntity_Up.this.maxProgress;
                    case 2 -> Abst_GrinderBlockEntity_Up.this.grindsleft;
                    case 3 -> Abst_GrinderBlockEntity_Up.this.maxGrinds;
                    case 4 -> Abst_GrinderBlockEntity_Up.this.grindEff;
                    case 5 -> Abst_GrinderBlockEntity_Up.this.hasBoost;
                    case 6 -> Abst_GrinderBlockEntity_Up.this.progress2;
                    case 7 -> Abst_GrinderBlockEntity_Up.this.progress3;
                    case 8 -> Abst_GrinderBlockEntity_Up.this.progress4;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> Abst_GrinderBlockEntity_Up.this.progress1 = pValue;
                    case 1 -> Abst_GrinderBlockEntity_Up.this.maxProgress = pValue;
                    case 2 -> Abst_GrinderBlockEntity_Up.this.grindsleft = pValue;
                    case 3 -> Abst_GrinderBlockEntity_Up.this.maxGrinds = pValue;
                    case 4 -> Abst_GrinderBlockEntity_Up.this.grindEff = pValue;
                    case 5 -> Abst_GrinderBlockEntity_Up.this.hasBoost = pValue;
                    case 6 -> Abst_GrinderBlockEntity_Up.this.progress2 = pValue;
                    case 7 -> Abst_GrinderBlockEntity_Up.this.progress3 = pValue;
                    case 8 -> Abst_GrinderBlockEntity_Up.this.progress4 = pValue;
                }
            }

            @Override
            public int getCount() {
                return 9;
            }
        };
    }



    @Override
    public void load(CompoundTag $$0) {
        super.load($$0);
        this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems($$0, this.stacks);
        this.progress1 = $$0.getShort(SimpelAddMod.MODCUSTOM +"upgraded_grinder_progress1");
        this.progress2 = $$0.getShort(SimpelAddMod.MODCUSTOM +"upgraded_grinder_progress2");
        this.progress3 = $$0.getShort(SimpelAddMod.MODCUSTOM +"upgraded_grinder_progress3");
        this.progress4 = $$0.getShort(SimpelAddMod.MODCUSTOM +"upgraded_grinder_progress4");
        this.grindsleft = $$0.getShort(SimpelAddMod.MODCUSTOM +"upgraded_grinder_grinds_left");
        this.grindEff = $$0.getShort(SimpelAddMod.MODCUSTOM +"upgraded_grinder_effec");

    }

    @Override
    protected void saveAdditional(CompoundTag $$0) {
        super.saveAdditional($$0);
        $$0.putShort(SimpelAddMod.MODCUSTOM +"upgraded_grinder_progress1", (short)this.progress1);
        $$0.putShort(SimpelAddMod.MODCUSTOM +"upgraded_grinder_progress2", (short)this.progress2);
        $$0.putShort(SimpelAddMod.MODCUSTOM +"upgraded_grinder_progress3", (short)this.progress3);
        $$0.putShort(SimpelAddMod.MODCUSTOM +"upgraded_grinder_progress4", (short)this.progress4);
        $$0.putShort(SimpelAddMod.MODCUSTOM +"upgraded_grinder_grinds_left", (short)this.grindsleft);
        $$0.putShort(SimpelAddMod.MODCUSTOM +"upgraded_grinder_effec", (short)this.grindEff);
        ContainerHelper.saveAllItems($$0, this.stacks);
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack var2, @Nullable Direction direction) {
        return (
                (direction == Direction.EAST || direction == Direction.WEST || direction == Direction.SOUTH || direction == Direction.NORTH) &&
                        (index == INPUTSLOT1 || index == INPUTSLOT2 || index == INPUTSLOT3 || index == INPUTSLOT4)||
                        direction == Direction.UP && (index == GRINDERSLOT)
        );
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack var2, Direction direction) {
        return (direction == Direction.DOWN && (index == OUTPUTSLOT1 || index == OUTPUTSLOT2 || index == OUTPUTSLOT3 || index == OUTPUTSLOT4));
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
        return Component.translatable("block.simpeladdmod.grinder_block_up");
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

    public void grinderUpTick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(stacks.get(BOOSTSLOT).is(ModItems.BOOSTUPGRADE)){
            this.hasBoost = 1;
        }
        if (stacks.get(BOOSTSLOT).isEmpty()){
            this.hasBoost = 0;
        }
        if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_1)) {
            this.maxProgress = 20;
        }if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_2)) {
            this.maxProgress = 12;
        }if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_3)) {
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
        pState = pState.setValue(Abst_GrinderBlock_Up.WORKING, Boolean.valueOf(isWorking()));
        pLevel.setBlock(pPos, pState, 3);
        if(grindsleft > 0){
            if(hasRecipe1()){
                increaseCraftingProgress1();
                setChanged(pLevel, pPos, pState);
                if(hasProgressFinished1()){
                    useGrind();
                    craftItem1();
                    resetProgress1();
                }
            }if(hasRecipe2()){
                increaseCraftingProgress2();
                setChanged(pLevel, pPos, pState);
                if(hasProgressFinished2()){
                    useGrind();
                    craftItem2();
                    resetProgress2();
                }
            }if(hasRecipe3()){
                increaseCraftingProgress3();
                setChanged(pLevel, pPos, pState);
                if(hasProgressFinished3()){
                    useGrind();
                    craftItem3();
                    resetProgress3();
                }
            }if(hasRecipe4()){
                increaseCraftingProgress4();
                setChanged(pLevel, pPos, pState);
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
        this.grindEff = 5;
    }
    private void useGrind(){
        if (this.stacks.get(BOOSTSLOT).is(ModItems.BOOSTUPGRADE)){
            if (this.grindEff > 0) {
                this.grindEff--;
            }else {
                this.grindsleft--;
                resetGrindEff();
            }
        }else {
            this.grindsleft--;
        }
    }
    private void resetGrinds() {
        if(this.stacks.get(GRINDERSLOT).is(ModItems.GRINDERHEAD)){
            if(this.stacks.get(GRINDERSLOT).getDamageValue() >= this.stacks.get(GRINDERSLOT).getMaxDamage()){
                this.stacks.set(GRINDERSLOT, ItemStack.EMPTY);
            }else{
                this.stacks.get(GRINDERSLOT).setDamageValue(this.stacks.get(GRINDERSLOT).getDamageValue() + 1);
                this.grindsleft = this.maxGrinds;
            }
        }else {
            this.grindsleft = 0;
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
        if (canWork()){
            if(this.grindsleft > 0 || stacks.get(GRINDERSLOT).is(ModItems.GRINDERHEAD)){
                return true;
            }
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
        Optional<RecipeHolder<GrinderRecipe>> recipe = getCurrentRecipe1();
        ItemStack result = recipe.get().value().getResultItem(null);
        this.removeItem(INPUTSLOT1, 1);
        this.stacks.set(OUTPUTSLOT1, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT1).getCount() + result.getCount()));

    }
    private void craftItem2() {
        Optional<RecipeHolder<GrinderRecipe>> recipe = getCurrentRecipe2();
        ItemStack result = recipe.get().value().getResultItem(null);
        this.removeItem(INPUTSLOT2, 1);
        this.stacks.set(OUTPUTSLOT2, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT2).getCount() + result.getCount()));

    }
    private void craftItem3() {
        Optional<RecipeHolder<GrinderRecipe>> recipe = getCurrentRecipe3();
        ItemStack result = recipe.get().value().getResultItem(null);
        this.removeItem(INPUTSLOT3, 1);
        this.stacks.set(OUTPUTSLOT3, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT3).getCount() + result.getCount()));

    }
    private void craftItem4() {
        Optional<RecipeHolder<GrinderRecipe>> recipe = getCurrentRecipe4();
        ItemStack result = recipe.get().value().getResultItem(null);
        this.removeItem(INPUTSLOT4, 1);
        this.stacks.set(OUTPUTSLOT4, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT4).getCount() + result.getCount()));

    }
    private boolean hasRecipe1() {
        Optional<RecipeHolder<GrinderRecipe>> recipe = getCurrentRecipe1();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().value().getResultItem(getLevel().registryAccess());

        return canInsertOutputAmount1(result.getCount()) && canInsertOutputItem1(result.getItem());
    }
    private boolean hasRecipe2() {
        Optional<RecipeHolder<GrinderRecipe>> recipe = getCurrentRecipe2();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().value().getResultItem(getLevel().registryAccess());

        return canInsertOutputAmount2(result.getCount()) && canInsertOutputItem2(result.getItem());
    }
    private boolean hasRecipe3() {
        Optional<RecipeHolder<GrinderRecipe>> recipe = getCurrentRecipe3();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().value().getResultItem(getLevel().registryAccess());

        return canInsertOutputAmount3(result.getCount()) && canInsertOutputItem3(result.getItem());
    }
    private boolean hasRecipe4() {
        Optional<RecipeHolder<GrinderRecipe>> recipe = getCurrentRecipe4();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().value().getResultItem(getLevel().registryAccess());

        return canInsertOutputAmount4(result.getCount()) && canInsertOutputItem4(result.getItem());
    }
    private Optional<RecipeHolder<GrinderRecipe>> getCurrentRecipe1() {
        SimpleContainer inventory = new SimpleContainer(this.stacks.get(INPUTSLOT1));
        return this.level.getRecipeManager().getRecipeFor(GrinderRecipe.Type.INSTANCE, inventory, level);
    }
    private Optional<RecipeHolder<GrinderRecipe>> getCurrentRecipe2() {
        SimpleContainer inventory = new SimpleContainer(this.stacks.get(INPUTSLOT2));
        return this.level.getRecipeManager().getRecipeFor(GrinderRecipe.Type.INSTANCE, inventory, level);
    }
    private Optional<RecipeHolder<GrinderRecipe>> getCurrentRecipe3() {
        SimpleContainer inventory = new SimpleContainer(this.stacks.get(INPUTSLOT3));
        return this.level.getRecipeManager().getRecipeFor(GrinderRecipe.Type.INSTANCE, inventory, level);
    }
    private Optional<RecipeHolder<GrinderRecipe>> getCurrentRecipe4() {
        SimpleContainer inventory = new SimpleContainer(this.stacks.get(INPUTSLOT4));
        return this.level.getRecipeManager().getRecipeFor(GrinderRecipe.Type.INSTANCE, inventory, level);
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