package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.blocks.Abst_TickAcceleratorBlock;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import net.x_j0nnay_x.simpeladd.item.GrinderHeadItem_Broken;
import org.jetbrains.annotations.Nullable;


public abstract class Abst_ToolRepairBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    protected NonNullList<ItemStack> stacks = NonNullList.withSize(2, ItemStack.EMPTY);
    public static int REPAIRSLOT = 0;
    public static int COPPERSLOT = 1;
    public int maxcopper =  100;
    private int tickCount;
    private int maxTickCount = 40;
    public int coperLevel;
    private static final int[] SLOTS_FOR_UP = new int[]{COPPERSLOT, REPAIRSLOT};
    private static final int[] SLOTS_FOR_DOWN = new int[]{COPPERSLOT, REPAIRSLOT};
    private static final int[] SLOTS_FOR_SIDES = new int[]{COPPERSLOT};
    protected final ContainerData data;

    protected Abst_ToolRepairBlockEntity(BlockEntityType<?> $$0, BlockPos $$1, BlockState $$2) {
        super($$0, $$1, $$2);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> Abst_ToolRepairBlockEntity.this.coperLevel;
                    case 1 -> Abst_ToolRepairBlockEntity.this.maxcopper;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> Abst_ToolRepairBlockEntity.this.coperLevel = pValue;
                    case 1 -> Abst_ToolRepairBlockEntity.this.maxcopper = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(pTag, this.stacks, pRegistries);
        this.coperLevel = pTag.getInt(SimpelAddMod.MODCUSTOM +"tool_repair_copper_level");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        pTag.putInt(SimpelAddMod.MODCUSTOM +"tool_repair_copper_level", coperLevel);
        ContainerHelper.saveAllItems(pTag, this.stacks, pRegistries);
    }
    @Override
    public int[] getSlotsForFace(Direction var1) {
        if (var1 == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        } else {
            return var1 == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int var1, ItemStack var2, @Nullable Direction var3) {
        if(var1 == COPPERSLOT && var2.is(Items.COPPER_INGOT)){
            return true;
        }
        if(var1 == REPAIRSLOT && (var2.isDamaged() || var2.is(ModItems.GRINDERHEAD_BROKEN))){
            return true;
        }
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int var1, ItemStack var2, Direction var3) {
        if(var1 == REPAIRSLOT){
            if(var2.isDamaged() || var2.is(ModItems.GRINDERHEAD_BROKEN)){
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.simpeladdmod.tool_repair");
    }

    @Override
    public ItemStack getItem(int var1) {
        return this.stacks.get(var1);
    }

    @Override
    public ItemStack removeItem(int var1, int var2) {
        return ContainerHelper.removeItem(this.stacks, var1, var2);
    }

    @Override
    public ItemStack removeItemNoUpdate(int var1) {
        return ContainerHelper.takeItem(this.stacks, var1);
    }

    @Override
    public void setItem(int var1, ItemStack var2) {
        ItemStack $$2 = this.stacks.get(var1);
        boolean $$3 = !var2.isEmpty() && ItemStack.isSameItem($$2, var2);
        this.stacks.set(var1, var2);
        if (var2.getCount() > this.getMaxStackSize()) {
            var2.setCount(this.getMaxStackSize());
        }
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.stacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        this.stacks = nonNullList;
    }

    @Override
    public int getContainerSize() {
        return this.stacks.size();
    }
    @Override
    public boolean stillValid(Player $$0) {
        return Container.stillValidBlockEntity(this, $$0);
    }



    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return this.saveWithFullMetadata(pRegistries);
    }

    public void tick(ServerLevel world, BlockPos pos) {
        if(world.isClientSide)
            return;
        setCopperLevel();
        if(!this.stacks.get(REPAIRSLOT).isEmpty() && coperLevel > 0){
            if(this.stacks.get(REPAIRSLOT).is(ModItems.GRINDERHEAD_BROKEN)) {
                    this.tickCount++;
                    if (this.tickCount == this.maxTickCount) {
                        world.playLocalSound(pos, SoundEvents.ANVIL_HIT, SoundSource.BLOCKS, 1.0f, 1.0f, true);
                        this.stacks.set(REPAIRSLOT, GrinderHeadItem_Broken.healItem(20, this.stacks.get(REPAIRSLOT)));
                        this.coperLevel -= 1;
                        this.tickCount = 0;
                    }
            }
            if(this.stacks.get(REPAIRSLOT).isDamaged()) {
                    this.tickCount++;
                    if (this.tickCount == this.maxTickCount) {
                        world.playLocalSound(pos, SoundEvents.ANVIL_HIT, SoundSource.BLOCKS, 1.0f, 1.0f, true);
                        this.stacks.get(REPAIRSLOT).setDamageValue(this.stacks.get(REPAIRSLOT).getDamageValue() - 20);
                        this.coperLevel -= 1;
                        this.tickCount = 0;
                    }
            }
        }else {
            this.tickCount = 0;
        }
    }

    private void setCopperLevel(){
        if(this.coperLevel == maxcopper)
            return;
        if(coperLevel < maxcopper) {
            if (this.stacks.get(COPPERSLOT).is(Items.COPPER_INGOT)) {
                this.coperLevel += 1;
                this.removeItem(COPPERSLOT, 1);
            }
        }
    }
    public ItemStack getCopperOutput() {
        return new ItemStack(Items.COPPER_INGOT, this.coperLevel);
    }
}
