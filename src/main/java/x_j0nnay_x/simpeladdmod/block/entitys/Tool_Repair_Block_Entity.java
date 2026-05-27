package x_j0nnay_x.simpeladdmod.block.entitys;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.RangedResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jspecify.annotations.Nullable;
import x_j0nnay_x.simpeladdmod.init.Mod_Constants;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.items.Grow_Staff_Item;
import x_j0nnay_x.simpeladdmod.menu.Tool_Repair_Menu;


public class Tool_Repair_Block_Entity extends BlockEntity implements MenuProvider {

    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(2) {
        @Override
        protected void onContentsChanged(int index, ItemStack previousContents) {
            super.onContentsChanged(index, previousContents);
            Tool_Repair_Block_Entity.this.setChanged();
        }
    };
    private final ResourceHandler<ItemResource> sideHandler = RangedResourceHandler.ofSingleIndex(inventory, COPPERSLOT);
    private final ResourceHandler<ItemResource> otherHandler = RangedResourceHandler.ofSingleIndex(inventory, REPAIRSLOT);

    public static int REPAIRSLOT = 0;
    public static int COPPERSLOT = 1;
    protected final ContainerData data;
    public int maxcopper =  100;
    private int tickCount;
    private int maxTickCount = 40;
    public int coperLevel;

    public Tool_Repair_Block_Entity(BlockPos worldPosition, BlockState blockState) {
        super(Mod_Registrys.Mod_Block_Entity.TOOL_REPAIR_BLOCK_ENTTY.get(), worldPosition, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int dataId) {
                return switch (dataId) {
                    case 0 -> Tool_Repair_Block_Entity.this.coperLevel;
                    case 1 -> Tool_Repair_Block_Entity.this.maxcopper;
                    default -> 0;
                };
            }

            @Override
            public void set(int dataId, int value) {
                switch (dataId) {
                    case 0 -> Tool_Repair_Block_Entity.this.coperLevel = value;
                    case 1 -> Tool_Repair_Block_Entity.this.maxcopper = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.simpeladdmod.tool_repair");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new Tool_Repair_Menu(containerId, inventory, this, this.inventory, this.data);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt(Mod_Constants.MOD_CUSTOM +"tool_repair_copper_level", coperLevel);
        output.putChild("inventory", inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.coperLevel = input.getIntOr(Mod_Constants.MOD_CUSTOM +"tool_repair_copper_level", 0);
        input.child("inventory").ifPresent(inventory::deserialize);
    }


    public void drops() {
        if (this.level == null || this.level.isClientSide()) {
            return;
        }

        SimpleContainer inv = new SimpleContainer(inventory.size());
        for (int i = 0; i < inventory.size(); i++) {
            ItemAccess itemAccess = ItemAccess.forHandlerIndex(inventory, i);
            int amount = itemAccess.getAmount();
            ItemStack stack = itemAccess.getResource().toStack();
            inv.setItem(i, amount > 0 ? stack.copyWithCount(amount) : ItemStack.EMPTY);
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
        Containers.dropItemStack(this.level, this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ(),this.getCopperOutput());
    }

    public ResourceHandler<ItemResource> getItemHandler(Direction direction) {
        if (direction == null)
            return inventory;

        Direction facing = this.getBlockState().hasProperty(BlockStateProperties.HORIZONTAL_FACING)
                ? this.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING)
                : Direction.NORTH;

        return switch (getRelativeSide(facing, direction)) {
            case UP, DOWN -> otherHandler;
            case WEST, EAST,NORTH, SOUTH -> sideHandler;
        };
    }

    public ItemStack getCopperOutput() {
        return new ItemStack(Items.COPPER_INGOT, this.coperLevel);
    }

    private Direction getRelativeSide(Direction facing, Direction absoluteSide) {
        if (absoluteSide.getAxis().isVertical()) return absoluteSide;
        if (absoluteSide == facing) return Direction.NORTH;
        if (absoluteSide == facing.getOpposite()) return Direction.SOUTH;
        if (absoluteSide == facing.getClockWise()) return Direction.WEST;
        if (absoluteSide == facing.getCounterClockWise()) return Direction.EAST;
        return absoluteSide;
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        setCopperLevel();
        if(!this.inventory.getResource(REPAIRSLOT).isEmpty() && coperLevel > 0){
            if(this.inventory.getResource(REPAIRSLOT).toStack().isDamaged()) {
                this.tickCount++;
                if (this.tickCount >= this.maxTickCount) {
                    level.playLocalSound(pos, SoundEvents.ANVIL_HIT, SoundSource.BLOCKS, 1.0f, 1.0f, true);
                    this.inventory.set(REPAIRSLOT, ItemResource.of(this.healItem(this.inventory.getResource(REPAIRSLOT).toStack())), 1);
                    this.coperLevel -= 1;
                    this.tickCount = 0;
                }
            }
        }else {
            this.tickCount = 0;
        }
    }

    private ItemStack healItem(ItemStack stack){
       ItemStack newItem = stack.copy();
       newItem.setDamageValue(newItem.getDamageValue() - 20);
        return newItem;
    }

    private void setCopperLevel(){
        if(this.coperLevel == maxcopper)
            return;
        if(coperLevel < maxcopper) {
            if (this.inventory.getResource(COPPERSLOT).is(Items.COPPER_INGOT)) {
                this.coperLevel += 1;
                try(Transaction transaction = Transaction.openRoot()) {
                    inventory.extract(COPPERSLOT, ItemResource.of(inventory.getResource(COPPERSLOT).toStack()), 1, transaction);
                    transaction.commit();
                }
            }
        }
    }

    /* BLOCK ENTITY SYNC STUFF */
    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

}