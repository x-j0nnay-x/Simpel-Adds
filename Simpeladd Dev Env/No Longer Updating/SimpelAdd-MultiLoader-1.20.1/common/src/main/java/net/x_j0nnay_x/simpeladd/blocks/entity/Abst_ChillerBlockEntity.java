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
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.blocks.Abst_ChillerBlock;
import net.x_j0nnay_x.simpeladd.blocks.Abst_FurnaceBlock_Up;
import org.jetbrains.annotations.Nullable;

public abstract class Abst_ChillerBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {

    protected NonNullList<ItemStack> stacks = NonNullList.withSize(3, ItemStack.EMPTY);

    public static int CHILLINGSLOT = 0;
    public static int WATERSLOT = 1;
    public static int OUTPUTSLOT = 2;
    private static final int[] SLOTS_FOR_UP = new int[]{CHILLINGSLOT};
    private static final int[] SLOTS_FOR_DOWN = new int[]{OUTPUTSLOT};
    private static final int[] SLOTS_FOR_SIDES = new int[]{WATERSLOT};
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 60;
    private int snowLevel = 0;
    public int waterUese = 0;
    public int waterLevel = 0;
    private int bucketValue = 1000;

    protected Abst_ChillerBlockEntity(BlockEntityType<?> $$0, BlockPos $$1, BlockState $$2) {
        super($$0, $$1, $$2);

        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> Abst_ChillerBlockEntity.this.progress;
                    case 1 -> Abst_ChillerBlockEntity.this.maxProgress;
                    case 2 -> Abst_ChillerBlockEntity.this.snowLevel;
                    case 3 -> Abst_ChillerBlockEntity.this.waterUese;
                    case 4 -> Abst_ChillerBlockEntity.this.waterLevel;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> Abst_ChillerBlockEntity.this.progress = pValue;
                    case 1 -> Abst_ChillerBlockEntity.this.maxProgress = pValue;
                    case 2 -> Abst_ChillerBlockEntity.this.snowLevel = pValue;
                    case 3 -> Abst_ChillerBlockEntity.this.waterUese = pValue;
                    case 4 -> Abst_ChillerBlockEntity.this.waterLevel = pValue;
                }
            }

            @Override
            public int getCount() {
                return 5;
            }
        };
    }


    @Override
    public void load(CompoundTag $$0) {
        super.load($$0);
        this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems($$0, this.stacks);
        this.progress = $$0.getShort(SimpelAddMod.MODCUSTOM + "chiller_progress");
        this.snowLevel = $$0.getShort(SimpelAddMod.MODCUSTOM + "chiller_snow");
        this.waterUese = $$0.getShort(SimpelAddMod.MODCUSTOM + "chiller_wateruse");
    }

    @Override
    protected void saveAdditional(CompoundTag $$0) {
        super.saveAdditional($$0);
        $$0.putShort(SimpelAddMod.MODCUSTOM + "chiller_progress", (short) this.progress);
        $$0.putShort(SimpelAddMod.MODCUSTOM + "chiller_snow", (short) this.snowLevel);
        $$0.putShort(SimpelAddMod.MODCUSTOM + "chiller_wateruse", (short) this.waterUese);
        ContainerHelper.saveAllItems($$0, this.stacks);
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return ((direction == Direction.EAST || direction == Direction.WEST || direction == Direction.SOUTH || direction == Direction.NORTH) &&
                (index == CHILLINGSLOT) ||
                direction == Direction.UP && (index == WATERSLOT));
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack var2, Direction direction) {
        return (direction == Direction.DOWN && (index == OUTPUTSLOT ||
                index == WATERSLOT && var2.is(Items.BUCKET)));
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
        return Component.translatable("block.simpeladdmod.chiller_block");
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

    public void chillerTick(Level pLevel, BlockPos pPos, BlockState pState) {
        if (canFillWater()) {
            fillWater();
        }
        if (canFillSnow()) {
            fillSnow();
        }
        if (waterUese == 0) {
            setWaterUese();
        }
        pState = pState.setValue(Abst_ChillerBlock.WORKING, Boolean.valueOf(isWorking()));
        pLevel.setBlock(pPos, pState, 3);
        if (hasSnow()) {
            if (hasWater()) {
                if (hasSpace()) {
                    increaseCraftingProgress();
                    if (hasProgressFinished()) {
                        useContent();
                        craftItem();
                        resetProgress();
                    }
                }
            }
        } else {
            resetProgress();
        }

    }
    private boolean isWorking(){
        return hasSnow() && hasWater() && hasSpace();
    }
    private void  useContent(){
        this.waterUese --;
        this.snowLevel --;
    }
    public void  fillSnow(){
        if (canFillSnow()){
            if (snowLevel < 20) {
                if (this.stacks.get(CHILLINGSLOT).is(Items.SNOWBALL)) {
                    this.removeItem(CHILLINGSLOT, 1);
                    snowLevel += 1;
                }
            }
            if(snowLevel <= 16){
                if(this.stacks.get(CHILLINGSLOT).is(Items.SNOW_BLOCK)){
                    this.removeItem(CHILLINGSLOT, 1);
                    snowLevel += 4;
                }
            }
            if (snowLevel <= 12){
                if(this.stacks.get(CHILLINGSLOT).is(Items.ICE)){
                    this.removeItem(CHILLINGSLOT, 1);
                    snowLevel += 8;
                }
            }
            if (snowLevel <= 8){
                if(this.stacks.get(CHILLINGSLOT).is(Items.PACKED_ICE)){
                    this.removeItem(CHILLINGSLOT, 1);
                    snowLevel += 12;
                }
            }
            if (snowLevel <= 1){
                if(this.stacks.get(CHILLINGSLOT).is(Items.BLUE_ICE)){
                    this.removeItem(CHILLINGSLOT, 1);
                    snowLevel += 19;
                }
            }
        }
    }
    public void  fillWater(){
        if(this.stacks.get(WATERSLOT).getItem() == (Items.WATER_BUCKET)){
            this.removeItem(WATERSLOT, 1);
            this.stacks.set(WATERSLOT, new ItemStack(Items.BUCKET));
            waterLevel += bucketValue;
        }
    }
    public boolean canFillWater() {
        return this.waterLevel < bucketValue * 10;
    }
    private boolean canFillSnow() {
        return this.snowLevel < 20;
    }
    public void setWaterUese(){
        if(this.waterLevel > 0){
            this.waterLevel -= bucketValue;
            this.waterUese = 10;
        }

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
        ItemStack result = new ItemStack(Items.ICE, 1);
        this.stacks.set(OUTPUTSLOT, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT).getCount() + result.getCount()));
    }
    private boolean hasSpace(){
        return this.stacks.get(OUTPUTSLOT).getCount() < 64;
    }
    private boolean hasSnow() {
        return this.snowLevel >0;
    }
    public boolean hasWater() {
        return this.waterLevel >0 || this.waterUese > 0;
    }



}