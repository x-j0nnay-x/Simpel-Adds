package net.x_j0nnay_x.simpeladdmod.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.x_j0nnay_x.simpeladdmod.block.ImplementedInventory;
import net.x_j0nnay_x.simpeladdmod.block.ModBlockEntities;
import net.x_j0nnay_x.simpeladdmod.block.custom.StoneSifterBlock;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.screen.StoneSifter.StoneSifterMenu;
import org.jetbrains.annotations.Nullable;

public class StoneSifterBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory, SidedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(8, ItemStack.EMPTY);
    public static int GRINDERSLOT = 0;
    public static int INPUTSLOT = 1;
    public static int COPPERSLOT = 2;
    public static int IRONSLOT = 3;
    public static int GOLDSLOT = 4;
    public static int REDSTONESLOT = 5;
    public static int QUARTZSLOT = 6;
    public static int DIMOANDSLOT = 7;
    protected final PropertyDelegate data;
    private int progress = 0;
    private int maxProgress = 60;

    public StoneSifterBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.STONE_SIFTER, pPos, pBlockState);
        this.data = new PropertyDelegate() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> StoneSifterBlockEntity.this.progress;
                    case 1 -> StoneSifterBlockEntity.this.maxProgress;

                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> StoneSifterBlockEntity.this.progress = pValue;
                    case 1 -> StoneSifterBlockEntity.this.maxProgress = pValue;

                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.simpeladdmod.stone_sifter_block");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return  new StoneSifterMenu(syncId, playerInventory, this, this.data);
    }
    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 8);
        super.markDirty();
    }
    public boolean canTransferTo(Inventory hopperInventory, int slot, ItemStack stack) {
        return true;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void readNbt(NbtCompound compound) {
        super.readNbt(compound);
        Inventories.readNbt(compound, inventory);
        progress = compound.getInt("sifter_progress");
    }

    @Override
    public void writeNbt(NbtCompound compound) {
        super.writeNbt(compound);
        Inventories.writeNbt(compound, inventory);
        compound.putInt("sifter_progress", progress);
     }
    @Override
    public boolean canInsert(int index, ItemStack stack, @Nullable Direction direction) {
        return (direction == Direction.EAST  && (index == INPUTSLOT) ||
                direction == Direction.WEST && (index == INPUTSLOT) ||
                direction == Direction.SOUTH && (index == INPUTSLOT) ||
                direction == Direction.NORTH && (index == INPUTSLOT) ||
                direction == Direction.UP && (index == GRINDERSLOT));
    }

    @Override
    public boolean canExtract(int slotIndex, ItemStack stack, Direction direction) {
        return (direction == Direction.DOWN && (slotIndex == COPPERSLOT || slotIndex == IRONSLOT || slotIndex == GOLDSLOT
                || slotIndex == REDSTONESLOT || slotIndex == QUARTZSLOT || slotIndex == DIMOANDSLOT));
    }
    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if (slot == GRINDERSLOT) {
            return stack.isOf(ModItems.GRINDERHEAD);
        }
        if (slot == INPUTSLOT) {
            return  stack.getItem() == Items.COBBLESTONE;
        }
        return false;
    }

// processing
    public void tick(World pLevel, BlockPos pPos, BlockState pState) {
        if(world.isClient()) {
            return;
        }
        pState = (BlockState)pState.with(StoneSifterBlock.WORKING, isWorking());
        pLevel.setBlockState(pPos, pState, Block.NOTIFY_ALL);
        if(hasRecipe()){
                increaseCraftingProgress();
                setCachedState(pState);
                if(hasProgressFinished()){
                    usestone();
                    useGrind();
                    craftItem();
                    resetProgress();
                }

        }else {

            resetProgress();
        }
    }

    private void useGrind() {
        if (inventory.get(GRINDERSLOT).isOf(ModItems.GRINDERHEAD)) {
            if (inventory.get(GRINDERSLOT).getDamage() >= inventory.get(GRINDERSLOT).getMaxDamage()) {
                inventory.set(GRINDERSLOT, ItemStack.EMPTY);
            } else {
                inventory.get(GRINDERSLOT).setDamage(inventory.get(GRINDERSLOT).getDamage() + 1);

            }

        }
    }
    private void usestone() {
        inventory.get(INPUTSLOT).setCount(inventory.get(INPUTSLOT).getCount() - 1);
    }

    private boolean canWork(){
        if( !isCopperFull() || !isIronFull() || !isGoldFull() || !isRedstoneFull() || !isQaruzFull() || !isDiamondFull()){
            return true;
        }
        return false;
    }
    private boolean isWorking() {
        return canWork() && hasRecipe();
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
        var d = Math.random();
        if(d < 0.3) {
            var c = Math.random();
            if (c < 0.4 && !isCopperFull()) {
                this.inventory.set(COPPERSLOT, new ItemStack(ModItems.COPPERDUST, this.inventory.get(COPPERSLOT).getCount() + 1));
            }
            else if (c < 0.5 && !isIronFull()) {
                this.inventory.set(IRONSLOT, new ItemStack(ModItems.IRONDUST, this.inventory.get(IRONSLOT).getCount() +1));
            }
            else if (c < 0.6 && !isGoldFull()) {
                this.inventory.set(GOLDSLOT, new ItemStack(ModItems.GOLDDUST, this.inventory.get(GOLDSLOT).getCount() +1));
            }
            else if (c < 0.7 && !isRedstoneFull()) {
                this.inventory.set(REDSTONESLOT, new ItemStack(Items.REDSTONE, this.inventory.get(REDSTONESLOT).getCount() +1));
            }
            else if (c < 0.8 && !isQaruzFull()) {
                this.inventory.set(QUARTZSLOT, new ItemStack(Items.QUARTZ, this.inventory.get(QUARTZSLOT).getCount() +1));
            }
            else if (c < 0.9 && !isDiamondFull()) {
                this.inventory.set(DIMOANDSLOT, new ItemStack(Items.DIAMOND, this.inventory.get(DIMOANDSLOT).getCount() +1));
            }

        }

    }
    private boolean hasRecipe() {
       if (inventory.get(INPUTSLOT).isOf(Items.COBBLESTONE) && inventory.get(GRINDERSLOT).isOf(ModItems.GRINDERHEAD)){
           return true;
       }
       return false;
}

    private boolean isCopperFull(){
        if (inventory.get(COPPERSLOT).getCount() < 64){
            return false;
        }
        return true;
    }
    private boolean isIronFull(){
        if (inventory.get(IRONSLOT).getCount() < 64){
            return false;
        }
        return true;
    }
    private boolean isGoldFull(){
        if (inventory.get(GOLDSLOT).getCount() < 64){
            return false;
        }
        return true;
    }
    private boolean isRedstoneFull(){
        if (inventory.get(REDSTONESLOT).getCount() < 64){
            return false;
        }
        return true;
    }
    private boolean isQaruzFull(){
        if (inventory.get(QUARTZSLOT).getCount() < 64){
            return false;
        }
        return true;
    }
    private boolean isDiamondFull(){
        if (inventory.get(DIMOANDSLOT).getCount() < 64){
            return false;
        }
        return true;
    }
}