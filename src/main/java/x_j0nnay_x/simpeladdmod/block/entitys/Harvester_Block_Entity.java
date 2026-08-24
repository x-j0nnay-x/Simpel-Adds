package x_j0nnay_x.simpeladdmod.block.entitys;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.RangedResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jspecify.annotations.Nullable;
import x_j0nnay_x.simpeladdmod.block.Harvester_Block;
import x_j0nnay_x.simpeladdmod.init.Mod_Config;
import x_j0nnay_x.simpeladdmod.init.Mod_Constants;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.items.Grow_Staff_Item;
import x_j0nnay_x.simpeladdmod.menu.Harvester_Menu;
import java.util.ArrayList;
import java.util.List;

public class Harvester_Block_Entity extends BlockEntity implements MenuProvider {

    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(4) {
        @Override
        protected void onContentsChanged(int index, ItemStack previousContents) {
            super.onContentsChanged(index, previousContents);
            Harvester_Block_Entity.this.setChanged();
        }
    };
    private final ResourceHandler<ItemResource> allHandler = RangedResourceHandler.ofSingleIndex(inventory, GROWSLOT);


    public static int SPEEDSLOT = 0;
    public static int EFFICIENCYSLOT = 1;
    public static int GROWSLOT = 2;
    public static int REMOTESLOT = 3;
    protected final ContainerData data;
    public int tickSpeed;
    public int tickCount;
    public int tickEfficiency = 0;
    private boolean workLocked;

    public Harvester_Block_Entity(BlockPos worldPosition, BlockState blockState) {
        super(Mod_Registrys.Mod_Block_Entity.HARVESTER_BLOCK_ENTITY.get(), worldPosition, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int dataId) {
                return switch (dataId) {
                    case 0 -> Harvester_Block_Entity.this.tickSpeed;
                    case 1 -> Harvester_Block_Entity.this.tickEfficiency;
                    default -> 0;
                };
            }

            @Override
            public void set(int dataId, int value) {
                switch (dataId) {
                    case 0 -> Harvester_Block_Entity.this.tickSpeed = value;
                    case 1 -> Harvester_Block_Entity.this.tickEfficiency = value;
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
        return Component.translatable("block.simpeladdmod.harvester");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new Harvester_Menu(containerId, inventory, this, this.inventory, this.data);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt(Mod_Constants.MOD_CUSTOM +"harvester_progress", this.tickSpeed);
        output.putInt(Mod_Constants.MOD_CUSTOM +"harvester_blaze_uses", this.tickEfficiency);
        output.putChild("inventory", inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        tickSpeed = input.getIntOr(Mod_Constants.MOD_CUSTOM +"harvester_progress", 0);
        tickEfficiency = input.getIntOr(Mod_Constants.MOD_CUSTOM +"harvester_blaze_uses", 0);
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
    }



    public ResourceHandler<ItemResource> getItemHandler(Direction direction) {
        if (direction == null)
            return inventory;

        Direction facing = this.getBlockState().hasProperty(BlockStateProperties.HORIZONTAL_FACING)
                ? this.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING)
                : Direction.NORTH;

        return switch (getRelativeSide(facing, direction)) {
            case UP, WEST, EAST,NORTH, SOUTH, DOWN -> allHandler;
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

    public void tick(ServerLevel level, BlockPos pos, BlockState state) {
        setUpGrades();
        if(isGrowStaffusable()) {
            startFarmLandLoop( level);
        }
        if(level.getBlockState(pos).getValue(Harvester_Block.POWERED))
            return;
        if(this.tickCount >= this.tickSpeed) {
            startHarvestLoop(level);
        }else{
            this.tickCount++;
        }
    }

    private void setUpGrades(){
        if (this.inventory.getResource(SPEEDSLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_1.get())) {
            this.tickSpeed = 80;
        }if (this.inventory.getResource(SPEEDSLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_2.get())) {
            this.tickSpeed = 60;
        }if (this.inventory.getResource(SPEEDSLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_3.get())) {
            this.tickSpeed = 40;
        }if (this.inventory.getResource(SPEEDSLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_4.get())) {
            this.tickSpeed = 20;
        }if (this.inventory.getResource(SPEEDSLOT).isEmpty()){
            this.tickSpeed = 100;
        }if (this.inventory.getResource(EFFICIENCYSLOT).isEmpty()){
            this.tickEfficiency = 0;
        }if (!this.inventory.getResource(EFFICIENCYSLOT).isEmpty()){
            this.tickEfficiency = 1;
        }
    }

    public int getCountBoost(){
        return this.inventory.getAmountAsInt(EFFICIENCYSLOT);
    }

    //chest above code
    public boolean isChestabove() {
        if (this.level == null) {
            return false;
        }
        return hasContainerAbove(this.getBlockPos(), this.level) || hasItemHandlerAbove(this.getBlockPos(), this.level);
    }

    public boolean hasRemoteUpgrade() {
        // Older saved worlds can deserialize this block entity with only 3 slots.
        if (REMOTESLOT >= this.inventory.size()) {
            return false;
        }
        return this.inventory.getResource(REMOTESLOT).is(Mod_Registrys.Mod_Items.REMOTE_UPGRADE.get());
    }

    @Nullable
    private Container getRemoteContainer() {
        if (this.level == null || !hasRemoteUpgrade()) {
            return null;
        }
        ItemStack remoteUpgrade = this.inventory.getResource(REMOTESLOT).toStack();
        BlockPos remotePos = remoteUpgrade.get(Mod_Registrys.Mod_Data_Componet.COORDINATES.get());
        if (remotePos == null) {
            return null;
        }
        BlockState state = this.level.getBlockState(remotePos);
        Container container = getBlockContainer(this.level, remotePos, state);
        return container != null
                ? container
                : getEntityContainer(this.level, remotePos.getX(), remotePos.getY(), remotePos.getZ());
    }

    @Nullable
    private ResourceHandler<ItemResource> getRemoteItemHandler() {
        if (this.level == null || !hasRemoteUpgrade()) {
            return null;
        }
        ItemStack remoteUpgrade = this.inventory.getResource(REMOTESLOT).toStack();
        BlockPos remotePos = remoteUpgrade.get(Mod_Registrys.Mod_Data_Componet.COORDINATES.get());
        if (remotePos == null) {
            return null;
        }
        return this.level.getCapability(Capabilities.Item.BLOCK, remotePos, null);
    }

    @Nullable
    public static Container getContainerAbove(BlockPos blockPos, Level level) {
        BlockPos containerPos = blockPos.above();
        BlockState state = level.getBlockState(containerPos);
        Container container = getBlockContainer(level, containerPos, state);
        return container != null ? container : getEntityContainer(level, containerPos.getX(), containerPos.getY(), containerPos.getZ());
    }

    public static boolean hasContainerAbove(BlockPos blockPos, Level level) {
        return getContainerAbove(blockPos, level) != null;
    }

    public static boolean hasItemHandlerAbove(BlockPos blockPos, Level level) {
        return getItemHandlerAbove(blockPos, level) != null;
    }

    @Nullable
    public static ResourceHandler<ItemResource> getItemHandlerAbove(BlockPos blockPos, Level level) {
        BlockPos containerPos = blockPos.above();
        return level.getCapability(Capabilities.Item.BLOCK, containerPos, Direction.DOWN);
    }

    @Nullable
    private static Container getEntityContainer(Level level, double x, double y, double z) {
        List<Entity> list = level.getEntities((Entity)null, new AABB(x - (double)0.5F, y - (double)0.5F, z - (double)0.5F, x + (double)0.5F, y + (double)0.5F, z + (double)0.5F), EntitySelector.CONTAINER_ENTITY_SELECTOR);
        return !list.isEmpty() ? (Container)list.get(level.getRandom().nextInt(list.size())) : null;
    }

   @Nullable
    private static Container getBlockContainer(Level level, BlockPos pos, BlockState state) {
        Block block = state.getBlock();
        if (block instanceof WorldlyContainerHolder worldlyContainerHolder) {
            return worldlyContainerHolder.getContainer(state, level, pos);
        }
        if (!state.hasBlockEntity()) {
            return null;
        }
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!(blockEntity instanceof Container container)) {
            return null;
        }
        if (container instanceof ChestBlockEntity && block instanceof ChestBlock chestBlock) {
            return ChestBlock.getContainer(chestBlock, state, level, pos, true);
        }
        return container;
    }

    //Item Handleing
    public static void justDrop(ServerLevel pLevel, List<ItemStack> drops, BlockPos cropPos) {
        for (ItemStack drop : drops) {
            ItemEntity itemEntity = new ItemEntity(pLevel, cropPos.getX(), cropPos.getY(), cropPos.getZ(), drop);
            pLevel.addFreshEntity(itemEntity);
        }
    }

    private List<ItemStack> putDropsInChest(List<ItemStack> drops, Container container) {
        if (container == null) {
            return drops;
        }
        List<ItemStack> remainingDrops = new ArrayList<>();
        for (ItemStack drop : drops) {
            ItemStack remaining = HopperBlockEntity.addItem(null, container, drop.copy(), Direction.DOWN);
            if (!remaining.isEmpty()) {
                remainingDrops.add(remaining);
            }
        }
        return remainingDrops;
    }

    private List<ItemStack> putDropsInItemHandler(List<ItemStack> drops, ResourceHandler<ItemResource> itemHandler) {
        if (itemHandler == null) {
            return drops;
        }
        List<ItemStack> remainingDrops = new ArrayList<>();
        try (Transaction transaction = Transaction.openRoot()) {
            for (ItemStack drop : drops) {
                if (drop.isEmpty()) {
                    continue;
                }
                ItemResource resource = ItemResource.of(drop);
                if (resource.isEmpty()) {
                    continue;
                }
                int inserted = itemHandler.insert(resource, drop.getCount(), transaction);
                if (inserted < drop.getCount()) {
                    remainingDrops.add(drop.copyWithCount(drop.getCount() - inserted));
                }
            }
            transaction.commit();
        }
        return remainingDrops;
    }

    //Harvesting code
    private void startHarvestLoop(ServerLevel world) {
        BlockPos blockPos = this.getBlockPos();
        int range = this.getCountBoost() + 1 ;
        int cropRange = 2;
        for (BlockPos blockPos1 : BlockPos.betweenClosed(blockPos.offset(-range, -0,  -range), blockPos.offset(range, 0, range))) {
            havestBlocks(world, blockPos1);
        }
        this.tickCount = 0;
    }

    public void havestBlocks(ServerLevel serverLevel, BlockPos blockPos) {
        BlockState crop = serverLevel.getBlockState(blockPos);
        if (crop.getBlock() instanceof VegetationBlock) {
            List<ItemStack> drops = harvestCropDrops(serverLevel, blockPos, crop);
            if (!drops.isEmpty()) {
                Container remoteContainer = getRemoteContainer();
                ResourceHandler<ItemResource> remoteItemHandler = getRemoteItemHandler();
                Container aboveContainer = getContainerAbove(this.getBlockPos(), this.level);
                ResourceHandler<ItemResource> aboveItemHandler = getItemHandlerAbove(this.getBlockPos(), this.level);

                if (remoteContainer != null) {
                    drops = putDropsInChest(drops, remoteContainer);
                }
                if (!drops.isEmpty() && remoteContainer == null && remoteItemHandler != null) {
                    drops = putDropsInItemHandler(drops, remoteItemHandler);
                }
                if (!drops.isEmpty() && aboveContainer != null) {
                    drops = putDropsInChest(drops, aboveContainer);
                }
                if (!drops.isEmpty() && aboveContainer == null && aboveItemHandler != null) {
                    drops = putDropsInItemHandler(drops, aboveItemHandler);
                }
                if (!drops.isEmpty()) {
                    justDrop(serverLevel, drops, blockPos.above());
                }
            }
        } else if (crop.getBlock() instanceof SugarCaneBlock || crop.getBlock() instanceof CactusBlock || crop.is(Blocks.BAMBOO)) {
            BlockState secondState = serverLevel.getBlockState(blockPos);
            if (secondState.is(crop.getBlock())) {
                List<ItemStack> drops = harvestTallCropDrops(serverLevel, blockPos.above(), secondState);
                Container remoteContainer = getRemoteContainer();
                ResourceHandler<ItemResource> remoteItemHandler = getRemoteItemHandler();
                Container aboveContainer = getContainerAbove(this.getBlockPos(), this.level);
                ResourceHandler<ItemResource> aboveItemHandler = getItemHandlerAbove(this.getBlockPos(), this.level);

                if (remoteContainer != null) {
                    drops = putDropsInChest(drops, remoteContainer);
                }
                if (!drops.isEmpty() && remoteContainer == null && remoteItemHandler != null) {
                    drops = putDropsInItemHandler(drops, remoteItemHandler);
                }
                if (!drops.isEmpty() && aboveContainer != null) {
                    drops = putDropsInChest(drops, aboveContainer);
                }
                if (!drops.isEmpty() && aboveContainer == null && aboveItemHandler != null) {
                    drops = putDropsInItemHandler(drops, aboveItemHandler);
                }
                if (!drops.isEmpty()) {
                    justDrop(serverLevel, drops, blockPos.above());
                }
            }
        }
    }
    public static List<ItemStack> harvestTallCropDrops(ServerLevel pLevel, BlockPos cropPos, BlockState crop) {
        List<ItemStack> drops = new ArrayList<>();
        if (crop.getBlock() instanceof SugarCaneBlock || crop.getBlock() instanceof CactusBlock || crop.is(Blocks.BAMBOO)) {
            List<BlockPos> posToCheck = new ArrayList<>();
            for (int i = 0; i < 10; i++) { //In case it grew a lot since last check
                BlockPos pos = cropPos.above(i);
                if (pLevel.getBlockState(pos).is(crop.getBlock()))
                    posToCheck.add(pos);
                else
                    break;
            }
            for (int i = posToCheck.size() - 1; i >= 0; i--) {
                BlockPos clearPos = posToCheck.get(i);
                BlockEntity blockEntity = pLevel.getBlockEntity(clearPos);
                drops.addAll(Block.getDrops(pLevel.getBlockState(clearPos), pLevel, clearPos, blockEntity));
                pLevel.destroyBlock(clearPos, false);
            }
        }
        return drops;
    }

    public static List<ItemStack> harvestCropDrops(ServerLevel pLevel, BlockPos cropPos, BlockState crop) {
        List<ItemStack> drops = new ArrayList<>();
        if (crop.getBlock() instanceof VegetationBlock) {
            BlockEntity blockEntity = pLevel.getBlockEntity(cropPos);
            List<ItemStack> potentialDrops = Block.getDrops(crop, pLevel, cropPos, blockEntity);
            if (potentialDrops.isEmpty())
                return drops;
            if (potentialDrops.size() > 1 || potentialDrops.get(0).getCount() > 1) {
                BlockState placeState = Blocks.AIR.defaultBlockState();
                drops.addAll(potentialDrops);
                for (ItemStack drop : drops) {
                    if (drop.getItem() instanceof BlockItem blockItem) {
                        placeState = blockItem.getBlock().defaultBlockState();
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

    //change farmland to simpelfarmland
    private void startFarmLandLoop(ServerLevel world) {
        BlockPos blockPos = this.getBlockPos();
        int range = this.getCountBoost() + 1 ;
        int cropRange = 2;
        for (BlockPos blockPos1 : BlockPos.betweenClosed(blockPos.offset(-range, -cropRange, -range), blockPos.offset(range, cropRange, range))) {
            turnToFarmLand(world, blockPos1);
        }
    }

    private void damageGrowStaff() {
        if(this.inventory.getResource(this.GROWSLOT).is(Mod_Registrys.Mod_Items.GROW_STAFF.get())) {
                ItemStack grinderStack = Grow_Staff_Item.brakeItem(this.inventory.getResource(GROWSLOT).toStack(), Mod_Config.GROW_STAFF_BRAKES_HARVESTER.getAsBoolean());
                if (grinderStack.isEmpty()) {
                    this.inventory.set(GROWSLOT, this.inventory.getResource(GROWSLOT), 0);
                    return;
                }
                this.inventory.set(GROWSLOT, ItemResource.of(grinderStack), 1);
        }
    }


    private boolean isGrowStaffusable(){
        return Grow_Staff_Item.canUse(this.inventory.getResource(GROWSLOT).toStack()) || Mod_Config.GROW_STAFF_BRAKES_HARVESTER.getAsBoolean();
    }

    public void turnToFarmLand(ServerLevel level, BlockPos blockPos){
            BlockPos farmlandPos = blockPos.below();
            if (level.getBlockState(farmlandPos).getBlock() != Blocks.FARMLAND)
                return;
            int blockValue = level.getBlockState(farmlandPos).getValue(FarmlandBlock.MOISTURE);
            level.setBlockAndUpdate(farmlandPos, Mod_Registrys.Mod_Blocks.SIMPEL_FARM_LAND.get().defaultBlockState().setValue(FarmlandBlock.MOISTURE, blockValue));
            damageGrowStaff();
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