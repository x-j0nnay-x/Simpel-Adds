package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesFabric;
import net.x_j0nnay_x.simpeladd.menu.FabricTickAcceleratorMenu;
import net.x_j0nnay_x.simpeladd.util.data.TickAcceleratorData;


public class FabricTickAcceleratorBlockEntity extends Abst_TickAcceleratorBlockEntity implements ExtendedScreenHandlerFactory<TickAcceleratorData> {

    public FabricTickAcceleratorBlockEntity(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesFabric.TICK_ACCELERATOR, $$1, $$2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new FabricTickAcceleratorMenu(i, inventory, this, this.data);
    }

    @Override
    public void tick(ServerLevel world, BlockPos pos) {
        super.tick(world, pos);
    }

    @Override
    public TickAcceleratorData getScreenOpeningData(ServerPlayer player) {
        return new TickAcceleratorData(true);
    }

}
