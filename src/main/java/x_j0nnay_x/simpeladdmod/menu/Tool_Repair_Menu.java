package x_j0nnay_x.simpeladdmod.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ResourceHandlerSlot;
import x_j0nnay_x.simpeladdmod.block.entitys.Tool_Repair_Block_Entity;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.init.Mod_Tags;

public class Tool_Repair_Menu extends AbstractContainerMenu {
    public final Tool_Repair_Block_Entity blockEntity;
    private final Level level;
    private final ContainerData data;

    public Tool_Repair_Menu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()),
                new ItemStacksResourceHandler(2), new SimpleContainerData(2));
    }

    public Tool_Repair_Menu(int pContainerId, Inventory inv, BlockEntity entity, ItemStacksResourceHandler handler, ContainerData data) {
        super(Mod_Registrys.Mod_Menu.TOOL_REPAIR.get(), pContainerId);
        blockEntity = ((Tool_Repair_Block_Entity) entity);
        this.level = inv.player.level();
        this.data = data;
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        addPlayerArrmor(inv);
        this.addSlot(new ResourceHandlerSlot(handler, handler::set, Tool_Repair_Block_Entity.COPPERSLOT, 88, 32) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(Items.COPPER_INGOT);
            }
        });
        this.addSlot(new ResourceHandlerSlot(handler, handler::set, Tool_Repair_Block_Entity.REPAIRSLOT, 68, 13) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return (!itemStack.is(Mod_Tags.Items.REPAIR_NOT_ALLOWED));
            }

        });

        addDataSlots(data);
    }

    public int getCopperLevel(){
        int coperlevel = this.data.get(0);
        int maxCopperLevel = this.data.get(1);
        int levelSize = 37;
        return maxCopperLevel != 0 && coperlevel != 0 ? coperlevel * levelSize / maxCopperLevel : 0;
    }
    public int getCoperValue(){
        return this.data.get(0);
    }
    public int getMaxCopper(){
        return this.data.get(1);
    }
    public <string> MutableComponent getCopperName(){
        return Component.translatable("gui.simpeladdmod.tick_accelerator.tooltip");
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
    private static final int TE_INVENTORY_SLOT_COUNT = 2;  // must be the number of slots you have!
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

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, Mod_Registrys.Mod_Blocks.TOOL_REPAIR_BLOCK.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    private void addPlayerArrmor(Inventory playerInventory){
        int yOff = 127;
        this.addSlot(new Slot(playerInventory, 36 , -14, yOff){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ItemTags.FOOT_ARMOR);
            }
        });
        this.addSlot(new Slot(playerInventory, 37 , -14, yOff - 1 * 18){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ItemTags.LEG_ARMOR);
            }
        });
        this.addSlot(new Slot(playerInventory, 38 , -14, yOff - 2 * 18){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ItemTags.CHEST_ARMOR);
            }
        });
        this.addSlot(new Slot(playerInventory, 39 , -14, yOff - 3 * 18){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ItemTags.HEAD_ARMOR);
            }
        });
        this.addSlot(new Slot(playerInventory, 40, -14, yOff + 1 * 18));
    }
}