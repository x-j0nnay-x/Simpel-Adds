package net.x_j0nnay_x.simpeladd.blocks.entity;


import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModBlockEntitiesNeoForge;
import net.x_j0nnay_x.simpeladd.menu.NeoForgeChillerMenu;


public class NeoForgeChillerBlockEntity extends Abst_ChillerBlockEntity  {


    public NeoForgeChillerBlockEntity(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntitiesNeoForge.CHILLER.get(), $$1, $$2);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new NeoForgeChillerMenu(i, inventory, this, this.data);
    }

    @Override
    public void load(CompoundTag $$0) {
        super.load($$0);
        this.waterLevel = $$0.getShort(SimpelAddMod.MODCUSTOM + "chiller_waterlevel");
    }

    @Override
    protected void saveAdditional(CompoundTag $$0) {
        super.saveAdditional($$0);
        $$0.putShort(SimpelAddMod.MODCUSTOM + "chiller_waterlevel", (short) this.waterLevel);
    }

    @Override
    public void chillerTick(Level pLevel, BlockPos pPos, BlockState pState) {
        super.chillerTick(pLevel, pPos, pState);

    }



}
