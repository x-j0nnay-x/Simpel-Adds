package net.x_j0nnay_x.simpeladdmod.block.entity;


import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
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
import net.x_j0nnay_x.simpeladdmod.block.custom.BlockFactoryBlock;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.screen.BlockFactory.BlockFactoryMenu;
import org.jetbrains.annotations.Nullable;
public class BlockFactoryBlockEntity extends BlockEntity  implements ExtendedScreenHandlerFactory, ImplementedInventory, SidedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(7, ItemStack.EMPTY);



    public static int WATERSLOT = 5;
    public static int LAVASLOT = 6;

    public static int GRINDERSLOT = 0;
    public static int COBBLESLOT = 1;
    public static int GRAVALSLOT = 2;
    public static int SANDSLOT = 3;
    public static int OBSIDIANSLOT = 4;
    protected final PropertyDelegate data;
    private int progress = 0;
    private int maxProgress = 35;
    private int lavaLevel = 0 ;
    private int waterLevel = 0;
    private int lavaUses = 0 ;
    private int maxLavaUses = 4;
    private int grindsleft = 0;
    private int maxGrinds = 3;
    public BlockFactoryBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.BLOCK_FACTORY, pPos, pBlockState);
        this.data = new PropertyDelegate() {
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
            public int size() {
                return 6;
            }

        };
    }
    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 7);
        super.markDirty();
    }
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }
    public boolean canTransferTo(Inventory hopperInventory, int slot, ItemStack stack) {
        return true;
    }
    @Override
    public Text getDisplayName() {
        return Text.translatable("block.simpeladdmod.blockfactory_block");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return  new BlockFactoryMenu(syncId, playerInventory, this, this.data);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }


    @Override
    public void readNbt(NbtCompound compound) {
        super.readNbt(compound);
        Inventories.readNbt(compound, inventory);
        progress = compound.getInt("blockfactroy_progress");
        grindsleft = compound.getInt("blockfactroy_grinds_left");
        waterLevel = compound.getInt("blockfactroy_water_level");
        lavaLevel = compound.getInt("blockfactroy_lava_level");

    }

    @Override
    public void writeNbt(NbtCompound compound) {
        super.writeNbt(compound);
        Inventories.writeNbt(compound, inventory);
        compound.putInt("blockfactroy_progress", progress);
        compound.putInt("blockfactroy_grinds_left", grindsleft);
        compound.putInt("blockfactroy_water_level", waterLevel);
        compound.putInt("blockfactroy_lava_level", lavaLevel);

    }
    @Override
    public boolean canInsert(int index, ItemStack stack, @Nullable Direction direction) {
        return (direction == Direction.EAST  && (index == LAVASLOT ) && stack.isOf(Items.LAVA_BUCKET) ||
                direction == Direction.WEST && (index == LAVASLOT) && stack.isOf(Items.LAVA_BUCKET)||
                direction == Direction.SOUTH && (index == LAVASLOT) && stack.isOf(Items.LAVA_BUCKET) ||
                direction == Direction.NORTH && (index == LAVASLOT) && stack.isOf(Items.LAVA_BUCKET) ||
                direction == Direction.EAST  && (index == WATERSLOT ) && stack.isOf(Items.WATER_BUCKET) ||
                direction == Direction.WEST && (index == WATERSLOT) && stack.isOf(Items.WATER_BUCKET)||
                direction == Direction.SOUTH && (index == WATERSLOT) && stack.isOf(Items.WATER_BUCKET) ||
                direction == Direction.NORTH && (index == WATERSLOT) && stack.isOf(Items.WATER_BUCKET) ||
                direction == Direction.UP && (index == GRINDERSLOT));
    }

    @Override
    public boolean canExtract(int index, ItemStack stack, Direction direction) {
        return (direction == Direction.DOWN && (
                index == WATERSLOT && stack.isOf(Items.BUCKET)||
                        index == LAVASLOT && stack.isOf(Items.BUCKET)));
    }
    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if (slot == GRINDERSLOT) {
            return stack.getItem() == ModItems.GRINDERHEAD;
        }
        if (slot == WATERSLOT) {
            return  stack.getItem() == Items.WATER_BUCKET;
        }
        if (slot == LAVASLOT) {
            return  stack.getItem() == Items.LAVA_BUCKET;
        }
        return false;
    }


//processing

    public void tick(World pLevel, BlockPos pPos, BlockState pState) {
        if(pLevel.isClient()) {
            return;
        }
        if (canFillWater()){
            fillWater();
        }
        if (ccanFillLava()){
            fillLava();
        }
        pState = (BlockState)pState.with(BlockFactoryBlock.WORKING, isWorking());
        pLevel.setBlockState(pPos, pState, Block.NOTIFY_ALL);

        if(hasLiquid()) {
            if (!isFull()){
                increaseCraftingProgress();
                setCachedState(pState);
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
        if(inventory.get(GRINDERSLOT).getItem() == (ModItems.GRINDERHEAD)){
            if(inventory.get(GRINDERSLOT).getDamage() >= inventory.get(GRINDERSLOT).getMaxDamage()){
                inventory.set(GRINDERSLOT, ItemStack.EMPTY);
            }else{
                inventory.get(GRINDERSLOT).setDamage(inventory.get(GRINDERSLOT).getDamage() + 1);
                grindsleft = maxGrinds;
            }
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
        if(hasLiquid() && !isFull()){
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
        this.inventory.set(COBBLESLOT, new ItemStack(result.getItem(),
            this.inventory.get(COBBLESLOT).getCount() + result.getCount()));
    }
    private void makeGraval() {
        ItemStack result = new ItemStack(Items.GRAVEL, 1);
        this.inventory.set(GRAVALSLOT, new ItemStack(result.getItem(),
                this.inventory.get(GRAVALSLOT).getCount() + result.getCount()));
    }
    private void makeSand() {
        ItemStack result = new ItemStack(Items.SAND, 1);
        this.inventory.set(SANDSLOT, new ItemStack(result.getItem(),
                this.inventory.get(SANDSLOT).getCount() + result.getCount()));
    }
    private void makeObsidain() {
        ItemStack result = new ItemStack(Items.OBSIDIAN, 1);
        this.inventory.set(OBSIDIANSLOT, new ItemStack(result.getItem(),
                this.inventory.get(OBSIDIANSLOT).getCount() + result.getCount()));
    }
    private boolean hasLiquid() {
        return waterLevel > 0 && lavaLevel > 0 || lavaUses > 0;
    }
    private boolean CobbleSpace(){
        return this.inventory.get(COBBLESLOT).isEmpty() || this.inventory.get(COBBLESLOT).getCount() < 64;
    }
    private boolean GravalSpace(){
        return this.inventory.get(GRAVALSLOT).isEmpty() || this.inventory.get(GRAVALSLOT).getCount() < 64;
    }
    private boolean SandSpace(){
        return this.inventory.get(SANDSLOT).isEmpty() || this.inventory.get(SANDSLOT).getCount() < 64;
    }
    private boolean ObslidanSpace(){
        return this.inventory.get(OBSIDIANSLOT).isEmpty() || this.inventory.get(OBSIDIANSLOT).getCount() < 64;
    }
    private boolean canFillWater() {
        return waterLevel < 6;
    }
    private boolean ccanFillLava() {
        return  lavaLevel < 6;
    }
    private void  fillWater(){
        if(this.inventory.get(WATERSLOT).getItem() == (Items.WATER_BUCKET)){
            this.removeStack(WATERSLOT);
            this.inventory.set(WATERSLOT, new ItemStack(Items.BUCKET));
            waterLevel += 1;
        }
    }
    private void  fillLava(){
        if(this.inventory.get(LAVASLOT).getItem() ==(Items.LAVA_BUCKET)){
            this.removeStack(LAVASLOT);
            this.inventory.set(LAVASLOT, new ItemStack(Items.BUCKET));
            lavaLevel += 1;
        }
    }



}
