package net.x_j0nnay_x.simpeladdmod.block.entity;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.tags.ItemTags;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.x_j0nnay_x.simpeladdmod.block.ModBlockEntities;
import net.x_j0nnay_x.simpeladdmod.block.ModBlocks;
import net.x_j0nnay_x.simpeladdmod.block.custom.GrinderBlock;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.recipe.GrinderRecipeold;
import net.x_j0nnay_x.simpeladdmod.screen.grinder.GrinderMenu;
import net.x_j0nnay_x.simpeladdmod.until.ModTags;
import org.jetbrains.annotations.Nullable;
import java.util.Optional;
import java.util.stream.IntStream;

public class GrinderBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4);
    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
    private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());
    public static int INPUTSLOT = 0;
    public static int GRINDERSLOT = 1;
    public static int OUTPUTSLOT = 2;
    public  static int UPGRADESLOT = 3;
    private int outputAmount = 0;
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 60;
    private int grindsleft = 0 ;
    private int maxGrinds = 3;
    public GrinderBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.GRINDER.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> GrinderBlockEntity.this.progress;
                    case 1 -> GrinderBlockEntity.this.maxProgress;
                    case 2 -> GrinderBlockEntity.this.grindsleft;
                    case 3 -> GrinderBlockEntity.this.maxGrinds;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> GrinderBlockEntity.this.progress = pValue;
                    case 1 -> GrinderBlockEntity.this.maxProgress = pValue;
                    case 2 -> GrinderBlockEntity.this.grindsleft = pValue;
                    case 3 -> GrinderBlockEntity.this.maxGrinds = pValue;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.simpeladdmod.grinder_block");
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return  new GrinderMenu(pContainerId,pInventory, this, this.data);
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
        return (direction == Direction.EAST  && (index == INPUTSLOT) ||
                direction == Direction.WEST && (index == INPUTSLOT) ||
                direction == Direction.SOUTH && (index == INPUTSLOT) ||
                direction == Direction.NORTH && (index == INPUTSLOT) ||
                direction == Direction.UP && (index == GRINDERSLOT));
    }
    @Override
    public boolean canTakeItemThroughFace(int slotIndex, ItemStack itemStack, Direction direction) {
        // Only allow the down direction and only for the result slot.
        return (direction == Direction.DOWN && (slotIndex == OUTPUTSLOT));
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
        progress = compound.getInt("grinder_progress");
        grindsleft = compound.getInt("grinder_grinds_left");
        if (!this.tryLoadLootTable(compound))
            this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compound, this.stacks);
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("inventory", itemHandler.serializeNBT());
        compound.putInt("grinder_progress", progress);
        compound.putInt("grinder_grinds_left", grindsleft);
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
        if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER)
            return handlers[facing.ordinal()].cast();
        return super.getCapability(capability, facing);
    }


// processing

    public void tick(Level pLevel, BlockPos pPos, BlockState pState){
        if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_1.get())) {
            this.maxProgress = 40;
        }if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_2.get())) {
            this.maxProgress = 24;
        }if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_3.get())) {
            this.maxProgress = 10;
        }if (stacks.get(UPGRADESLOT).isEmpty()){
            this.maxProgress = 60;
        }
        pState = pState.setValue(GrinderBlock.WORKING, Boolean.valueOf(isWorking()));
        pLevel.setBlock(pPos, pState, 3);
        if(hasRecipe()){
            if(grindsleft > 0){
                if(canInsertOutputAmount(outputAmount)) {
                    increaseCraftingProgress();
                    setChanged(pLevel, pPos, pState);
                    if (hasProgressFinished()) {
                        useGrind();
                        craftItem();
                        resetProgress();
                    }
                }
            }else{
                resetGrinds();
            }

        }else {

            resetProgress();
        }
    }

    private void useGrind(){
        grindsleft --;
    }
    private void resetGrinds() {
        if(stacks.get(GRINDERSLOT).is(ModItems.GRINDERHEAD.get())){
            if(stacks.get(GRINDERSLOT).getDamageValue() >= stacks.get(GRINDERSLOT).getMaxDamage()){
                stacks.set(GRINDERSLOT, ItemStack.EMPTY);
            }else{
                stacks.get(GRINDERSLOT).setDamageValue(stacks.get(GRINDERSLOT).getDamageValue() + 1);
                grindsleft = maxGrinds;
            }
        }else {
            grindsleft = 0;
        }
    }
    private boolean canWork(){
        return  hasRecipe();
    }
    private boolean isWorking() {
        if (canWork()){
            if(grindsleft > 0 || stacks.get(GRINDERSLOT).is(ModItems.GRINDERHEAD.get())){
                return true;
            }
        }
        return false;
    }
    private void resetProgress() {
        progress = 0;
    }
    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void craftItem() {
        if(stacks.get(INPUTSLOT).is(Items.RAW_IRON)){
            if(canInsertOutputItem(ModItems.IRONDUST.get()) && canInsertOutputAmount(outputAmount)) {
                this.removeItem(INPUTSLOT, 1);
                stacks.set(OUTPUTSLOT, new ItemStack(ModItems.IRONDUST.get(), stacks.get(OUTPUTSLOT).getCount() + 2));
            }
        }
        if(stacks.get(INPUTSLOT).is(Items.RAW_COPPER)){
            if(canInsertOutputItem(ModItems.COPPERDUST.get()) && canInsertOutputAmount(outputAmount)) {
                this.removeItem(INPUTSLOT, 1);
                stacks.set(OUTPUTSLOT, new ItemStack(ModItems.COPPERDUST.get(), stacks.get(OUTPUTSLOT).getCount() + 2));
            }
        }
        if(stacks.get(INPUTSLOT).is(Items.RAW_GOLD)){
            if(canInsertOutputItem(ModItems.GOLDDUST.get()) && canInsertOutputAmount(outputAmount)) {
                this.removeItem(INPUTSLOT, 1);
                stacks.set(OUTPUTSLOT, new ItemStack(ModItems.GOLDDUST.get(), stacks.get(OUTPUTSLOT).getCount() + 2));
            }
        }
        if(stacks.get(INPUTSLOT).is(Items.OBSIDIAN)){
            if(canInsertOutputItem(ModItems.OBSIDAINDUST.get()) && canInsertOutputAmount(outputAmount)) {
                this.removeItem(INPUTSLOT, 1);
                stacks.set(OUTPUTSLOT, new ItemStack(ModItems.OBSIDAINDUST.get(), stacks.get(OUTPUTSLOT).getCount() + 2));
            }
        }
        if(stacks.get(INPUTSLOT).is(Items.ANCIENT_DEBRIS)){
            if(canInsertOutputItem(ModItems.NETHERITEDUST.get()) && canInsertOutputAmount(outputAmount)) {
                this.removeItem(INPUTSLOT, 1);
                stacks.set(OUTPUTSLOT, new ItemStack(ModItems.NETHERITEDUST.get(), stacks.get(OUTPUTSLOT).getCount() + 2));
            }
        }
        if(stacks.get(INPUTSLOT).is(ModItems.NEHTERITE_SHARD_RAW.get())){
            if(canInsertOutputItem(ModItems.NEHTERITE_SHARD_DUST.get()) && canInsertOutputAmount(outputAmount)) {
                this.removeItem(INPUTSLOT, 1);
                stacks.set(OUTPUTSLOT, new ItemStack(ModItems.NEHTERITE_SHARD_DUST.get(), stacks.get(OUTPUTSLOT).getCount() + 2));
            }
        }
        if(stacks.get(INPUTSLOT).is(Items.BLAZE_ROD)){
            if(canInsertOutputItem(Items.BLAZE_POWDER) && canInsertOutputAmount(outputAmount)) {
                this.removeItem(INPUTSLOT, 1);
                stacks.set(OUTPUTSLOT, new ItemStack(Items.BLAZE_POWDER, stacks.get(OUTPUTSLOT).getCount() + 3));
            }
        }
        if(stacks.get(INPUTSLOT).is(Items.BONE)){
            if(canInsertOutputItem(Items.BONE_MEAL) && canInsertOutputAmount(outputAmount)) {
                this.removeItem(INPUTSLOT, 1);
                stacks.set(OUTPUTSLOT, new ItemStack(Items.BONE_MEAL, stacks.get(OUTPUTSLOT).getCount() + 4));
            }
        }
        if(stacks.get(INPUTSLOT).is(ItemTags.WOOL)){
            if(canInsertOutputItem(Items.STRING) && canInsertOutputAmount(outputAmount)) {
                this.removeItem(INPUTSLOT, 1);
                stacks.set(OUTPUTSLOT, new ItemStack(Items.STRING, stacks.get(OUTPUTSLOT).getCount() + 5));
            }
        }
        if(stacks.get(INPUTSLOT).is(ItemTags.LOGS)){
            if(canInsertOutputItem(ModItems.WOODFIBER.get()) && canInsertOutputAmount(outputAmount)) {
                this.removeItem(INPUTSLOT, 1);
                stacks.set(OUTPUTSLOT, new ItemStack(ModItems.WOODFIBER.get(), stacks.get(OUTPUTSLOT).getCount() + 8));
            }
        }
        if(stacks.get(INPUTSLOT).is(ModBlocks.UNOBTANIUM_ORE.get().asItem())){
            if(canInsertOutputItem(ModItems.UNOBTIANIUMDUST.get()) && canInsertOutputAmount(outputAmount)) {
                this.removeItem(INPUTSLOT, 1);
                stacks.set(OUTPUTSLOT, new ItemStack(ModItems.UNOBTIANIUMDUST.get(), stacks.get(OUTPUTSLOT).getCount() + 2));
            }
        }


        /*
        Optional<RecipeHolder<GrinderRecipe>> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().value().getResultItem(null);
        this.removeItem(INPUTSLOT, 1);
        this.stacks.set(OUTPUTSLOT, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT).getCount() + result.getCount()));

         */

    }
    private void doubleCrafting(){
        outputAmount = 2;
    }
    private void trippleCrafting(){
        outputAmount = 3;
    }
    private void fourCrafting(){
        outputAmount = 4;
    }
    private void fiveCrafting(){
        outputAmount = 5;
    }
    private void fiberCrafting(){
        outputAmount = 8;
    }


    private boolean hasRecipe() {
        if(this.stacks.get(INPUTSLOT).is(ModTags.Items.CANGRIND)){
            if(this.stacks.get(INPUTSLOT).is(Items.RAW_GOLD) || this.stacks.get(INPUTSLOT).is(Items.RAW_IRON) ||this.stacks.get(INPUTSLOT).is(Items.RAW_COPPER) ||this.stacks.get(INPUTSLOT).is(Items.OBSIDIAN) ||
                    this.stacks.get(INPUTSLOT).is(Items.ANCIENT_DEBRIS) ||this.stacks.get(INPUTSLOT).is(ModItems.NEHTERITE_SHARD_RAW.get()) || this.stacks.get(INPUTSLOT).is(ModBlocks.UNOBTANIUM_ORE.get().asItem())){
                doubleCrafting();
            }
            if(this.stacks.get(INPUTSLOT).is(ItemTags.IRON_ORES) || this.stacks.get(INPUTSLOT).is(ItemTags.COPPER_ORES) ||this.stacks.get(INPUTSLOT).is(ModTags.Items.RAW_GOLD_DROPPER) ||this.stacks.get(INPUTSLOT).is(Items.BLAZE_ROD)){
                trippleCrafting();
            }
            if(this.stacks.get(INPUTSLOT).is(Items.BONE)){
                fourCrafting();
            }
            if(this.stacks.get(INPUTSLOT).is(ItemTags.WOOL)){
                fiveCrafting();
            }
            if(this.stacks.get(INPUTSLOT).is(ItemTags.LOGS)){
                fiberCrafting();
            } return true;
        }
        return false;
        /*
        Optional<RecipeHolder<GrinderRecipe>> recipe = getCurrentRecipe();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().value().getResultItem(getLevel().registryAccess());

        return canInsertOutputAmount(result.getCount()) && canInsertOutputItem(result.getItem());

         */
    }
    private Optional<RecipeHolder<GrinderRecipeold>> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.stacks.size());
        for(int i = 0; i < stacks.size(); i++) {
            inventory.setItem(i, this.stacks.get(i));
        }

        return this.level.getRecipeManager().getRecipeFor(GrinderRecipeold.Type.INSTANCE, inventory, level);
    }


    private boolean canInsertOutputItem(Item item) {

        return this.stacks.get(OUTPUTSLOT).isEmpty() || this.stacks.get(OUTPUTSLOT).is(item);
    }

    private boolean canInsertOutputAmount(int count) {
        return this.stacks.get(OUTPUTSLOT).getCount() + count <= this.stacks.get(OUTPUTSLOT).getMaxStackSize();
    }


}