package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesFabric;
import net.x_j0nnay_x.simpeladd.menu.FabricBlockFactoryMenu;
import net.x_j0nnay_x.simpeladd.util.data.BlockFactroyData;
import org.jetbrains.annotations.Nullable;

public class FabricBlockFactoryBlockEntity  extends Abst_BlockFactoryBlockEntity implements ExtendedScreenHandlerFactory<BlockFactroyData> {

    public FabricBlockFactoryBlockEntity( BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesFabric.BLOCK_FACTORY, $$1, $$2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new FabricBlockFactoryMenu(i, inventory, this, this.data);
    }

    public void updateBlock(){
        super.getUpdatePacket();
    }

    @Override
    public void blockFactoryTick(Level pLevel, BlockPos pPos, BlockState pState) {
        super.blockFactoryTick(pLevel, pPos, pState);
        updateBlock();
    }

    @Override
    public BlockFactroyData getScreenOpeningData(ServerPlayer player) {
        return new BlockFactroyData(true);
    }


}
