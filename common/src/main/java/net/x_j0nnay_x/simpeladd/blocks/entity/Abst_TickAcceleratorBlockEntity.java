package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.blocks.Abst_TickAcceleratorBlock;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import org.jetbrains.annotations.Nullable;

//note
//this block was inspired by Starforcrafts Tick Accelerator Mod all code was inspired by the work in that mod. I did change things with the time and how it works.
//I did not copy the code, but I did use it as a reference to make this block work.
//I did use the Image used in the mod for homage to the inspiration of the block.

public abstract class Abst_TickAcceleratorBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    protected NonNullList<ItemStack> stacks = NonNullList.withSize(3, ItemStack.EMPTY);
    public static int SPEEDSLOT = 0;
    public static int EFFICIENCYSLOT = 1;
    public static int COPPERSLOT = 2;
    public int maxcopper =  100;
    public int coperLevel;
    public int tickSpeed;
    public int tickEfficiency = 0;
    public static int efficiencyUse = 3;
    public int effUse;
    private static int tickCountMax = 3600;
    private int tickCount;
    private static final int[] SLOTS_FOR_UP = new int[]{COPPERSLOT};
    private static final int[] SLOTS_FOR_DOWN = new int[]{COPPERSLOT};
    private static final int[] SLOTS_FOR_SIDES = new int[]{COPPERSLOT};
    protected final ContainerData data;

    protected Abst_TickAcceleratorBlockEntity(BlockEntityType<?> $$0, BlockPos $$1, BlockState $$2) {
        super($$0, $$1, $$2);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> Abst_TickAcceleratorBlockEntity.this.coperLevel;
                    case 1 -> Abst_TickAcceleratorBlockEntity.this.tickSpeed;
                    case 2 -> Abst_TickAcceleratorBlockEntity.this.tickEfficiency;
                    case 3 -> Abst_TickAcceleratorBlockEntity.this.effUse;
                    case 4 -> Abst_TickAcceleratorBlockEntity.this.tickCount;
                    case 5 -> Abst_TickAcceleratorBlockEntity.this.maxcopper;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> Abst_TickAcceleratorBlockEntity.this.coperLevel = pValue;
                    case 1 -> Abst_TickAcceleratorBlockEntity.this.tickSpeed = pValue;
                    case 2 -> Abst_TickAcceleratorBlockEntity.this.tickEfficiency = pValue;
                    case 3 -> Abst_TickAcceleratorBlockEntity.this.effUse = pValue;
                    case 4 -> Abst_TickAcceleratorBlockEntity.this.tickCount = pValue;
                    case 5 -> Abst_TickAcceleratorBlockEntity.this.maxcopper = pValue;
                }
            }

            @Override
            public int getCount() {
                return 6;
            }
        };
    }

    @Override
    public void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(pTag, this.stacks, pRegistries);
        this.coperLevel = pTag.getInt(SimpelAddMod.MODCUSTOM +"tick_accelerator_copper_level");
        this.tickSpeed = pTag.getInt(SimpelAddMod.MODCUSTOM +"tick_accelerator_tick_speed");
        this.tickEfficiency = pTag.getInt(SimpelAddMod.MODCUSTOM +"tick_accelerator_tick_efficiency");
        this.effUse = pTag.getInt(SimpelAddMod.MODCUSTOM +"tick_accelerator_efficiency_use");
        this.tickCount = pTag.getInt(SimpelAddMod.MODCUSTOM +"tick_accelerator_tick_count");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        pTag.putInt(SimpelAddMod.MODCUSTOM +"tick_accelerator_copper_level", coperLevel);
        pTag.putInt(SimpelAddMod.MODCUSTOM +"tick_accelerator_tick_speed", tickSpeed);
        pTag.putInt(SimpelAddMod.MODCUSTOM +"tick_accelerator_tick_efficiency", tickEfficiency);
        pTag.putInt(SimpelAddMod.MODCUSTOM +"tick_accelerator_efficiency_use", effUse);
        pTag.putInt(SimpelAddMod.MODCUSTOM +"tick_accelerator_tick_count", tickCount);
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
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int var1, ItemStack var2, Direction var3) {
        return false;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.simpeladdmod.tick_accelerator");
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
        setUpGrades();
        setCopperLevel();
        if(Boolean.TRUE.equals(world.getBlockState(pos).getValue(Abst_TickAcceleratorBlock.POWERED)))
            return;
        int range = this.getCountBoost() + 1 ;
        if (!hasTickableBlocksinRange(world, pos)) {
            return;
        }
        if (this.coperLevel > 0) {
            this.tickCount++;
            if (this.tickCount >= tickCountMax) {
                if (this.tickEfficiency == 1) {
                    if (this.effUse == efficiencyUse) {
                        this.effUse = 0;
                        this.coperLevel--;
                    }
                    if (this.effUse < efficiencyUse) {
                        this.effUse++;
                    }
                    this.tickCount = 0;
                }
                if (this.tickEfficiency == 0) {
                    this.coperLevel--;
                    this.tickCount = 0;
                }
            }
            for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-range, -range, -range), pos.offset(range, range, range))) {
                accelerateTick(world, blockPos);
            }
        }
    }

    public ItemStack getCopperOutput() {
        return new ItemStack(Items.COPPER_INGOT, this.coperLevel);
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

    private void setUpGrades(){
        if (this.stacks.get(SPEEDSLOT).is(ModItems.SPEEDUPGRADE_1)) {
            this.tickSpeed = 4;
        }if (this.stacks.get(SPEEDSLOT).is(ModItems.SPEEDUPGRADE_2)) {
            this.tickSpeed = 6;
        }if (this.stacks.get(SPEEDSLOT).is(ModItems.SPEEDUPGRADE_3)) {
            this.tickSpeed = 8;
        }if (this.stacks.get(SPEEDSLOT).is(ModItems.SPEEDUPGRADE_4)) {
            this.tickSpeed = 10;
        }if (this.stacks.get(SPEEDSLOT).isEmpty()){
            this.tickSpeed = 2;
        }if (this.stacks.get(EFFICIENCYSLOT).isEmpty()){
            this.tickEfficiency = 0;
        }if (!this.stacks.get(EFFICIENCYSLOT).isEmpty()){
            this.tickEfficiency = 1;
        }
    }
    public boolean hasTickableBlocksinRange(ServerLevel serverLevel, BlockPos blockPos) {
        int range = this.getCountBoost() + 1 ;
        for (BlockPos blockPos1 : BlockPos.betweenClosed(blockPos.offset(-range, -range, -range), blockPos.offset(range, range, range))) {
            if (isTickAccelBlock(serverLevel, serverLevel.getBlockState(blockPos1), serverLevel.getBlockEntity(blockPos1))) {
                return true;
            }
        }
        return false;
    }
    public int getCountBoost(){
        return this.stacks.get(EFFICIENCYSLOT).getCount();
    }


    public void accelerateTick(ServerLevel serverLevel, BlockPos blockPos) {
        BlockState blockState = serverLevel.getBlockState(blockPos);
        BlockEntity blockEntity = serverLevel.getBlockEntity(blockPos);
        if (!isTickAccelBlock(serverLevel, blockState, blockEntity))
            return;
        for (int i = 0; i < (double) tickSpeed; i++) {
            if (blockEntity != null) {
                BlockEntityTicker<BlockEntity> ticker = blockEntity.getBlockState().getTicker(serverLevel, (BlockEntityType<BlockEntity>) blockEntity.getType());
                if (ticker != null) {
                    ticker.tick(serverLevel, blockPos, blockEntity.getBlockState(), blockEntity);
                }
            } else if (blockState.isRandomlyTicking()) {
                if (serverLevel.random.nextInt(1365) == 0) {
                    blockState.randomTick(serverLevel, blockPos, serverLevel.random);
                }
            }
        }
    }

    public static boolean isTickAccelBlock(ServerLevel serverLevel, BlockState blockState, BlockEntity blockEntity) {
        if (blockEntity == null && !blockState.isRandomlyTicking()) {
            return false;
        }
        if (blockEntity instanceof Abst_TickAcceleratorBlockEntity) {
            return false;
        }
        if (blockEntity != null) {
            BlockEntityTicker<BlockEntity> ticker = blockEntity.getBlockState().getTicker(serverLevel, (BlockEntityType<BlockEntity>) blockEntity.getType());
            if (ticker == null) {
                return false;
            }
        }
        return true;
    }
}
