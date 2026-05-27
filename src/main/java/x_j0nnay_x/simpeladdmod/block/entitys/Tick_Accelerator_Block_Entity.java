package x_j0nnay_x.simpeladdmod.block.entitys;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.transfer.RangedResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jspecify.annotations.Nullable;
import x_j0nnay_x.simpeladdmod.block.Tick_Accelerator_Block;
import x_j0nnay_x.simpeladdmod.init.Mod_Constants;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.menu.Tick_Accelerator_Menu;

import java.util.function.Supplier;

//note
//this block was inspired by Starforcrafts Tick Accelerator Mod all code was inspired by the work in that mod. I did change things with the time and how it works.
//I did not copy the code, but I did use it as a reference to make this block work.
//I did use the Image used in the mod for homage to the inspiration of the block.

public class Tick_Accelerator_Block_Entity extends BlockEntity implements MenuProvider {

    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(5) {
        @Override
        protected void onContentsChanged(int index, ItemStack previousContents) {
            super.onContentsChanged(index, previousContents);
            Tick_Accelerator_Block_Entity.this.setChanged();
        }
    };
    private final ResourceHandler<ItemResource> allSideHandler = RangedResourceHandler.ofSingleIndex(inventory, COPPERSLOT);

    public static int SPEEDSLOT = 0;
    public static int EFFICIENCYSLOT = 1;
    public static int COPPERSLOT = 2;
    public static int REMOTESLOT1 =3;
    public static int REMOTESLOT2 = 4;
    protected final ContainerData data;
    public int maxcopper =  100;
    public int coperLevel;
    public int tickSpeed;
    public static int efficiencyUse = 3;
    public int effUse;
    private static int tickCountMax = 3600;
    private int tickCount;


    public Tick_Accelerator_Block_Entity(BlockPos worldPosition, BlockState blockState) {
        super(Mod_Registrys.Mod_Block_Entity.TICK_ACCELERATOR_BLOCK_ENTITY.get(), worldPosition, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int dataId) {
                return switch (dataId) {
                    case 0 -> Tick_Accelerator_Block_Entity.this.coperLevel;
                    case 1 -> Tick_Accelerator_Block_Entity.this.tickSpeed;
                    case 2 -> Tick_Accelerator_Block_Entity.this.effUse;
                    case 3 -> Tick_Accelerator_Block_Entity.this.tickCount;
                    case 4 -> Tick_Accelerator_Block_Entity.this.maxcopper;
                    default -> 0;
                };
            }

            @Override
            public void set(int dataId, int value) {
                switch (dataId) {
                    case 0 -> Tick_Accelerator_Block_Entity.this.coperLevel = value;
                    case 1 -> Tick_Accelerator_Block_Entity.this.tickSpeed = value;
                    case 2 -> Tick_Accelerator_Block_Entity.this.effUse = value;
                    case 3 -> Tick_Accelerator_Block_Entity.this.tickCount = value;
                    case 4 -> Tick_Accelerator_Block_Entity.this.maxcopper = value;
                }
            }

            @Override
            public int getCount() {
                return 5;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.simpeladdmod.tick_accelerator");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new Tick_Accelerator_Menu(containerId, inventory, this, this.inventory, this.data);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt(Mod_Constants.MOD_CUSTOM +"tick_accelerator_copper_level", coperLevel);
        output.putInt(Mod_Constants.MOD_CUSTOM  +"tick_accelerator_tick_speed", tickSpeed);
        output.putInt(Mod_Constants.MOD_CUSTOM  +"tick_accelerator_efficiency_use", effUse);
        output.putInt(Mod_Constants.MOD_CUSTOM  +"tick_accelerator_tick_count", tickCount);
        output.putChild("inventory", inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.coperLevel = input.getIntOr(Mod_Constants.MOD_CUSTOM  +"tick_accelerator_copper_level", 0);
        this.tickSpeed = input.getIntOr(Mod_Constants.MOD_CUSTOM  +"tick_accelerator_tick_speed", 0);
        this.effUse = input.getIntOr(Mod_Constants.MOD_CUSTOM  +"tick_accelerator_efficiency_use", 0);
        this.tickCount = input.getIntOr(Mod_Constants.MOD_CUSTOM  +"tick_accelerator_tick_count", 0);
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
            case UP, DOWN, WEST, EAST,NORTH, SOUTH -> allSideHandler;
        };
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
        setUpGrades();
        setCopperLevel();
        if (level.getBlockState(pos).getValue(Tick_Accelerator_Block.POWERED) || this.coperLevel <= 0) {
            return;
        }
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        this.tickCount++;
        if (this.tickCount >= tickCountMax) {
            consumeCopperForTick();
        }

        int maxRange = this.getRange();
        BlockPos anchorBlockPos1 = getRemotePosition(REMOTESLOT1);
        BlockPos anchorBlockPos2 = getRemotePosition(REMOTESLOT2);

        if (anchorBlockPos1 != null && anchorBlockPos2 != null) {
            if (!isWithinRange(anchorBlockPos1, anchorBlockPos2, maxRange)) {
                return;
            }
            accelerateArea(serverLevel, anchorBlockPos1, anchorBlockPos2);
            return;
        }

        if (anchorBlockPos1 != null) {
            accelerateRangeAround(serverLevel, anchorBlockPos1, maxRange);
            return;
        }

        if (anchorBlockPos2 != null) {
            accelerateRangeAround(serverLevel, anchorBlockPos2, maxRange);
            return;
        }

        if (this.inventory.getResource(EFFICIENCYSLOT).isEmpty()) {
            accelerateRangeAround(serverLevel, this.getBlockPos(), maxRange);
        }
    }



    private void consumeCopperForTick() {
        if (this.inventory.getResource(EFFICIENCYSLOT).is(Mod_Registrys.Mod_Items.BOOST_UPGRADE.get())) {
            if (this.effUse == efficiencyUse) {
                this.effUse = 0;
                this.coperLevel--;
            }
            if (this.effUse < efficiencyUse) {
                this.effUse++;
            }
            this.tickCount = 0;
            return;
        }

        this.coperLevel--;
        this.tickCount = 0;
    }

    private @Nullable BlockPos getRemotePosition(int slot) {
        if (this.inventory.getResource(slot).isEmpty()) {
            return null;
        }
        ItemStack remoteUpgrade = this.inventory.getResource(slot).toStack();
        return remoteUpgrade.get(Mod_Registrys.Mod_Data_Componet.COORDINATES.get());
    }

    private boolean isWithinRange(BlockPos firstPos, BlockPos secondPos, int range) {
        return Math.abs(firstPos.getX() - secondPos.getX()) <= range
                && Math.abs(firstPos.getY() - secondPos.getY()) <= range
                && Math.abs(firstPos.getZ() - secondPos.getZ()) <= range;
    }

    private void accelerateArea(ServerLevel serverLevel, BlockPos startPos, BlockPos endPos) {
        for (BlockPos blockPos : BlockPos.betweenClosed(startPos, endPos)) {
            accelerateTick(serverLevel, blockPos);
        }
    }

    private void accelerateRangeAround(ServerLevel serverLevel, BlockPos centerPos, int range) {
        BlockPos minPos = centerPos.offset(-range, -range, -range);
        BlockPos maxPos = centerPos.offset(range, range, range);
        accelerateArea(serverLevel, minPos, maxPos);
    }

    public ItemStack getCopperOutput() {
        return new ItemStack(Items.COPPER_INGOT, this.coperLevel);
    }

    private int getRange(){
        if(this.inventory.getAmountAsInt(EFFICIENCYSLOT) == 1){
            return 5;
        }
        if(this.inventory.getAmountAsInt(EFFICIENCYSLOT) == 2){
            return 8;
        }
        if(this.inventory.getAmountAsInt(EFFICIENCYSLOT) == 3){
            return 16;
        }
        else {
            return 2;
        }
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

    private void setUpGrades(){
        if (this.inventory.getResource(SPEEDSLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_1.get())) {
            this.tickSpeed = 4;
        }if (this.inventory.getResource(SPEEDSLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_2.get())) {
            this.tickSpeed = 6;
        }if (this.inventory.getResource(SPEEDSLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_3.get())) {
            this.tickSpeed = 8;
        }if (this.inventory.getResource(SPEEDSLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_4.get())) {
            this.tickSpeed = 10;
        }if (this.inventory.getResource(SPEEDSLOT).isEmpty()){
            this.tickSpeed = 2;
        }
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
                if (serverLevel.getRandom().nextInt(this.tickSpeed * 100) == 0) {
                    blockState.randomTick(serverLevel, blockPos, serverLevel.getRandom());
                }
            }
        }
    }

    public static boolean isTickAccelBlock(ServerLevel serverLevel, BlockState blockState, BlockEntity blockEntity) {
        if (blockEntity == null && !blockState.isRandomlyTicking()) {
            return false;
        }
        if (blockEntity instanceof Tick_Accelerator_Block_Entity) {
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