package net.x_j0nnay_x.simpeladdmod.block.entity;


import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
import net.x_j0nnay_x.simpeladdmod.block.custom.GrinderBlock_upgrade;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.recipe.GrinderRecipe;
import net.x_j0nnay_x.simpeladdmod.screen.grinder.GrinderMenu;
import net.x_j0nnay_x.simpeladdmod.screen.grinder_up.GrinderMenu_up;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

public class GrinderBlockEntity_upgrade extends RandomizableContainerBlockEntity implements WorldlyContainer {
    private final ItemStackHandler itemHandler = new ItemStackHandler(11);
    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(11, ItemStack.EMPTY);
    private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());

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

    protected final ContainerData data;
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
        super(ModBlockEntities.GRINDER_UP.get(), pPos, pBlockState);
        this.data = new ContainerData() {
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
            public int getCount() {
                return 9;
            }
        };
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.simpeladdmod.grinder_block_up");
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return  new GrinderMenu_up(pContainerId,pInventory, this, this.data);
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
        return (
                (direction == Direction.EAST || direction == Direction.WEST || direction == Direction.SOUTH || direction == Direction.NORTH) &&
                        (index == INPUTSLOT1 || index == INPUTSLOT2 || index == INPUTSLOT3 || index == INPUTSLOT4)||
                direction == Direction.UP && (index == GRINDERSLOT)
        );
    }
    @Override
    public boolean canTakeItemThroughFace(int slotIndex, ItemStack itemStack, Direction direction) {
        // Only allow the down direction and only for the result slot.
        return (direction == Direction.DOWN && (slotIndex == OUTPUTSLOT1 || slotIndex == OUTPUTSLOT2 || slotIndex == OUTPUTSLOT3 || slotIndex == OUTPUTSLOT4));
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
        progress1 = compound.getInt("grinder_progress1");
        progress2 = compound.getInt("grinder_progress2");
        progress3 = compound.getInt("grinder_progress3");
        progress4 = compound.getInt("grinder_progress4");
        grindsleft = compound.getInt("grinder_grinds_left");
        grindEff = compound.getInt("grinder_effec");
        if (!this.tryLoadLootTable(compound))
            this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compound, this.stacks);
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("inventory", itemHandler.serializeNBT());
        compound.putInt("grinder_progress1", progress1);
        compound.putInt("grinder_progress2", progress2);
        compound.putInt("grinder_progress3", progress3);
        compound.putInt("grinder_progress4", progress4);
        compound.putInt("grinder_grinds_left", grindsleft);
        compound.putInt("grinder_effec", grindEff);
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
        if(stacks.get(BOOSTSLOT).is(ModItems.BOOSTUPGRADE.get())){
            this.hasBoost = 1;
        }
        if (stacks.get(BOOSTSLOT).isEmpty()){
            this.hasBoost = 0;
        }
        if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_1.get())) {
            this.maxProgress = 20;
        }if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_2.get())) {
            this.maxProgress = 12;
        }if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_3.get())) {
            this.maxProgress = 5;
        }if (stacks.get(UPGRADESLOT).isEmpty()){
            this.maxProgress = 30;
        }
        pState = pState.setValue(GrinderBlock_upgrade.WORKING, Boolean.valueOf(isWorking()));
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
        grindEff = 5;
    }
    private void useGrind(){
        if (stacks.get(BOOSTSLOT).is(ModItems.BOOSTUPGRADE.get())){
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
        return  hasRecipe1()||hasRecipe2()||hasRecipe3()||hasRecipe4();
    }
    private boolean isWorking() {
        if (canWork()){
            if(grindsleft > 0 || stacks.get(GRINDERSLOT).is(ModItems.GRINDERHEAD.get())){
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
        Optional<GrinderRecipe> recipe = getCurrentRecipe1();
        ItemStack result = recipe.get().getResultItem(null);
        this.removeItem(INPUTSLOT1, 1);
        this.stacks.set(OUTPUTSLOT1, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT1).getCount() + result.getCount()));

    }
    private void craftItem2() {
        Optional<GrinderRecipe> recipe = getCurrentRecipe2();
        ItemStack result = recipe.get().getResultItem(null);
        this.removeItem(INPUTSLOT2, 1);
        this.stacks.set(OUTPUTSLOT2, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT2).getCount() + result.getCount()));

    }
    private void craftItem3() {
        Optional<GrinderRecipe> recipe = getCurrentRecipe3();
        ItemStack result = recipe.get().getResultItem(null);
        this.removeItem(INPUTSLOT3, 1);
        this.stacks.set(OUTPUTSLOT3, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT3).getCount() + result.getCount()));

    }
    private void craftItem4() {
        Optional<GrinderRecipe> recipe = getCurrentRecipe4();
        ItemStack result = recipe.get().getResultItem(null);
        this.removeItem(INPUTSLOT4, 1);
        this.stacks.set(OUTPUTSLOT4, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT4).getCount() + result.getCount()));

    }
    private boolean hasRecipe1() {
        Optional<GrinderRecipe> recipe = getCurrentRecipe1();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());

        return canInsertOutputAmount1(result.getCount()) && canInsertOutputItem1(result.getItem());
}
    private boolean hasRecipe2() {
        Optional<GrinderRecipe> recipe = getCurrentRecipe2();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());

        return canInsertOutputAmount2(result.getCount()) && canInsertOutputItem2(result.getItem());
    }
    private boolean hasRecipe3() {
        Optional<GrinderRecipe> recipe = getCurrentRecipe3();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());

        return canInsertOutputAmount3(result.getCount()) && canInsertOutputItem3(result.getItem());
    }
    private boolean hasRecipe4() {
        Optional<GrinderRecipe> recipe = getCurrentRecipe4();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());

        return canInsertOutputAmount4(result.getCount()) && canInsertOutputItem4(result.getItem());
    }
    private Optional<GrinderRecipe> getCurrentRecipe1() {
        SimpleContainer inventory = new SimpleContainer(this.stacks.get(INPUTSLOT1));
        return this.level.getRecipeManager().getRecipeFor(GrinderRecipe.Type.INSTANCE, inventory, level);
    }
    private Optional<GrinderRecipe> getCurrentRecipe2() {
        SimpleContainer inventory = new SimpleContainer(this.stacks.get(INPUTSLOT2));
        return this.level.getRecipeManager().getRecipeFor(GrinderRecipe.Type.INSTANCE, inventory, level);
    }
    private Optional<GrinderRecipe> getCurrentRecipe3() {
        SimpleContainer inventory = new SimpleContainer(this.stacks.get(INPUTSLOT3));
        return this.level.getRecipeManager().getRecipeFor(GrinderRecipe.Type.INSTANCE, inventory, level);
    }
    private Optional<GrinderRecipe> getCurrentRecipe4() {
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