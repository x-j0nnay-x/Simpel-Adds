package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesForge;
import net.x_j0nnay_x.simpeladd.menu.ForgeCropGrowthMenu;
import org.jetbrains.annotations.Nullable;

public class ForgeCropGrowthBlockEntity extends Abst_CropGrowthBlockEntity {

    private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());

    public ForgeCropGrowthBlockEntity(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesForge.CROP_GROWTH.get(), $$1, $$2);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER)
            return handlers[facing.ordinal()].cast();
        return super.getCapability(capability, facing);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new ForgeCropGrowthMenu(i, inventory, this, this.data);
    }

    @Override
    public void cropGrowthTick(ServerLevel pLevel, BlockPos pPos, BlockState pState) {
        super.cropGrowthTick(pLevel, pPos, pState);
    }
}
