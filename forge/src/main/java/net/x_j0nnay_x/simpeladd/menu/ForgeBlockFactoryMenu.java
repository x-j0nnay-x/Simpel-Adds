package net.x_j0nnay_x.simpeladd.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.x_j0nnay_x.simpeladd.blocks.entity.ForgeBlockFactoryBlockEntity;
import net.x_j0nnay_x.simpeladd.core.ModBlockRegForge;
import net.x_j0nnay_x.simpeladd.core.ModMenuTypeForge;
import net.x_j0nnay_x.simpeladd.core.ModTags;

public class ForgeBlockFactoryMenu extends AbstractContainerMenu {

    public  final ForgeBlockFactoryBlockEntity blockEntity;
    private final Container inventory;
    private final Level level;
    private final ContainerData data;

    public ForgeBlockFactoryMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData){
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(10));
    }

    public ForgeBlockFactoryMenu(int pContainerID, Inventory inv, BlockEntity entity, ContainerData data){
        super(ModMenuTypeForge.BLOCKFACTORY_MENU.get(), pContainerID);
        checkContainerSize(inv, 7);
        blockEntity = ((ForgeBlockFactoryBlockEntity) entity);
        this.level = inv.player.level();
        this.inventory = ((Container) entity);
        this.data = data;
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        this.addSlot(new Slot(this.inventory, ForgeBlockFactoryBlockEntity.GRINDERSLOT, 79, 8){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.GRINDERS);
            }
        });
        this.addSlot(new Slot(this.inventory, ForgeBlockFactoryBlockEntity.LAVASLOT, 124, 53){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return Items.LAVA_BUCKET == stack.getItem();
            }
        });
        this.addSlot(new Slot(this.inventory, ForgeBlockFactoryBlockEntity.WATERSLOT, 34, 53){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return Items.WATER_BUCKET == stack.getItem();
            }
        });
        this.addSlot(new Slot(this.inventory, ForgeBlockFactoryBlockEntity.COBBLESLOT, 52, 35){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(this.inventory, ForgeBlockFactoryBlockEntity.GRAVALSLOT, 70, 35){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(this.inventory, ForgeBlockFactoryBlockEntity.SANDSLOT, 88, 35){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(this.inventory, ForgeBlockFactoryBlockEntity.OBSIDIANSLOT, 106, 35){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
        addDataSlots(data);
    }

    public int getOutPutSlot(){
        return this.data.get(6);
    }

    public int getButtonPosX(){
        if (this.data.get(6) == 1 ){
            return 0;
        }
        if (this.data.get(6) == 2 ){
            return 12;
        }
        if (this.data.get(6) == 3 ){
            return 24;
        }
        if (this.data.get(6) == 4 ){
            return 36;
        }
        return 48;
    }

    public boolean isCrafting(){
        return data.get(0) > 0 ;
    }

    public int getScalledProgress(){
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressAerrowSize = 12;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }

    public boolean hasWater(){
        return data.get(4) > 0 ;
    }

    public boolean hasLava( ){
        return data.get(5) > 0 ;
    }

    public int getScalledwater(){
        int waterLevel = this.data.get(4);
        int tankSize = 61;
        return waterLevel != 0  ? waterLevel * tankSize / 6000 : 0;
    }

    public int getScalledlava(){
        int lavaLevel = this.data.get(5);
        int tankSize = 61;
        return lavaLevel != 0  ? lavaLevel * tankSize / 6000 : 0;
    }

    public ForgeBlockFactoryBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlockRegForge.BLOCK_FACTORY.get());
    }

    private void addPlayerInventory(Inventory playerInventory){
        for (int si = 0; si < 3; ++si)
            for (int sj = 0; sj < 9; ++sj)
                this.addSlot(new Slot(playerInventory, sj + (si + 1) * 9, 0 + 8 + sj * 18, 0 + 84 + si * 18));
    }

    private void addPlayerHotbar(Inventory playerInventory){
        for (int si = 0; si < 9; ++si)
            this.addSlot(new Slot(playerInventory, si, 0 + 8 + si * 18, 0 + 142));
    }
    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 7;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();
        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }
}
