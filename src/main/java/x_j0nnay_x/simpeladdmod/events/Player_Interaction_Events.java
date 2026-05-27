package x_j0nnay_x.simpeladdmod.events;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.equine.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;

public final class Player_Interaction_Events {

    private Player_Interaction_Events() {
    }

    @SubscribeEvent
    public static void onEntityInteractSpecific(PlayerInteractEvent.EntityInteractSpecific event) {
        InteractionResult result = handleHorseTravelCrystalUse(event, event.getTarget());
        if (!result.consumesAction()) {
            return;
        }
        event.setCanceled(true);
        event.setCancellationResult(result);
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        InteractionResult result = handleHorseTravelCrystalUse(event, event.getTarget());
        if (!result.consumesAction()) {
            return;
        }
        event.setCanceled(true);
        event.setCancellationResult(result);
    }

    private static InteractionResult handleHorseTravelCrystalUse(PlayerInteractEvent event, Entity target) {
        if (!(target instanceof Horse horse)) {
            return InteractionResult.PASS;
        }

        Player player = event.getEntity();
        ItemStack heldStack = event.getItemStack();
        if (!heldStack.is(Mod_Registrys.Mod_Items.TRAVEL_CRYSTAL.get())) {
            return InteractionResult.PASS;
        }

        return heldStack.getItem().interactLivingEntity(heldStack, player, horse, event.getHand());
    }
}
