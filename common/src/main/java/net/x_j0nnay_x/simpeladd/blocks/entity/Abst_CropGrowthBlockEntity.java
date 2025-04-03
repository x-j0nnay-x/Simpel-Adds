package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.blocks.Abst_CropGrowthBlock;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import net.x_j0nnay_x.simpeladd.recipe.CropGrothRecipeInput;
import net.x_j0nnay_x.simpeladd.recipe.CropGrowthRecipe;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
  this is inspired by Botany Pots/Botany trees this is my own spin on the idea. I've done this because I wanted something that would work as i hate tree farms and stuff like that.
  this is a block that will grow crops for you and give you the drops. it will use copper to run and will use dirt as a base for the crops to grow on.
  I know there is probably a better way to do this but this is my way, and it may not be pretty but it works and I like it.
  I also made it so you can just add recipes to this machine instead of having to make an addon.
 */

public abstract class Abst_CropGrowthBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {

    protected NonNullList<ItemStack> stacks = NonNullList.withSize(20, ItemStack.EMPTY);
    public final RecipeManager.CachedCheck<CropGrothRecipeInput, CropGrowthRecipe> recipeCheck;
    public static int DIRTSLOT = 0;
    public static int CROPSLOT = 1;
    public static int OUTPUTSLOT1 = 2;
    public static int OUTPUTSLOT2 = 3;
    public static int OUTPUTSLOT3 = 4;
    public static int OUTPUTSLOT4 = 5;
    public static int OUTPUTSLOT5 = 6;
    public static int OUTPUTSLOT6 = 7;
    public static int OUTPUTSLOT7 = 8;
    public static int OUTPUTSLOT8 = 9;
    public static int OUTPUTSLOT9 = 10;
    public static int OUTPUTSLOT10 = 11;
    public static int OUTPUTSLOT11 = 12;
    public static int OUTPUTSLOT12 = 13;
    public static int OUTPUTSLOT13 = 14;
    public static int OUTPUTSLOT14 = 15;
    public static int OUTPUTSLOT15 = 16;
    public static int COPPERSLOT = 17;
    public static int SPEEDSLOT = 18;
    public static int EFFSLOT = 19;

    private static final int[] OUTPUTSLOTS = new int[]{OUTPUTSLOT1, OUTPUTSLOT2, OUTPUTSLOT3, OUTPUTSLOT4, OUTPUTSLOT5, OUTPUTSLOT6, OUTPUTSLOT7, OUTPUTSLOT8, OUTPUTSLOT9, OUTPUTSLOT10, OUTPUTSLOT11, OUTPUTSLOT12, OUTPUTSLOT13, OUTPUTSLOT14, OUTPUTSLOT15};
    private static final int[] INPUTSLOTS = new int[]{COPPERSLOT};

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress;
    private int copperLevel = 0;
    private int maxCopper = 100;
    private int hasBoost;
    private int copperuse;
    private boolean shouldWork;



    protected Abst_CropGrowthBlockEntity(BlockEntityType<?> $$0, BlockPos $$1, BlockState $$2) {
        super($$0, $$1, $$2);
        this.recipeCheck = RecipeManager.createCheck(CropGrowthRecipe.CropGrowthType.INSTANCE);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> Abst_CropGrowthBlockEntity.this.progress;
                    case 1 -> Abst_CropGrowthBlockEntity.this.maxProgress;
                    case 2 -> Abst_CropGrowthBlockEntity.this.copperLevel;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> Abst_CropGrowthBlockEntity.this.progress = pValue;
                    case 1 -> Abst_CropGrowthBlockEntity.this.maxProgress = pValue;
                    case 2 -> Abst_CropGrowthBlockEntity.this.copperLevel = pValue;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    @Override
    public void loadAdditional(CompoundTag $$0, HolderLookup.Provider pRegistries) {
        super.loadAdditional($$0, pRegistries);
        this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems($$0, this.stacks, pRegistries);
        this.progress = $$0.getInt(SimpelAddMod.MODCUSTOM + "crop_growth_progress");
        this.copperLevel = $$0.getInt(SimpelAddMod.MODCUSTOM + "crop_growth_copperlevel");

    }

    @Override
    protected void saveAdditional(CompoundTag $$0, HolderLookup.Provider pRegistries) {
        super.saveAdditional($$0, pRegistries);
        $$0.putInt(SimpelAddMod.MODCUSTOM + "crop_growth_progress", this.progress);
        $$0.putInt(SimpelAddMod.MODCUSTOM + "crop_growth_copperlevel", this.copperLevel);
        ContainerHelper.saveAllItems($$0, this.stacks, pRegistries);
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return index == COPPERSLOT && stack.getItem() == Items.COPPER_INGOT;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack var2, Direction direction) {
        return true;
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
        if (var1 == Direction.UP) {
            return INPUTSLOTS;
        } else {
            return OUTPUTSLOTS;
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
        return Component.translatable("block.simpeladdmod.crop_growth_block");
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return this.saveWithFullMetadata(pRegistries);
    }

    public ItemStack getCopperOutput() {
        return new ItemStack(Items.COPPER_INGOT, this.copperLevel);
    }
//Processing
    public void cropGrowthTick(ServerLevel pLevel, BlockPos pPos, BlockState pState) {
        if(pLevel.isClientSide()){
            return;
        }
        RecipeHolder<? extends CropGrowthRecipe> irecipe1 = this.getRecipeNonCached(this.stacks.get(CROPSLOT), this.stacks.get(DIRTSLOT));
        setUpgrades();
        setCopperLevel();
        pState = pState.setValue(Abst_CropGrowthBlock.WORKING, Boolean.valueOf(isWorking()));
        pLevel.setBlock(pPos, pState, 3);
            if (hasRecipe(irecipe1) && this.copperLevel >0) {
                    if (this.progress < this.maxProgress) {
                        this.progress++;
                    } else {
                        craftItem(irecipe1);
                }
            } else {
                    this.progress = 0;
                }

    }

    private void setUpgrades(){
        if(this.stacks.get(EFFSLOT).is(ModItems.BOOSTUPGRADE)){
            this.hasBoost = 1;
        }if (stacks.get(EFFSLOT).isEmpty()){
            this.hasBoost = 0;
        }if (stacks.get(SPEEDSLOT).is(ModItems.SPEEDUPGRADE_1)) {
            this.maxProgress = 2880;
        }if (stacks.get(SPEEDSLOT).is(ModItems.SPEEDUPGRADE_2)) {
            this.maxProgress = 2160;
        }if (stacks.get(SPEEDSLOT).is(ModItems.SPEEDUPGRADE_3)) {
            this.maxProgress = 1440;
        }if (this.stacks.get(SPEEDSLOT).is(ModItems.SPEEDUPGRADE_4)) {
            this.maxProgress = 720;
        }if (stacks.get(SPEEDSLOT).isEmpty()){
            this.maxProgress = 3600;
        }
    }

    private void setCopperLevel(){
        if(this.copperLevel == maxCopper)
            return;
        if(copperLevel < maxCopper) {
            if (this.stacks.get(COPPERSLOT).is(Items.COPPER_INGOT)) {
                this.copperLevel += 1;
                this.removeItem(COPPERSLOT, 1);
            }
        }
    }

    private boolean isBlockFull(){
        for (int i = 0; i < 15; i++) {
            ItemStack slotStack = this.getItem(OUTPUTSLOT1 + i);
            if(slotStack.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    private boolean canWorkCheck(ItemStack itemStack, int slot) {
        if(isBlockFull()){
          if(!this.stacks.get(slot).is(itemStack.getItem())){
              return false;
          }
        }
        return true;
    }

    private static List<ItemStack> getOuputList(ItemStack itemStack) {
        Item item = itemStack.getItem();
        List<ItemStack> output = new ArrayList<>();
        Random random = new Random();
        int count = random.nextInt(1, 3);
        ItemStack itemStack1 = new ItemStack(item, count);
        output.add(itemStack1);
        return output;
    }

    public void putDropsIn(ItemStack input) {

        List<ItemStack> output = getOuputList(input);
        int firstSlot = OUTPUTSLOT1;
        int size = 15;
        for (ItemStack drops : output) {
            for (int i = 0; i < size; i++) {
                int slot = firstSlot + i;
                ItemStack stack = this.stacks.get(slot).copy();
                setShouldWork(input, slot);
                if (canWorkCheck(drops, slot)) {
                    if (stack.isEmpty()) {
                        this.setItem(slot, drops);
                        break;
                    } else if (canMergeItems(stack, drops)) {
                        int count = Math.min(drops.getCount(), stack.getMaxStackSize() - stack.getCount());
                        this.stacks.set(slot, new ItemStack(stack.getItem(), stack.getCount() + count));
                        drops.shrink(count);
                        if (drops.isEmpty()) {
                            break;
                        }
                    }
                }
            }
        }
        this.progress = 0;
        if(getShouldWork()){
            useCopper();
        }
    }

    private static boolean canMergeItems(ItemStack stack1, ItemStack stack2) {
        return stack1.getCount() <= stack1.getMaxStackSize() && ItemStack.isSameItemSameComponents(stack1, stack2);
    }

    private void setShouldWork(ItemStack itemStack, int slot) {
        if(this.stacks.get(slot).isEmpty() || this.stacks.get(slot).is(itemStack.getItem())){
            this.shouldWork = (this.stacks.get(slot).getCount() < 64 );
        }
    }

    private boolean getShouldWork(){
        return this.shouldWork;
    }

    public boolean allowedInSlot(ItemStack stack){
        return recipeCheck.getRecipeFor(new CropGrothRecipeInput(stack, stack), level).isPresent();
    }
    private void restCopperuse(){
        if (this.hasBoost == 1){
            this.copperuse = 4;
        }else{
            this.copperuse = 1;
        }
    }

    private void useCopper(){
        if(this.copperuse > 1){
            this.copperuse --;
        }else {
            restCopperuse();
            this.copperLevel--;
        }
    }

    private boolean hasDirt(){
        return !this.stacks.get(DIRTSLOT).isEmpty();
    }

    private boolean hasCrops(){
        return !this.stacks.get(CROPSLOT).isEmpty();
    }

    private boolean hasCopper(){
        return this.copperLevel > 0;
    }


    private boolean isWorking(){
        return hasDirt() && hasCrops() && hasCopper() && hasRecipe(getRecipeNonCached(this.stacks.get(CROPSLOT), this.stacks.get(DIRTSLOT))) && this.shouldWork;
    }
    private RecipeHolder<? extends CropGrowthRecipe> getRecipeNonCached(ItemStack crop, ItemStack soil) {
        return this.recipeCheck.getRecipeFor(new CropGrothRecipeInput(crop, soil), this.level).orElse(null);
    }

    private  void craftItem(@Nullable RecipeHolder<?> recipe){
        if (recipe != null && this.hasRecipe(recipe)) {
            ItemStack itemstack1 = recipe.value().getResultItem(RegistryAccess.EMPTY);
                putDropsIn(itemstack1);
        }
    }

    public  boolean hasRecipe(@Nullable RecipeHolder<?> recipe) {
        if(!this.getItem(CROPSLOT).isEmpty() && !this.getItem(DIRTSLOT).isEmpty() && recipe != null){
            ItemStack recipeOutput = recipe.value().getResultItem(RegistryAccess.EMPTY);
            if (!recipeOutput.isEmpty()){
                return true;
            }
        }
        return false;
    }

}