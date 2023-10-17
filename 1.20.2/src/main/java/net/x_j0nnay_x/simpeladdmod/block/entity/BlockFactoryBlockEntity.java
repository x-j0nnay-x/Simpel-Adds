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
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.world.inventory.BlockfactoryMenu;

public class BlockFactoryBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
	private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(7, ItemStack.EMPTY);
	private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());



	public BlockFactoryBlockEntity(BlockPos position, BlockState state) {
		super(ModBlockEntities.BLOCK_FACTORY_BLOCK.get(), position, state);
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
		return Component.literal("block_factory_block");
	}

	@Override
	public int getMaxStackSize() {
		return 64;
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return new BlockfactoryMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
	}

	@Override
	public Component getDisplayName() {
		return Component.literal("Block Factory Block");
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
		if (index == BlockfactoryMenu.Cobbel_Slot)
			return false;
		if (index == BlockfactoryMenu.Graval_Slot)
			return false;
		if (index == BlockfactoryMenu.Sand_slot)
			return false;
		if (index == BlockfactoryMenu.Obsidian_slot)
			return false;
		if (index == BlockfactoryMenu.GRINDER_SLOT && stack.is(ModItems.GRINDERHEAD.get()))
			return true;
		if (index == BlockfactoryMenu.Lava_Slot && stack.is(Items.LAVA_BUCKET))
			return true;
		if (index == BlockfactoryMenu.Water_Slot && stack.is(Items.WATER_BUCKET))
			return true;
		return false;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return IntStream.range(0, this.getContainerSize()).toArray();
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
		return (direction == Direction.EAST  && (index == BlockfactoryMenu.Lava_Slot ) && stack.is(Items.LAVA_BUCKET) ||
				direction == Direction.WEST && (index == BlockfactoryMenu.Lava_Slot) && stack.is(Items.LAVA_BUCKET)||
				direction == Direction.SOUTH && (index == BlockfactoryMenu.Lava_Slot) && stack.is(Items.LAVA_BUCKET) ||
				direction == Direction.NORTH && (index == BlockfactoryMenu.Lava_Slot) && stack.is(Items.LAVA_BUCKET) ||
				direction == Direction.EAST  && (index == BlockfactoryMenu.Water_Slot ) && stack.is(Items.WATER_BUCKET) ||
				direction == Direction.WEST && (index == BlockfactoryMenu.Water_Slot) && stack.is(Items.WATER_BUCKET)||
				direction == Direction.SOUTH && (index == BlockfactoryMenu.Water_Slot) && stack.is(Items.WATER_BUCKET) ||
				direction == Direction.NORTH && (index == BlockfactoryMenu.Water_Slot) && stack.is(Items.WATER_BUCKET) ||
				direction == Direction.UP && (index == BlockfactoryMenu.GRINDER_SLOT));
	}


	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {

		return (direction == Direction.DOWN && (
				index == BlockfactoryMenu.Cobbel_Slot || index == BlockfactoryMenu.Graval_Slot ||
				index == BlockfactoryMenu.Sand_slot ||index == BlockfactoryMenu.Obsidian_slot ||
				index == BlockfactoryMenu.Water_Slot && stack.is(Items.BUCKET)||
				index == BlockfactoryMenu.Lava_Slot && stack.is(Items.BUCKET)));
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
