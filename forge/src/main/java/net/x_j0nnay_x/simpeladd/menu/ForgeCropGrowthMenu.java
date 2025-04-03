package net.x_j0nnay_x.simpeladd.menu;

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
import net.x_j0nnay_x.simpeladd.blocks.entity.ForgeCropGrowthBlockEntity;
import net.x_j0nnay_x.simpeladd.core.ModBlockRegForge;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import net.x_j0nnay_x.simpeladd.core.ModMenuTypeForge;
import net.x_j0nnay_x.simpeladd.core.ModTags;

public class ForgeCropGrowthMenu extends AbstractContainerMenu {

    public  final ForgeCropGrowthBlockEntity blockEntity;
    private final Container inventory;
    private final Level level;
    private final ContainerData data;

    public ForgeCropGrowthMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData){
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(3));
    }

    public ForgeCropGrowthMenu(int pContainerID, Inventory inv, BlockEntity entity, ContainerData data){
        super(ModMenuTypeForge.CROP_GROWTH_MENU.get(), pContainerID);
        checkContainerSize(inv, 20);
        blockEntity = ((ForgeCropGrowthBlockEntity) entity);
        this.inventory = ((Container) entity);
        this.level = inv.player.level();
        this.data = data;
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
            this.addSlot(new Slot(this.inventory, ForgeCropGrowthBlockEntity.CROPSLOT, 13, 30){
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return blockEntity.allowedInSlot(stack);
                }

                @Override
                public int getMaxStackSize() {
                    return 1;
                }
            });
            this.addSlot(new Slot(this.inventory, ForgeCropGrowthBlockEntity.DIRTSLOT, 13, 48){
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return stack.is(Items.DIRT);
                }

                @Override
                public int getMaxStackSize() {
                    return 1;
                }
            });
            this.addSlot(new Slot(this.inventory, ForgeCropGrowthBlockEntity.COPPERSLOT, 136, 40){
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return stack.is(Items.COPPER_INGOT);
                }
            });
            this.addSlot(new Slot(this.inventory, ForgeCropGrowthBlockEntity.SPEEDSLOT, 152, 3){
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return stack.is(ModTags.Items.UPGRADES);
                }
                @Override
                public int getMaxStackSize() {
                    return 1;
                }
            });
            this.addSlot(new Slot(this.inventory, ForgeCropGrowthBlockEntity.EFFSLOT, 132, 4){
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return stack.is(ModItems.BOOSTUPGRADE);
                }
                @Override
                public int getMaxStackSize() {
                    return 1;
                }
            });
        addOutputSlots(this.inventory);
        addDataSlots(data);
    }
    private void addOutputSlots(Container pInventory){
        int row1 = 19;int row2 = 37;int row3 = 55;
        int col1 = 44;int col2 = 62;int col3 = 80;int col4 = 98;int col5 = 116;
        boolean canPlace = false;
        this.addSlot(new Slot(pInventory, ForgeCropGrowthBlockEntity.OUTPUTSLOT1, col1 , row1) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return canPlace;
            }
        });
        this.addSlot(new Slot(pInventory, ForgeCropGrowthBlockEntity.OUTPUTSLOT2, col2 , row1) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return canPlace;
            }
        });
        this.addSlot(new Slot(pInventory, ForgeCropGrowthBlockEntity.OUTPUTSLOT3, col3 , row1) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return canPlace;
            }
        });
        this.addSlot(new Slot(pInventory, ForgeCropGrowthBlockEntity.OUTPUTSLOT4, col4 , row1) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return canPlace;
            }
        });
        this.addSlot(new Slot(pInventory, ForgeCropGrowthBlockEntity.OUTPUTSLOT5, col5 , row1) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return canPlace;
            }
        });
        this.addSlot(new Slot(pInventory, ForgeCropGrowthBlockEntity.OUTPUTSLOT6, col1 , row2) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return canPlace;
            }
        });
        this.addSlot(new Slot(pInventory, ForgeCropGrowthBlockEntity.OUTPUTSLOT7, col2 , row2) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return canPlace;
            }
        });
        this.addSlot(new Slot(pInventory, ForgeCropGrowthBlockEntity.OUTPUTSLOT8, col3 , row2) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return canPlace;
            }
        });
        this.addSlot(new Slot(pInventory, ForgeCropGrowthBlockEntity.OUTPUTSLOT9, col4 , row2) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return canPlace;
            }
        });
        this.addSlot(new Slot(pInventory, ForgeCropGrowthBlockEntity.OUTPUTSLOT10, col5 , row2) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return canPlace;
            }
        });
        this.addSlot(new Slot(pInventory, ForgeCropGrowthBlockEntity.OUTPUTSLOT11, col1 , row3) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return canPlace;
            }
        });
        this.addSlot(new Slot(pInventory, ForgeCropGrowthBlockEntity.OUTPUTSLOT12, col2 , row3) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return canPlace;
            }
        });
        this.addSlot(new Slot(pInventory, ForgeCropGrowthBlockEntity.OUTPUTSLOT13, col3 , row3) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return canPlace;
            }
        });
        this.addSlot(new Slot(pInventory, ForgeCropGrowthBlockEntity.OUTPUTSLOT14, col4 , row3) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return canPlace;
            }
        });
        this.addSlot(new Slot(pInventory, ForgeCropGrowthBlockEntity.OUTPUTSLOT15, col5 , row3) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return canPlace;
            }
        });
    }
    public boolean isCrafting(){
        return data.get(0) > 0 ;
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
    public ForgeCropGrowthBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlockRegForge.CROP_GROWTH.get());
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
}
