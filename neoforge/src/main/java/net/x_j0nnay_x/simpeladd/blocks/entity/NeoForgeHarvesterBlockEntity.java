package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesNeoForge;
import net.x_j0nnay_x.simpeladd.menu.NeoForgeHarvesterMenu;


public class NeoForgeHarvesterBlockEntity extends Abst_HavesterBlockEntity{


    public NeoForgeHarvesterBlockEntity(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesNeoForge.HARVESTER.get(), $$1, $$2);
    }


    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new NeoForgeHarvesterMenu(i, inventory, this, this.data);
    }

    @Override
    public void tick(ServerLevel world, BlockPos pos) {
        super.tick(world, pos);
    }

}
