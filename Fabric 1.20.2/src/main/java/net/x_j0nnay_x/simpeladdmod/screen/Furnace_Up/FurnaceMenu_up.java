package net.x_j0nnay_x.simpeladdmod.screen.Furnace_Up;


import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.x_j0nnay_x.simpeladdmod.block.ModBlocks;

import net.x_j0nnay_x.simpeladdmod.block.entity.GrinderBlockEntity_upgrade;
import net.x_j0nnay_x.simpeladdmod.block.entity.Upgrade_Furnace_BlockEntity;

import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.screen.ModMenuType;
import net.x_j0nnay_x.simpeladdmod.until.ModTags;
import org.jetbrains.annotations.NotNull;

public class FurnaceMenu_up extends ScreenHandler {
    public  final Upgrade_Furnace_BlockEntity blockEntity;
    private final Inventory inventory;
    private final PropertyDelegate data;

    public FurnaceMenu_up(int pContainerId, PlayerInventory inv, PacketByteBuf extraData){
        this(pContainerId, inv, inv.player.getWorld().getBlockEntity(extraData.readBlockPos()), new ArrayPropertyDelegate(11));
    }
    public FurnaceMenu_up(int pContainerID, PlayerInventory inv, BlockEntity entity, PropertyDelegate data){
        super(ModMenuType.UPGRADED_FURNACE_MENU, pContainerID);
        checkSize(inv, 11);
        blockEntity = ((Upgrade_Furnace_BlockEntity) entity);
        this.inventory = ((Inventory) entity);
        inventory.onOpen(inv.player);
        this.data = data;


        this.addSlot(new Slot(inventory, Upgrade_Furnace_BlockEntity.INPUTSLOT1, 63, 17){
            @Override
            public boolean canInsert(ItemStack stack) {
                if(!stack.isIn(ModTags.Items.UPGRADES)){
                    return true;
                }
                return false;
            }
        });
        this.addSlot(new Slot(inventory, Upgrade_Furnace_BlockEntity.INPUTSLOT2, 81, 17){
            @Override
            public boolean canInsert(ItemStack stack) {
                if(!stack.isIn(ModTags.Items.UPGRADES)){
                    return true;
                }
                return false;
            }
        });
        this.addSlot(new Slot(inventory, Upgrade_Furnace_BlockEntity.INPUTSLOT3, 99, 17){
            @Override
            public boolean canInsert(ItemStack stack) {
                if(!stack.isIn(ModTags.Items.UPGRADES)){
                    return true;
                }
                return false;
            }
        });
        this.addSlot(new Slot(inventory, Upgrade_Furnace_BlockEntity.INPUTSLOT4, 117, 17){
            @Override
            public boolean canInsert(ItemStack stack) {
                if(!stack.isIn(ModTags.Items.UPGRADES)){
                    return true;
                }
                return false;
            }
        });
        this.addSlot(new Slot(inventory, Upgrade_Furnace_BlockEntity.OUTPUTSLOT1, 63, 53){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(inventory, Upgrade_Furnace_BlockEntity.OUTPUTSLOT2, 81, 53){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(inventory, Upgrade_Furnace_BlockEntity.OUTPUTSLOT3, 99, 53){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(inventory, Upgrade_Furnace_BlockEntity.OUTPUTSLOT4, 117, 53){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(inventory, Upgrade_Furnace_BlockEntity.XPBOTTLESLOT, 144, 60){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override
            public int getMaxItemCount() {
                return 64;
            }
        });
        this.addSlot(new Slot(inventory, Upgrade_Furnace_BlockEntity.FUELSLOT, 16, 45){
            @Override
            public boolean canInsert(ItemStack stack) {
                return AbstractFurnaceBlockEntity.canUseAsFuel(stack);
            }
        });
        this.addSlot(new Slot(inventory, Upgrade_Furnace_BlockEntity.UPGRADESLOT, 144, 12){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModTags.Items.UPGRADES);
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }

        });


        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        addProperties(data);

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
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
    private void addPlayerInventory(Inventory playerInventory){
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }
    private void addPlayerHotbar(Inventory playerInventory){
        for (int si = 0; si < 9; ++si)
            this.addSlot(new Slot(playerInventory, si, 0 + 8 + si * 18, 0 + 142));
    }
}



