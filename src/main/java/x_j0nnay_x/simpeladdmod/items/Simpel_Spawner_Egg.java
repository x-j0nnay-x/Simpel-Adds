package x_j0nnay_x.simpeladdmod.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Spawner;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jspecify.annotations.Nullable;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;

import java.util.Objects;
import java.util.function.Consumer;

public class Simpel_Spawner_Egg extends SpawnEggItem {

    public Simpel_Spawner_Egg(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        } else {
            ItemStack itemStack = context.getItemInHand();
            BlockPos pos = context.getClickedPos();
            Direction clickedFace = context.getClickedFace();
            BlockState blockState = level.getBlockState(pos);
            BlockEntity var9 = level.getBlockEntity(pos);
            if (var9 instanceof Spawner spawnerHolder) {
                EntityType<?> type = getType(itemStack);
                if (type == null) {
                    return InteractionResult.FAIL;
                } else if (!serverLevel.isSpawnerBlockEnabled()) {
                    Player var11 = context.getPlayer();
                    if (var11 instanceof ServerPlayer) {
                        ServerPlayer serverPlayer = (ServerPlayer)var11;
                        serverPlayer.sendSystemMessage(Component.translatable("advMode.notEnabled.spawner"));
                    }

                    return InteractionResult.FAIL;
                } else {
                    spawnerHolder.setEntityId(type, level.getRandom());
                    level.sendBlockUpdated(pos, blockState, blockState, 3);
                    level.gameEvent(context.getPlayer(), GameEvent.BLOCK_CHANGE, pos);
                    itemStack.shrink(1);
                    return InteractionResult.SUCCESS;
                }
            } else {
                BlockPos spawnPos;
                if (blockState.getCollisionShape(level, pos).isEmpty()) {
                    spawnPos = pos;
                } else {
                    spawnPos = pos.relative(clickedFace);
                }

                return spawnMob(context.getPlayer(), itemStack, level, spawnPos, true, !Objects.equals(pos, spawnPos) && clickedFace == Direction.UP);
            }
        }
    }
    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        BlockHitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        if (hitResult.getType() != HitResult.Type.BLOCK) {
            return InteractionResult.PASS;
        } else if (level instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)level;
            BlockPos pos = hitResult.getBlockPos();
            if (!(level.getBlockState(pos).getBlock() instanceof LiquidBlock)) {
                return InteractionResult.PASS;
            } else if (level.mayInteract(player, pos) && player.mayUseItemAt(pos, hitResult.getDirection(), itemStack)) {
                InteractionResult result = spawnMob(player, itemStack, level, pos, false, false);
                if (result == InteractionResult.SUCCESS) {
                    player.awardStat(Stats.ITEM_USED.get(this));
                }

                return result;
            } else {
                return InteractionResult.FAIL;
            }
        } else {
            return InteractionResult.SUCCESS;
        }
    }

    private static InteractionResult spawnMob(@Nullable LivingEntity user, ItemStack itemStack, Level level, BlockPos spawnPos, boolean tryMoveDown, boolean movedUp) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.FAIL;
        }

        EntityType<?> type = getCapturedEntityType(itemStack);
        if (type == null) {
            return InteractionResult.FAIL;
        }

        if (!type.isAllowedInPeaceful() && level.getDifficulty() == Difficulty.PEACEFUL) {
            return InteractionResult.FAIL;
        }

        if (type.spawn(serverLevel, itemStack, user, spawnPos, EntitySpawnReason.SPAWN_ITEM_USE, tryMoveDown, movedUp) != null) {
            itemStack.setCount(0);
            level.gameEvent(user, GameEvent.ENTITY_PLACE, spawnPos);
        }

        return InteractionResult.SUCCESS;
    }

    @Nullable
    private static EntityType<?> getCapturedEntityType(ItemStack itemStack) {
        String capturedEntityId = itemStack.get(Mod_Registrys.Mod_Data_Componet.SIMPEL_SPAWNER_ENTITY.get());
        if (capturedEntityId != null && !capturedEntityId.isBlank()) {
            Identifier identifier = Identifier.tryParse(capturedEntityId);
            if (identifier != null) {
                return BuiltInRegistries.ENTITY_TYPE.get(identifier)
                        .map(reference -> reference.value())
                        .orElse(null);
            }
        }

        String fallbackEntityId = itemStack.get(Mod_Registrys.Mod_Data_Componet.SIMPEL_SPAWNER_FALLBACK_ENTITY.get());
        if (fallbackEntityId != null && !fallbackEntityId.isBlank()) {
            Identifier identifier = Identifier.tryParse(fallbackEntityId);
            if (identifier != null) {
                return BuiltInRegistries.ENTITY_TYPE.get(identifier)
                        .map(reference -> reference.value())
                        .orElse(null);
            }
        }

        return SpawnEggItem.getType(itemStack);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        EntityType<?> capturedEntityType = getCapturedEntityType(itemStack);
        if (capturedEntityType != null) {
            builder.accept(Component.translatable("tooltip.simpeladdmod.simpel_spawner_egg.entity", capturedEntityType.getDescription()));
        }
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}
