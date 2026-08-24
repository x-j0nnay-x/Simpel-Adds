package x_j0nnay_x.simpeladdmod.events;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import top.theillusivec4.curios.api.CuriosApi;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.items.Fire_Gem;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class Player_Fire_Gem_Events {

    private static final Set<UUID> FIRE_GEM_PLAYERS = new HashSet<>();

    private Player_Fire_Gem_Events() {
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide() || !(player instanceof ServerPlayer serverPlayer)) {
            return;
        }

        UUID playerId = serverPlayer.getUUID();
        if (serverPlayer.isCreative() || serverPlayer.isSpectator()) {
            disableFireImmunity(serverPlayer, playerId);
            return;
        }

        boolean hasFireGem = hasUsableFireGem(serverPlayer);
        boolean trackedFireImmunity = FIRE_GEM_PLAYERS.contains(playerId);

        if (hasFireGem && !trackedFireImmunity) {
            serverPlayer.setRemainingFireTicks(0);
            FIRE_GEM_PLAYERS.add(playerId);
            return;
        }

        if (!hasFireGem && trackedFireImmunity) {
            FIRE_GEM_PLAYERS.remove(playerId);
        }
    }

    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        if (!FIRE_GEM_PLAYERS.contains(player.getUUID())) {
            return;
        }

        if (event.getSource().is(DamageTypeTags.IS_FIRE)) {
            event.setCanceled(true);
            player.setRemainingFireTicks(0);
        }
    }

    private static void disableFireImmunity(ServerPlayer player, UUID playerId) {
        FIRE_GEM_PLAYERS.remove(playerId);
    }

    private static boolean hasUsableFireGem(ServerPlayer player) {
        if (hasUsableFireGemInInventory(player.getInventory())) {
            return true;
        }

        return CuriosApi.getCuriosInventory(player)
                .map(handler -> handler.findFirstCurio(stack -> stack.is(Mod_Registrys.Mod_Items.FIRE_GEM.get()) && Fire_Gem.canUse(stack)).isPresent())
                .orElse(false);
    }

    private static boolean hasUsableFireGemInInventory(Inventory inventory) {
        for (int slotIndex = 0; slotIndex < inventory.getContainerSize(); slotIndex++) {
            ItemStack stack = inventory.getItem(slotIndex);
            if (stack.is(Mod_Registrys.Mod_Items.FIRE_GEM.get()) && Fire_Gem.canUse(stack)) {
                return true;
            }
        }
        return false;
    }
}
