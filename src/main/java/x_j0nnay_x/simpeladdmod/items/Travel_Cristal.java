package x_j0nnay_x.simpeladdmod.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.equine.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.init.Mod_Tags;

import java.util.Optional;
import java.util.function.Consumer;

public class Travel_Cristal extends Item {
    public Travel_Cristal(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        if (!level.isClientSide() && player != null && player.isCrouching()) {
            BlockPos blockPos = context.getClickedPos();
            BlockState blockState = level.getBlockState(blockPos);
            ItemStack blockDrop = new ItemStack(blockState.getBlock().asItem(), 1);
            if (!blockState.isAir() && !blockState.is(Mod_Tags.Blocks.TRAVIL_GEM_BAN)) {
                if(blockState.is(Blocks.SPAWNER)){
                    ItemStack simpelSpawner = new ItemStack(Mod_Registrys.Mod_Blocks.SIMPEL_SPAWNER.asItem(), 1);
                    level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
                    player.drop(simpelSpawner, false);
                    context.getItemInHand().shrink(1);
                    return InteractionResult.SUCCESS;
                }else {
                    level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
                    player.drop(blockDrop, false);
                    context.getItemInHand().shrink(1);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand hand) {
        Level level = player.level();
        if (livingEntity instanceof Player || livingEntity.isDeadOrDying()) {
            return InteractionResult.PASS;
        }

        Optional<Item> vanillaSpawnEgg = SpawnEggItem.byId(livingEntity.getType()).map(Holder::value);

        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        ItemStack spawnEggStack = createSimpelSpawnerEgg(livingEntity, vanillaSpawnEgg);
        if (spawnEggStack.isEmpty()) {
            return InteractionResult.PASS;
        }

        if (livingEntity instanceof Container container) {
            for (int slot = 0; slot < container.getContainerSize(); slot++) {
                ItemStack stackInSlot = container.getItem(slot);
                if (!stackInSlot.isEmpty()) {
                    Containers.dropItemStack(level, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), stackInSlot.copy());
                    container.setItem(slot, ItemStack.EMPTY);
                }
            }
        }

        if (livingEntity.isVehicle()) {
            livingEntity.ejectPassengers();
        }
        if (livingEntity.isPassenger()) {
            livingEntity.stopRiding();
        }
        if (player.getVehicle() == livingEntity) {
            player.stopRiding();
        }


        livingEntity.discard();
        itemStack.shrink(1);
        player.drop(spawnEggStack, false);
        return InteractionResult.SUCCESS;
    }

    private ItemStack createSimpelSpawnerEgg(LivingEntity livingEntity, Optional<Item> vanillaSpawnEgg) {
        ItemStack simpelSpawnerEgg = new ItemStack(Mod_Registrys.Mod_Items.SIMPEL_SPAWNER_EGG.get());

        String entityId = EntityType.getKey(livingEntity.getType()).toString();
        simpelSpawnerEgg.set(Mod_Registrys.Mod_Data_Componet.SIMPEL_SPAWNER_ENTITY.get(), entityId);

        String fallbackEntityId = vanillaSpawnEgg
                .filter(item -> item != Blocks.AIR.asItem())
                .map(item -> {
                    ItemStack stack = new ItemStack(item);
                    EntityType<?> spawnType = SpawnEggItem.getType(stack);
                    return spawnType != null ? EntityType.getKey(spawnType).toString() : "";
                })
                .orElseGet(() -> {
                    if (livingEntity instanceof Horse) {
                        EntityType<?> horseType = SpawnEggItem.getType(new ItemStack(Items.HORSE_SPAWN_EGG));
                        return horseType != null ? EntityType.getKey(horseType).toString() : "";
                    }
                    return "";
                });

        if (fallbackEntityId.isEmpty()) {
            return ItemStack.EMPTY;
        }

        simpelSpawnerEgg.set(Mod_Registrys.Mod_Data_Componet.SIMPEL_SPAWNER_FALLBACK_ENTITY.get(), fallbackEntityId);
        return simpelSpawnerEgg;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        builder.accept(Component.translatable("tooltip.simpeladdmod.travel_crystal"));
    }
}
