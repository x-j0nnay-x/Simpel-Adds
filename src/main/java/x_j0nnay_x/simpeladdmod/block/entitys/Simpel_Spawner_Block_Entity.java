package x_j0nnay_x.simpeladdmod.block.entitys;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.neoforged.neoforge.transfer.RangedResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jspecify.annotations.Nullable;
import x_j0nnay_x.simpeladdmod.init.Mod_Constants;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.menu.Simpel_Spawner_Menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class Simpel_Spawner_Block_Entity extends BlockEntity implements MenuProvider {

    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(20) {
        @Override
        protected void onContentsChanged(int index, ItemStack previousContents) {
            super.onContentsChanged(index, previousContents);
            Simpel_Spawner_Block_Entity.this.setChanged();
        }
    };
    private final ResourceHandler<ItemResource> outPutHandler = RangedResourceHandler.of(inventory, OUTPUTSLOT1, OUTPUTSLOT15);
    private final ResourceHandler<ItemResource> inputHandler = RangedResourceHandler.ofSingleIndex(inventory, COPPERSLOT);

    public static int EGGSLOT = 0;
    public static int XPSLOT = 1;
    public static int OUTPUTSLOT1 = 2;
    public static int OUTPUTSLOT2 = 3;
    public static int OUTPUTSLOT3 = 4;
    public static int OUTPUTSLOT4 = 5;
    public static int OUTPUTSLOT5 = 6;
    public static int OUTPUTSLOT6 = 7;
    public static int OUTPUTSLOT7 = 8;
    public static int OUTPUTSLOT8 = 9;
    public static int OUTPUTSLOT9 = 10;
    public static int OUTPUTSLOT10 = 11;
    public static int OUTPUTSLOT11 = 12;
    public static int OUTPUTSLOT12 = 13;
    public static int OUTPUTSLOT13 = 14;
    public static int OUTPUTSLOT14 = 15;
    public static int OUTPUTSLOT15 = 16;
    public static int COPPERSLOT = 17;
    public static int SPEEDSLOT = 18;
    public static int EFFSLOT = 19;
    protected final ContainerData data;
    private int progress = 0;
    private int max_Progress;
    private int copper_Level = 0;
    private int max_Copper = 100;
    private int maxcopperUses = 5;
    private int copperUses;
    private boolean outputBlocked;
    private final List<ItemStack> pendingLootDrops = new ArrayList<>();
    public UUID owner;
    private @Nullable Entity displayEntity;

    public Simpel_Spawner_Block_Entity(BlockPos worldPosition, BlockState blockState) {
        super(Mod_Registrys.Mod_Block_Entity.SIMPEL_SPAWNER_BLOCK_ENTTY.get(), worldPosition, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int dataId) {
                return switch (dataId) {
                    case 0 -> Simpel_Spawner_Block_Entity.this.progress;
                    case 1 -> Simpel_Spawner_Block_Entity.this.max_Progress;
                    case 2 -> Simpel_Spawner_Block_Entity.this.copper_Level;
                    case 3 -> Simpel_Spawner_Block_Entity.this.max_Copper;
                    default -> 0;
                };
            }

            @Override
            public void set(int dataId, int value) {
                switch (dataId) {
                    case 0 -> Simpel_Spawner_Block_Entity.this.progress = value;
                    case 1 -> Simpel_Spawner_Block_Entity.this.max_Progress = value;
                    case 2 -> Simpel_Spawner_Block_Entity.this.copper_Level = value;
                    case 3 -> Simpel_Spawner_Block_Entity.this.max_Copper = value;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.simpeladdmod.simpel_spawner");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new Simpel_Spawner_Menu(containerId, inventory, this, this.inventory, this.data);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt(Mod_Constants.MOD_CUSTOM +"simpel_spawner_progress", this.progress);
        output.putInt(Mod_Constants.MOD_CUSTOM +"simpel_spawner_copper_level", this.copper_Level);
        output.storeNullable(Mod_Constants.MOD_CUSTOM + "simpel_spawner_owner", UUIDUtil.CODEC, this.owner);
        output.putChild("inventory", inventory);
    }
    
    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        progress = input.getIntOr(Mod_Constants.MOD_CUSTOM +"simpel_spawner_progress", 0);
        copper_Level = input.getIntOr(Mod_Constants.MOD_CUSTOM +"simpel_spawner_copper_level", 0);
        owner = input.read(Mod_Constants.MOD_CUSTOM + "simpel_spawner_owner", UUIDUtil.CODEC).orElse(null);
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

    public ItemStack getCopperOutput() {
        return new ItemStack(Items.COPPER_INGOT, this.copper_Level);
    }

    public ResourceHandler<ItemResource> getItemHandler(Direction direction) {
        if (direction == null)
            return inventory;

        Direction facing = this.getBlockState().hasProperty(BlockStateProperties.HORIZONTAL_FACING)
                ? this.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING)
                : Direction.NORTH;

        return switch (getRelativeSide(facing, direction)) {
            case UP -> inputHandler;
            case DOWN, WEST, EAST,NORTH, SOUTH -> outPutHandler;
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
    
    public void setOWNER(UUID owner){
        this.owner = owner;
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }
        setCopperLevel();
        setUpGrades();
        if(!state.getValue(BlockStateProperties.POWERED) && this.copper_Level > 0) {
            if (!hasValidSpawnEgg()) {
                outputBlocked = false;
                pendingLootDrops.clear();
                this.progress = 0;
                updateLitState(level, pos, state, false);
                return;
            }

            if (outputBlocked) {
                List<ItemStack> remainingLoot = insertLootBatch(pendingLootDrops);
                pendingLootDrops.clear();
                pendingLootDrops.addAll(remainingLoot);
                outputBlocked = !pendingLootDrops.isEmpty();
            }

            if (outputBlocked || !canAcceptSpawnLoot()) {
                updateLitState(level, pos, state, true);
                return;
            }

            increaseCraftingProgress();
            updateLitState(level, pos, state, true);
            setChanged(level, pos, state);

            if (hasCraftingFinished()) {
                boolean craftCompleted = processSpawnEgg(serverLevel);
                if (craftCompleted) {
                    resetProgress();
                }
                setChanged(level, pos, state);
            }
        }
    }
    private void setCopperLevel(){
        if(this.copper_Level == max_Copper)
            return;
        if(copper_Level < max_Copper) {
            if (this.inventory.getResource(COPPERSLOT).is(Items.COPPER_INGOT)) {
                this.copper_Level += 1;
                try(Transaction transaction = Transaction.openRoot()) {
                    inventory.extract(COPPERSLOT, ItemResource.of(inventory.getResource(COPPERSLOT).toStack()), 1, transaction);
                    transaction.commit();
                }
            }
        }
    }

    private void updateLitState(Level level, BlockPos pos, BlockState state, boolean isLit) {
        if (!state.hasProperty(BlockStateProperties.LIT) || state.getValue(BlockStateProperties.LIT) == isLit) {
            return;
        }
        level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.LIT, isLit));
    }

    private boolean hasValidSpawnEgg() {
        ItemStack eggStack = this.inventory.getResource(EGGSLOT).toStack();
        if (eggStack.isEmpty()) {
            return false;
        }

        if (eggStack.is(Mod_Registrys.Mod_Items.SIMPEL_SPAWNER_EGG.get())) {
            return getEntityTypeFromSimpelEgg(eggStack).isPresent() || getFallbackEntityTypeFromSimpelEgg(eggStack).isPresent();
        }

        return eggStack.getItem() instanceof SpawnEggItem;
    }

    private boolean processSpawnEgg(ServerLevel level) {
        ItemStack eggStack = this.inventory.getResource(EGGSLOT).toStack();
        if (!(eggStack.getItem() instanceof SpawnEggItem)) {
            return false;
        }

        Optional<EntityType<?>> entityType = getEntityTypeFromEgg(eggStack);
        if (entityType.isEmpty()) {
            return false;
        }

        List<ItemStack> pendingLoot = new ArrayList<>(rollEntityLoot(level, entityType.get()));
        if (pendingLoot.isEmpty()) {
            outputBlocked = false;
            pendingLootDrops.clear();
            tryGenerateXpBottle(level);
            return true;
        }

        if (shouldDoubleRoll(level)) {
            pendingLoot.addAll(rollEntityLoot(level, entityType.get()));
        }

        List<ItemStack> remainingLoot = insertLootBatch(pendingLoot);
        pendingLootDrops.clear();
        pendingLootDrops.addAll(remainingLoot);
        outputBlocked = !pendingLootDrops.isEmpty();

        if (outputBlocked) {
            return false;
        }

        tryGenerateXpBottle(level);
        return true;
    }

    private Optional<EntityType<?>> getEntityTypeFromEgg(ItemStack eggStack) {
        if (eggStack.is(Mod_Registrys.Mod_Items.SIMPEL_SPAWNER_EGG.get())) {
            Optional<EntityType<?>> customEntity = getEntityTypeFromSimpelEgg(eggStack);
            if (customEntity.isPresent()) {
                return customEntity;
            }
            return getFallbackEntityTypeFromSimpelEgg(eggStack);
        }

        EntityType<?> entityType = SpawnEggItem.getType(eggStack);
        return Optional.ofNullable(entityType);
    }

    private Optional<EntityType<?>> getEntityTypeFromSimpelEgg(ItemStack eggStack) {
        String entityId = eggStack.get(Mod_Registrys.Mod_Data_Componet.SIMPEL_SPAWNER_ENTITY.get());
        if (entityId == null || entityId.isBlank()) {
            return Optional.empty();
        }

        Identifier identifier = Identifier.tryParse(entityId);
        if (identifier == null) {
            return Optional.empty();
        }

        return BuiltInRegistries.ENTITY_TYPE.get(identifier)
                .map(reference -> reference.value());
    }

    private Optional<EntityType<?>> getFallbackEntityTypeFromSimpelEgg(ItemStack eggStack) {
        String fallbackEntityId = eggStack.get(Mod_Registrys.Mod_Data_Componet.SIMPEL_SPAWNER_FALLBACK_ENTITY.get());
        if (fallbackEntityId == null || fallbackEntityId.isBlank()) {
            return Optional.empty();
        }

        Identifier identifier = Identifier.tryParse(fallbackEntityId);
        if (identifier == null) {
            return Optional.empty();
        }

        return BuiltInRegistries.ENTITY_TYPE.get(identifier)
                .map(reference -> reference.value());
    }

    private boolean shouldDoubleRoll(ServerLevel level) {
        return !this.inventory.getResource(EFFSLOT).isEmpty() && level.getRandom().nextInt(8) == 0;
    }

    private boolean shouldGenerateXpBottle(ServerLevel level) {
        return level.getRandom().nextInt(8) == 0;
    }

    private void tryGenerateXpBottle(ServerLevel level) {
        if (!shouldGenerateXpBottle(level)) {
            return;
        }

        try(Transaction transaction = Transaction.openRoot()) {
            ItemAccess itemAccess = ItemAccess.forHandlerIndex(inventory, XPSLOT);
            ItemStack output = new ItemStack(Items.EXPERIENCE_BOTTLE);
            if(itemAccess.getAmount() >= 64){
                return;
            }
            inventory.set(XPSLOT, ItemResource.of(output), itemAccess.getAmount() + output.getCount());
            transaction.commit();
        }
    }

    private record LootSourceContext(@Nullable Player attackingPlayer, DamageSource damageSource) {
    }

    private LootSourceContext createLootDamageSource(ServerLevel level) {
        Player attackingPlayer = null;
        if (this.owner != null) {
            attackingPlayer = level.getPlayerByUUID(this.owner);
        }

        if (attackingPlayer == null) {
            attackingPlayer = level.players().stream()
                    .min(Comparator.comparingDouble(player -> player.distanceToSqr(
                            this.worldPosition.getX() + 0.5D,
                            this.worldPosition.getY() + 0.5D,
                            this.worldPosition.getZ() + 0.5D)))
                    .orElse(null);
        }

        if (attackingPlayer != null) {
            return new LootSourceContext(attackingPlayer, level.damageSources().playerAttack(attackingPlayer));
        }

        return new LootSourceContext(null, level.damageSources().genericKill());
    }

    private List<ItemStack> rollEntityLoot(ServerLevel level, EntityType<?> entityType) {
        if (!(entityType.create(level, EntitySpawnReason.COMMAND) instanceof Mob mob)) {
            return getSpecialCaseLootDrops(level, entityType);
        }

        mob.setPos(this.worldPosition.getX() + 0.5D, this.worldPosition.getY() + 1.0D, this.worldPosition.getZ() + 0.5D);

        var lootTableKey = mob.getLootTable();
        if (lootTableKey.isEmpty() ){
            return getSpecialCaseLootDrops(level, entityType);
        }

        LootTable lootTable = level.getServer().reloadableRegistries().getLootTable(lootTableKey.get());

        LootSourceContext lootSourceContext = createLootDamageSource(level);

        LootParams.Builder paramsBuilder = new LootParams.Builder(level)
                .withParameter(LootContextParams.THIS_ENTITY, mob)
                .withParameter(LootContextParams.ORIGIN, mob.position())
                .withParameter(LootContextParams.DAMAGE_SOURCE, lootSourceContext.damageSource());

        if (lootSourceContext.attackingPlayer() != null) {
            paramsBuilder.withParameter(LootContextParams.ATTACKING_ENTITY, lootSourceContext.attackingPlayer());
            paramsBuilder.withParameter(LootContextParams.LAST_DAMAGE_PLAYER, lootSourceContext.attackingPlayer());
        }

        LootParams params = paramsBuilder.create(LootContextParamSets.ENTITY);
        List<ItemStack> randomLoot = lootTable.getRandomItems(params).stream().map(ItemStack::copy).toList();
        if (!randomLoot.isEmpty()) {
            return randomLoot;
        }

        return getSpecialCaseLootDrops(level, entityType);
    }

    private List<ItemStack> getSpecialCaseLootDrops(ServerLevel level, EntityType<?> entityType) {
        if (entityType == EntityType.WITHER) {
            if (level.getRandom().nextInt(3) == 0) {
                return List.of(new ItemStack(Items.NETHER_STAR));
            }
            return List.of();
        }

        return List.of();
    }

    private boolean canAcceptSpawnLoot() {
        return !outputBlocked && hasFreeOutputSpace();
    }

    private boolean hasFreeOutputSpace() {
        for (int slot = OUTPUTSLOT1; slot <= OUTPUTSLOT15; slot++) {
            ItemStack outputStack = this.inventory.getResource(slot).toStack();
            int outputAmount = this.inventory.getAmountAsInt(slot);
            if (outputStack.isEmpty() || outputAmount < outputStack.getMaxStackSize()) {
                return true;
            }
        }
        return false;
    }

    private List<ItemStack> insertLootBatch(List<ItemStack> drops) {
        List<ItemStack> remainingDrops = new ArrayList<>();
        for (ItemStack drop : drops) {
            if (drop.isEmpty()) {
                continue;
            }
            ItemStack remaining = insertLootIntoOutput(drop.copy());
            if (!remaining.isEmpty()) {
                remainingDrops.add(remaining);
            }
        }
        return remainingDrops;
    }

    private ItemStack insertLootIntoOutput(ItemStack stack) {
        ItemStack remaining = stack.copy();

        for (int slot = OUTPUTSLOT1; slot <= OUTPUTSLOT15 && !remaining.isEmpty(); slot++) {
            ItemStack outputStack = this.inventory.getResource(slot).toStack();
            int outputAmount = this.inventory.getAmountAsInt(slot);

            if (outputStack.isEmpty()) {
                int moved = Math.min(remaining.getCount(), remaining.getMaxStackSize());
                setOutputSlot(slot, remaining.copyWithCount(moved));
                remaining.shrink(moved);
                continue;
            }

            if (!ItemStack.isSameItemSameComponents(outputStack, remaining)) {
                continue;
            }

            int space = outputStack.getMaxStackSize() - outputAmount;
            if (space <= 0) {
                continue;
            }

            int moved = Math.min(remaining.getCount(), space);
            setOutputSlot(slot, outputStack.copyWithCount(outputAmount + moved));
            remaining.shrink(moved);
        }

        return remaining;
    }

    private void setOutputSlot(int slot, ItemStack stack) {
        try (Transaction transaction = Transaction.openRoot()) {
            this.inventory.set(slot, ItemResource.of(stack), stack.getCount());
            transaction.commit();
        }
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.max_Progress;
    }

    private void resetProgress() {
        this.progress = 0;
        if (this.copperUses >= maxcopperUses) {
            this.copper_Level--;
            this.copperUses = 0;
        }else{
            copperUses++;
        }
    }

    private void setUpGrades(){
        if (this.inventory.getResource(SPEEDSLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_1.get())) {
            this.max_Progress = 550;
        }if (this.inventory.getResource(SPEEDSLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_2.get())) {
            this.max_Progress = 350;
        }if (this.inventory.getResource(SPEEDSLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_3.get())) {
            this.max_Progress = 100;
        }if (this.inventory.getResource(SPEEDSLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_4.get())) {
            this.max_Progress =  40; //450;
        }if (this.inventory.getResource(SPEEDSLOT).isEmpty()){
            this.max_Progress = 800;
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