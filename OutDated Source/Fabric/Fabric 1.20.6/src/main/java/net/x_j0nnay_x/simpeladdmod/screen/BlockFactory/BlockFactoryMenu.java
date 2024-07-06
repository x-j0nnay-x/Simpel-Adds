package net.x_j0nnay_x.simpeladdmod.screen.BlockFactory;


import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.Generic3x3ContainerScreenHandler;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.x_j0nnay_x.simpeladdmod.block.entity.BlockFactoryBlockEntity;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.screen.ModMenuType;

public class BlockFactoryMenu extends ScreenHandler  {
    public  final BlockFactoryBlockEntity blockEntity;
    private final Inventory inventory;

    private final PropertyDelegate data;


    public BlockFactoryMenu(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(6));
    }

    public BlockFactoryMenu(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
        super(ModMenuType.BLOCKFACTORY_MENU, syncId);
        checkSize(((Inventory) blockEntity), 7);
        this.inventory = ((Inventory) blockEntity);
        inventory.onOpen(playerInventory.player);
        this.data = arrayPropertyDelegate;
        this.blockEntity = ((BlockFactoryBlockEntity) blockEntity);

        this.addSlot(new Slot(inventory, BlockFactoryBlockEntity.GRINDERSLOT, 79, 8){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.GRINDERHEAD);
            }
        });
        this.addSlot(new Slot(inventory, BlockFactoryBlockEntity.LAVASLOT, 124, 53){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(Items.LAVA_BUCKET);
            }
        });
        this.addSlot(new Slot(inventory, BlockFactoryBlockEntity.WATERSLOT, 34, 53){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(Items.WATER_BUCKET);
            }
        });
        this.addSlot(new Slot(inventory, BlockFactoryBlockEntity.COBBLESLOT, 52, 35){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(inventory, BlockFactoryBlockEntity.GRAVALSLOT, 70, 35){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(inventory, BlockFactoryBlockEntity.SANDSLOT, 88, 35){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(inventory, BlockFactoryBlockEntity.OBSIDIANSLOT, 106, 35){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });




        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(arrayPropertyDelegate);
    }




    public boolean isCrafting(){
        return data.get(0) > 0 ;
    }
    public int getScalledProgress(){
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressAerrowSize = 12;
        return maxProgress != 0 && progress != 0 ? progress * progressAerrowSize / maxProgress : 0;
    }
    public boolean hasWater(){
        return data.get(3) > 0 ;
    }
    public boolean hasLava( ){
        return data.get(2) > 0 ;
    }
    public int getScalledwater(){
        int waterLevel = this.data.get(3);
       int tankSize = 61;
        return waterLevel != 0  ? waterLevel * tankSize / 6 : 0;
    }
    public int getScalledlava(){
        int lavaLevel = this.data.get(2);
        int tankSize = 61;
        return lavaLevel != 0  ? lavaLevel * tankSize / 6 : 0;
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
