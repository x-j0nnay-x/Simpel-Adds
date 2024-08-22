package net.x_j0nnay_x.simpeladd.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.x_j0nnay_x.simpeladd.blocks.Abst_BlockFactoryBlock;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModTags;
import net.x_j0nnay_x.simpeladd.data.OutPutSlotChange;
import org.jetbrains.annotations.Nullable;

public abstract class Abst_BlockFactoryBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer{

    protected NonNullList<ItemStack> stacks = NonNullList.withSize(7, ItemStack.EMPTY);
    public static int WATERSLOT = 5;
    public static int LAVASLOT = 6;
    public static int GRINDERSLOT = 0;
    public static int COBBLESLOT = 1;
    public static int GRAVALSLOT = 2;
    public static int SANDSLOT = 3;
    public static int OBSIDIANSLOT = 4;
    private static final int[] SLOTS_FOR_UP = new int[]{GRINDERSLOT};
    private static final int[] SLOTS_FOR_DOWN = new int[]{WATERSLOT, LAVASLOT, COBBLESLOT, SANDSLOT, GRAVALSLOT, OBSIDIANSLOT};
    private static final int[] SLOTS_FOR_SIDES = new int[]{WATERSLOT, LAVASLOT};
    protected final ContainerData data;
    private int makingItem;
    private int progress = 0;
    private int maxProgress = 35;
    private int grindsleft = 0;
    private int maxGrinds = 3;
    public int maxLavaUses = 4;
    public int lavaUses = 0;
    public int lavaLevel = 0;
    public int waterLevel = 0;
    public int outPutSlot = 0;
    public OutPutSlotChange SlotOutPut = OutPutSlotChange.DISABLE;
    public int canExtractOutput = 0;
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
                    case 6 -> Abst_BlockFactoryBlockEntity.this.outPutSlot;
                    case 7 -> Abst_BlockFactoryBlockEntity.this.SlotOutPut.ordinal();
                    case 8 -> Abst_BlockFactoryBlockEntity.this.canExtractOutput;
                    case 9 -> Abst_BlockFactoryBlockEntity.this.makingItem;
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
                    case 6 -> Abst_BlockFactoryBlockEntity.this.outPutSlot = pValue;
                    case 7 -> Abst_BlockFactoryBlockEntity.this.SlotOutPut = OutPutSlotChange.values()[pValue];
                    case 8 -> Abst_BlockFactoryBlockEntity.this.canExtractOutput = pValue;
                    case 9 -> Abst_BlockFactoryBlockEntity.this.makingItem = pValue;
                }
            }

            @Override
            public int getCount() {
                return 10;
            }
        };
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(pTag, this.stacks, pRegistries);
        this.progress = pTag.getInt(SimpelAddMod.MODCUSTOM + "blockfactroy_progress");
        this.grindsleft = pTag.getInt(SimpelAddMod.MODCUSTOM + "blockfactroy_grinds_left");
        this.lavaUses = pTag.getInt(SimpelAddMod.MODCUSTOM + "blockfactory_lavauses");
        this.lavaLevel = pTag.getInt(SimpelAddMod.MODCUSTOM + "blockfactroy_lavalevel");
        this.waterLevel = pTag.getInt(SimpelAddMod.MODCUSTOM + "blockfactroy_waterlevel");
        this.outPutSlot = pTag.getInt(SimpelAddMod.MODCUSTOM + "blockfactory_outputslot");
        this.makingItem = pTag.getInt(SimpelAddMod.MODCUSTOM + "blockfactory_making");
        this.SlotOutPut = OutPutSlotChange.values()[pTag.getInt(SimpelAddMod.MODCUSTOM + "blockfactory_outputslot_data")];
        this.canExtractOutput = pTag.getInt(SimpelAddMod.MODCUSTOM + "blockfactory_allow_output");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag,pRegistries);
        pTag.putInt(SimpelAddMod.MODCUSTOM + "blockfactroy_progress", this.progress);
        pTag.putInt(SimpelAddMod.MODCUSTOM + "blockfactroy_grinds_left", this.grindsleft);
        pTag.putInt(SimpelAddMod.MODCUSTOM + "blockfactory_lavauses", this.lavaUses);
        pTag.putInt(SimpelAddMod.MODCUSTOM + "blockfactroy_lavalevel", this.lavaLevel);
        pTag.putInt(SimpelAddMod.MODCUSTOM + "blockfactroy_waterlevel", this.waterLevel);
        pTag.putInt(SimpelAddMod.MODCUSTOM + "blockfactory_outputslot", this.outPutSlot);
        pTag.putInt(SimpelAddMod.MODCUSTOM + "blockfactory_making", this.makingItem);
        pTag.putInt(SimpelAddMod.MODCUSTOM + "blockfactory_outputslot_data", this.SlotOutPut.ordinal());
        pTag.putInt(SimpelAddMod.MODCUSTOM + "blockfactory_allow_output", this.canExtractOutput);
        ContainerHelper.saveAllItems(pTag, this.stacks, pRegistries);
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        if(direction == Direction.EAST || direction == Direction.WEST || direction == Direction.SOUTH ||direction == Direction.NORTH){
            if(index == LAVASLOT && stack.is(Items.LAVA_BUCKET)){
                return true;
            }
            if(index == WATERSLOT && stack.is(Items.WATER_BUCKET)){
                return true;
            }
        }if(direction == Direction.UP && index == GRINDERSLOT && stack.is(ModTags.Items.GRINDERS)){
            return true;
        }
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack var2, Direction direction) {
        if(direction == Direction.DOWN){
            if(index == WATERSLOT || index == LAVASLOT){
                if(var2.is(Items.BUCKET)){
                    return true;
                }
            }
            if(index == outPutSlot && this.canExtractOutput == 1){
                return true;
            }
            return false;
        }
        return false;
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

    public ContainerData getData(int index) {
        return this.data;
    }


    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void setData(int data, int set) {
        this.data.set(data, set);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        CompoundTag updateCompoundTag = new CompoundTag();
        saveAdditional(updateCompoundTag, pRegistries);
        return updateCompoundTag;
    }
//Processing
    public void blockFactoryTick(Level pLevel, BlockPos pPos, BlockState pState) {
        setBlockOuput();
        if (canFillWater()) {
            fillWater();
        }
        if (canFillLava()) {
            fillLava();
        }
        pState = pState.setValue(Abst_BlockFactoryBlock.WORKING, Boolean.valueOf(isWorking()));
        pLevel.setBlock(pPos, pState, 3);
        if (hasLiquid() && !isFull()) {
                increaseCraftingProgress();
                setChanged(pLevel, pPos, pState);
                if (CobbleSpace()) {
                    if (hasProgressFinished()) {
                        craftItems();
                        resetProgress();
                    }
                }
                if (this.grindsleft > 0) {
                    if (GravalSpace() && !CobbleSpace()) {
                        if (hasProgressFinished()) {
                            useGrind();
                            craftItems();
                            resetProgress();
                        }
                    }
                    if (SandSpace() && !GravalSpace() && !CobbleSpace()) {
                        if (hasProgressFinished()) {
                            useGrind();
                            craftItems();
                            resetProgress();
                        }
                    }
                } else {
                    resetGrinds();
                }
                if (ObslidanSpace() && !SandSpace() && !GravalSpace() && !CobbleSpace()) {
                    if (this.lavaUses > 0) {
                        if (hasProgressFinished()) {
                            craftItems();
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
        if (stacks.get(GRINDERSLOT).is(ModTags.Items.GRINDERS)) {
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
            if(this.progress == 0){
                return false;
            }
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

    private void setBlockOuput(){
        if(CobbleSpace()){
            this.makingItem = 1;
        }
        if(!CobbleSpace() && GravalSpace()){
            this.makingItem = 2;
        }
        if(!CobbleSpace() && !GravalSpace() && SandSpace()){
            this.makingItem = 3;
        }
        if(!CobbleSpace() && !GravalSpace() && !SandSpace() && ObslidanSpace()){
            this.makingItem = 4;
        }
    }

    private void craftItems(){
        if(this.makingItem ==1 ){
            ItemStack result = new ItemStack(Items.COBBLESTONE, 1);
            this.stacks.set(COBBLESLOT, new ItemStack(result.getItem(),
                    this.stacks.get(COBBLESLOT).getCount() + result.getCount()));
        }
        if(this.makingItem == 2){
            ItemStack result = new ItemStack(Items.GRAVEL, 1);
            this.stacks.set(GRAVALSLOT, new ItemStack(result.getItem(),
                    this.stacks.get(GRAVALSLOT).getCount() + result.getCount()));
        }
        if(this.makingItem == 3){
            ItemStack result = new ItemStack(Items.SAND, 1);
            this.stacks.set(SANDSLOT, new ItemStack(result.getItem(),
                    this.stacks.get(SANDSLOT).getCount() + result.getCount()));
        }
        if(this.makingItem == 4){
            ItemStack result = new ItemStack(Items.OBSIDIAN, 1);
            this.stacks.set(OBSIDIANSLOT, new ItemStack(result.getItem(),
                    this.stacks.get(OBSIDIANSLOT).getCount() + result.getCount()));
        }
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

    public OutPutSlotChange getSlotOutPut() {
        return this.SlotOutPut;
    }

    public void setSlotOutPut(OutPutSlotChange slotOutPut) {
        this.SlotOutPut = slotOutPut;
        this.syncData();
    }

    public void syncData() {
        setChanged();
        if (level instanceof ServerLevel serverLevel) {
            LevelChunk chunk = serverLevel.getChunkAt(getBlockPos());
            if (chunk.getLevel().getChunkSource() instanceof ServerChunkCache serverChunkCache) {
                serverChunkCache.chunkMap.getPlayers(chunk.getPos(), false).forEach(this::syncContents);
            }
        }
    }

    public void syncContents(ServerPlayer player) {
        player.connection.send(getUpdatePacket());
    }
}