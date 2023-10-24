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
import net.x_j0nnay_x.simpeladdmod.block.custom.BlockFactoryBlock;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.screen.BlockFactory.BlockFactoryMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Map;



public class BlockFactoryBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(7){
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot){
                case 0 -> stack.getItem() == ModItems.GRINDERHEAD.get();
                case 1, 2, 3, 4 -> false;
                case 5 -> stack.getItem() == Items.WATER_BUCKET;
                case 6 -> stack.getItem() == Items.LAVA_BUCKET;
                default ->  super.isItemValid(slot, stack);

            };
        }
    };
    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
            Map.of(
                    Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 5 || i == 6,
                            (index, stack) -> itemHandler.isItemValid(LAVASLOT, stack) || itemHandler.isItemValid(WATERSLOT, stack))),
                    Direction.UP, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 0,
                            (index, stack) -> itemHandler.isItemValid(0, stack))),
                    Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 5 || index == 6,
                            (index, stack) -> itemHandler.isItemValid(LAVASLOT, stack) || itemHandler.isItemValid(WATERSLOT, stack))),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 5 || index == 6,
                            (index, stack) -> itemHandler.isItemValid(LAVASLOT, stack) || itemHandler.isItemValid(WATERSLOT, stack))),
                    Direction.EAST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 5 || index == 6,
                            (index, stack) -> itemHandler.isItemValid(LAVASLOT, stack) || itemHandler.isItemValid(WATERSLOT, stack))),
                    Direction.WEST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 5 || index == 6,
                            (index, stack) -> itemHandler.isItemValid(LAVASLOT, stack) || itemHandler.isItemValid(WATERSLOT, stack))));

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public static int WATERSLOT = 5;
    public static int LAVASLOT = 6;

    public static int GRINDERSLOT = 0;
    public static int COBBLESLOT = 1;
    public static int GRAVALSLOT = 2;
    public static int SANDSLOT = 3;
    public static int OBSIDIANSLOT = 4;
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 35;
    private int lavaLevel = 0 ;
    private int waterLevel = 0;
    private int lavaUses = 0 ;
    private int maxLavaUses = 4;
    private int grindsleft = 0;
    private int maxGrinds = 3;
    public BlockFactoryBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.BLOCK_FACTORY.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> BlockFactoryBlockEntity.this.progress;
                    case 1 -> BlockFactoryBlockEntity.this.maxProgress;
                    case 2 -> BlockFactoryBlockEntity.this.lavaLevel;
                    case 3 -> BlockFactoryBlockEntity.this.waterLevel;
                    case 4 -> BlockFactoryBlockEntity.this.grindsleft;
                    case 5 -> BlockFactoryBlockEntity.this.lavaUses;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> BlockFactoryBlockEntity.this.progress = pValue;
                    case 1 -> BlockFactoryBlockEntity.this.maxProgress = pValue;
                    case 2 -> BlockFactoryBlockEntity.this.lavaLevel = pValue;
                    case 3 -> BlockFactoryBlockEntity.this.waterLevel = pValue;
                    case 4 -> BlockFactoryBlockEntity.this.grindsleft = pValue;
                    case 5 -> BlockFactoryBlockEntity.this.lavaUses = pValue;
            }
            }

            @Override
            public int getCount() {
                return 6;
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
            if (side == null) {
                return lazyItemHandler.cast();
            }

            if(directionWrappedHandlerMap.containsKey(side)) {
                Direction localDir = this.getBlockState().getValue(BlockFactoryBlock.FACING);

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

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.simpeladdmod.blockfactory_block");
    }


    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new BlockFactoryMenu(pContainerId,pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("factory_progress", progress);
        pTag.putInt("factory_lava", lavaLevel);
        pTag.putInt("factory_water", waterLevel);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("factory_progress");
        lavaLevel = pTag.getInt("factory_lava");
        waterLevel = pTag.getInt("factory_water");
    }
    public void tick(Level pLevel, BlockPos pPos, BlockState pState){
        if (canFillWater()){
            fillWater();
        }
        if (ccanFillLava()){
            fillLava();
        }
        pState = pState.setValue(BlockFactoryBlock.WORKING, Boolean.valueOf(isWorking()));
        pLevel.setBlock(pPos, pState, 3);
        if(hasLiquid()) {
            if (!isFull()){
                increaseCraftingProgress();
                setChanged(pLevel, pPos, pState);
                if (CobbleSpace()) {
                    if (hasProgressFinished()) {
                        makeCobble();
                        resetProgress();
                    }
                }
                if (grindsleft > 0) {
                    if (GravalSpace() && !CobbleSpace()) {
                            if (hasProgressFinished()) {
                                useGrind();
                                makeGraval();
                                resetProgress();
                            }
                    }
                    if (SandSpace() && !GravalSpace() && !CobbleSpace()) {
                        if (hasProgressFinished()) {
                            useGrind();
                            makeSand();
                            resetProgress();
                        }
                    }
                }else{
                    resetGrinds();
                }
                if (ObslidanSpace() && !SandSpace() && !GravalSpace() && !CobbleSpace()){
                    if(lavaUses > 0){
                        if (hasProgressFinished()) {
                            makeObsidain();
                            useLava();
                            resetProgress();
                        }
                    }else{
                        resteLavaUses();
                    }
                }
                if(!ObslidanSpace() && !SandSpace() && !GravalSpace() && !CobbleSpace()){
                    resetProgress();
                }
                if (grindsleft == 0 && !isFull() && !CobbleSpace()){
                    resetProgress();
                }
            }
        }
    }


    private void useGrind(){
        grindsleft --;
    }
    private void useLava(){
        lavaUses --;
    }
    private void resteLavaUses(){
        if(this.lavaLevel > 0){
            this.lavaLevel --;
            lavaUses = maxLavaUses;
        }else{
            lavaUses = 0;
        }
    }
    private void resetGrinds() {
        if(itemHandler.getStackInSlot(GRINDERSLOT).is(ModItems.GRINDERHEAD.get())){
            itemHandler.getStackInSlot(GRINDERSLOT).setDamageValue(itemHandler.getStackInSlot(GRINDERSLOT).getDamageValue() +1);
            grindsleft = maxGrinds;
        }else {
            grindsleft = 0;
        }
    }
    private boolean isFull(){
        if (!CobbleSpace() && !GravalSpace() && !SandSpace() && !ObslidanSpace()){
            return true;
        }
        return false;
    }
    private boolean isWorking() {
        if(this.progress >0 && hasLiquid() && !isFull()){
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
    private void makeCobble() {
        ItemStack result = new ItemStack(Items.COBBLESTONE, 1);
        this.itemHandler.setStackInSlot(COBBLESLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(COBBLESLOT).getCount() + result.getCount()));
    }
    private void makeGraval() {
        ItemStack result = new ItemStack(Items.GRAVEL, 1);
        this.itemHandler.setStackInSlot(GRAVALSLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(GRAVALSLOT).getCount() + result.getCount()));
    }
    private void makeSand() {
        ItemStack result = new ItemStack(Items.SAND, 1);
        this.itemHandler.setStackInSlot(SANDSLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(SANDSLOT).getCount() + result.getCount()));
    }
    private void makeObsidain() {
        ItemStack result = new ItemStack(Items.OBSIDIAN, 1);
        this.itemHandler.setStackInSlot(OBSIDIANSLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OBSIDIANSLOT).getCount() + result.getCount()));
    }
    private boolean hasLiquid() {
        return waterLevel > 0 && lavaLevel > 0 || lavaUses > 0;
    }
    private boolean CobbleSpace(){
        return this.itemHandler.getStackInSlot(COBBLESLOT).isEmpty() || this.itemHandler.getStackInSlot(COBBLESLOT).getCount() < 64;
    }
    private boolean GravalSpace(){
        return this.itemHandler.getStackInSlot(GRAVALSLOT).isEmpty() || this.itemHandler.getStackInSlot(GRAVALSLOT).getCount() < 64;
    }
    private boolean SandSpace(){
        return this.itemHandler.getStackInSlot(SANDSLOT).isEmpty() || this.itemHandler.getStackInSlot(SANDSLOT).getCount() < 64;
    }
    private boolean ObslidanSpace(){
        return this.itemHandler.getStackInSlot(OBSIDIANSLOT).isEmpty() || this.itemHandler.getStackInSlot(OBSIDIANSLOT).getCount() < 64;
    }
    private boolean canFillWater() {
        return waterLevel < 6;
    }
    private boolean ccanFillLava() {
        return  lavaLevel < 6;
    }
    private void  fillWater(){
        if(this.itemHandler.getStackInSlot(WATERSLOT).is(Items.WATER_BUCKET)){
            this.itemHandler.extractItem(WATERSLOT, 1, false);
            this.itemHandler.setStackInSlot(WATERSLOT, new ItemStack(Items.BUCKET));
            waterLevel += 1;
        }
    }
    private void  fillLava(){
        if(this.itemHandler.getStackInSlot(LAVASLOT).is(Items.LAVA_BUCKET)){
            this.itemHandler.extractItem(LAVASLOT, 1, false);
            this.itemHandler.setStackInSlot(LAVASLOT, new ItemStack(Items.BUCKET));
            lavaLevel += 1;
        }
    }



}
