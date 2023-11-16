package net.x_j0nnay_x.simpeladdmod.screen.NetheriteCrafter;


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
import net.x_j0nnay_x.simpeladdmod.block.entity.NetheriteCrafterBlockEntity;
import net.x_j0nnay_x.simpeladdmod.screen.ModMenuType;


public class NetheriteCrafterMenu extends ScreenHandler {
    public  final NetheriteCrafterBlockEntity blockEntity;
    private final Inventory inventory;
    private final PropertyDelegate data;

    public NetheriteCrafterMenu(int pContainerId, PlayerInventory inv, PacketByteBuf extraData){
        this(pContainerId, inv, inv.player.getWorld().getBlockEntity(extraData.readBlockPos()),
                new ArrayPropertyDelegate(4));
    }
    public NetheriteCrafterMenu(int pContainerID, PlayerInventory inv, BlockEntity entity, PropertyDelegate data){
        super(ModMenuType.Netherite_Menu, pContainerID);
        checkSize(((Inventory) entity), 3);
        this.inventory = ((Inventory) entity);
        inventory.onOpen(inv.player);
        this.data = data;
        blockEntity = ((NetheriteCrafterBlockEntity) entity);

        this.addSlot(new Slot(inventory, NetheriteCrafterBlockEntity.SCRAPSLOT, 25, 35));
        this.addSlot(new Slot(inventory, NetheriteCrafterBlockEntity.GOLDSLOT, 52, 35));
        this.addSlot(new Slot(inventory, NetheriteCrafterBlockEntity.BLAZESLOT, 79, 35));
        this.addSlot(new Slot(inventory, NetheriteCrafterBlockEntity.OUTPUTSLOT, 124, 35));

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        addProperties(data);
    }
    public boolean isCrafting(){
        return data.get(0) > 0 ;
    }
    public int getBlazeLevel(){
        int blazeLevel = this.data.get(2);
        int maxBlazeUes = this.data.get(3);
        int levelSize = 18;
        return maxBlazeUes != 0 && blazeLevel != 0 ? blazeLevel * levelSize / maxBlazeUes : 0;
    }
    public int getScalledProgress(){
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressAerrowSize = 79;
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
