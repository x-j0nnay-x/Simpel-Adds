package net.x_j0nnay_x.simpeladdmod.block.entity;

import net.minecraft.world.item.Items;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;



import javax.annotation.Nullable;

import java.util.stream.IntStream;

import io.netty.buffer.Unpooled;
import net.x_j0nnay_x.simpeladdmod.block.ModBlockEntities;
import net.x_j0nnay_x.simpeladdmod.until.ModTags;
import net.x_j0nnay_x.simpeladdmod.world.inventory.ChillerBlockGUIMenu;

public class ChillerBlockBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
	private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
	private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());

	public ChillerBlockBlockEntity(BlockPos position, BlockState state) {
		super(ModBlockEntities.CHILLER_BLOCK.get(), position, state);
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);
		if (!this.tryLoadLootTable(compound))
			this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(compound, this.stacks);
	}

	@Override
	public void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);
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
	public Component getDefaultName() {
		return Component.literal("chiller_block");
	}

	@Override
	public int getMaxStackSize() {
		return 64;
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return new ChillerBlockGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
	}

	@Override
	public Component getDisplayName() {
		return Component.literal("Chiller Block");
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.stacks;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> stacks) {
		this.stacks = stacks;
	}

	@Override
	public boolean canPlaceItem(int index, ItemStack stack) {
		if (index == ChillerBlockGUIMenu.WATTER_SLOT && stack.is(Items.WATER_BUCKET))
			return true;
		if (index == ChillerBlockGUIMenu.CHILL_SLOT && stack.is(ModTags.Items.CHILLING))
			return true;
		return false;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return IntStream.range(0, this.getContainerSize()).toArray();
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
		return (direction == Direction.EAST  && (index == ChillerBlockGUIMenu.CHILL_SLOT) ||
				direction == Direction.WEST && (index == ChillerBlockGUIMenu.CHILL_SLOT) ||
				direction == Direction.SOUTH && (index == ChillerBlockGUIMenu.CHILL_SLOT) ||
				direction == Direction.NORTH && (index == ChillerBlockGUIMenu.CHILL_SLOT) ||
				direction == Direction.UP && (index == ChillerBlockGUIMenu.WATTER_SLOT));
	}
	@Override
	public boolean canTakeItemThroughFace(int slotIndex, ItemStack itemStack, Direction direction) {
		// Only allow the down direction and only for the result slot.
		return (direction == Direction.DOWN && (slotIndex == ChillerBlockGUIMenu.OUTPUT_SLOT ||
				slotIndex == ChillerBlockGUIMenu.WATTER_SLOT && itemStack.is(Items.BUCKET)) );
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER)
			return handlers[facing.ordinal()].cast();
		return super.getCapability(capability, facing);
	}

	@Override
	public void setRemoved() {
		super.setRemoved();
		for (LazyOptional<? extends IItemHandler> handler : handlers)
			handler.invalidate();
	}
}
