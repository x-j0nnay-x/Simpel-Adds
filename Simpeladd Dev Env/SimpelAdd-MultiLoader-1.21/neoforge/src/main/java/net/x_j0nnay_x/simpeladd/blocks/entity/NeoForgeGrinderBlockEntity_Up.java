package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesNeoForge;
import net.x_j0nnay_x.simpeladd.menu.NeoForgeGrinderMenu_up;

public class NeoForgeGrinderBlockEntity_Up extends Abst_GrinderBlockEntity_Up  {

    public NeoForgeGrinderBlockEntity_Up(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesNeoForge.GRINDER_UP.get(), $$1, $$2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new NeoForgeGrinderMenu_up(i, inventory, this, this.data);
    }

    @Override
    public void grinderUpTick(Level pLevel, BlockPos pPos, BlockState pState) {
        super.grinderUpTick(pLevel, pPos, pState);
    }
}
