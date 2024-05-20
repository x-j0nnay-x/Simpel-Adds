package net.x_j0nnay_x.simpeladdmod.screen.grinder_up;


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

import net.x_j0nnay_x.simpeladdmod.block.entity.GrinderBlockEntity_upgrade;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.screen.ModMenuType;
import net.x_j0nnay_x.simpeladdmod.until.ModTags;
import org.jetbrains.annotations.NotNull;

public class GrinderMenu_up extends ScreenHandler {
    public  final GrinderBlockEntity_upgrade blockEntity;
    private final Inventory inventory;
    private final PropertyDelegate data;

    public GrinderMenu_up(int pContainerId, PlayerInventory inv, PacketByteBuf extraData){
        this(pContainerId, inv, inv.player.getWorld().getBlockEntity(extraData.readBlockPos()),
                new ArrayPropertyDelegate(9));
    }
    public GrinderMenu_up(int pContainerID, PlayerInventory inv, BlockEntity entity, PropertyDelegate data){
        super(ModMenuType.GRINDER_MENU_UP, pContainerID);
        checkSize(inv, 11);
        blockEntity = ((GrinderBlockEntity_upgrade) entity);
        this.inventory = ((Inventory) entity);
        inventory.onOpen(inv.player);
        this.data = data;
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
            this.addSlot(new Slot(inventory, GrinderBlockEntity_upgrade.INPUTSLOT1, 43, 17){
                @Override
                public boolean canInsert(ItemStack stack) {
                    return stack.isIn(ModTags.Items.CANGRIND);
                }
            });
            this.addSlot(new Slot(inventory, GrinderBlockEntity_upgrade.INPUTSLOT2, 61, 17){
                @Override
                public boolean canInsert(ItemStack stack) {
                    return stack.isIn(ModTags.Items.CANGRIND);
                }
            });
            this.addSlot(new Slot(inventory, GrinderBlockEntity_upgrade.INPUTSLOT3, 79, 17){
                @Override
                public boolean canInsert(ItemStack stack) {
                    return stack.isIn(ModTags.Items.CANGRIND);
                }
            });
            this.addSlot(new Slot(inventory, GrinderBlockEntity_upgrade.INPUTSLOT4, 97, 17){
                @Override
                public boolean canInsert(ItemStack stack) {
                    return stack.isIn(ModTags.Items.CANGRIND);
                }
            });
            this.addSlot(new Slot(inventory, GrinderBlockEntity_upgrade.OUTPUTSLOT1, 43, 53){
                @Override
                public boolean canInsert(ItemStack stack) {
                    return false;
                }
            });
            this.addSlot(new Slot(inventory, GrinderBlockEntity_upgrade.OUTPUTSLOT2, 61, 53){
                @Override
                public boolean canInsert(ItemStack stack) {
                    return false;
                }
            });
            this.addSlot(new Slot(inventory, GrinderBlockEntity_upgrade.OUTPUTSLOT3, 79, 53){
                @Override
                public boolean canInsert(ItemStack stack) {
                    return false;
                }
            });
            this.addSlot(new Slot(inventory, GrinderBlockEntity_upgrade.OUTPUTSLOT4, 97, 53){
                @Override
                public boolean canInsert(ItemStack stack) {
                    return false;
                }
            });
            this.addSlot(new Slot(inventory, GrinderBlockEntity_upgrade.GRINDERSLOT, 16, 35){
                @Override
                public boolean canInsert(ItemStack stack) {
                    return ModItems.GRINDERHEAD == stack.getItem();
                }
            });
            this.addSlot(new Slot(inventory, GrinderBlockEntity_upgrade.UPGRADESLOT, 144, 12){
                @Override
                public boolean canInsert(ItemStack stack) {
                    return stack.isIn(ModTags.Items.UPGRADES);
                }

                @Override
                public int getMaxItemCount() {
                    return 1;
                }

            });
            this.addSlot(new Slot(inventory, GrinderBlockEntity_upgrade.BOOSTSLOT, 124, 13){
                @Override
                public boolean canInsert(@NotNull ItemStack stack) {
                    return stack.isOf(ModItems.BOOSTUPGRADE);
                }

                @Override
                public int getMaxItemCount() {
                    return 1;
                }

            });


        addProperties(data);
    }
    public boolean isCrafting1(){
        return data.get(0) > 0 ;
    }
    public boolean isCrafting2(){
        return data.get(6) > 0 ;
    }
    public boolean isCrafting3(){
        return data.get(7) > 0 ;
    }
    public boolean isCrafting4(){
        return data.get(8) > 0 ;
    }
    public int getScalledProgress1(){
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressAerrowSize = 12;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }
    public int getScalledProgress2(){
        int progress = this.data.get(6);
        int maxProgress = this.data.get(1);
        int progressAerrowSize = 12;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }
    public int getScalledProgress3(){
        int progress = this.data.get(7);
        int maxProgress = this.data.get(1);
        int progressAerrowSize = 12;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }
    public int getScalledProgress4(){
        int progress = this.data.get(8);
        int maxProgress = this.data.get(1);
        int progressAerrowSize = 12;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }
    public int getGrindsLeft(){
        int grindsLeft = this.data.get(2);

        return grindsLeft;
    }
    public boolean hasEffUpgrade(){
        int hasboost = this.data.get(5);
        if (hasboost == 1){
            return true;
        }
        return false;

    }


    public int getGrinderEff(){
        int grinderEff = this.data.get(4);
        return grinderEff;
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
        for (int si = 0; si < 3; ++si)
            for (int sj = 0; sj < 9; ++sj)
                this.addSlot(new Slot(playerInventory, sj + (si + 1) * 9, 0 + 8 + sj * 18, 0 + 84 + si * 18));
    }
    private void addPlayerHotbar(Inventory playerInventory){
        for (int si = 0; si < 9; ++si)
            this.addSlot(new Slot(playerInventory, si, 0 + 8 + si * 18, 0 + 142));
    }
}
