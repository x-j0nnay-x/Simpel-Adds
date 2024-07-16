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
import net.x_j0nnay_x.simpeladd.menu.FabricChillerMenu;
import net.x_j0nnay_x.simpeladd.util.ChillerData;

public class FabricChillerBlockEntity extends Abst_ChillerBlockEntity implements ExtendedScreenHandlerFactory {


    public FabricChillerBlockEntity(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesFabric.CHILLER, $$1, $$2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new FabricChillerMenu(i, inventory, this, this.data);
    }


    @Override
    public void chillerTick(Level pLevel, BlockPos pPos, BlockState pState) {
        super.chillerTick(pLevel, pPos, pState);

    }


    @Override
    public Object getScreenOpeningData(ServerPlayer player) {
        return ChillerData.CODEC;
    }
}
