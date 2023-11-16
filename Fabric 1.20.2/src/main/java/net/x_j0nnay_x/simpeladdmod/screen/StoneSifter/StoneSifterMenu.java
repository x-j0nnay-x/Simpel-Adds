package net.x_j0nnay_x.simpeladdmod.screen.StoneSifter;


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
import net.x_j0nnay_x.simpeladdmod.block.entity.ChillerBlockEntity;
import net.x_j0nnay_x.simpeladdmod.block.entity.StoneSifterBlockEntity;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.screen.ModMenuType;


public class StoneSifterMenu extends ScreenHandler {
    public  final StoneSifterBlockEntity blockEntity;
    private final Inventory inventory;
    private final PropertyDelegate data;

    public StoneSifterMenu(int pContainerId, PlayerInventory inv, PacketByteBuf extraData){
        this(pContainerId, inv, inv.player.getWorld().getBlockEntity(extraData.readBlockPos()), new ArrayPropertyDelegate(2));
    }
    public StoneSifterMenu(int pContainerID, PlayerInventory inv, BlockEntity entity, PropertyDelegate data){
        super(ModMenuType.STONESIFTER_MENU, pContainerID);
        checkSize(((Inventory) entity), 8);
        this.inventory = ((Inventory) entity);
        inventory.onOpen(inv.player);
        this.data = data;
        blockEntity = ((StoneSifterBlockEntity) entity);

        this.addSlot(new Slot(inventory, StoneSifterBlockEntity.INPUTSLOT, 16, 53));
        this.addSlot(new Slot(inventory, StoneSifterBlockEntity.GRINDERSLOT, 16, 17));
        this.addSlot(new Slot(inventory, StoneSifterBlockEntity.COPPERSLOT, 124, 17));
        this.addSlot(new Slot(inventory, StoneSifterBlockEntity.IRONSLOT, 142, 17));
        this.addSlot(new Slot(inventory, StoneSifterBlockEntity.GOLDSLOT, 124, 35));
        this.addSlot(new Slot(inventory, StoneSifterBlockEntity.REDSTONESLOT, 142, 35));
        this.addSlot(new Slot(inventory, StoneSifterBlockEntity.QUARTZSLOT, 124, 53));
        this.addSlot(new Slot(inventory, StoneSifterBlockEntity.DIMOANDSLOT, 142, 53));

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
        int progressAerrowSize = 66;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
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


