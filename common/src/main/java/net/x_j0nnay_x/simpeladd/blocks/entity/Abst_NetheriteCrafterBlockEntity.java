package net.x_j0nnay_x.simpeladd.blocks.entity;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.blocks.Abst_NetheriteCrafterBlock;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import org.jetbrains.annotations.Nullable;

public abstract class Abst_NetheriteCrafterBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {

    protected NonNullList<ItemStack> stacks = NonNullList.withSize(5, ItemStack.EMPTY);
    public static int SCRAPSLOT = 0;
    public static int GOLDSLOT = 1;
    public static int BLAZESLOT = 2;
    public static int OUTPUTSLOT = 3;
    public static int UPGRADESLOT = 4;
    private static final int[] SLOTS_FOR_UP = new int[]{SCRAPSLOT, GOLDSLOT, BLAZESLOT};
    private static final int[] SLOTS_FOR_DOWN = new int[]{OUTPUTSLOT};
    private static final int[] SLOTS_FOR_SIDES = new int[]{SCRAPSLOT, GOLDSLOT, BLAZESLOT};
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 90;
    private int blazeUse = 0;
    private int  maxBlazeuse = 4;

    protected Abst_NetheriteCrafterBlockEntity(BlockEntityType<?> $$0, BlockPos $$1, BlockState $$2) {
        super($$0, $$1, $$2);

        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> Abst_NetheriteCrafterBlockEntity.this.progress;
                    case 1 -> Abst_NetheriteCrafterBlockEntity.this.maxProgress;
                    case 2 -> Abst_NetheriteCrafterBlockEntity.this.blazeUse;
                    case 3 -> Abst_NetheriteCrafterBlockEntity.this.maxBlazeuse;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> Abst_NetheriteCrafterBlockEntity.this.progress = pValue;
                    case 1 -> Abst_NetheriteCrafterBlockEntity.this.maxProgress = pValue;
                    case 2 -> Abst_NetheriteCrafterBlockEntity.this.blazeUse = pValue;
                    case 3 -> Abst_NetheriteCrafterBlockEntity.this.maxBlazeuse = pValue;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public void load(CompoundTag $$0) {
        super.load($$0);
        this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems($$0, this.stacks);
        this.progress = $$0.getShort(SimpelAddMod.MODCUSTOM +"netherite_crafter_progress");
        this.blazeUse = $$0.getShort(SimpelAddMod.MODCUSTOM +"netherite_crafter_blaze_uses");
    }

    @Override
    protected void saveAdditional(CompoundTag $$0) {
        super.saveAdditional($$0);
        $$0.putShort(SimpelAddMod.MODCUSTOM +"netherite_crafter_progress", (short)this.progress);
        $$0.putShort(SimpelAddMod.MODCUSTOM +"netherite_crafter_blaze_uses", (short)this.blazeUse);
        ContainerHelper.saveAllItems($$0, this.stacks);
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        if (index == SCRAPSLOT && stack.is(Items.NETHERITE_SCRAP))
            return true;
        if (index == GOLDSLOT && stack.is(Items.GOLD_INGOT))
            return true;
        if (index == BLAZESLOT && stack.is(Items.BLAZE_ROD))
            return true;
        return false;
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack var2, @Nullable Direction direction) {
        if(direction == Direction.EAST || direction == Direction.WEST || direction == Direction.SOUTH || direction == Direction.NORTH || direction == Direction.UP){
            if(index == GOLDSLOT && var2.is(Items.GOLD_INGOT)){
                return true;
            }
            if(index == SCRAPSLOT && var2.is(Items.NETHERITE_SCRAP)){
                return true;
            }
            if(index == BLAZESLOT && var2.is(Items.BLAZE_ROD)){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack var2, Direction direction) {
        if(direction == Direction.DOWN){
            if(index == OUTPUTSLOT){
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
        return Component.translatable("block.simpeladdmod.netherite_crafter_block");
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
    public void netheriteCrafterTick(Level pLevel, BlockPos pPos, BlockState pState) {
        setUpgrades();
        pState = pState.setValue(Abst_NetheriteCrafterBlock.WORKING, Boolean.valueOf(isWorking()));
        pLevel.setBlock(pPos, pState, 3);
        if(hasRecipe()) {
            if (hasSpace() && blazeUse > 0) {
                increaseCraftingProgress();
                if (hasProgressFinished()) {
                    useContent();
                    craftItem();
                    resetProgress();
                }
            }else {
                refillBlaze();
            }
        }else{
            resetProgress();
        }
    }

    private void setUpgrades(){
        if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_1)) {
            this.maxProgress = 60;
        }if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_2)) {
            this.maxProgress = 36;
        }if (stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_3)) {
            this.maxProgress = 15;
        }if (this.stacks.get(UPGRADESLOT).is(ModItems.SPEEDUPGRADE_4)) {
            this.maxProgress = 5;
        }if (stacks.get(UPGRADESLOT).isEmpty()){
            this.maxProgress = 90;
        }
    }

    private void  useContent(){
        this.removeItem(SCRAPSLOT, 1);
        this.removeItem(GOLDSLOT, 1);
        blazeUse --;
    }

    private void  refillBlaze(){
        if(this.stacks.get(BLAZESLOT).is(Items.BLAZE_ROD)) {
            this.removeItem(BLAZESLOT, 1);
            blazeUse = maxBlazeuse;
        }else{
            blazeUse = 0;
        }
    }

    private boolean isWorking(){
        if(hasRecipe() && blazeUse > 0){
            return true;
        }
        return false;
    }

    private boolean hasRecipe(){
        if(this.stacks.get(SCRAPSLOT).is(Items.NETHERITE_SCRAP) && this.stacks.get(GOLDSLOT).is(Items.GOLD_INGOT)){
            return true;
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
        ItemStack result = new ItemStack(Items.NETHERITE_INGOT, 1);
        this.stacks.set(OUTPUTSLOT, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT).getCount() + result.getCount()));
    }

    private boolean hasSpace(){
        return this.stacks.get(OUTPUTSLOT).getCount() < 64;
    }
}
