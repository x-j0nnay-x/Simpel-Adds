package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesFabric;
import net.x_j0nnay_x.simpeladd.menu.FabricGrinderMenu;
import net.x_j0nnay_x.simpeladd.util.data.GrinderData;

public class FabricGrinderBlockEntity extends Abst_GrinderBlockEntity implements ExtendedScreenHandlerFactory<GrinderData>  {
    public FabricGrinderBlockEntity(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesFabric.GRINDER, $$1, $$2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new FabricGrinderMenu(i, inventory, this, this.data);
    }
    @Override
    public void grinderTick(Level pLevel, BlockPos pPos, BlockState pState) {
        super.grinderTick(pLevel, pPos, pState);
    }

    @Override
    public GrinderData getScreenOpeningData(ServerPlayer player) {
        return new GrinderData(true);
    }
}
