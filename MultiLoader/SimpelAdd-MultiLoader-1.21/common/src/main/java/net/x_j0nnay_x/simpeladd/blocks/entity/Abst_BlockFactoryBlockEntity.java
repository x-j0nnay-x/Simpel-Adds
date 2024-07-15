package net.x_j0nnay_x.simpeladd.blocks.entity;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.blocks.Abst_BlockFactoryBlock;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import org.jetbrains.annotations.Nullable;

public abstract class Abst_BlockFactoryBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {

    protected NonNullList<ItemStack> stacks = NonNullList.withSize(7, ItemStack.EMPTY);

    public static int WATERSLOT = 5;
    public static int LAVASLOT = 6;

    public static int GRINDERSLOT = 0;
    public static int COBBLESLOT = 1;
    public static int GRAVALSLOT = 2;
    public static int SANDSLOT = 3;
    public static int OBSIDIANSLOT = 4;
    private static final int[] SLOTS_FOR_UP = new int[]{GRINDERSLOT};
    private static final int[] SLOTS_FOR_DOWN = new int[]{WATERSLOT, LAVASLOT};
    private static final int[] SLOTS_FOR_SIDES = new int[]{WATERSLOT, LAVASLOT};
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 35;
    private int grindsleft = 0;
    private int maxGrinds = 3;
    public int maxLavaUses = 4;
    public int lavaUses = 0;
    public int lavaLevel = 0;
    public int waterLevel = 0;

    private int bucketValue = 1000;


    protected Abst_BlockFactoryBlockEntity(BlockEntityType<?> $$0, BlockPos $$1, BlockState $$2) {
        super($$0, $$1, $$2);

        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> Abst_BlockFactoryBlockEntity.this.progress;
                    case 1 -> Abst_BlockFactoryBlockEntity.this.maxProgress;
                    case 2 -> Abst_BlockFactoryBlockEntity.this.grindsleft;
                    case 3 -> Abst_BlockFactoryBlockEntity.this.lavaUses;
                    case 4 -> Abst_BlockFactoryBlockEntity.this.waterLevel;
                    case 5 -> Abst_BlockFactoryBlockEntity.this.lavaLevel;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> Abst_BlockFactoryBlockEntity.this.progress = pValue;
                    case 1 -> Abst_BlockFactoryBlockEntity.this.maxProgress = pValue;
                    case 2 -> Abst_BlockFactoryBlockEntity.this.grindsleft = pValue;
                    case 3 -> Abst_BlockFactoryBlockEntity.this.lavaUses = pValue;
                    case 4 -> Abst_BlockFactoryBlockEntity.this.waterLevel = pValue;
                    case 5 -> Abst_BlockFactoryBlockEntity.this.lavaLevel = pValue;
                }
            }

            @Override
            public int getCount() {
                return 6;
            }
        };
    }



    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(pTag, this.stacks, pRegistries);
        this.progress = pTag.getShort(SimpelAddMod.MODCUSTOM + "blockfactroy_progress");
        this.grindsleft = pTag.getShort(SimpelAddMod.MODCUSTOM + "blockfactroy_grinds_left");
        this.lavaUses = pTag.getShort(SimpelAddMod.MODCUSTOM + "blockfactory_lavauses");
        this.lavaLevel = pTag.getShort(SimpelAddMod.MODCUSTOM + "blockfactroy_lavalevel");
        this.waterLevel = pTag.getShort(SimpelAddMod.MODCUSTOM + "blockfactroy_waterlevel");
    }

    @Override
    protected void saveAdditional(CompoundTag $$0, HolderLookup.Provider pRegistries) {
        super.saveAdditional($$0,pRegistries);
        $$0.putShort(SimpelAddMod.MODCUSTOM + "blockfactroy_progress", (short) this.progress);
        $$0.putShort(SimpelAddMod.MODCUSTOM + "blockfactroy_grinds_left", (short) this.grindsleft);
        $$0.putShort(SimpelAddMod.MODCUSTOM + "blockfactory_lavauses", (short) this.lavaUses);
        $$0.putShort(SimpelAddMod.MODCUSTOM + "blockfactroy_lavalevel", (short) this.lavaLevel);
        $$0.putShort(SimpelAddMod.MODCUSTOM + "blockfactroy_waterlevel", (short) this.waterLevel);
        ContainerHelper.saveAllItems($$0, this.stacks, pRegistries);
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return (direction == Direction.EAST && (index == LAVASLOT) && stack.is(Items.LAVA_BUCKET) ||
                direction == Direction.WEST && (index == LAVASLOT) && stack.is(Items.LAVA_BUCKET) ||
                direction == Direction.SOUTH && (index == LAVASLOT) && stack.is(Items.LAVA_BUCKET) ||
                direction == Direction.NORTH && (index == LAVASLOT) && stack.is(Items.LAVA_BUCKET) ||
                direction == Direction.EAST && (index == WATERSLOT) && stack.is(Items.WATER_BUCKET) ||
                direction == Direction.WEST && (index == WATERSLOT) && stack.is(Items.WATER_BUCKET) ||
                direction == Direction.SOUTH && (index == WATERSLOT) && stack.is(Items.WATER_BUCKET) ||
                direction == Direction.NORTH && (index == WATERSLOT) && stack.is(Items.WATER_BUCKET) ||
                direction == Direction.UP && (index == GRINDERSLOT));
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack var2, Direction direction) {
        return (direction == Direction.DOWN && (index == WATERSLOT && var2.is(Items.BUCKET)) ||
                (index == LAVASLOT && var2.is(Items.BUCKET)));
    }

    @Override
    public int getContainerSize() {
        return this.stacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.stacks)
            if (!itemstack.isEmpty())
                return false;
        return true;
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
    public int[] getSlotsForFace(Direction var1) {
        if (var1 == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        } else {
            return var1 == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
        }
    }

    @Override
    public void clearContent() {
        this.stacks.clear();
    }


    @Override
    public boolean stillValid(Player $$0) {
        return Container.stillValidBlockEntity(this, $$0);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.simpeladdmod.blockfactory_block");
    }

    public void sendUpdate() {
        setChanged();

        if (this.level != null)
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }



    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return  this.saveWithFullMetadata(pRegistries);
    }
    //Processing

    public void blockFactoryTick(Level pLevel, BlockPos pPos, BlockState pState) {
        if (canFillWater()) {
            fillWater();
        }
        if (canFillLava()) {
            fillLava();
        }
        pState = pState.setValue(Abst_BlockFactoryBlock.WORKING, Boolean.valueOf(isWorking()));
        pLevel.setBlock(pPos, pState, 3);
        if (hasLiquid()) {
            if (!isFull()) {
                increaseCraftingProgress();
                setChanged(pLevel, pPos, pState);
                if (CobbleSpace()) {
                    if (hasProgressFinished()) {
                        makeCobble();
                        resetProgress();
                    }
                }
                if (this.grindsleft > 0) {
                    if (GravalSpace() && !CobbleSpace()) {
                        if (hasProgressFinished()) {
                            useGrind();
                            makeGraval();
                            resetProgress();
                        }
                    }
                    if (SandSpace() && !GravalSpace() && !CobbleSpace()) {
                        if (hasProgressFinished()) {
                            useGrind();
                            makeSand();
                            resetProgress();
                        }
                    }
                } else {
                    resetGrinds();
                }
                if (ObslidanSpace() && !SandSpace() && !GravalSpace() && !CobbleSpace()) {
                    if (this.lavaUses > 0) {
                        if (hasProgressFinished()) {
                            makeObsidain();
                            useLava();
                            resetProgress();
                        }
                    } else {
                        resteLavaUses();
                    }
                }
                if (!ObslidanSpace() && !SandSpace() && !GravalSpace() && !CobbleSpace()) {
                    resetProgress();
                }
                if (this.grindsleft == 0 && !isFull() && !CobbleSpace()) {
                    resetProgress();
                }
            }
        }
    }

    private void useGrind() {
        this.grindsleft--;
    }

    private void useLava() {
        this.lavaUses--;
    }

    public void resteLavaUses() {
        if (this.lavaLevel > 0) {
            this.lavaLevel -= bucketValue;
            this.lavaUses = this.maxLavaUses;
        } else {
            this.lavaUses = 0;
        }
    }

    private void resetGrinds() {
        if (stacks.get(GRINDERSLOT).is(ModItems.GRINDERHEAD)) {
            if (stacks.get(GRINDERSLOT).getDamageValue() >= stacks.get(GRINDERSLOT).getMaxDamage()) {
                stacks.set(GRINDERSLOT, ItemStack.EMPTY);
            } else {
                stacks.get(GRINDERSLOT).setDamageValue(stacks.get(GRINDERSLOT).getDamageValue() + 1);
                grindsleft = maxGrinds;
            }
        } else {
            grindsleft = 0;
        }
    }

    public boolean isFull() {
        if (!CobbleSpace() && !GravalSpace() && !SandSpace() && !ObslidanSpace()) {
            return true;
        }
        return false;
    }

    private boolean isWorking() {
        if (hasLiquid() && !isFull()) {
            return true;
        }
        return false;
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasProgressFinished() {
        return this.progress >= this.maxProgress;
    }

    private void makeCobble() {
        ItemStack result = new ItemStack(Items.COBBLESTONE, 1);
        this.stacks.set(COBBLESLOT, new ItemStack(result.getItem(),
                this.stacks.get(COBBLESLOT).getCount() + result.getCount()));
    }

    private void makeGraval() {
        ItemStack result = new ItemStack(Items.GRAVEL, 1);
        this.stacks.set(GRAVALSLOT, new ItemStack(result.getItem(),
                this.stacks.get(GRAVALSLOT).getCount() + result.getCount()));
    }

    private void makeSand() {
        ItemStack result = new ItemStack(Items.SAND, 1);
        this.stacks.set(SANDSLOT, new ItemStack(result.getItem(),
                this.stacks.get(SANDSLOT).getCount() + result.getCount()));
    }

    private void makeObsidain() {
        ItemStack result = new ItemStack(Items.OBSIDIAN, 1);
        this.stacks.set(OBSIDIANSLOT, new ItemStack(result.getItem(),
                this.stacks.get(OBSIDIANSLOT).getCount() + result.getCount()));
    }

    public boolean hasLiquid() {
        return waterLevel > 0 && lavaLevel > 0 || lavaUses > 0;
    }

    private boolean CobbleSpace() {
        return this.stacks.get(COBBLESLOT).isEmpty() || this.stacks.get(COBBLESLOT).getCount() < 64;
    }

    private boolean GravalSpace() {
        return this.stacks.get(GRAVALSLOT).isEmpty() || this.stacks.get(GRAVALSLOT).getCount() < 64;
    }

    private boolean SandSpace() {
        return this.stacks.get(SANDSLOT).isEmpty() || this.stacks.get(SANDSLOT).getCount() < 64;
    }

    private boolean ObslidanSpace() {
        return this.stacks.get(OBSIDIANSLOT).isEmpty() || this.stacks.get(OBSIDIANSLOT).getCount() < 64;
    }

    public boolean canFillWater() {
        return waterLevel < bucketValue * 6;
    }

    public boolean canFillLava() {
        return lavaLevel < bucketValue * 6;

    }

    public void fillWater() {
        if (this.stacks.get(WATERSLOT).getItem() == (Items.WATER_BUCKET)) {
            this.removeItem(WATERSLOT, 1);
            this.stacks.set(WATERSLOT, new ItemStack(Items.BUCKET));
            this.waterLevel += bucketValue;
        }
    }


    public void fillLava() {
        if (this.stacks.get(LAVASLOT).getItem() == (Items.LAVA_BUCKET)) {
            this.removeItem(LAVASLOT, 1);
            this.stacks.set(LAVASLOT, new ItemStack(Items.BUCKET));
            lavaLevel += bucketValue;
        }
    }
}