package net.x_j0nnay_x.simpeladdmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.x_j0nnay_x.simpeladdmod.block.ModBlockEntities;
import net.x_j0nnay_x.simpeladdmod.block.custom.GrinderBlock;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.recipe.GrinderRecipe;
import net.x_j0nnay_x.simpeladdmod.screen.grinder.GrinderMenu;
import net.x_j0nnay_x.simpeladdmod.until.ModTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;


public class GrinderBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3){
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot){
                case 0 -> stack.is(ModTags.Items.CANGRIND);
                case 1 -> stack.getItem() == ModItems.GRINDERHEAD.get();
                case 2 -> false;
                default ->  super.isItemValid(slot, stack);

            };
        }
    };
    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
            Map.of(Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 2, (i, s) -> false)),
                    Direction.UP, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 0,
                            (index, stack) -> itemHandler.isItemValid(GRINDERSLOT, stack))),
                    Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 1,
                            (index, stack) -> itemHandler.isItemValid(INPUTSLOT, stack))),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 1,
                            (index, stack) -> itemHandler.isItemValid(INPUTSLOT, stack))),
                    Direction.EAST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 1,
                            (index, stack) -> itemHandler.isItemValid(INPUTSLOT, stack))),
                    Direction.WEST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 1,
                            (index, stack) -> itemHandler.isItemValid(INPUTSLOT, stack))));


    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    public static int INPUTSLOT = 0;
    public static int GRINDERSLOT = 1;
    public static int OUTPUTSLOT = 2;
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 20;
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
                Direction localDir = this.getBlockState().getValue(GrinderBlock.FACING);

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
        return new GrinderMenu(pContainerId,pPlayerInventory, this, this.data);
    }



    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("grinder_progress", progress);
        pTag.putInt("grinder_grinds_left", grindsleft);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("grinder_progress");
        grindsleft = pTag.getInt("grinder_grinds_left");
    }
    public void tick(Level pLevel, BlockPos pPos, BlockState pState){
        pState = pState.setValue(GrinderBlock.WORKING, Boolean.valueOf(isWorking()));
        pLevel.setBlock(pPos, pState, 3);
        if(hasRecipe()){
            if(grindsleft > 0){
                increaseCraftingProgress();
                setChanged(pLevel, pPos, pState);
                if(hasProgressFinished()){
                    useGrind();
                    craftItem();
                    resetProgress();
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
        if(itemHandler.getStackInSlot(GRINDERSLOT).is(ModItems.GRINDERHEAD.get())){
            itemHandler.getStackInSlot(GRINDERSLOT).setDamageValue(itemHandler.getStackInSlot(GRINDERSLOT).getDamageValue() +1);
            grindsleft = maxGrinds;
        }else {
            grindsleft = 0;
        }
    }
    private boolean canWork(){
        return  hasRecipe() && grindsleft >0;
    }
    private boolean isWorking() {
        return this.progress > 0 && canWork();
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
        Optional<GrinderRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);

        this.itemHandler.extractItem(INPUTSLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUTSLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUTSLOT).getCount() + result.getCount()));


       /* ItemStack result = new ItemStack(ModItems.NEHTERITE_SHARD_DUST.get(), 2);
        this.itemHandler.extractItem(INPUTSLOT, 1, false);
        this.itemHandler.setStackInSlot(OUTPUTSLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUTSLOT).getCount() + result.getCount()));*/
    }
    private boolean hasRecipe() {
        Optional<GrinderRecipe> recipe = getCurrentRecipe();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());

        return canInsertOutputAmount(result.getCount()) && canInsertOutputItem(result.getItem());

        /*
        boolean hasCraftingItem = this.itemHandler.getStackInSlot(INPUTSLOT).getItem() == ModItems.NEHTERITE_SHARD_RAW.get();
        ItemStack result = new ItemStack(ModItems.NEHTERITE_SHARD_DUST.get());
        return hasCraftingItem && canInsertOutputAmount(result.getCount()) && canInsertOutputItem(result.getItem());*/
    }
    private Optional<GrinderRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

       return this.level.getRecipeManager().getRecipeFor(GrinderRecipe.Type.INSTANCE, inventory, level);

    }


    private boolean canInsertOutputItem(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUTSLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUTSLOT).is(item);
    }

    private boolean canInsertOutputAmount(int count) {
        return this.itemHandler.getStackInSlot(OUTPUTSLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUTSLOT).getMaxStackSize();
    }


    @Override
    public Component getDisplayName() {
        return Component.translatable("block.simpeladdmod.grinder_block");
    }
}
