package x_j0nnay_x.simpeladdmod.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ResourceHandlerSlot;
import x_j0nnay_x.simpeladdmod.block.entitys.Crop_Growth_Block_Entity;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.init.Mod_Tags;

public class Crop_Growth_Menu extends AbstractContainerMenu {
    public final Crop_Growth_Block_Entity blockEntity;
    private final Level level;
    private final ContainerData data;

    public Crop_Growth_Menu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()),
                new ItemStacksResourceHandler(20), new SimpleContainerData(3));
    }

    public Crop_Growth_Menu(int pContainerId, Inventory inv, BlockEntity entity, ItemStacksResourceHandler handler, ContainerData data) {
        super(Mod_Registrys.Mod_Menu.CROP_GROWTH_MENU.get(), pContainerId);
        blockEntity = ((Crop_Growth_Block_Entity) entity);
        this.level = inv.player.level();
        this.data = data;
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        this.addSlot(new ResourceHandlerSlot(handler, handler::set, Crop_Growth_Block_Entity.CROPSLOT, 13, 30){
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
        this.addSlot(new ResourceHandlerSlot(handler, handler::set, Crop_Growth_Block_Entity.DIRTSLOT, 13, 48){
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
        this.addSlot(new ResourceHandlerSlot(handler, handler::set, Crop_Growth_Block_Entity.COPPERSLOT, 136, 40){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(Items.COPPER_INGOT);
            }
        });
        this.addSlot(new ResourceHandlerSlot(handler, handler::set, Crop_Growth_Block_Entity.SPEEDSLOT, 152, 3){
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(Mod_Tags.Items.UPGRADES);
            }

        });
        this.addSlot(new ResourceHandlerSlot(handler, handler::set, Crop_Growth_Block_Entity.EFFSLOT, 132, 4){
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(Mod_Registrys.Mod_Items.BOOST_UPGRADE.get());
            }
        });

        addOutputSlots(handler);
        addDataSlots(data);
    }


    private void addOutputSlots(ItemStacksResourceHandler handler){
        int[] rows = {19, 37, 55};
        int[] cols = {44, 62, 80, 98, 116};
        int slotIndex = Crop_Growth_Block_Entity.OUTPUTSLOT1;
        for (int row : rows) {
            for (int col : cols) {
                addReadOnlyOutputSlot(handler, slotIndex++, col, row);
            }
        }
    }

    private void addReadOnlyOutputSlot(ItemStacksResourceHandler handler, int slotIndex, int x, int y) {
        this.addSlot(new ResourceHandlerSlot(handler, handler::set, slotIndex, x, y) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }
    public int getCoperValue(){
        return this.data.get(2);
    }
    public int getBurnValue(){return this.data.get(0);}
    public int getMaxBurn(){return this.data.get(1);}

    public int getScalledProgress(){
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressAerrowSize = 43;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }

    public int getCopperLevel(){
        int coperlevel = this.data.get(2);
        int maxCopperLevel = 100;
        int levelSize = 46;
        return maxCopperLevel != 0 && coperlevel != 0 ? coperlevel * levelSize / maxCopperLevel : 0;
    }

    public <string> MutableComponent getCopperName(){
        return Component.translatable("gui.simpeladdmod.tick_accelerator.tooltip");
    }
    public <string> MutableComponent getburnName(){
        return Component.translatable("gui.simpeladdmod.tick_accelerator.tooltip.tick");
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
    private static final int TE_INVENTORY_SLOT_COUNT = 20;  // must be the number of slots you have!
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
                pPlayer, Mod_Registrys.Mod_Blocks.CROP_GROWTH_BLOCK.get());
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
}