package net.x_j0nnay_x.simpeladd.blocks.entity;


import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesNeoForge;
import net.x_j0nnay_x.simpeladd.menu.NeoForgeBlockFactoryMenu;


public class NeoForgeBlockFactoryBlockEntity extends Abst_BlockFactoryBlockEntity  {
    public NeoForgeBlockFactoryBlockEntity(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesNeoForge.BLOCK_FACTORY.get(), $$1, $$2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new NeoForgeBlockFactoryMenu(i, inventory, this, this.data);
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



}
