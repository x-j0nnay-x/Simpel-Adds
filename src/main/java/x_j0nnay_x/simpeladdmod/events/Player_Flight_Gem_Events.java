package x_j0nnay_x.simpeladdmod.events;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import top.theillusivec4.curios.api.CuriosApi;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.items.Flight_Gem;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class Player_Flight_Gem_Events {

    private static final Set<UUID> FLIGHT_GEM_PLAYERS = new HashSet<>();

    private Player_Flight_Gem_Events() {
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide() || !(player instanceof ServerPlayer serverPlayer)) {
            return;
        }

        UUID playerId = serverPlayer.getUUID();
        if (serverPlayer.isCreative() || serverPlayer.isSpectator()) {
            FLIGHT_GEM_PLAYERS.remove(playerId);
            return;
        }

        boolean hasFlightGem = hasUsableFlightGem(serverPlayer);
        boolean trackedFlight = FLIGHT_GEM_PLAYERS.contains(playerId);

        if (hasFlightGem && !serverPlayer.getAbilities().mayfly) {
            serverPlayer.getAbilities().mayfly = true;
            serverPlayer.onUpdateAbilities();
            FLIGHT_GEM_PLAYERS.add(playerId);
            return;
        }

        if (!hasFlightGem && trackedFlight) {
            serverPlayer.getAbilities().mayfly = false;
            serverPlayer.getAbilities().flying = false;
            serverPlayer.onUpdateAbilities();
            FLIGHT_GEM_PLAYERS.remove(playerId);
        }
    }

    private static boolean hasUsableFlightGem(ServerPlayer player) {
        if (hasUsableFlightGemInInventory(player.getInventory())) {
            return true;
        }

        return CuriosApi.getCuriosInventory(player)
                .map(handler -> handler.findFirstCurio(stack -> stack.is(Mod_Registrys.Mod_Items.FLIGHT_GEM.get()) && Flight_Gem.canUse(stack)).isPresent())
                .orElse(false);
    }

    private static boolean hasUsableFlightGemInInventory(Inventory inventory) {
        for (int slotIndex = 0; slotIndex < inventory.getContainerSize(); slotIndex++) {
            ItemStack stack = inventory.getItem(slotIndex);
            if (stack.is(Mod_Registrys.Mod_Items.FLIGHT_GEM.get()) && Flight_Gem.canUse(stack)) {
                return true;
            }
        }
        return false;
    }
}
