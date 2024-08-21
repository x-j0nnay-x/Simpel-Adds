package net.x_j0nnay_x.simpeladd.menu;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.x_j0nnay_x.simpeladd.blocks.entity.FabricFurnaceBlockEntity_Up;
import net.x_j0nnay_x.simpeladd.core.ModItemRegFabric;
import net.x_j0nnay_x.simpeladd.core.ModMenuTypeFabric;
import net.x_j0nnay_x.simpeladd.core.ModTags;
import org.jetbrains.annotations.NotNull;

public class FabricFurnaceMenu_up extends AbstractContainerMenu {

    private final Container inventory;
    private final Level level;
    private final ContainerData data;

    public FabricFurnaceMenu_up(int id, Inventory playerInventoryIn) {
        this( id, playerInventoryIn, new SimpleContainer(12), new SimpleContainerData(7));
    }

    public FabricFurnaceMenu_up(int pContainerID, Inventory inv, Container upFurnace, ContainerData data){
        super(ModMenuTypeFabric.UPGRADED_FURNACE_MENU, pContainerID);
        checkContainerSize(inv, 12);
        checkContainerDataCount(data, 7);
        upFurnace.startOpen(inv.player);
        this.level = inv.player.level();
        this.inventory = upFurnace;
        this.data = data;
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
            this.addSlot(new Slot(this.inventory, FabricFurnaceBlockEntity_Up.INPUTSLOT1, 63, 17){
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    if(!stack.is(ModTags.Items.UPGRADES)|| !stack.is(ModItemRegFabric.XPBOOSTUPGRADE)){
                        return true;
                    }
                    return false;
                }
            });
            this.addSlot(new Slot(this.inventory, FabricFurnaceBlockEntity_Up.INPUTSLOT2, 81, 17){
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    if(!stack.is(ModTags.Items.UPGRADES)|| !stack.is(ModItemRegFabric.XPBOOSTUPGRADE)){
                        return true;
                    }
                    return false;
                }
            });
            this.addSlot(new Slot(this.inventory, FabricFurnaceBlockEntity_Up.INPUTSLOT3, 99, 17){
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    if(!stack.is(ModTags.Items.UPGRADES)|| !stack.is(ModItemRegFabric.XPBOOSTUPGRADE)){
                        return true;
                    }
                    return false;
                }
            });
            this.addSlot(new Slot(this.inventory, FabricFurnaceBlockEntity_Up.INPUTSLOT4, 117, 17){
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    if(!stack.is(ModTags.Items.UPGRADES)|| !stack.is(ModItemRegFabric.XPBOOSTUPGRADE)){
                        return true;
                    }
                    return false;
                }
            });
            this.addSlot(new Slot(this.inventory, FabricFurnaceBlockEntity_Up.OUTPUTSLOT1, 63, 53){
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return false;
                }
            });
            this.addSlot(new Slot(this.inventory, FabricFurnaceBlockEntity_Up.OUTPUTSLOT2, 81, 53){
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return false;
                }
            });
            this.addSlot(new Slot(this.inventory, FabricFurnaceBlockEntity_Up.OUTPUTSLOT3, 99, 53){
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return false;
                }
            });
            this.addSlot(new Slot(this.inventory, FabricFurnaceBlockEntity_Up.OUTPUTSLOT4, 117, 53){
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return false;
                }
            });
            this.addSlot(new Slot(this.inventory, FabricFurnaceBlockEntity_Up.XPBOTTLESLOT, 144, 60){
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return false;
                }
            });
            this.addSlot(new Slot(this.inventory, FabricFurnaceBlockEntity_Up.FUELSLOT, 16, 45){
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return FabricFurnaceBlockEntity_Up.isFuel(stack);
                }
            });
            this.addSlot(new Slot(this.inventory, FabricFurnaceBlockEntity_Up.UPGRADESLOT, 144, 12){
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return stack.is(ModTags.Items.UPGRADES);
                }
                @Override
                public int getMaxStackSize() {
                    return 1;
                }
            });
            this.addSlot(new Slot(this.inventory, FabricFurnaceBlockEntity_Up.XPBOOSTSLOT, 144, 28){
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return stack.is(ModItemRegFabric.XPBOOSTUPGRADE);
                }
                @Override
                public int getMaxStackSize() {
                    return 1;
                }
            });
        addDataSlots(data);
    }

    public boolean isCrafting1(){
        return data.get(0) > 0 ;
    }

    public boolean isCrafting2(){
        return data.get(3) > 0 ;
    }

    public boolean isCrafting3(){
        return data.get(4) > 0 ;
    }

    public boolean isCrafting4(){
        return data.get(5) > 0 ;
    }

    public int getScalledProgress1(){
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressAerrowSize = 12;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }

    public int getScalledProgress2(){
        int progress = this.data.get(3);
        int maxProgress = this.data.get(1);
        int progressAerrowSize = 12;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }

    public int getScalledProgress3(){
        int progress = this.data.get(4);
        int maxProgress = this.data.get(1);
        int progressAerrowSize = 12;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }

    public int getScalledProgress4(){
        int progress = this.data.get(5);
        int maxProgress = this.data.get(1);
        int progressAerrowSize = 12;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }

    public int getFuleLeft(){
        int fuelLeft = this.data.get(2);
        return fuelLeft;
    }

    public int getXPStored(){
        int fuelLeft = this.data.get(6);
        return fuelLeft;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return this.inventory.stillValid(pPlayer);
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
    private static final int TE_INVENTORY_SLOT_COUNT = 12;  // must be the number of slots you have!
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
