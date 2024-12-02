package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesFabric;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import net.x_j0nnay_x.simpeladd.menu.FabricFurnaceMenu_up;


public class FabricFurnaceBlockEntity_Up extends Abst_FurnaceBlockEntity_Up  implements ExtendedScreenHandlerFactory {

    public FabricFurnaceBlockEntity_Up(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesFabric.UPGRADED_FURNACE, $$1, $$2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new FabricFurnaceMenu_up(i, inventory, this, this.data);
    }

    @Override
    public void addFuel() {
        if(!this.stacks.get(FUELSLOT).isEmpty() && !this.stacks.get(FUELSLOT).is(Items.BUCKET)){
            if (this.getFuelTime(this.stacks.get(FUELSLOT)) >= 200 || this.stacks.get(FUELSLOT).getItem() == ModItems.FUELCHUNKS) {
                if (this.stacks.get(FUELSLOT).is(ModItems.FUELCHUNKS)) {
                    this.fuelLevel += this.getFuelTime(this.stacks.get(FUELSLOT)) * 4 / 200;
                    this.removeItem(FUELSLOT, 1);
                } else {
                    fuelLevel += this.getFuelTime(this.stacks.get(FUELSLOT)) / 200;
                    if (this.stacks.get(FUELSLOT).getItem() == (Items.LAVA_BUCKET)) {
                        this.removeItem(FUELSLOT, 1);
                        this.stacks.set(FUELSLOT, new ItemStack(Items.BUCKET));
                    } else {
                        this.removeItem(FUELSLOT, 1);
                    }
                }
            }
        }
    }

    @Override
    public void upFurnaceTick(ServerLevel pLevel, BlockPos pPos, BlockState pState) {
        super.upFurnaceTick(pLevel, pPos, pState);
    }


    @Override
    public void writeScreenOpeningData(ServerPlayer player, FriendlyByteBuf buf) {
        buf.writeBlockPos(this.getBlockPos());
    }
}
