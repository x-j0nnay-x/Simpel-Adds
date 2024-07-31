package net.x_j0nnay_x.simpeladd.blocks.entity;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesNeoForge;
import net.x_j0nnay_x.simpeladd.menu.NeoForgeBlockFactoryMenu;
import org.jetbrains.annotations.Nullable;


public class NeoForgeBlockFactoryBlockEntity extends Abst_BlockFactoryBlockEntity {



    public NeoForgeBlockFactoryBlockEntity(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesNeoForge.BLOCK_FACTORY.get(), $$1, $$2);
    }


    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new NeoForgeBlockFactoryMenu(i, inventory, this, this.data);
    }



    @Override
    public void blockFactoryTick(Level pLevel, BlockPos pPos, BlockState pState) {
        super.blockFactoryTick(pLevel, pPos, pState);
    }



}
