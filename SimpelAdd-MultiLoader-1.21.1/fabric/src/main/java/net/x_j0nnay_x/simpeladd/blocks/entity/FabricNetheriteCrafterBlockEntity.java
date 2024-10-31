package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesFabric;
import net.x_j0nnay_x.simpeladd.menu.FabricNetheriteCrafterMenu;
import net.x_j0nnay_x.simpeladd.util.data.NetheriteCrafterData;

public class FabricNetheriteCrafterBlockEntity extends Abst_NetheriteCrafterBlockEntity implements ExtendedScreenHandlerFactory<NetheriteCrafterData> {

    public FabricNetheriteCrafterBlockEntity(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesFabric.NETHERITE_CRAFTER, $$1, $$2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new FabricNetheriteCrafterMenu(i, inventory, this, this.data);
    }

    @Override
    public void netheriteCrafterTick(Level pLevel, BlockPos pPos, BlockState pState) {
        super.netheriteCrafterTick(pLevel, pPos, pState);
    }

    @Override
    public NetheriteCrafterData getScreenOpeningData(ServerPlayer player) {
        return new NetheriteCrafterData(true);
    }
}
