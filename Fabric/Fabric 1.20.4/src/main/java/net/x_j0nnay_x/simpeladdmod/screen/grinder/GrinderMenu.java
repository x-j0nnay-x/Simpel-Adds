package net.x_j0nnay_x.simpeladdmod.screen.grinder;

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
import net.x_j0nnay_x.simpeladdmod.block.entity.GrinderBlockEntity;
import net.x_j0nnay_x.simpeladdmod.block.entity.GrinderBlockEntity_upgrade;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.screen.ModMenuType;
import net.x_j0nnay_x.simpeladdmod.until.ModTags;
import org.jetbrains.annotations.NotNull;


public class GrinderMenu extends ScreenHandler {
    public  final GrinderBlockEntity blockEntity;
    private final Inventory inventory;
    private final PropertyDelegate data;

    public GrinderMenu(int pContainerId, PlayerInventory inv, PacketByteBuf extraData){
        this(pContainerId, inv, inv.player.getWorld().getBlockEntity(extraData.readBlockPos()),
                new ArrayPropertyDelegate(6));
    }
    public GrinderMenu(int pContainerID, PlayerInventory inv, BlockEntity entity, PropertyDelegate data){
        super(ModMenuType.GRINDER_MENU, pContainerID);
        checkSize(((Inventory) entity), 5);
        this.inventory = ((Inventory) entity);
        inventory.onOpen(inv.player);
        this.data = data;
        blockEntity = ((GrinderBlockEntity) entity);

        this.addSlot(new Slot(inventory, GrinderBlockEntity.INPUTSLOT, 34, 44){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModTags.Items.CANGRIND);
            }
        });
        this.addSlot(new Slot(inventory, GrinderBlockEntity.GRINDERSLOT, 79, 17){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.GRINDERHEAD);
            }
        });
        this.addSlot(new Slot(inventory, GrinderBlockEntity.OUTPUTSLOT, 124, 44){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override
            public boolean canTakeItems(PlayerEntity playerEntity) {
                return true;
            }
        });
        this.addSlot(new Slot(inventory, GrinderBlockEntity.UPGRADESLOT, 144, 12){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModTags.Items.UPGRADES);
            }

            @Override
            public int getMaxItemCount(ItemStack stack) {
                return 1;
            }

        });
        this.addSlot(new Slot(inventory, GrinderBlockEntity.BOOSTSLOT, 124, 13){
            @Override
            public boolean canInsert(@NotNull ItemStack stack) {
                return stack.isOf(ModItems.BOOSTUPGRADE);
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
    public boolean isCrafting(){
        return data.get(0) > 0 ;
    }
    public int getScalledProgress(){
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressAerrowSize = 57;
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


