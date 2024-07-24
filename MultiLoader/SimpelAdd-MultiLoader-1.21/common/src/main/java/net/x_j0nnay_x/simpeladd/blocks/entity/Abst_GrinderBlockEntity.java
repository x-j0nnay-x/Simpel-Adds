package net.x_j0nnay_x.simpeladd.blocks.entity;


import net.minecraft.core.*;
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
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import net.x_j0nnay_x.simpeladd.blocks.Abst_GrinderBlock;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModTags;
import net.x_j0nnay_x.simpeladd.recipe.GrinderRecipe;
import org.jetbrains.annotations.Nullable;
import java.util.Optional;

public abstract class Abst_GrinderBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {

    private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends GrinderRecipe> recipeCheckGrinder;
    protected NonNullList<ItemStack> stacks = NonNullList.withSize(5, ItemStack.EMPTY);
    public static int INPUTSLOT = 0;
    public static int GRINDERSLOT = 1;
    public static int OUTPUTSLOT = 2;
    public  static int UPGRADESLOT = 3;
    public  static int BOOSTSLOT = 4;
    private static final int[] SLOTS_FOR_UP = new int[]{GRINDERSLOT};
    private static final int[] SLOTS_FOR_DOWN = new int[]{OUTPUTSLOT};
    private static final int[] SLOTS_FOR_SIDES = new int[]{INPUTSLOT};
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress;
    private int grindsleft = 0 ;
    private int maxGrinds = 3;
    private int grindEff = 5;
    private int hasBoost = 0;


    protected Abst_GrinderBlockEntity(BlockEntityType<?> $$0, BlockPos $$1, BlockState $$2) {
        super($$0, $$1, $$2);
        this.recipeCheckGrinder = RecipeManager.createCheck(GrinderRecipe.Type.INSTANCE);

        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> Abst_GrinderBlockEntity.this.progress;
                    case 1 -> Abst_GrinderBlockEntity.this.maxProgress;
                    case 2 -> Abst_GrinderBlockEntity.this.grindsleft;
                    case 3 -> Abst_GrinderBlockEntity.this.maxGrinds;
                    case 4 -> Abst_GrinderBlockEntity.this.grindEff;
                    case 5 -> Abst_GrinderBlockEntity.this.hasBoost;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> Abst_GrinderBlockEntity.this.progress = pValue;
                    case 1 -> Abst_GrinderBlockEntity.this.maxProgress = pValue;
                    case 2 -> Abst_GrinderBlockEntity.this.grindsleft = pValue;
                    case 3 -> Abst_GrinderBlockEntity.this.maxGrinds = pValue;
                    case 4 -> Abst_GrinderBlockEntity.this.grindEff = pValue;
                    case 5 -> Abst_GrinderBlockEntity.this.hasBoost = pValue;
                }
            }

            @Override
            public int getCount() {
                return 6;
            }
        };
    }



    @Override
    public void loadAdditional(CompoundTag $$0, HolderLookup.Provider pRegistries) {
        super.loadAdditional($$0, pRegistries);
        this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems($$0, this.stacks, pRegistries);
        this.progress = $$0.getShort(SimpelAddMod.MODCUSTOM +"grinder_progress");
        this.grindsleft = $$0.getShort(SimpelAddMod.MODCUSTOM +"grinder_grinds_left");
        this.grindEff = $$0.getShort(SimpelAddMod.MODCUSTOM +"grinder_effec");

    }

    @Override
    protected void saveAdditional(CompoundTag $$0, HolderLookup.Provider pRegistries) {
        super.saveAdditional($$0, pRegistries);
        $$0.putShort(SimpelAddMod.MODCUSTOM +"grinder_progress", (short)this.progress);
        $$0.putShort(SimpelAddMod.MODCUSTOM +"grinder_grinds_left", (short)this.grindsleft);
        $$0.putShort(SimpelAddMod.MODCUSTOM +"grinder_effec", (short)this.grindEff);
        ContainerHelper.saveAllItems($$0, this.stacks, pRegistries);
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack var2, @Nullable Direction direction) {
        return (
                (direction == Direction.EAST || direction == Direction.WEST || direction == Direction.SOUTH || direction == Direction.NORTH) && (index == INPUTSLOT) ||
                        direction == Direction.UP && (index == GRINDERSLOT)
        );
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack var2, Direction direction) {
        return (direction == Direction.DOWN && (index == OUTPUTSLOT));
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
        return Component.translatable("block.simpeladdmod.grinder_block");
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

    public void grinderTick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(this.stacks.get(BOOSTSLOT).is(ModItems.BOOSTUPGRADE)){
            this.hasBoost = 1;
        }
        if (stacks.get(BOOSTSLOT).isEmpty()){
            this.hasBoost = 0;
        }
        if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_1)) {
            this.maxProgress = 40;
        }if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_2)) {
            this.maxProgress = 24;
        }if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_3)) {
            this.maxProgress = 10;
        }if (stacks.get(UPGRADESLOT).isEmpty()){
            this.maxProgress = 60;
        }
        pState = pState.setValue(Abst_GrinderBlock.WORKING, Boolean.valueOf(isWorking()));
        pLevel.setBlock(pPos, pState, 3);
        RecipeHolder<? extends GrinderRecipe> irecipe1 = this.getRecipeNonCached(this.stacks.get(INPUTSLOT));
        if(hasRecipe(irecipe1, INPUTSLOT)){
            if(this.grindsleft > 0){
                increaseCraftingProgress();
                setChanged(pLevel, pPos, pState);
                if(hasProgressFinished()){
                    useGrind();
                    craftItem(irecipe1, INPUTSLOT);
                    resetProgress();
                }
            }else{
                resetGrinds();
            }

        }else {

            resetProgress();
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
        if(this.stacks.get(GRINDERSLOT).is(ModTags.Items.GRINDERS)){
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

    private boolean isWorking() {
        if (progress > 0){
            if(this.grindsleft > 0 || this.stacks.get(GRINDERSLOT).is(ModItems.GRINDERHEAD)){
                return true;
            }
        }
        return false;
    }
    private void resetProgress() {
        this.progress = 0;
    }
    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasProgressFinished() {

        return this.progress >= this.maxProgress;

    }
    private RecipeHolder<? extends GrinderRecipe> getRecipeNonCached(ItemStack itemStack) {
        return this.recipeCheckGrinder.getRecipeFor(new SingleRecipeInput(itemStack), this.level).orElse(null);
    }

    private  void craftItem(@Nullable RecipeHolder<?> recipe, int slot){
        int outputSlot = OUTPUTSLOT;
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
        int outputslot = OUTPUTSLOT;
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
