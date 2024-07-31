package net.x_j0nnay_x.simpeladd.blocks.entity;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.items.IItemHandler;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesNeoForge;
import net.x_j0nnay_x.simpeladd.menu.NeoForgeGrindFactoryMenu;

import javax.annotation.Nullable;


public class NeoForgeGrindFactoryBlockEntity extends Abst_GrindFactoryBlockEntity  {
    public NeoForgeGrindFactoryBlockEntity(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesNeoForge.GRIND_FACTORY.get(), $$1, $$2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new NeoForgeGrindFactoryMenu(i, inventory, this, this.data);
    }

    @Override
    public void grindFactoryTick(Level pLevel, BlockPos pPos, BlockState pState) {
        super.grindFactoryTick(pLevel, pPos, pState);
    }

}
