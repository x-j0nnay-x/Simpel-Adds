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
import net.minecraft.world.WorldlyContainerHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.blocks.Abst_HarvesterBlock;
import net.x_j0nnay_x.simpeladd.blocks.SimpelFarmLand;
import net.x_j0nnay_x.simpeladd.core.ModBlocks;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class Abst_HavesterBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    protected NonNullList<ItemStack> stacks = NonNullList.withSize(3, ItemStack.EMPTY);
    public static int SPEEDSLOT = 0;
    public static int EFFICIENCYSLOT = 1;
    public static int GROWSLOT = 2;
    private static final int[] SLOTS_FOR_UP = new int[]{GROWSLOT};
    private static final int[] SLOTS_FOR_DOWN = new int[]{GROWSLOT};
    private static final int[] SLOTS_FOR_SIDES = new int[]{GROWSLOT};
    public int tickSpeed;
    public int tickCount;
    public int tickEfficiency = 0;

    protected final ContainerData data;

    protected Abst_HavesterBlockEntity(BlockEntityType<?> $$0, BlockPos $$1, BlockState $$2) {
        super($$0, $$1, $$2);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> Abst_HavesterBlockEntity.this.tickSpeed;
                    case 1 -> Abst_HavesterBlockEntity.this.tickEfficiency;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> Abst_HavesterBlockEntity.this.tickSpeed = pValue;
                    case 1 -> Abst_HavesterBlockEntity.this.tickEfficiency = pValue;
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
        this.tickSpeed = pTag.getInt(SimpelAddMod.MODCUSTOM +"harvester_tick_speed");
        this.tickEfficiency = pTag.getInt(SimpelAddMod.MODCUSTOM +"harvester_tick_efficiency");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        pTag.putInt(SimpelAddMod.MODCUSTOM +"harvester_tick_speed", tickSpeed);
        pTag.putInt(SimpelAddMod.MODCUSTOM +"harvester_tick_efficiency", tickEfficiency);
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
        if(var1 == GROWSLOT) {
            return var2.is(ModItems.GROWSTAFF);
        }
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int var1, ItemStack var2, Direction var3) {
       if(var1 == GROWSLOT) {
           if(var2.isDamaged() && var2.getDamageValue() >= var2.getMaxDamage()) {
               return true;
           }
        }
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
        if(isGrowStaffusable()) {
            startFarmLandLoop(world);
        }
        if(Boolean.TRUE.equals(world.getBlockState(pos).getValue(Abst_HarvesterBlock.POWERED)))
            return;
        if(this.tickCount >= this.tickSpeed) {
            startHarvestLoop(world);
        }else{
            this.tickCount++;
        }
    }

    private void setUpGrades(){
        if (this.stacks.get(SPEEDSLOT).is(ModItems.SPEEDUPGRADE_1)) {
            this.tickSpeed = 80;
        }if (this.stacks.get(SPEEDSLOT).is(ModItems.SPEEDUPGRADE_2)) {
            this.tickSpeed = 60;
        }if (this.stacks.get(SPEEDSLOT).is(ModItems.SPEEDUPGRADE_3)) {
            this.tickSpeed = 40;
        }if (this.stacks.get(SPEEDSLOT).is(ModItems.SPEEDUPGRADE_4)) {
            this.tickSpeed = 20;
        }if (this.stacks.get(SPEEDSLOT).isEmpty()){
            this.tickSpeed = 100;
        }if (this.stacks.get(EFFICIENCYSLOT).isEmpty()){
            this.tickEfficiency = 0;
        }if (!this.stacks.get(EFFICIENCYSLOT).isEmpty()){
            this.tickEfficiency = 1;
        }
    }

    public int getCountBoost(){
        return this.stacks.get(EFFICIENCYSLOT).getCount();
    }



//entyity above code
    public boolean isChestabove() {
        BlockPos blockPos = this.getBlockPos();
        BlockEntity blockEntity = this.level.getBlockEntity(blockPos.above());
        if(blockEntity != null) {
          if (getContainerAbove(blockPos, this.level, this.level.getBlockState(blockPos)) instanceof Container) {
              return true;
          }
        }
        return false;
    }
    @Nullable
    public static Container getContainerAbove(BlockPos blockPos, Level level, BlockState state) {
        BlockPos containerPos = blockPos.above();
        Container container = getBlockContainer(level, containerPos, state);
        if (container == null) {
            container = getEntityContainer(level, containerPos.getX(), containerPos.getY(), containerPos.getZ());
        }

        return container;
    }
    @Nullable
    private static Container getEntityContainer(Level level, double x, double y, double z) {
        List<Entity> list = level.getEntities((Entity)null, new AABB(x - (double)0.5F, y - (double)0.5F, z - (double)0.5F, x + (double)0.5F, y + (double)0.5F, z + (double)0.5F), EntitySelector.CONTAINER_ENTITY_SELECTOR);
        return !list.isEmpty() ? (Container)list.get(level.random.nextInt(list.size())) : null;
    }
    @Nullable
    private static Container getBlockContainer(Level level, BlockPos pos, BlockState state) {
        Block block = state.getBlock();
        if (block instanceof WorldlyContainerHolder) {
            return ((WorldlyContainerHolder)block).getContainer(state, level, pos);
        } else {
            if (state.hasBlockEntity()) {
                BlockEntity var5 = level.getBlockEntity(pos);
                if (var5 instanceof Container) {
                    Container container = (Container)var5;
                    if (container instanceof ChestBlockEntity && block instanceof ChestBlock) {
                        container = ChestBlock.getContainer((ChestBlock)block, state, level, pos, true);
                    }

                    return container;
                }
            }

            return null;
        }
    }
//Item Handleing
    private static boolean canMergeItems(ItemStack stack1, ItemStack stack2) {
        return stack1.getCount() <= stack1.getMaxStackSize() && ItemStack.isSameItemSameComponents(stack1, stack2);
    }

    public static void justDrop(ServerLevel pLevel, List<ItemStack> drops, BlockPos cropPos) {
        for (ItemStack drop : drops) {
            ItemEntity itemEntity = new ItemEntity(pLevel, cropPos.getX(), cropPos.getY(), cropPos.getZ(), drop);
            pLevel.addFreshEntity(itemEntity);
        }
    }
    public void putDropsInChest(List<ItemStack> drops) {
        BlockPos blockPos = this.getBlockPos();
        Container container = getContainerAbove(blockPos, this.level, this.level.getBlockState(blockPos));
        int size = container.getContainerSize();
        for (ItemStack drop : drops) {
            for (int i = 0; i < size; i++) {
                ItemStack stack = container.getItem(i);
                if (stack.isEmpty()) {
                    container.setItem(i, drop);
                    break;
                } else if (canMergeItems(stack, drop)) {
                    int count = Math.min(drop.getCount(), stack.getMaxStackSize() - stack.getCount());
                    stack.grow(count);
                    drop.shrink(count);
                    if (drop.isEmpty()) {
                        break;
                    }
                }
            }

        }
    }

//Harvesting code
    private void startHarvestLoop(ServerLevel world) {
        BlockPos blockPos = this.getBlockPos();
        int range = this.getCountBoost() + 1 ;
        int cropRange = 2;
        for (BlockPos blockPos1 : BlockPos.betweenClosed(blockPos.offset(-range, -cropRange, -range), blockPos.offset(range, cropRange, range))) {
            havestBlocks(world, blockPos1);
        }
        this.tickCount = 0;
    }

    public void havestBlocks(ServerLevel serverLevel, BlockPos blockPos) {
        BlockState blockState = serverLevel.getBlockState(blockPos);
        if (!isHavestableBlock(blockState))
            return;
        BlockState crop = serverLevel.getBlockState(blockPos);
        if (crop.getBlock() instanceof BushBlock) {
            List<ItemStack> drops = harvestCropDrops(serverLevel, blockPos, crop);
            if (!drops.isEmpty()) {
                if(isChestabove()) {
                    putDropsInChest(drops);
                }else{
                    justDrop(serverLevel, drops, blockPos.above());
                }
            }
        }
    }

    public static List<ItemStack> harvestCropDrops(ServerLevel pLevel, BlockPos cropPos, BlockState crop) {
        List<ItemStack> drops = new ArrayList<>();
        if (crop.getBlock() instanceof BushBlock) {
            BlockEntity blockEntity = pLevel.getBlockEntity(cropPos);
            List<ItemStack> potentialDrops = Block.getDrops(crop, pLevel, cropPos, blockEntity);
            if (potentialDrops.isEmpty())
                return drops;
            if (potentialDrops.size() > 1 || potentialDrops.get(0).getCount() > 1) {
                BlockState placeState = crop.getBlock().defaultBlockState();
                drops.addAll(potentialDrops);
                for (ItemStack drop : drops) {
                    if (drop.getItem() instanceof BlockItem blockItem) {
                        drop.shrink(1);
                        break;
                    }
                }
                pLevel.destroyBlock(cropPos, false);
                pLevel.setBlockAndUpdate(cropPos, placeState);
            }
        }
        return drops;
    }
    public static boolean isHavestableBlock(BlockState blockState) {
        Block block = blockState.getBlock();
        if (block instanceof CropBlock) {
            if (((CropBlock) block).getAge(blockState) == ((CropBlock) block).getMaxAge()) {
                return true;
            }
        }
        return false;
    }
//change farmland to simpelfarmland
    private void startFarmLandLoop(ServerLevel world) {
        BlockPos blockPos = this.getBlockPos();
        int range = this.getCountBoost() + 1 ;
        int cropRange = 2;
        for (BlockPos blockPos1 : BlockPos.betweenClosed(blockPos.offset(-range, -cropRange, -range), blockPos.offset(range, cropRange, range))) {
            turnToFarmLand(world, blockPos1);
        }
    }
    private void damageGrowStaff(){
        ItemStack growstaf = this.stacks.get(GROWSLOT);
        if(growstaf.getDamageValue() > growstaf.getMaxDamage()){
            return;
        }else {
            growstaf.setDamageValue(growstaf.getDamageValue() + 1);
        }
    }
    private boolean isGrowStaffusable(){
        return this.stacks.get(GROWSLOT).is(ModItems.GROWSTAFF) && this.stacks.get(GROWSLOT).getDamageValue() < this.stacks.get(GROWSLOT).getMaxDamage();
    }

    public void turnToFarmLand(ServerLevel level, BlockPos blockPos){
        BlockPos farmlandPos = blockPos.below();
        if(level.getBlockState(farmlandPos).getBlock() != Blocks.FARMLAND)
            return;
        int blockValue = level.getBlockState(farmlandPos).getValue(FarmBlock.MOISTURE);
        level.setBlockAndUpdate(farmlandPos, ModBlocks.SIMPELFARMLAND.defaultBlockState().setValue(FarmBlock.MOISTURE, blockValue));
        damageGrowStaff();

    }

}
