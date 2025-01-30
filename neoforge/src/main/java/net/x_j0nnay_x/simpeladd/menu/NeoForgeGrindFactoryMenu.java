package net.x_j0nnay_x.simpeladd.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.x_j0nnay_x.simpeladd.blocks.entity.NeoForgeGrindFactoryBlockEntity;
import net.x_j0nnay_x.simpeladd.core.ModBlockRegNeoForge;
import net.x_j0nnay_x.simpeladd.core.ModItemRegNeoForge;
import net.x_j0nnay_x.simpeladd.core.ModMenuTypeNeoForge;
import net.x_j0nnay_x.simpeladd.core.ModTags;
import org.jetbrains.annotations.NotNull;

public class NeoForgeGrindFactoryMenu extends AbstractContainerMenu {

    public  final NeoForgeGrindFactoryBlockEntity blockEntity;
    private final Container inventory;
    private final Level level;
    private final ContainerData data;

    public NeoForgeGrindFactoryMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData){
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(13));
    }

    public NeoForgeGrindFactoryMenu(int pContainerID, Inventory inv, BlockEntity entity, ContainerData data){
        super(ModMenuTypeNeoForge.GRIND_FACTORY_MENU.get(), pContainerID);
        checkContainerSize( inv, 18);
        blockEntity = ((NeoForgeGrindFactoryBlockEntity) entity);
        this.inventory = ((Container) entity);
        this.level = inv.player.level();
        this.data = data;
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        this.addSlot(new Slot(this.inventory, NeoForgeGrindFactoryBlockEntity.GRINDERINSLOT1, 43, 12){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return blockEntity.hasGrindRecipeforinput(stack);
            }
        });
        this.addSlot(new Slot(this.inventory, NeoForgeGrindFactoryBlockEntity.GRINDERINSLOT2, 61, 12){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return blockEntity.hasGrindRecipeforinput(stack);
            }
        });
        this.addSlot(new Slot(this.inventory, NeoForgeGrindFactoryBlockEntity.GRINDERINSLOT3, 79, 12){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return blockEntity.hasGrindRecipeforinput(stack);
            }
        });
        this.addSlot(new Slot(this.inventory, NeoForgeGrindFactoryBlockEntity.GRINDERINSLOT4, 97, 12){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return blockEntity.hasGrindRecipeforinput(stack);
            }
        });
        this.addSlot(new Slot(this.inventory, NeoForgeGrindFactoryBlockEntity.FURNACEINSLOT1, 43, 33){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return blockEntity.hasFurnRecipeforinput(stack);
            }
        });
        this.addSlot(new Slot(this.inventory, NeoForgeGrindFactoryBlockEntity.FURNACEINSLOT2, 61, 33){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return blockEntity.hasFurnRecipeforinput(stack);
            }
        });
        this.addSlot(new Slot(this.inventory, NeoForgeGrindFactoryBlockEntity.FURNACEINSLOT3, 79, 33){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return blockEntity.hasFurnRecipeforinput(stack);
            }
        });
        this.addSlot(new Slot(this.inventory, NeoForgeGrindFactoryBlockEntity.FURNACEINSLOT4, 97, 33){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return blockEntity.hasFurnRecipeforinput(stack);
            }
        });
        this.addSlot(new Slot(this.inventory, NeoForgeGrindFactoryBlockEntity.OUTPUTSLOT1, 43, 54){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(this.inventory, NeoForgeGrindFactoryBlockEntity.OUTPUTSLOT2, 61, 54){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(this.inventory, NeoForgeGrindFactoryBlockEntity.OUTPUTSLOT3, 79, 54){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(this.inventory, NeoForgeGrindFactoryBlockEntity.OUTPUTSLOT4, 97, 54){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(this.inventory, NeoForgeGrindFactoryBlockEntity.GRINDERSLOT, 16, 25){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.GRINDERS);
            }
        });
        this.addSlot(new Slot(this.inventory, NeoForgeGrindFactoryBlockEntity.FUELSLOT, 16, 43){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return NeoForgeGrindFactoryBlockEntity.isFuel(stack);
            }
        });
        this.addSlot(new Slot(this.inventory, NeoForgeGrindFactoryBlockEntity.UPGRADESLOT, 144, 12){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.UPGRADES);
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
        this.addSlot(new Slot(this.inventory, NeoForgeGrindFactoryBlockEntity.BOOSTSLOT, 124, 13){
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.is(ModItemRegNeoForge.BOOSTUPGRADE.get());
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
        this.addSlot(new Slot(this.inventory, NeoForgeGrindFactoryBlockEntity.XPBOTTLESLOT, 144, 60){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(this.inventory, NeoForgeGrindFactoryBlockEntity.XPBOOSTSLOT, 124, 45){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItemRegNeoForge.XPBOOSTUPGRADE.get());
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
        addDataSlots(data);
    }
    public boolean isGrinding1(){
        return data.get(5) > 0 ;
    }

    public boolean isGrinding2(){
        return data.get(6) > 0 ;
    }

    public boolean isGrinding3(){
        return data.get(7) > 0 ;
    }

    public boolean isGrinding4(){
        return data.get(8) > 0 ;
    }

    public boolean isSmelting1(){
        return data.get(9) > 0 ;
    }

    public boolean isSmelting2(){
        return data.get(10) > 0 ;
    }

    public boolean isSmelting3(){
        return data.get(11) > 0 ;
    }

    public boolean isSmelting4(){
        return data.get(12) > 0 ;
    }

    public int getGrindScalledProgress1(){
        int progress = this.data.get(5);
        int maxProgress = this.data.get(0);
        int progressAerrowSize = 12;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }

    public int getGrindScalledProgress2(){
        int progress = this.data.get(6);
        int maxProgress = this.data.get(0);
        int progressAerrowSize = 12;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }

    public int getGrindScalledProgress3(){
        int progress = this.data.get(7);
        int maxProgress = this.data.get(0);
        int progressAerrowSize = 12;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }

    public int getGrindScalledProgress4(){
        int progress = this.data.get(8);
        int maxProgress = this.data.get(0);
        int progressAerrowSize = 12;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }

    public int getFurnScalledProgress1(){
        int progress = this.data.get(9);
        int maxProgress = this.data.get(0);
        int progressAerrowSize = 12;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }

    public int getFurnScalledProgress2(){
        int progress = this.data.get(10);
        int maxProgress = this.data.get(0);
        int progressAerrowSize = 12;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }

    public int getFurnScalledProgress3(){
        int progress = this.data.get(11);
        int maxProgress = this.data.get(0);
        int progressAerrowSize = 12;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }

    public int getFurnScalledProgress4(){
        int progress = this.data.get(12);
        int maxProgress = this.data.get(0);
        int progressAerrowSize = 12;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }

    public int getGrindsLeft(){
        int grindsLeft = this.data.get(1);
        return grindsLeft;
    }

    public int getFuleLeft(){
        int fuelLeft = this.data.get(2);
        return fuelLeft;
    }

    public int getXPStored(){
        int fuelLeft = this.data.get(3);
        return fuelLeft;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlockRegNeoForge.GRIND_FACTORY_BLOCK.get());
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
    private static final int TE_INVENTORY_SLOT_COUNT = 18;  // must be the number of slots you have!
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
