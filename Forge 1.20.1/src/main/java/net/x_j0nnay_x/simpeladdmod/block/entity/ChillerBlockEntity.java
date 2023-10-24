package net.x_j0nnay_x.simpeladdmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.x_j0nnay_x.simpeladdmod.block.ModBlockEntities;

import net.x_j0nnay_x.simpeladdmod.block.custom.ChillerBlock;
import net.x_j0nnay_x.simpeladdmod.screen.Chiller.ChillerMenu;
import net.x_j0nnay_x.simpeladdmod.until.ModTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;


public class ChillerBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3){
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot){
                case 0 -> stack.is(ModTags.Items.CHILLING);
                case 1 -> stack.getItem() == Items.WATER_BUCKET;
                case 2 -> false;
                default ->  super.isItemValid(slot, stack);

            };
        }
    };
    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
            Map.of(Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 2, (i, s) -> false)),
                    Direction.UP, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 1,
                            (index, stack) -> itemHandler.isItemValid(WATERSLOT, stack))),
                    Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 0,
                            (index, stack) -> itemHandler.isItemValid(CHILLINGSLOT, stack))),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 0,
                            (index, stack) -> itemHandler.isItemValid(CHILLINGSLOT, stack))),
                    Direction.EAST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 0,
                            (index, stack) -> itemHandler.isItemValid(CHILLINGSLOT, stack))),
                    Direction.WEST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 0,
                            (index, stack) -> itemHandler.isItemValid(CHILLINGSLOT, stack))));


    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    public static int CHILLINGSLOT = 0;
    public static int WATERSLOT = 1;
    public static int OUTPUTSLOT = 2;
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 60;
    private int snowLevel = 0;
    private int waterLevel = 0;
    private int waterUese = 0;
    public ChillerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.CHILLER.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> ChillerBlockEntity.this.progress;
                    case 1 -> ChillerBlockEntity.this.maxProgress;
                    case 2 -> ChillerBlockEntity.this.snowLevel;
                    case 3 -> ChillerBlockEntity.this.waterLevel;
                    case 4 -> ChillerBlockEntity.this.waterUese;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> ChillerBlockEntity.this.progress = pValue;
                    case 1 -> ChillerBlockEntity.this.maxProgress = pValue;
                    case 2 -> ChillerBlockEntity.this.snowLevel = pValue;
                    case 3 -> ChillerBlockEntity.this.waterLevel = pValue;
                    case 4 -> ChillerBlockEntity.this.waterUese = pValue;

            }
            }

            @Override
            public int getCount() {
                return 5;
            }
        };
    }
    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++){
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            if(side == null) {
                return lazyItemHandler.cast();
            }

            if(directionWrappedHandlerMap.containsKey(side)) {
                Direction localDir = this.getBlockState().getValue(ChillerBlock.FACING);

                if(side == Direction.UP || side == Direction.DOWN) {
                    return directionWrappedHandlerMap.get(side).cast();
                }

                return switch (localDir) {
                    default -> directionWrappedHandlerMap.get(side.getOpposite()).cast();
                    case EAST -> directionWrappedHandlerMap.get(side.getClockWise()).cast();
                    case SOUTH -> directionWrappedHandlerMap.get(side).cast();
                    case WEST -> directionWrappedHandlerMap.get(side.getCounterClockWise()).cast();
                };
            }
        }
        return super.getCapability(cap, side);
    }


    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }


    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new ChillerMenu(pContainerId,pPlayerInventory, this, this.data);
    }



    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("chiller_progress", progress);
        pTag.putInt("chiller_snow", snowLevel);
        pTag.putInt("chiller_water", waterLevel);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("chiller_progress");
        snowLevel = pTag.getInt("chiller_snow");
        waterLevel = pTag.getInt("chiller_water");
    }
    public void tick(Level pLevel, BlockPos pPos, BlockState pState){
        if(canFillWater()){
            fillWater();
        }
        if(canFillSnow()){
            fillSnow();
        }
        if(waterUese == 0){
            setWaterUese();
        }
        if(hasSnow()){
            if(hasWater()) {
                if (hasSpace()) {
                    increaseCraftingProgress();
                    if (hasProgressFinished()) {
                        useContent();
                        craftItem();
                        resetProgress();
                    }
                }
            }
        }else{
            resetProgress();
        }

    }
    private void  useContent(){
        this.waterUese --;
        this.snowLevel --;
    }
    public void  fillSnow(){
        if (canFillSnow()){
            if (snowLevel < 20) {
                if (this.itemHandler.getStackInSlot(CHILLINGSLOT).is(Items.SNOWBALL)) {
                    this.itemHandler.extractItem(CHILLINGSLOT, 1, false);
                    snowLevel += 1;
                }
            }
                if(snowLevel <= 16){
                    if(this.itemHandler.getStackInSlot(CHILLINGSLOT).is(Items.SNOW_BLOCK)){
                        this.itemHandler.extractItem(CHILLINGSLOT, 1, false);
                        snowLevel += 4;
                    }
                }
                if (snowLevel <= 12){
                    if(this.itemHandler.getStackInSlot(CHILLINGSLOT).is(Items.ICE)){
                        this.itemHandler.extractItem(CHILLINGSLOT, 1 ,false);
                        snowLevel += 8;
                    }
                }
                if (snowLevel <= 8){
                    if(this.itemHandler.getStackInSlot(CHILLINGSLOT).is(Items.PACKED_ICE)){
                        this.itemHandler.extractItem(CHILLINGSLOT, 1, false);
                        snowLevel += 12;
                    }
                }
            if (snowLevel <= 1){
                if(this.itemHandler.getStackInSlot(CHILLINGSLOT).is(Items.BLUE_ICE)){
                    this.itemHandler.extractItem(CHILLINGSLOT, 1, false);
                    snowLevel += 19;
                }
            }
        }
    }
    private void  fillWater(){
        if(canFillWater()){
        if(this.itemHandler.getStackInSlot(WATERSLOT).is(Items.WATER_BUCKET)){
            this.itemHandler.extractItem(WATERSLOT, 1, false);
            this.itemHandler.setStackInSlot(WATERSLOT, new ItemStack(Items.BUCKET));
            this.waterLevel += 1;
        }}
    }
    private boolean canFillWater() {
        return this.waterLevel < 6;
    }
    private boolean canFillSnow() {
        return this.snowLevel < 20;
    }
    private void setWaterUese(){
        if(this.waterLevel > 0){
            this.waterLevel --;
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
        this.itemHandler.setStackInSlot(OUTPUTSLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUTSLOT).getCount() + result.getCount()));
    }
    private boolean hasSpace(){
        return this.itemHandler.getStackInSlot(OUTPUTSLOT).getCount() < 64;
    }
    private boolean hasSnow() {
        return this.snowLevel >0;
    }
    private boolean hasWater() {
        return this.waterLevel >0 || this.waterUese > 0;
    }


    @Override
    public Component getDisplayName() {
        return Component.translatable("block.simpeladdmod.chiller_block");
    }
}
