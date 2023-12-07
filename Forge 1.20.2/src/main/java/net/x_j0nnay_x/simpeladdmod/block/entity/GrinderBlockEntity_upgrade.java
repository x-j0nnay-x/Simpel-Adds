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
import net.x_j0nnay_x.simpeladdmod.block.custom.GrinderBlock_upgrade;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.screen.grinder_up.GrinderMenu_up;
import net.x_j0nnay_x.simpeladdmod.until.ModTags;
import org.jetbrains.annotations.Nullable;

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
    public static int [] INPUTSLOT = new int[]{1,2,3,4};
    public static int OUTPUTSLOT1 = 5;
    public static int OUTPUTSLOT2 = 6;
    public static int OUTPUTSLOT3 = 7;
    public static int OUTPUTSLOT4 = 8;
    public static int []OUTPUTSLOT = new int[]{5,6,7,8};
    public  static int UPGRADESLOT = 9;
    public  static int BOOSTSLOT = 10;
    private int outputAmount1 = 0;
    private int outputAmount2 = 0;
    private int outputAmount3 = 0;
    private int outputAmount4 = 0;

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
            this.maxProgress = 40;
        }if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_2.get())) {
            this.maxProgress = 24;
        }if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_3.get())) {
            this.maxProgress = 10;
        }if (stacks.get(UPGRADESLOT).isEmpty()){
            this.maxProgress = 60;
        }
        pState = pState.setValue(GrinderBlock_upgrade.WORKING, Boolean.valueOf(isWorking()));
        pLevel.setBlock(pPos, pState, 3);
        if(grindsleft > 0){
                if(hasRecipe1() && canInsertOutputAmount1(outputAmount1)){
                    increaseCraftingProgress1();
                    setChanged(pLevel, pPos, pState);
                        if(hasProgressFinished1()){
                            useGrind();
                            craftItem1();
                            resetProgress1();
                        }
                }if(hasRecipe2() && canInsertOutputAmount2(outputAmount2)){
                increaseCraftingProgress2();
                setChanged(pLevel, pPos, pState);
                if(hasProgressFinished2()){
                    useGrind();
                    craftItem2();
                    resetProgress2();
                }
            }if(hasRecipe3() && canInsertOutputAmount3(outputAmount3)){
                increaseCraftingProgress3();
                setChanged(pLevel, pPos, pState);
                if(hasProgressFinished3()){
                    useGrind();
                    craftItem3();
                    resetProgress3();
                }
            }if(hasRecipe4() && canInsertOutputAmount4(outputAmount4)){
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
        if (stacks.get(INPUTSLOT1).is(Items.RAW_IRON)) {
            if (canInsertOutputItem1(ModItems.IRONDUST.get()) && canInsertOutputAmount1(outputAmount1)) {
                this.removeItem(INPUTSLOT1, 1);
                stacks.set(OUTPUTSLOT1, new ItemStack(ModItems.IRONDUST.get(), stacks.get(OUTPUTSLOT1).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT1).is(Items.RAW_COPPER)) {
            if (canInsertOutputItem1(ModItems.COPPERDUST.get()) && canInsertOutputAmount1(outputAmount1)) {
                this.removeItem(INPUTSLOT1, 1);
                stacks.set(OUTPUTSLOT1, new ItemStack(ModItems.COPPERDUST.get(), stacks.get(OUTPUTSLOT1).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT1).is(Items.RAW_GOLD)) {
            if (canInsertOutputItem1(ModItems.GOLDDUST.get()) && canInsertOutputAmount1(outputAmount1)) {
                this.removeItem(INPUTSLOT1, 1);
                stacks.set(OUTPUTSLOT1, new ItemStack(ModItems.GOLDDUST.get(), stacks.get(OUTPUTSLOT1).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT1).is(Items.OBSIDIAN)) {
            if (canInsertOutputItem1(ModItems.OBSIDAINDUST.get()) && canInsertOutputAmount1(outputAmount1)) {
                this.removeItem(INPUTSLOT1, 1);
                stacks.set(OUTPUTSLOT1, new ItemStack(ModItems.OBSIDAINDUST.get(), stacks.get(OUTPUTSLOT1).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT1).is(Items.ANCIENT_DEBRIS)) {
            if (canInsertOutputItem1(ModItems.NETHERITEDUST.get()) && canInsertOutputAmount1(outputAmount1)) {
                this.removeItem(INPUTSLOT1, 1);
                stacks.set(OUTPUTSLOT1, new ItemStack(ModItems.NETHERITEDUST.get(), stacks.get(OUTPUTSLOT1).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT1).is(ModItems.NEHTERITE_SHARD_RAW.get())) {
            if (canInsertOutputItem1(ModItems.NEHTERITE_SHARD_DUST.get()) && canInsertOutputAmount1(outputAmount1)) {
                this.removeItem(INPUTSLOT1, 1);
                stacks.set(OUTPUTSLOT1, new ItemStack(ModItems.NEHTERITE_SHARD_DUST.get(), stacks.get(OUTPUTSLOT1).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT1).is(Items.BLAZE_ROD)) {
            if (canInsertOutputItem1(Items.BLAZE_POWDER) && canInsertOutputAmount1(outputAmount1)) {
                this.removeItem(INPUTSLOT1, 1);
                stacks.set(OUTPUTSLOT1, new ItemStack(Items.BLAZE_POWDER, stacks.get(OUTPUTSLOT1).getCount() + 3));
            }
        }
        if (stacks.get(INPUTSLOT1).is(Items.BONE)) {
            if (canInsertOutputItem1(Items.BONE_MEAL) && canInsertOutputAmount1(outputAmount1)) {
                this.removeItem(INPUTSLOT1, 1);
                stacks.set(OUTPUTSLOT1, new ItemStack(Items.BONE_MEAL, stacks.get(OUTPUTSLOT1).getCount() + 4));
            }
        }
        if (stacks.get(INPUTSLOT1).is(ItemTags.WOOL)) {
            if (canInsertOutputItem1(Items.STRING) && canInsertOutputAmount1(outputAmount1)) {
                this.removeItem(INPUTSLOT1, 1);
                stacks.set(OUTPUTSLOT1, new ItemStack(Items.STRING, stacks.get(OUTPUTSLOT1).getCount() + 5));
            }
        }
        if (stacks.get(INPUTSLOT1).is(ItemTags.LOGS)) {
            if (canInsertOutputItem1(ModItems.WOODFIBER.get()) && canInsertOutputAmount1(outputAmount1)) {
                this.removeItem(INPUTSLOT1, 1);
                stacks.set(OUTPUTSLOT1, new ItemStack(ModItems.WOODFIBER.get(), stacks.get(OUTPUTSLOT1).getCount() + 8));
            }
        }
        if (stacks.get(INPUTSLOT1).is(ModBlocks.UNOBTANIUM_ORE.get().asItem())) {
            if (canInsertOutputItem1(ModItems.UNOBTIANIUMDUST.get()) && canInsertOutputAmount1(outputAmount1)) {
                this.removeItem(INPUTSLOT1, 1);
                stacks.set(OUTPUTSLOT1, new ItemStack(ModItems.UNOBTIANIUMDUST.get(), stacks.get(OUTPUTSLOT1).getCount() + 2));
            }
        }
    }
    private void craftItem2() {
        if (stacks.get(INPUTSLOT2).is(Items.RAW_IRON)) {
            if (canInsertOutputItem2(ModItems.IRONDUST.get()) && canInsertOutputAmount2(outputAmount2)) {
                this.removeItem(INPUTSLOT2, 1);
                stacks.set(OUTPUTSLOT2, new ItemStack(ModItems.IRONDUST.get(), stacks.get(OUTPUTSLOT2).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT2).is(Items.RAW_COPPER)) {
            if (canInsertOutputItem2(ModItems.COPPERDUST.get()) && canInsertOutputAmount2(outputAmount2)) {
                this.removeItem(INPUTSLOT2, 1);
                stacks.set(OUTPUTSLOT2, new ItemStack(ModItems.COPPERDUST.get(), stacks.get(OUTPUTSLOT2).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT2).is(Items.RAW_GOLD)) {
            if (canInsertOutputItem2(ModItems.GOLDDUST.get()) && canInsertOutputAmount2(outputAmount2)) {
                this.removeItem(INPUTSLOT2, 1);
                stacks.set(OUTPUTSLOT2, new ItemStack(ModItems.GOLDDUST.get(), stacks.get(OUTPUTSLOT2).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT2).is(Items.OBSIDIAN)) {
            if (canInsertOutputItem2(ModItems.OBSIDAINDUST.get()) && canInsertOutputAmount2(outputAmount2)) {
                this.removeItem(INPUTSLOT2, 1);
                stacks.set(OUTPUTSLOT2, new ItemStack(ModItems.OBSIDAINDUST.get(), stacks.get(OUTPUTSLOT2).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT2).is(Items.ANCIENT_DEBRIS)) {
            if (canInsertOutputItem2(ModItems.NETHERITEDUST.get()) && canInsertOutputAmount2(outputAmount2)) {
                this.removeItem(INPUTSLOT2, 1);
                stacks.set(OUTPUTSLOT2, new ItemStack(ModItems.NETHERITEDUST.get(), stacks.get(OUTPUTSLOT2).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT2).is(ModItems.NEHTERITE_SHARD_RAW.get())) {
            if (canInsertOutputItem2(ModItems.NEHTERITE_SHARD_DUST.get()) && canInsertOutputAmount2(outputAmount2)) {
                this.removeItem(INPUTSLOT2, 1);
                stacks.set(OUTPUTSLOT2, new ItemStack(ModItems.NEHTERITE_SHARD_DUST.get(), stacks.get(OUTPUTSLOT2).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT2).is(Items.BLAZE_ROD)) {
            if (canInsertOutputItem2(Items.BLAZE_POWDER) && canInsertOutputAmount2(outputAmount2)) {
                this.removeItem(INPUTSLOT2, 1);
                stacks.set(OUTPUTSLOT2, new ItemStack(Items.BLAZE_POWDER, stacks.get(OUTPUTSLOT2).getCount() + 3));
            }
        }
        if (stacks.get(INPUTSLOT2).is(Items.BONE)) {
            if (canInsertOutputItem2(Items.BONE_MEAL) && canInsertOutputAmount2(outputAmount2)) {
                this.removeItem(INPUTSLOT2, 1);
                stacks.set(OUTPUTSLOT2, new ItemStack(Items.BONE_MEAL, stacks.get(OUTPUTSLOT2).getCount() + 4));
            }
        }
        if (stacks.get(INPUTSLOT2).is(ItemTags.WOOL)) {
            if (canInsertOutputItem2(Items.STRING) && canInsertOutputAmount2(outputAmount2)) {
                this.removeItem(INPUTSLOT2, 1);
                stacks.set(OUTPUTSLOT2, new ItemStack(Items.STRING, stacks.get(OUTPUTSLOT2).getCount() + 5));
            }
        }
        if (stacks.get(INPUTSLOT2).is(ItemTags.LOGS)) {
            if (canInsertOutputItem2(ModItems.WOODFIBER.get()) && canInsertOutputAmount2(outputAmount2)) {
                this.removeItem(INPUTSLOT2, 1);
                stacks.set(OUTPUTSLOT2, new ItemStack(ModItems.WOODFIBER.get(), stacks.get(OUTPUTSLOT2).getCount() + 8));
            }
        }
        if (stacks.get(INPUTSLOT2).is(ModBlocks.UNOBTANIUM_ORE.get().asItem())) {
            if (canInsertOutputItem2(ModItems.UNOBTIANIUMDUST.get()) && canInsertOutputAmount2(outputAmount2)) {
                this.removeItem(INPUTSLOT2, 1);
                stacks.set(OUTPUTSLOT2, new ItemStack(ModItems.UNOBTIANIUMDUST.get(), stacks.get(OUTPUTSLOT2).getCount() + 2));
            }
        }
    }
    private void craftItem3() {
        if (stacks.get(INPUTSLOT3).is(Items.RAW_IRON)) {
            if (canInsertOutputItem3(ModItems.IRONDUST.get()) && canInsertOutputAmount3(outputAmount3)) {
                this.removeItem(INPUTSLOT3, 1);
                stacks.set(OUTPUTSLOT3, new ItemStack(ModItems.IRONDUST.get(), stacks.get(OUTPUTSLOT3).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT3).is(Items.RAW_COPPER)) {
            if (canInsertOutputItem3(ModItems.COPPERDUST.get()) && canInsertOutputAmount3(outputAmount3)) {
                this.removeItem(INPUTSLOT3, 1);
                stacks.set(OUTPUTSLOT3, new ItemStack(ModItems.COPPERDUST.get(), stacks.get(OUTPUTSLOT3).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT3).is(Items.RAW_GOLD)) {
            if (canInsertOutputItem3(ModItems.GOLDDUST.get()) && canInsertOutputAmount3(outputAmount3)) {
                this.removeItem(INPUTSLOT3, 1);
                stacks.set(OUTPUTSLOT3, new ItemStack(ModItems.GOLDDUST.get(), stacks.get(OUTPUTSLOT3).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT3).is(Items.OBSIDIAN)) {
            if (canInsertOutputItem3(ModItems.OBSIDAINDUST.get()) && canInsertOutputAmount3(outputAmount3)) {
                this.removeItem(INPUTSLOT3, 1);
                stacks.set(OUTPUTSLOT3, new ItemStack(ModItems.OBSIDAINDUST.get(), stacks.get(OUTPUTSLOT3).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT3).is(Items.ANCIENT_DEBRIS)) {
            if (canInsertOutputItem3(ModItems.NETHERITEDUST.get()) && canInsertOutputAmount3(outputAmount3)) {
                this.removeItem(INPUTSLOT3, 1);
                stacks.set(OUTPUTSLOT3, new ItemStack(ModItems.NETHERITEDUST.get(), stacks.get(OUTPUTSLOT3).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT3).is(ModItems.NEHTERITE_SHARD_RAW.get())) {
            if (canInsertOutputItem3(ModItems.NEHTERITE_SHARD_DUST.get()) && canInsertOutputAmount3(outputAmount3)) {
                this.removeItem(INPUTSLOT3, 1);
                stacks.set(OUTPUTSLOT3, new ItemStack(ModItems.NEHTERITE_SHARD_DUST.get(), stacks.get(OUTPUTSLOT3).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT3).is(Items.BLAZE_ROD)) {
            if (canInsertOutputItem3(Items.BLAZE_POWDER) && canInsertOutputAmount3(outputAmount3)) {
                this.removeItem(INPUTSLOT3, 1);
                stacks.set(OUTPUTSLOT3, new ItemStack(Items.BLAZE_POWDER, stacks.get(OUTPUTSLOT3).getCount() + 3));
            }
        }
        if (stacks.get(INPUTSLOT3).is(Items.BONE)) {
            if (canInsertOutputItem3(Items.BONE_MEAL) && canInsertOutputAmount3(outputAmount3)) {
                this.removeItem(INPUTSLOT3, 1);
                stacks.set(OUTPUTSLOT3, new ItemStack(Items.BONE_MEAL, stacks.get(OUTPUTSLOT3).getCount() + 4));
            }
        }
        if (stacks.get(INPUTSLOT3).is(ItemTags.WOOL)) {
            if (canInsertOutputItem3(Items.STRING) && canInsertOutputAmount3(outputAmount3)) {
                this.removeItem(INPUTSLOT3, 1);
                stacks.set(OUTPUTSLOT3, new ItemStack(Items.STRING, stacks.get(OUTPUTSLOT3).getCount() + 5));
            }
        }
        if (stacks.get(INPUTSLOT3).is(ItemTags.LOGS)) {
            if (canInsertOutputItem3(ModItems.WOODFIBER.get()) && canInsertOutputAmount3(outputAmount3)) {
                this.removeItem(INPUTSLOT3, 1);
                stacks.set(OUTPUTSLOT3, new ItemStack(ModItems.WOODFIBER.get(), stacks.get(OUTPUTSLOT3).getCount() + 8));
            }
        }
        if (stacks.get(INPUTSLOT3).is(ModBlocks.UNOBTANIUM_ORE.get().asItem())) {
            if (canInsertOutputItem3(ModItems.UNOBTIANIUMDUST.get()) && canInsertOutputAmount3(outputAmount3)) {
                this.removeItem(INPUTSLOT3, 1);
                stacks.set(OUTPUTSLOT3, new ItemStack(ModItems.UNOBTIANIUMDUST.get(), stacks.get(OUTPUTSLOT3).getCount() + 2));
            }
        }
    }
    private void craftItem4() {
        if (stacks.get(INPUTSLOT4).is(Items.RAW_IRON)) {
            if (canInsertOutputItem4(ModItems.IRONDUST.get()) && canInsertOutputAmount4(outputAmount4)) {
                this.removeItem(INPUTSLOT4, 1);
                stacks.set(OUTPUTSLOT4, new ItemStack(ModItems.IRONDUST.get(), stacks.get(OUTPUTSLOT4).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT4).is(Items.RAW_COPPER)) {
            if (canInsertOutputItem4(ModItems.COPPERDUST.get()) && canInsertOutputAmount4(outputAmount4)) {
                this.removeItem(INPUTSLOT4, 1);
                stacks.set(OUTPUTSLOT4, new ItemStack(ModItems.COPPERDUST.get(), stacks.get(OUTPUTSLOT4).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT4).is(Items.RAW_GOLD)) {
            if (canInsertOutputItem4(ModItems.GOLDDUST.get()) && canInsertOutputAmount4(outputAmount4)) {
                this.removeItem(INPUTSLOT4, 1);
                stacks.set(OUTPUTSLOT4, new ItemStack(ModItems.GOLDDUST.get(), stacks.get(OUTPUTSLOT4).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT4).is(Items.OBSIDIAN)) {
            if (canInsertOutputItem4(ModItems.OBSIDAINDUST.get()) && canInsertOutputAmount4(outputAmount4)) {
                this.removeItem(INPUTSLOT4, 1);
                stacks.set(OUTPUTSLOT4, new ItemStack(ModItems.OBSIDAINDUST.get(), stacks.get(OUTPUTSLOT4).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT4).is(Items.ANCIENT_DEBRIS)) {
            if (canInsertOutputItem4(ModItems.NETHERITEDUST.get()) && canInsertOutputAmount4(outputAmount4)) {
                this.removeItem(INPUTSLOT4, 1);
                stacks.set(OUTPUTSLOT4, new ItemStack(ModItems.NETHERITEDUST.get(), stacks.get(OUTPUTSLOT4).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT4).is(ModItems.NEHTERITE_SHARD_RAW.get())) {
            if (canInsertOutputItem4(ModItems.NEHTERITE_SHARD_DUST.get()) && canInsertOutputAmount4(outputAmount4)) {
                this.removeItem(INPUTSLOT4, 1);
                stacks.set(OUTPUTSLOT4, new ItemStack(ModItems.NEHTERITE_SHARD_DUST.get(), stacks.get(OUTPUTSLOT4).getCount() + 2));
            }
        }
        if (stacks.get(INPUTSLOT4).is(Items.BLAZE_ROD)) {
            if (canInsertOutputItem4(Items.BLAZE_POWDER) && canInsertOutputAmount4(outputAmount4)) {
                this.removeItem(INPUTSLOT4, 1);
                stacks.set(OUTPUTSLOT4, new ItemStack(Items.BLAZE_POWDER, stacks.get(OUTPUTSLOT4).getCount() + 3));
            }
        }
        if (stacks.get(INPUTSLOT4).is(Items.BONE)) {
            if (canInsertOutputItem4(Items.BONE_MEAL) && canInsertOutputAmount4(outputAmount4)) {
                this.removeItem(INPUTSLOT4, 1);
                stacks.set(OUTPUTSLOT4, new ItemStack(Items.BONE_MEAL, stacks.get(OUTPUTSLOT4).getCount() + 4));
            }
        }
        if (stacks.get(INPUTSLOT4).is(ItemTags.WOOL)) {
            if (canInsertOutputItem4(Items.STRING) && canInsertOutputAmount4(outputAmount4)) {
                this.removeItem(INPUTSLOT4, 1);
                stacks.set(OUTPUTSLOT4, new ItemStack(Items.STRING, stacks.get(OUTPUTSLOT4).getCount() + 5));
            }
        }
        if (stacks.get(INPUTSLOT4).is(ItemTags.LOGS)) {
            if (canInsertOutputItem4(ModItems.WOODFIBER.get()) && canInsertOutputAmount4(outputAmount4)) {
                this.removeItem(INPUTSLOT4, 1);
                stacks.set(OUTPUTSLOT4, new ItemStack(ModItems.WOODFIBER.get(), stacks.get(OUTPUTSLOT4).getCount() + 8));
            }
        }
        if (stacks.get(INPUTSLOT4).is(ModBlocks.UNOBTANIUM_ORE.get().asItem())) {
            if (canInsertOutputItem4(ModItems.UNOBTIANIUMDUST.get()) && canInsertOutputAmount4(outputAmount4)) {
                this.removeItem(INPUTSLOT4, 1);
                stacks.set(OUTPUTSLOT4, new ItemStack(ModItems.UNOBTIANIUMDUST.get(), stacks.get(OUTPUTSLOT4).getCount() + 2));
            }
        }
    }
    private void doubleCrafting1(){
        outputAmount1 = 2;
    }
    private void trippleCrafting1(){
        outputAmount1 = 3;
    }
    private void fourCrafting1(){
        outputAmount1 = 4;
    }
    private void fiveCrafting1(){
        outputAmount1 = 5;
    }
    private void fiberCrafting1(){
        outputAmount1 = 8;
    }
    private void doubleCrafting2(){
        outputAmount2 = 2;
    }
    private void trippleCrafting2(){
        outputAmount2 = 3;
    }
    private void fourCrafting2(){
        outputAmount2 = 4;
    }
    private void fiveCrafting2(){
        outputAmount2 = 5;
    }
    private void fiberCrafting2(){
        outputAmount2 = 8;
    }
    private void doubleCrafting3(){
        outputAmount3 = 2;
    }
    private void trippleCrafting3(){
        outputAmount3 = 3;
    }
    private void fourCrafting3(){
        outputAmount3 = 4;
    }
    private void fiveCrafting3(){
        outputAmount3 = 5;
    }
    private void fiberCrafting3(){
        outputAmount3 = 8;
    }
    private void doubleCrafting4(){
        outputAmount4 = 2;
    }
    private void trippleCrafting4(){
        outputAmount4 = 3;
    }
    private void fourCrafting4(){
        outputAmount4 = 4;
    }
    private void fiveCrafting4(){
        outputAmount4 = 5;
    }
    private void fiberCrafting4(){
        outputAmount4 = 8;
    }
    private boolean hasRecipe1() {
        if(this.stacks.get(INPUTSLOT1).is(ModTags.Items.CANGRIND)){
            if(this.stacks.get(INPUTSLOT1).is(Items.RAW_GOLD) || this.stacks.get(INPUTSLOT1).is(Items.RAW_IRON) ||this.stacks.get(INPUTSLOT1).is(Items.RAW_COPPER) ||this.stacks.get(INPUTSLOT1).is(Items.OBSIDIAN) ||
                    this.stacks.get(INPUTSLOT1).is(Items.ANCIENT_DEBRIS) ||this.stacks.get(INPUTSLOT1).is(ModItems.NEHTERITE_SHARD_RAW.get()) || this.stacks.get(INPUTSLOT1).is(ModBlocks.UNOBTANIUM_ORE.get().asItem())){
                doubleCrafting1();
            }
            if(this.stacks.get(INPUTSLOT1).is(ItemTags.IRON_ORES) || this.stacks.get(INPUTSLOT1).is(ItemTags.COPPER_ORES) ||this.stacks.get(INPUTSLOT1).is(ModTags.Items.RAW_GOLD_DROPPER) ||this.stacks.get(INPUTSLOT1).is(Items.BLAZE_ROD)){
                trippleCrafting1();
            }
            if(this.stacks.get(INPUTSLOT1).is(Items.BONE)){
                fourCrafting1();
            }
            if(this.stacks.get(INPUTSLOT1).is(ItemTags.WOOL)){
                fiveCrafting1();
            }
            if(this.stacks.get(INPUTSLOT1).is(ItemTags.LOGS)){
                fiberCrafting1();
            } return true;
        }
        return false;
}
    private boolean hasRecipe2() {
        if(this.stacks.get(INPUTSLOT2).is(ModTags.Items.CANGRIND)){
            if(this.stacks.get(INPUTSLOT2).is(Items.RAW_GOLD) || this.stacks.get(INPUTSLOT2).is(Items.RAW_IRON) ||this.stacks.get(INPUTSLOT2).is(Items.RAW_COPPER) ||this.stacks.get(INPUTSLOT2).is(Items.OBSIDIAN) ||
                    this.stacks.get(INPUTSLOT2).is(Items.ANCIENT_DEBRIS) ||this.stacks.get(INPUTSLOT2).is(ModItems.NEHTERITE_SHARD_RAW.get()) || this.stacks.get(INPUTSLOT2).is(ModBlocks.UNOBTANIUM_ORE.get().asItem())){
                doubleCrafting2();
            }
            if(this.stacks.get(INPUTSLOT2).is(ItemTags.IRON_ORES) || this.stacks.get(INPUTSLOT2).is(ItemTags.COPPER_ORES) ||this.stacks.get(INPUTSLOT2).is(ModTags.Items.RAW_GOLD_DROPPER) ||this.stacks.get(INPUTSLOT2).is(Items.BLAZE_ROD)){
                trippleCrafting2();
            }
            if(this.stacks.get(INPUTSLOT2).is(Items.BONE)){
                fourCrafting2();
            }
            if(this.stacks.get(INPUTSLOT2).is(ItemTags.WOOL)){
                fiveCrafting2();
            }
            if(this.stacks.get(INPUTSLOT2).is(ItemTags.LOGS)){
                fiberCrafting2();
            } return true;
        }
        return false;
    }
    private boolean hasRecipe3() {
        if(this.stacks.get(INPUTSLOT3).is(ModTags.Items.CANGRIND)){
            if(this.stacks.get(INPUTSLOT3).is(Items.RAW_GOLD) || this.stacks.get(INPUTSLOT3).is(Items.RAW_IRON) ||this.stacks.get(INPUTSLOT3).is(Items.RAW_COPPER) ||this.stacks.get(INPUTSLOT3).is(Items.OBSIDIAN) ||
                    this.stacks.get(INPUTSLOT3).is(Items.ANCIENT_DEBRIS) ||this.stacks.get(INPUTSLOT3).is(ModItems.NEHTERITE_SHARD_RAW.get()) || this.stacks.get(INPUTSLOT3).is(ModBlocks.UNOBTANIUM_ORE.get().asItem())){
                doubleCrafting3();
            }
            if(this.stacks.get(INPUTSLOT3).is(ItemTags.IRON_ORES) || this.stacks.get(INPUTSLOT3).is(ItemTags.COPPER_ORES) ||this.stacks.get(INPUTSLOT3).is(ModTags.Items.RAW_GOLD_DROPPER) ||this.stacks.get(INPUTSLOT3).is(Items.BLAZE_ROD)){
                trippleCrafting3();
            }
            if(this.stacks.get(INPUTSLOT3).is(Items.BONE)){
                fourCrafting3();
            }
            if(this.stacks.get(INPUTSLOT3).is(ItemTags.WOOL)){
                fiveCrafting3();
            }
            if(this.stacks.get(INPUTSLOT3).is(ItemTags.LOGS)){
                fiberCrafting3();
            } return true;
        }
        return false;
    }
    private boolean hasRecipe4() {
        if(this.stacks.get(INPUTSLOT4).is(ModTags.Items.CANGRIND)){
            if(this.stacks.get(INPUTSLOT4).is(Items.RAW_GOLD) || this.stacks.get(INPUTSLOT4).is(Items.RAW_IRON) ||this.stacks.get(INPUTSLOT4).is(Items.RAW_COPPER) ||this.stacks.get(INPUTSLOT4).is(Items.OBSIDIAN) ||
                    this.stacks.get(INPUTSLOT4).is(Items.ANCIENT_DEBRIS) ||this.stacks.get(INPUTSLOT4).is(ModItems.NEHTERITE_SHARD_RAW.get()) || this.stacks.get(INPUTSLOT4).is(ModBlocks.UNOBTANIUM_ORE.get().asItem())){
                doubleCrafting4();
            }
            if(this.stacks.get(INPUTSLOT4).is(ItemTags.IRON_ORES) || this.stacks.get(INPUTSLOT4).is(ItemTags.COPPER_ORES) ||this.stacks.get(INPUTSLOT4).is(ModTags.Items.RAW_GOLD_DROPPER) ||this.stacks.get(INPUTSLOT4).is(Items.BLAZE_ROD)){
                trippleCrafting4();
            }
            if(this.stacks.get(INPUTSLOT4).is(Items.BONE)){
                fourCrafting4();
            }
            if(this.stacks.get(INPUTSLOT4).is(ItemTags.WOOL)){
                fiveCrafting4();
            }
            if(this.stacks.get(INPUTSLOT4).is(ItemTags.LOGS)){
                fiberCrafting4();
            } return true;
        }
        return false;
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