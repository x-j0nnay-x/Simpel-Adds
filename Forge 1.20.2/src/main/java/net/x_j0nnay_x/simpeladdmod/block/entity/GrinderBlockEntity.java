package net.x_j0nnay_x.simpeladdmod.block.entity;


import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.x_j0nnay_x.simpeladdmod.block.ModBlockEntities;
import net.x_j0nnay_x.simpeladdmod.block.custom.GrinderBlock;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;

import net.x_j0nnay_x.simpeladdmod.recipe.GrinderRecipe;
import net.x_j0nnay_x.simpeladdmod.screen.grinder.GrinderMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.stream.IntStream;

public class GrinderBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3);
    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
    private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());
    public static int INPUTSLOT = 0;
    public static int GRINDERSLOT = 1;
    public static int OUTPUTSLOT = 2;
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 20;
    private int grindsleft = 0 ;
    private int maxGrinds = 3;
    public GrinderBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.GRINDER.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> GrinderBlockEntity.this.progress;
                    case 1 -> GrinderBlockEntity.this.maxProgress;
                    case 2 -> GrinderBlockEntity.this.grindsleft;
                    case 3 -> GrinderBlockEntity.this.maxGrinds;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> GrinderBlockEntity.this.progress = pValue;
                    case 1 -> GrinderBlockEntity.this.maxProgress = pValue;
                    case 2 -> GrinderBlockEntity.this.grindsleft = pValue;
                    case 3 -> GrinderBlockEntity.this.maxGrinds = pValue;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.simpeladdmod.grinder_block");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new GrinderMenu(id, inventory, this, this.data);
    }

    @Override
    public int[] getSlotsForFace(Direction pSide) {
        return IntStream.range(0, this.getContainerSize()).toArray();
    }
    @Override
    public int getMaxStackSize() {
        return 64;
    }
    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return (direction == Direction.EAST  && (index == INPUTSLOT) ||
                direction == Direction.WEST && (index == INPUTSLOT) ||
                direction == Direction.SOUTH && (index == INPUTSLOT) ||
                direction == Direction.NORTH && (index == INPUTSLOT) ||
                direction == Direction.UP && (index == GRINDERSLOT));
    }
    @Override
    public boolean canTakeItemThroughFace(int slotIndex, ItemStack itemStack, Direction direction) {
        // Only allow the down direction and only for the result slot.
        return (direction == Direction.DOWN && (slotIndex == OUTPUTSLOT));
    }

    @Override
    public int getContainerSize() {
        return stacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.stacks)
            if (!itemstack.isEmpty())
                return false;
        return true;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.stacks;
    }

    @Override
    protected void setItems( NonNullList<ItemStack> stacks) {
        this.stacks = stacks;
    }
    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        itemHandler.deserializeNBT(compound.getCompound("inventory"));
        progress = compound.getInt("grinder_progress");
        grindsleft = compound.getInt("grinder_grinds_left");
        if (!this.tryLoadLootTable(compound))
            this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compound, this.stacks);
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("inventory", itemHandler.serializeNBT());
        compound.putInt("grinder_progress", progress);
        compound.putInt("grinder_grinds_left", grindsleft);
        if (!this.trySaveLootTable(compound)) {
            ContainerHelper.saveAllItems(compound, this.stacks);
        }
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER)
            return handlers[facing.ordinal()].cast();
        return super.getCapability(capability, facing);
    }


// processing

    public void tick(Level pLevel, BlockPos pPos, BlockState pState){
        pState = pState.setValue(GrinderBlock.WORKING, Boolean.valueOf(isWorking()));
        pLevel.setBlock(pPos, pState, 3);
        if(hasRecipe()){
            if(grindsleft > 0){
                increaseCraftingProgress();
                setChanged(pLevel, pPos, pState);
                if(hasProgressFinished()){
                    useGrind();
                    craftItem();
                    resetProgress();
                }
            }else{
                resetGrinds();
            }

        }else {

            resetProgress();
        }
    }

    private void useGrind(){
        grindsleft --;
    }
    private void resetGrinds() {
        if(stacks.get(GRINDERSLOT).is(ModItems.GRINDERHEAD.get())){
            stacks.get(GRINDERSLOT).setDamageValue(stacks.get(GRINDERSLOT).getDamageValue() + 1);
            grindsleft = maxGrinds;
        }else {
            grindsleft = 0;
        }
    }
    private boolean canWork(){
        return  hasRecipe();
    }
    private boolean isWorking() {
        if (canWork()){
            if(grindsleft > 0 || stacks.get(GRINDERSLOT).is(ModItems.GRINDERHEAD.get())){
                return true;
            }
        }
        return false;
    }
    private void resetProgress() {
        progress = 0;
    }
    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

   private void craftItem() {
       /* Optional<GrinderRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);
        this.removeItem(INPUTSLOT, 1);
        this.stacks.set(OUTPUTSLOT, new ItemStack(result.getItem(),
                this.stacks.get(OUTPUTSLOT).getCount() + result.getCount()));*/
       ItemStack result = new ItemStack(ModItems.NEHTERITE_SHARD_DUST.get(), 2);
       this.removeItem(INPUTSLOT, 1);
       this.stacks.set(OUTPUTSLOT, new ItemStack(result.getItem(),
               this.stacks.get(OUTPUTSLOT).getCount() + result.getCount()));


    }
    private boolean hasRecipe() {
     /*   Optional<GrinderRecipe> recipe = getCurrentRecipe();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());
         return canInsertOutputAmount(result.getCount()) && canInsertOutputItem(result.getItem());
         
      */

         boolean hasCraftingItem = this.itemHandler.getStackInSlot(INPUTSLOT).getItem() == ModItems.NEHTERITE_SHARD_RAW.get();
         ItemStack result = new ItemStack(ModItems.NEHTERITE_SHARD_DUST.get());


        return hasCraftingItem && canInsertOutputAmount(result.getCount()) && canInsertOutputItem(result.getItem());
}
 /*   private Optional<GrinderRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.stacks.size());
        for(int i = 0; i < stacks.size(); i++) {
            inventory.setItem(i, this.stacks.get(i));
        }

        return  this.level.getRecipeManager().getRecipeFor(GrinderRecipe.Type.INSTANCE, inventory, level);
    }
*/

    private boolean canInsertOutputItem(Item item) {
        return this.stacks.get(OUTPUTSLOT).isEmpty() || this.stacks.get(OUTPUTSLOT).is(item);
    }

    private boolean canInsertOutputAmount(int count) {
        return this.stacks.get(OUTPUTSLOT).getCount() + count <= this.stacks.get(OUTPUTSLOT).getMaxStackSize();
    }


}