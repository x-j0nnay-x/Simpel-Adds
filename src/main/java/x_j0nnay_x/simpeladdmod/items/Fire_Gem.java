package x_j0nnay_x.simpeladdmod.items;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import x_j0nnay_x.simpeladdmod.init.Mod_Config;

import java.util.function.Consumer;

public class Fire_Gem extends Item implements ICurioItem {

    public Fire_Gem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        if (!(owner instanceof Player player)) {
            return;
        }
        if (isEquippedAsCurio(player, itemStack)) {
            return;
        }
        tickSteps(level, player, itemStack);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        Level level = entity.level();
        if (entity instanceof Player player) {
            tickSteps(level, player, stack);
        }
    }

    private void tickSteps(Level level, Player player, ItemStack stack) {
        if (!canUse(stack)) {
            return;
        }

        clearFireState(player);

        if (!level.isClientSide() && !player.isCreative() && !player.isSpectator() && player.isInLava() && player.tickCount % 200 == 0) {
            brakeItem(stack, Mod_Config.FIRE_GEM_BRAKES.getAsBoolean());
        }
    }

    private void clearFireState(Player player) {
        if (player.getRemainingFireTicks() > 0) {
            player.setRemainingFireTicks(0);
        }

        if (player.displayFireAnimation()) {
            player.setSharedFlagOnFire(false);
        }
    }

    private boolean isEquippedAsCurio(Player player, ItemStack targetStack) {
        return CuriosApi.getCuriosInventory(player)
                .map(handler -> handler.getCurios().values().stream()
                        .anyMatch(curioStacksHandler -> {
                            for (int slotIndex = 0; slotIndex < curioStacksHandler.getSlots(); slotIndex++) {
                                if (curioStacksHandler.getStacks().getStackInSlot(slotIndex) == targetStack) {
                                    return true;
                                }
                            }
                            return false;
                        }))
                .orElse(false);
    }

    public static ItemStack brakeItem(ItemStack itemStack, boolean canBreak) {
        if(canBreak){
            itemStack.setDamageValue(itemStack.getDamageValue() + 1);
        }
        if (canUse(itemStack) && !canBreak) {
            itemStack.setDamageValue(itemStack.getDamageValue() + 1);
        }
        return itemStack;
    }

    public static boolean canUse(ItemStack itemStack) {
        return itemStack.getDamageValue() < itemStack.getMaxDamage();
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        if (!canUse(itemStack)) {
            builder.accept(Component.translatable("tooltip.simpeladdmod.broken_item"));
        }
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}
