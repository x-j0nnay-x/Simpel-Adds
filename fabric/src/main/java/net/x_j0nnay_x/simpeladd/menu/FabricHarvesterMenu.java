package net.x_j0nnay_x.simpeladd.menu;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.x_j0nnay_x.simpeladd.blocks.entity.FabricHarvesterBlockEntity;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import net.x_j0nnay_x.simpeladd.core.ModMenuTypeFabric;
import net.x_j0nnay_x.simpeladd.core.ModTags;

public class FabricHarvesterMenu extends AbstractContainerMenu {

    private final Level level;
    private final Container inventory;
    private final ContainerData data;

    public FabricHarvesterMenu(int pContainerId, Inventory inv) {
        this(pContainerId, inv, new SimpleContainer(3), new SimpleContainerData(2));
    }

    public FabricHarvesterMenu(int pContainerID, Inventory inv, Container entity, ContainerData data) {
        super(ModMenuTypeFabric.HARVESTER_MENU, pContainerID);
        checkContainerSize(inv, 3);
        checkContainerDataCount(data, 2);
        entity.startOpen(inv.player);
        level = inv.player.level();
        this.inventory = entity;
        this.data = data;
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
            this.addSlot(new Slot(this.inventory, FabricHarvesterBlockEntity.SPEEDSLOT, 89, 11) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return stack.is(ModTags.Items.UPGRADES);
                }

                @Override
                public int getMaxStackSize() {
                    return 1;
                }
            });
            this.addSlot(new Slot(this.inventory, FabricHarvesterBlockEntity.EFFICIENCYSLOT, 71, 11) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return stack.is(ModItems.BOOSTUPGRADE);
                }

                @Override
                public int getMaxStackSize() {
                    return 3;
                }
            });
            this.addSlot(new Slot(this.inventory, FabricHarvesterBlockEntity.GROWSLOT, 80, 29){
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return stack.is(ModItems.GROWSTAFF);
                }

                @Override
                public int getMaxStackSize() {
                    return 1;
                }
            });

        addDataSlots(data);
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
    private static final int TE_INVENTORY_SLOT_COUNT = 3;  // must be the number of slots you have!
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
