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
import net.x_j0nnay_x.simpeladdmod.block.custom.BlockFactoryBlock;
import net.x_j0nnay_x.simpeladdmod.block.custom.NetheriteCrafterBlock;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.screen.BlockFactory.BlockFactoryMenu;
import net.x_j0nnay_x.simpeladdmod.screen.NetheriteCrafter.NetheriteCrafterMenu;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;


public class NetheriteCrafterBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory, SidedInventory {
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);

    public static int SCRAPSLOT = 0;
    public static int GOLDSLOT = 1;
    public static int BLAZESLOT = 2;
    public static int OUTPUTSLOT = 3;
    public static int UPGRADESLOT = 4;
    protected final PropertyDelegate data;
    private int progress = 0;
    private int maxProgress = 90;
    private int blazeUse = 0;
    private int  maxBlazeuse = 4;

    public NetheriteCrafterBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.NETHERITE_CRAFTER, pPos, pBlockState);
        this.data = new PropertyDelegate() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> NetheriteCrafterBlockEntity.this.progress;
                    case 1 -> NetheriteCrafterBlockEntity.this.maxProgress;
                    case 2 -> NetheriteCrafterBlockEntity.this.blazeUse;
                    case 3 -> NetheriteCrafterBlockEntity.this.maxBlazeuse;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> NetheriteCrafterBlockEntity.this.progress = pValue;
                    case 1 -> NetheriteCrafterBlockEntity.this.maxProgress = pValue;
                    case 2 -> NetheriteCrafterBlockEntity.this.blazeUse = pValue;
                    case 3 -> NetheriteCrafterBlockEntity.this.maxBlazeuse = pValue;
                }
            }

            @Override
            public int size() {
                return 4;
            }
        };
    }
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }
    @Override
    public Text getDisplayName() {
        return Text.translatable("block.simpeladdmod.netherite_crafter_block");
    }
    @Override
    public void markDirty() {
        world.updateListeners(this.pos, getCachedState(), getCachedState(), 4);
        super.markDirty();
    }
    public boolean canTransferTo(Inventory hopperInventory, int slot, ItemStack stack) {
        return true;
    }
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return  new NetheriteCrafterMenu(syncId, playerInventory, this, this.data);
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
        progress = compound.getInt("nether_crafter_progress");
        blazeUse = compound.getInt("netherite_crafter_blaze");

    }

    @Override
    public void writeNbt(NbtCompound compound) {
        super.writeNbt(compound);
        Inventories.writeNbt(compound, inventory);
        compound.putInt("nether_crafter_progress", progress);
        compound.putInt("netherite_crafter_blaze", blazeUse);


    }

    @Override
    public boolean canInsert(int index, ItemStack stack, @Nullable Direction direction) {
        return (direction == Direction.EAST  && (index == GOLDSLOT || index == SCRAPSLOT|| index == BLAZESLOT) ||
                direction == Direction.WEST && (index == GOLDSLOT || index == SCRAPSLOT|| index == BLAZESLOT) ||
                direction == Direction.SOUTH && (index == GOLDSLOT || index == SCRAPSLOT|| index == BLAZESLOT) ||
                direction == Direction.NORTH && (index == GOLDSLOT || index == SCRAPSLOT|| index == BLAZESLOT) ||
                direction == Direction.UP && (index == GOLDSLOT || index == SCRAPSLOT|| index == BLAZESLOT));
    }

    @Override
    public boolean canExtract(int index, ItemStack stack, Direction direction) {
        return (direction == Direction.DOWN && (index == OUTPUTSLOT));
    }
    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if (slot == SCRAPSLOT) {
            return stack.getItem() == Items.NETHERITE_SCRAP;
        }
        if (slot == GOLDSLOT) {
            return  stack.getItem() == Items.GOLD_INGOT;
        }
        if (slot == BLAZESLOT) {
            return  stack.getItem() == Items.BLAZE_ROD;
        }
        return false;
    }
 //process

        public void tick(World pLevel, BlockPos pPos, BlockState pState) {
            if(world.isClient()) {
                return;
            }
            if (inventory.get(UPGRADESLOT).isOf(ModItems.SPEEDUPGRADE_1)) {
                this.maxProgress = 60;
            }if (inventory.get(UPGRADESLOT).isOf(ModItems.SPEEDUPGRADE_2)) {
                this.maxProgress = 36;
            }if (inventory.get(UPGRADESLOT).isOf(ModItems.SPEEDUPGRADE_3)) {
                this.maxProgress = 15;
            }if (inventory.get(UPGRADESLOT).isEmpty()){
                this.maxProgress = 90;
            }
        pState = (BlockState)pState.with(NetheriteCrafterBlock.WORKING, isWorking());
        pLevel.setBlockState(pPos, pState, Block.NOTIFY_ALL);
            if(hasRecipe()) {
                if (hasSpace() && blazeUse > 0) {
                    increaseCraftingProgress();
                    setCachedState(pState);
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
    private void  useContent(){
        this.removeStack(SCRAPSLOT, 1);
        this.removeStack(GOLDSLOT, 1);
        blazeUse --;
    }
    private void  refillBlaze(){
        if(this.inventory.get(BLAZESLOT).isOf(Items.BLAZE_ROD)) {
            this.removeStack(BLAZESLOT, 1);
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
        if(this.inventory.get(SCRAPSLOT).isOf(Items.NETHERITE_SCRAP) && this.inventory.get(GOLDSLOT).isOf(Items.GOLD_INGOT)){
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
        this.inventory.set(OUTPUTSLOT, new ItemStack(result.getItem(),
                this.inventory.get(OUTPUTSLOT).getCount() + result.getCount()));
    }
    private boolean hasSpace(){
        return this.inventory.get(OUTPUTSLOT).getCount() < 64;
    }


}
