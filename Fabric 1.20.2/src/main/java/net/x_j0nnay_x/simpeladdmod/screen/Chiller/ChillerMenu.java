package net.x_j0nnay_x.simpeladdmod.screen.Chiller;


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
import net.x_j0nnay_x.simpeladdmod.block.entity.ChillerBlockEntity;
import net.x_j0nnay_x.simpeladdmod.screen.ModMenuType;


public class ChillerMenu extends ScreenHandler {
    public  final ChillerBlockEntity blockEntity;
    private final Inventory inventory;
    private final PropertyDelegate data;

    public ChillerMenu(int pContainerId, PlayerInventory inv, PacketByteBuf extraData){
        this(pContainerId, inv, inv.player.getWorld().getBlockEntity(extraData.readBlockPos()),
                new ArrayPropertyDelegate(5));
    }
    public ChillerMenu(int pContainerID, PlayerInventory inv, BlockEntity entity, PropertyDelegate data){
        super(ModMenuType.Chiller_MENU, pContainerID);
        checkSize(((Inventory) entity), 3);
        this.inventory = ((Inventory) entity);
        inventory.onOpen(inv.player);
        this.data = data;
        this.blockEntity = ((ChillerBlockEntity) entity);

        this.addSlot(new Slot(inventory, ChillerBlockEntity.CHILLINGSLOT, 16, 53));
        this.addSlot(new Slot(inventory, ChillerBlockEntity.WATERSLOT, 52, 53));
        this.addSlot(new Slot(inventory, ChillerBlockEntity.OUTPUTSLOT, 133, 26));


        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        addProperties(data);

    }
    public boolean isCrafting(){
        return data.get(0) > 0 ;
    }
    public boolean hasSnow(){
        return data.get(2) > 0 ;
    }
    public boolean hasWater(){
        return data.get(3) > 0 ;
    }
    public int getScalledProgress(){
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressAerrowSize = 28;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }
    public int getScalledwater(){
        int waterLevel = this.data.get(3);
        int tankSize = 59;
        return waterLevel != 0  ? waterLevel * tankSize / 6 : 0;
    }

    public int getScalledsnow(){
        int snowLev = this.data.get(2);
        int tankSize = 58;
        return snowLev != 0  ? snowLev * tankSize / 20  : 0;
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
