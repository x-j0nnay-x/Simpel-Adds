package x_j0nnay_x.simpeladdmod.events;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.CuriosApi;
import x_j0nnay_x.simpeladdmod.init.Mod_Config;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.items.Totem_Gem;

public final class Player_Totem_Gem_Events {

    private Player_Totem_Gem_Events() {
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        ItemStack totemGem = findUsableTotemGem(player);
        if (totemGem.isEmpty()) {
            return;
        }

        event.setCanceled(true);
        player.setHealth(1.0F);
        player.removeAllEffects();
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 400, 1));
        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3));
        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0));
        player.level().broadcastEntityEvent(player, (byte) 35);
        Totem_Gem.brakeItem(totemGem, Mod_Config.TOTEM_GEM_BRAKES.getAsBoolean());
    }

    private static ItemStack findUsableTotemGem(ServerPlayer player) {
        ItemStack inventoryGem = findUsableTotemGemInInventory(player.getInventory());
        if (!inventoryGem.isEmpty()) {
            return inventoryGem;
        }

        return CuriosApi.getCuriosInventory(player)
                .flatMap(handler -> handler.findFirstCurio(stack -> stack.is(Mod_Registrys.Mod_Items.TOTEM_GEM.get()) && Totem_Gem.canUse(stack)))
                .map(slotResult -> slotResult.stack())
                .orElse(ItemStack.EMPTY);
    }

    private static ItemStack findUsableTotemGemInInventory(Inventory inventory) {
        for (int slotIndex = 0; slotIndex < inventory.getContainerSize(); slotIndex++) {
            ItemStack stack = inventory.getItem(slotIndex);
            if (stack.is(Mod_Registrys.Mod_Items.TOTEM_GEM.get()) && Totem_Gem.canUse(stack)) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }
}
