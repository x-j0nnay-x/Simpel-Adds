package net.x_j0nnay_x.simpeladdmod.block.entity;


import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
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
import net.x_j0nnay_x.simpeladdmod.screen.Chiller.ChillerMenu;
import net.x_j0nnay_x.simpeladdmod.until.ModTags;
import org.jetbrains.annotations.Nullable;



public class ChillerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory, SidedInventory {
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    public static int CHILLINGSLOT = 0;
    public static int WATERSLOT = 1;
    public static int OUTPUTSLOT = 2;
    protected final PropertyDelegate data;
    private int progress = 0;
    private int maxProgress = 60;
    private int snowLevel = 0;
    private int waterLevel = 0;
    private int waterUese = 0;
    public ChillerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.CHILLER, pPos, pBlockState);
        this.data = new PropertyDelegate() {
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
            public int size() {
                return 5;
            }
        };
    }
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.simpeladdmod.chiller_block");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return  new ChillerMenu(syncId, playerInventory, this, this.data);
    }
    public boolean canTransferTo(Inventory hopperInventory, int slot, ItemStack stack) {
        return true;
    }
    @Override
    public void markDirty() {
        world.updateListeners(this.pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }
    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }


    @Override
    public void readNbt(NbtCompound compound) {
        super.readNbt(compound);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        Inventories.readNbt(compound, inventory);
        progress = compound.getInt("chiller_progress");
        snowLevel = compound.getInt("chiller_snow");
        waterLevel = compound.getInt("chiller_water");

    }

    @Override
    public void writeNbt(NbtCompound compound) {
        super.writeNbt(compound);
        Inventories.writeNbt(compound, inventory);
        compound.putInt("chiller_progress", progress);
        compound.putInt("chiller_snow", snowLevel);
        compound.putInt("chiller_water", waterLevel);
    }
    @Override
    public boolean canInsert(int index, ItemStack stack, @Nullable Direction direction) {
        return (direction == Direction.EAST  && (index == CHILLINGSLOT ) && stack.isIn(ModTags.Items.CHILLING) ||
                direction == Direction.WEST && (index == CHILLINGSLOT) && stack.isIn(ModTags.Items.CHILLING)||
                direction == Direction.SOUTH && (index == CHILLINGSLOT) && stack.isIn(ModTags.Items.CHILLING) ||
                direction == Direction.NORTH && (index == CHILLINGSLOT) && stack.isIn(ModTags.Items.CHILLING) ||
                direction == Direction.EAST  && (index == WATERSLOT ) && stack.isOf(Items.WATER_BUCKET) ||
                direction == Direction.WEST && (index == WATERSLOT) && stack.isOf(Items.WATER_BUCKET)||
                direction == Direction.SOUTH && (index == WATERSLOT) && stack.isOf(Items.WATER_BUCKET) ||
                direction == Direction.NORTH && (index == WATERSLOT) && stack.isOf(Items.WATER_BUCKET));
    }

    @Override
    public boolean canExtract(int index, ItemStack stack, Direction direction) {
        return (direction == Direction.DOWN && (
                index == WATERSLOT && stack.isOf(Items.BUCKET)||
                        index == OUTPUTSLOT));
    }
    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if (slot == CHILLINGSLOT) {
            return stack.isIn(ModTags.Items.CHILLING);
        }
        if (slot == WATERSLOT) {
            return  stack.getItem() == Items.WATER_BUCKET;
        }
        return false;
    }

 //process

 public void tick(World world, BlockPos pos, BlockState state) {
     if(world.isClient()) {
         return;
     }
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
                if (inventory.get(CHILLINGSLOT).isOf(Items.SNOWBALL)) {
                   this.removeStack(CHILLINGSLOT, 1);
                    snowLevel += 1;
                }
            }
            if(snowLevel <= 16){
                if(inventory.get(CHILLINGSLOT).isOf(Items.SNOW_BLOCK)){
                    this.removeStack(CHILLINGSLOT, 1);
                    snowLevel += 4;
                }
            }
            if (snowLevel <= 12){
                if(inventory.get(CHILLINGSLOT).isOf(Items.ICE)){
                    this.removeStack(CHILLINGSLOT, 1);
                    snowLevel += 8;
                }
            }
            if (snowLevel <= 8){
                if(inventory.get(CHILLINGSLOT).isOf(Items.PACKED_ICE)){
                    this.removeStack(CHILLINGSLOT, 1);
                    snowLevel += 12;
                }
            }
            if (snowLevel <= 1){
                if(inventory.get(CHILLINGSLOT).isOf(Items.BLUE_ICE)){
                    this.removeStack(CHILLINGSLOT, 1);
                    snowLevel += 19;
                }
            }
        }
    }
    private void  fillWater(){
        if(this.inventory.get(WATERSLOT).getItem() == (Items.WATER_BUCKET)){
            this.removeStack(WATERSLOT);
            this.inventory.set(WATERSLOT, new ItemStack(Items.BUCKET));
            waterLevel += 1;
        }
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
        inventory.set(OUTPUTSLOT, new ItemStack(result.getItem(),
                inventory.get(OUTPUTSLOT).getCount() + result.getCount()));
    }
    private boolean hasSpace(){
        return inventory.get(OUTPUTSLOT).getCount() < 64;
    }
    private boolean hasSnow() {
        return this.snowLevel >0;
    }
    private boolean hasWater() {
        return this.waterLevel >0 || this.waterUese > 0;
    }




}
