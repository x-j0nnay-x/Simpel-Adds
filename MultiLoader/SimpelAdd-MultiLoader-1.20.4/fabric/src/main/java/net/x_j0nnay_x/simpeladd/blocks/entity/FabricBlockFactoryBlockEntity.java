package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesFabric;
import net.x_j0nnay_x.simpeladd.menu.FabricBlockFactoryMenu;


public class FabricBlockFactoryBlockEntity extends Abst_BlockFactoryBlockEntity implements ExtendedScreenHandlerFactory {
    public FabricBlockFactoryBlockEntity( BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesFabric.BLOCK_FACTORY, $$1, $$2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new FabricBlockFactoryMenu(i, inventory, this, this.data);
    }

    @Override
    public void load(CompoundTag $$0) {
        super.load($$0);
        this.lavaLevel = $$0.getShort(SimpelAddMod.MODCUSTOM + "blockfactroy_lavalevel");
        this.waterLevel = $$0.getShort(SimpelAddMod.MODCUSTOM + "blockfactroy_waterlevel");

    }

    @Override
    protected void saveAdditional(CompoundTag $$0) {
        super.saveAdditional($$0);
        $$0.putShort(SimpelAddMod.MODCUSTOM + "blockfactroy_lavalevel", (short) this.lavaLevel);
        $$0.putShort(SimpelAddMod.MODCUSTOM + "blockfactroy_waterlevel", (short) this.waterLevel);
    }

    @Override
    public void blockFactoryTick(Level pLevel, BlockPos pPos, BlockState pState) {
        super.blockFactoryTick(pLevel, pPos, pState);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayer player, FriendlyByteBuf buf) {
        buf.writeBlockPos(this.getBlockPos());
    }
}
