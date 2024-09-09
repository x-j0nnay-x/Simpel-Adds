package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.tags.TagKey;
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
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.blocks.Abst_GrindFactoryBlock;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import net.x_j0nnay_x.simpeladd.core.ModTags;
import net.x_j0nnay_x.simpeladd.recipe.GrindFactoryRecipe;
import net.x_j0nnay_x.simpeladd.recipe.GrinderRecipe;
import org.jetbrains.annotations.Nullable;
import static net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity.getFuel;

public abstract class Abst_GrindFactoryBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {

    private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCookingRecipe> recipeCheckSmelting;
    private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends GrinderRecipe> recipeCheckGrinding;
    private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends GrindFactoryRecipe> recipeCheckGrindFactory;
    protected NonNullList<ItemStack> stacks = NonNullList.withSize(18, ItemStack.EMPTY);
    public static int FUELSLOT = 0;
    public static int GRINDERSLOT = 1;
    public static int GRINDERINSLOT1 = 2;
    public static int GRINDERINSLOT2 = 3;
    public static int GRINDERINSLOT3 = 4;
    public static int GRINDERINSLOT4 = 5;
    public static int FURNACEINSLOT1 = 6;
    public static int FURNACEINSLOT2 = 7;
    public static int FURNACEINSLOT3 = 8;
    public static int FURNACEINSLOT4 = 9;
    public static int OUTPUTSLOT1 = 10;
    public static int OUTPUTSLOT2 = 11;
    public static int OUTPUTSLOT3 = 12;
    public static int OUTPUTSLOT4 = 13;
    public static int UPGRADESLOT = 14;
    public static int BOOSTSLOT = 15;
    public static int XPBOTTLESLOT = 16;
    public static int XPBOOSTSLOT = 17;
    private static final int[] SLOTS_FOR_UP = new int[]{FUELSLOT, GRINDERSLOT};
    private static final int[] SLOTS_FOR_DOWN = new int[]{FUELSLOT, OUTPUTSLOT1, OUTPUTSLOT2, OUTPUTSLOT3, OUTPUTSLOT4, FURNACEINSLOT1, FURNACEINSLOT2, FURNACEINSLOT3, FURNACEINSLOT4,};
    private static final int[] SLOTS_FOR_SIDES = new int[]{XPBOTTLESLOT, GRINDERINSLOT1, GRINDERINSLOT2, GRINDERINSLOT3, GRINDERINSLOT4};
    protected final ContainerData data;
    private int grindProg1 = 0;
    private int grindProg2 = 0;
    private int grindProg3 = 0;
    private int grindProg4 = 0;
    private int furnProg1 = 0;
    private int furnProg2 = 0;
    private int furnProg3 = 0;
    private int furnProg4 = 0;
    private int maxProgress;
    private int xpBoost;
    private int fuelLevel = 0 ;
    private int storedXP = 0;
    private int maxXP = 10000;
    private int grindsleft = 0 ;
    private int maxGrinds = 8;
    private int grindEff = 5;
    private int hasBoost = 0;
    private int grindXP = 10;

    protected Abst_GrindFactoryBlockEntity(BlockEntityType<?> $$0, BlockPos $$1, BlockState $$2) {
        super($$0, $$1, $$2);
        this.recipeCheckGrinding = RecipeManager.createCheck(GrinderRecipe.Type.INSTANCE);
        this.recipeCheckSmelting = RecipeManager.createCheck(RecipeType.SMELTING);
        this.recipeCheckGrindFactory = RecipeManager.createCheck(GrindFactoryRecipe.Type.INSTANCE);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> Abst_GrindFactoryBlockEntity.this.maxProgress;
                    case 1 -> Abst_GrindFactoryBlockEntity.this.grindsleft;
                    case 2 -> Abst_GrindFactoryBlockEntity.this.fuelLevel;
                    case 3 -> Abst_GrindFactoryBlockEntity.this.storedXP;
                    case 4 -> Abst_GrindFactoryBlockEntity.this.hasBoost;
                    case 5 -> Abst_GrindFactoryBlockEntity.this.grindProg1;
                    case 6 -> Abst_GrindFactoryBlockEntity.this.grindProg2;
                    case 7 -> Abst_GrindFactoryBlockEntity.this.grindProg3;
                    case 8 -> Abst_GrindFactoryBlockEntity.this.grindProg4;
                    case 9 -> Abst_GrindFactoryBlockEntity.this.furnProg1;
                    case 10 -> Abst_GrindFactoryBlockEntity.this.furnProg2;
                    case 11 -> Abst_GrindFactoryBlockEntity.this.furnProg3;
                    case 12 -> Abst_GrindFactoryBlockEntity.this.furnProg4;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> Abst_GrindFactoryBlockEntity.this.maxProgress = pValue;
                    case 1 -> Abst_GrindFactoryBlockEntity.this.grindsleft = pValue;
                    case 2 -> Abst_GrindFactoryBlockEntity.this.fuelLevel = pValue;
                    case 3 -> Abst_GrindFactoryBlockEntity.this.storedXP = pValue;
                    case 4 -> Abst_GrindFactoryBlockEntity.this.hasBoost = pValue;
                    case 5 -> Abst_GrindFactoryBlockEntity.this.grindProg1 = pValue;
                    case 6 -> Abst_GrindFactoryBlockEntity.this.grindProg2 = pValue;
                    case 7 -> Abst_GrindFactoryBlockEntity.this.grindProg3 = pValue;
                    case 8 -> Abst_GrindFactoryBlockEntity.this.grindProg4 = pValue;
                    case 9 -> Abst_GrindFactoryBlockEntity.this.furnProg1 = pValue;
                    case 10 -> Abst_GrindFactoryBlockEntity.this.furnProg2 = pValue;
                    case 11 -> Abst_GrindFactoryBlockEntity.this.furnProg3 = pValue;
                    case 12 -> Abst_GrindFactoryBlockEntity.this.furnProg4 = pValue;
                }
            }

            @Override
            public int getCount() {
                return 13;
            }
        };
    }

    @Override
    public void loadAdditional(CompoundTag $$0, HolderLookup.Provider pRegistries) {
        super.loadAdditional($$0, pRegistries);
        this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems($$0, this.stacks, pRegistries);
        this.grindProg1 = $$0.getInt(SimpelAddMod.MODCUSTOM +"grind_factory_grind_prog1");
        this.grindProg2 = $$0.getInt(SimpelAddMod.MODCUSTOM +"grind_factory_grind_prog2");
        this.grindProg3 = $$0.getInt(SimpelAddMod.MODCUSTOM +"grind_factory_grind_prog3");
        this.grindProg4 = $$0.getInt(SimpelAddMod.MODCUSTOM +"grind_factory_grind_prog4");
        this.furnProg1 = $$0.getInt(SimpelAddMod.MODCUSTOM +"grind_factory_furn_prog1");
        this.furnProg2 = $$0.getInt(SimpelAddMod.MODCUSTOM +"grind_factory_furn_prog2");
        this.furnProg3 = $$0.getInt(SimpelAddMod.MODCUSTOM +"grind_factory_furn_prog3");
        this.furnProg4 = $$0.getInt(SimpelAddMod.MODCUSTOM +"grind_factory_furn_prog4");
        this.fuelLevel = $$0.getInt(SimpelAddMod.MODCUSTOM +"grind_factory_fuel_left");
        this.storedXP = $$0.getInt(SimpelAddMod.MODCUSTOM +"grind_factory_stored_xp");
        this.grindsleft = $$0.getInt(SimpelAddMod.MODCUSTOM +"grind_factory_grinds_left");
        this.grindEff = $$0.getInt(SimpelAddMod.MODCUSTOM +"grind_factory_grinder_effec");
       }

    @Override
    protected void saveAdditional(CompoundTag $$0, HolderLookup.Provider pRegistries) {
        super.saveAdditional($$0, pRegistries);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"grind_factory_grind_prog1", grindProg1);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"grind_factory_grind_prog2", grindProg2);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"grind_factory_grind_prog3", grindProg3);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"grind_factory_grind_prog4", grindProg4);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"grind_factory_furn_prog1", furnProg1);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"grind_factory_furn_prog2", furnProg2);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"grind_factory_furn_prog3", furnProg3);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"grind_factory_furn_prog4", furnProg4);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"grind_factory_fuel_left", fuelLevel);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"grind_factory_stored_xp", storedXP);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"grind_factory_grinds_left", grindsleft);
        $$0.putInt(SimpelAddMod.MODCUSTOM +"grind_factory_grinder_effec", grindEff);
        ContainerHelper.saveAllItems($$0, this.stacks, pRegistries);
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack var2, @Nullable Direction direction) {
        if(direction == Direction.EAST || direction == Direction.WEST || direction == Direction.SOUTH || direction == Direction.NORTH){
            if(index == GRINDERINSLOT1 || index == GRINDERINSLOT2 || index == GRINDERINSLOT3 || index == GRINDERINSLOT4){
                if(var2.is(ModTags.Items.CANGRIND)){
                    return true;
                }
                return false;
            }
            return false;
        }
        if(direction == Direction.UP){
            if(index == GRINDERSLOT && var2.is(ModTags.Items.GRINDERS)){
                return true;
            }
            if(index == FUELSLOT && isFuel(var2)){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack var2, Direction direction) {
        if(direction == Direction.DOWN){
             if(index == OUTPUTSLOT1 || index == OUTPUTSLOT2 || index == OUTPUTSLOT3 || index == OUTPUTSLOT4){
                 return true;
             }
             if(index == FURNACEINSLOT1 || index == FURNACEINSLOT2 || index == FURNACEINSLOT3 || index == FURNACEINSLOT4){
                 if(!var2.is(ModTags.Items.DUST)){
                     return true;
                 }
                 return false;
             }
             if(index == FUELSLOT && var2.is(Items.BUCKET)){
                 return true;
             }
            return false;
        }
        if(direction == Direction.WEST ||direction == Direction.EAST ||direction == Direction.SOUTH ||direction == Direction.NORTH){
            if(index == XPBOTTLESLOT){
                return true;
            }
            return false;
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
        return Component.translatable("block.simpeladdmod.grind_factory_block");
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
    public void grindFactoryTick(Level pLevel, BlockPos pPos, BlockState pState) {
        makeXPBottle();
        addFuel();
        splitStack();
        if(stacks.get(BOOSTSLOT).is(ModItems.BOOSTUPGRADE)){
            this.hasBoost = 1;
        }if (stacks.get(BOOSTSLOT).isEmpty()){
            this.hasBoost = 0;
        }if (this.stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_1)) {
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
        }if(!hasGrind()){
            resetGrinds();
        }
        pState = pState.setValue(Abst_GrindFactoryBlock.WORKING, Boolean.valueOf(isWorking()));
        pLevel.setBlock(pPos, pState, 3);
        resetCheck();
        grindingStep();
        smeltingStep();
    }

    private void resetCheck(){
        if(isSlotEmpty(GRINDERINSLOT1)){
            resetProgress(GRINDERINSLOT1);
        }
        if(isSlotEmpty(GRINDERINSLOT2)){
            resetProgress(GRINDERINSLOT2);
        }
        if(isSlotEmpty(GRINDERINSLOT3)){
            resetProgress(GRINDERINSLOT3);
        }
        if(isSlotEmpty(GRINDERINSLOT4)){
            resetProgress(GRINDERINSLOT4);
        }
        if(isSlotEmpty(FURNACEINSLOT1)){
            resetProgress(FURNACEINSLOT1);
        }
        if(isSlotEmpty(FURNACEINSLOT2)){
            resetProgress(FURNACEINSLOT2);
        }
        if(isSlotEmpty(FURNACEINSLOT3)){
            resetProgress(FURNACEINSLOT3);
        }
        if(isSlotEmpty(FURNACEINSLOT4)){
            resetProgress(FURNACEINSLOT4);
        }
        if(fuelLevel < 0){
            fuelLevel = 0;
        }
        if(grindsleft < 0){
            grindsleft = 0;
        }
    }

    private void grindingStep(){
            RecipeHolder<? extends GrinderRecipe> irecipeGrind1 = this.getGrindRecipeNonCached(this.stacks.get(GRINDERINSLOT1));
            RecipeHolder<? extends GrinderRecipe> irecipeGrind2 = this.getGrindRecipeNonCached(this.stacks.get(GRINDERINSLOT2));
            RecipeHolder<? extends GrinderRecipe> irecipeGrind3 = this.getGrindRecipeNonCached(this.stacks.get(GRINDERINSLOT3));
            RecipeHolder<? extends GrinderRecipe> irecipeGrind4 = this.getGrindRecipeNonCached(this.stacks.get(GRINDERINSLOT4));
            RecipeHolder<? extends GrindFactoryRecipe> irecipeGrindF1 = this.getGrindFactoryRecipeNonCached(this.stacks.get(GRINDERINSLOT1));
            RecipeHolder<? extends GrindFactoryRecipe> irecipeGrindF2 = this.getGrindFactoryRecipeNonCached(this.stacks.get(GRINDERINSLOT2));
            RecipeHolder<? extends GrindFactoryRecipe> irecipeGrindF3 = this.getGrindFactoryRecipeNonCached(this.stacks.get(GRINDERINSLOT3));
            RecipeHolder<? extends GrindFactoryRecipe> irecipeGrindF4 = this.getGrindFactoryRecipeNonCached(this.stacks.get(GRINDERINSLOT4));
        if(grindsleft > 0) {
            if (hasGrindRecipe(irecipeGrind1, GRINDERINSLOT1) || hasGrindFactoryRecipe(irecipeGrindF1, GRINDERINSLOT1)) {
                incresseProgress(GRINDERINSLOT1);
                if (hasProgressFinished(GRINDERINSLOT1)) {
                    useGrind();
                    this.storedXP += this.grindXP * xpBoost;
                    if(!isOreBlock(GRINDERINSLOT1)) {
                        craftGrindItem(irecipeGrind1, GRINDERINSLOT1);
                    }
                    if(isOreBlock(GRINDERINSLOT1)){
                        craftGrindFactoryItem(irecipeGrindF1, GRINDERINSLOT1);
                    }
                    resetProgress(GRINDERINSLOT1);
                }
            }
            if (hasGrindRecipe(irecipeGrind2, GRINDERINSLOT2) || hasGrindFactoryRecipe(irecipeGrindF2, GRINDERINSLOT2)) {
                incresseProgress(GRINDERINSLOT2);
                if (hasProgressFinished(GRINDERINSLOT2)) {
                    useGrind();
                    this.storedXP += this.grindXP * xpBoost;
                    if(!isOreBlock(GRINDERINSLOT2)) {
                        craftGrindItem(irecipeGrind2, GRINDERINSLOT2);
                    }
                    if(isOreBlock(GRINDERINSLOT2)){
                        craftGrindFactoryItem(irecipeGrindF2, GRINDERINSLOT2);
                    }
                    resetProgress(GRINDERINSLOT2);
                }
            }
            if (hasGrindRecipe(irecipeGrind3, GRINDERINSLOT3) || hasGrindFactoryRecipe(irecipeGrindF3, GRINDERINSLOT3)) {
                incresseProgress(GRINDERINSLOT3);
                if (hasProgressFinished(GRINDERINSLOT3)) {
                    useGrind();
                    this.storedXP += this.grindXP * xpBoost;
                    if(!isOreBlock(GRINDERINSLOT3)) {
                        craftGrindItem(irecipeGrind3, GRINDERINSLOT3);
                    }
                    if(isOreBlock(GRINDERINSLOT3)){
                        craftGrindFactoryItem(irecipeGrindF3, GRINDERINSLOT3);
                    }
                    resetProgress(GRINDERINSLOT3);
                }
            }
            if (hasGrindRecipe(irecipeGrind4, GRINDERINSLOT4) || hasGrindFactoryRecipe(irecipeGrindF4, GRINDERINSLOT4)) {
                incresseProgress(GRINDERINSLOT4);
                if (hasProgressFinished(GRINDERINSLOT4)) {
                    useGrind();
                    this.storedXP += this.grindXP * xpBoost;
                    if(!isOreBlock(GRINDERINSLOT4)) {
                        craftGrindItem(irecipeGrind4, GRINDERINSLOT4);
                    }
                    if(isOreBlock(GRINDERINSLOT4)){
                        craftGrindFactoryItem(irecipeGrindF4, GRINDERINSLOT4);
                    }
                    resetProgress(GRINDERINSLOT4);
                }
            }
        }
    }

    private void smeltingStep(){
        RecipeHolder<? extends AbstractCookingRecipe> irecipeFurn1 = this.getFurnRecipeNonCached(this.stacks.get(FURNACEINSLOT1));
        RecipeHolder<? extends AbstractCookingRecipe> irecipeFurn2 = this.getFurnRecipeNonCached(this.stacks.get(FURNACEINSLOT2));
        RecipeHolder<? extends AbstractCookingRecipe> irecipeFurn3 = this.getFurnRecipeNonCached(this.stacks.get(FURNACEINSLOT3));
        RecipeHolder<? extends AbstractCookingRecipe> irecipeFurn4 = this.getFurnRecipeNonCached(this.stacks.get(FURNACEINSLOT4));
        if(fuelLevel > 0) {
            if (hasFurnRecipe(irecipeFurn1, FURNACEINSLOT1)) {
                incresseProgress(FURNACEINSLOT1);
                if (hasProgressFinished(FURNACEINSLOT1)) {
                    useFuel();
                    craftFurnItem(irecipeFurn1, FURNACEINSLOT1);
                    this.storedXP += Math.round(irecipeFurn1.value().getExperience() * 2 * xpBoost);
                    resetProgress(FURNACEINSLOT1);
                }
            }
            if (hasFurnRecipe(irecipeFurn2, FURNACEINSLOT2)) {
                incresseProgress(FURNACEINSLOT2);
                if (hasProgressFinished(FURNACEINSLOT2)) {
                    useFuel();
                    craftFurnItem(irecipeFurn2, FURNACEINSLOT2);
                    this.storedXP += Math.round(irecipeFurn2.value().getExperience() * 2* xpBoost);
                    resetProgress(FURNACEINSLOT2);
                }
            }
            if (hasFurnRecipe(irecipeFurn3, FURNACEINSLOT3)) {
                incresseProgress(FURNACEINSLOT3);
                if (hasProgressFinished(FURNACEINSLOT3)) {
                    useFuel();
                    craftFurnItem(irecipeFurn3, FURNACEINSLOT3);
                    this.storedXP += Math.round(irecipeFurn3.value().getExperience() * 2* xpBoost);
                    resetProgress(FURNACEINSLOT3);
                }
            }
            if (hasFurnRecipe(irecipeFurn4, FURNACEINSLOT4)) {
                incresseProgress(FURNACEINSLOT4);
                if (hasProgressFinished(FURNACEINSLOT4)) {
                    useFuel();
                    craftFurnItem(irecipeFurn4, FURNACEINSLOT4);
                    this.storedXP += Math.round(irecipeFurn4.value().getExperience() * 3* xpBoost);
                    resetProgress(FURNACEINSLOT4);
                }
            }
        }
    }

    private boolean isblockEmpty(){
        return isSlotEmpty(GRINDERINSLOT1) && isSlotEmpty(GRINDERINSLOT2) && isSlotEmpty(GRINDERINSLOT3) && isSlotEmpty(GRINDERINSLOT4) &&
               isSlotEmpty(FURNACEINSLOT1) && isSlotEmpty(FURNACEINSLOT2) && isSlotEmpty(FURNACEINSLOT3) && isSlotEmpty(FURNACEINSLOT4) ;
    }

    private boolean hasGrind(){
        return grindsleft > 0;
    }

    private boolean hasFuel(){
        return fuelLevel > 0;
    }

    private void resetGrindEff(){
        this.grindEff = 16;
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
        if(this.stacks.get(GRINDERSLOT).is(ModTags.Items.GRINDERS)){
            if(this.stacks.get(GRINDERSLOT).getDamageValue() >= this.stacks.get(GRINDERSLOT).getMaxDamage()){
                this.stacks.set(GRINDERSLOT, ItemStack.EMPTY);
            }else{
                this.stacks.get(GRINDERSLOT).setDamageValue(this.stacks.get(GRINDERSLOT).getDamageValue() + 1);
                this.grindsleft += this.maxGrinds;
            }
        }else {
            this.grindsleft = 0;
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
        if(isFuel(this.stacks.get(FUELSLOT))){
        if(!this.stacks.get(FUELSLOT).isEmpty() && !this.stacks.get(FUELSLOT).is(Items.BUCKET)){
            fuelLevel += (int) (this.getFuelTime(this.stacks.get(FUELSLOT)) * 1.5 / 200);
            if (this.stacks.get(FUELSLOT).getItem() == (Items.LAVA_BUCKET)) {
                this.removeItem(FUELSLOT, 1);
                this.stacks.set(FUELSLOT, new ItemStack(Items.BUCKET));
            }
            else {
                this.removeItem(FUELSLOT, 1);
            }
        }}
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

    private boolean isSlotEmpty(int slot){
        return  this.stacks.get(slot).isEmpty();
    }

    private boolean isOreBlock(int slot){
        if(this.getItem(slot).is(ModTags.Items.OREBLOCKGRIND)){
            return true;
        }
        return false;
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
            if (areStacksSame(GRINDERINSLOT1 + i, GRINDERINSLOT2 + i)) {
                slotCount++;
            }
        }
        for (int i = 0; i < 3; i ++) {
            int count = this.stacks.get(GRINDERINSLOT1 + i).getCount() / slotCount;
            moveItem(GRINDERINSLOT1 + i, GRINDERINSLOT2 + i, count);
        }
    }

    private boolean isWorking() {
        if (this.grindProg1 > 0 || this.grindProg2 > 0 || this.grindProg3 > 0 || this.grindProg4 > 0 ||
            this.furnProg1 > 0 || this.furnProg2 > 0 || this.furnProg3 > 0 || this.furnProg4 > 0 && !isblockEmpty() ){
            if(hasGrind() || hasFuel()){
            return true;
            }
        }
        return false;
    }

    private void resetProgress(int slot){
        if(slot == GRINDERINSLOT1){
            this.grindProg1 = 0;
        }
        if(slot == GRINDERINSLOT2){
            this.grindProg2 = 0;
        }
        if(slot == GRINDERINSLOT3){
            this.grindProg3 = 0;
        }
        if(slot == GRINDERINSLOT4){
            this.grindProg4 = 0;
        }
        if(slot == FURNACEINSLOT1){
            this.furnProg1 = 0;
        }
        if(slot == FURNACEINSLOT2){
            this.furnProg2 = 0;
        }
        if(slot == FURNACEINSLOT3){
            this.furnProg3 = 0;
        }
        if(slot == FURNACEINSLOT4){
            this.furnProg4 = 0;
        }
    }

   private void incresseProgress(int slot){
       if(slot == GRINDERINSLOT1){
           this.grindProg1 ++;
       }
       if(slot == GRINDERINSLOT2){
           this.grindProg2 ++;
       }
       if(slot == GRINDERINSLOT3){
           this.grindProg3 ++;
       }
       if(slot == GRINDERINSLOT4){
           this.grindProg4 ++;
       }
       if(slot == FURNACEINSLOT1){
           this.furnProg1 ++;
       }
       if(slot == FURNACEINSLOT2){
           this.furnProg2 ++;
       }
       if(slot == FURNACEINSLOT3){
           this.furnProg3 ++;
       }
       if(slot == FURNACEINSLOT4){
           this.furnProg4 ++;
       }
   }

   private boolean hasProgressFinished(int slot){
       if(slot == GRINDERINSLOT1){
           return this.grindProg1 >= this.maxProgress;
       }
       if(slot == GRINDERINSLOT2){
           return this.grindProg2 >= this.maxProgress;
       }
       if(slot == GRINDERINSLOT3){
           return this.grindProg3 >= this.maxProgress;
       }
       if(slot == GRINDERINSLOT4){
           return this.grindProg4 >= this.maxProgress;
       }
       if(slot == FURNACEINSLOT1){
           return this.furnProg1 >= this.maxProgress;
       }
       if(slot == FURNACEINSLOT2){
           return this.furnProg2 >= this.maxProgress;
       }
       if(slot == FURNACEINSLOT3){
           return this.furnProg3 >= this.maxProgress;
       }
       if(slot == FURNACEINSLOT4){
           return this.furnProg4 >= this.maxProgress;
       }
       return false;
   }

    private RecipeHolder<? extends AbstractCookingRecipe> getFurnRecipeNonCached(ItemStack itemStack) {
        return this.recipeCheckSmelting.getRecipeFor(new SingleRecipeInput(itemStack), this.level).orElse(null);
    }

    private  void craftFurnItem(@Nullable RecipeHolder<?> recipe, int slot){
        int outputSlot = slot + 4;
        if (recipe != null && this.hasFurnRecipe(recipe, slot)) {
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

    public  boolean hasFurnRecipe(@Nullable RecipeHolder<?> recipe, int slot){
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

    private RecipeHolder<? extends GrinderRecipe> getGrindRecipeNonCached(ItemStack itemStack) {
        return this.recipeCheckGrinding.getRecipeFor(new SingleRecipeInput(itemStack), this.level).orElse(null);
    }

    private  void craftGrindItem(@Nullable RecipeHolder<?> recipe, int slot){
        int outputSlot = slot + 4;
        if (recipe != null && this.hasGrindRecipe(recipe, slot)) {
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

    public  boolean hasGrindRecipe(@Nullable RecipeHolder<?> recipe, int slot){
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

    private RecipeHolder<? extends GrindFactoryRecipe> getGrindFactoryRecipeNonCached(ItemStack itemStack) {
        return this.recipeCheckGrindFactory.getRecipeFor(new SingleRecipeInput(itemStack), this.level).orElse(null);
    }

    private  void craftGrindFactoryItem(@Nullable RecipeHolder<?> recipe, int slot){
        int outputSlot = slot + 4;
        if (recipe != null && this.hasGrindFactoryRecipe(recipe, slot)) {
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

    public  boolean hasGrindFactoryRecipe(@Nullable RecipeHolder<?> recipe, int slot){
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