package x_j0nnay_x.simpeladdmod.items;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jspecify.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import x_j0nnay_x.simpeladdmod.init.Mod_Config;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.init.Mod_Tags;

import java.util.function.Consumer;

public class Repair_Gem extends Item implements ICurioItem {

    private int ticks;

    public Repair_Gem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        if (owner instanceof Player player) {
            tickSteps(player, itemStack);
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        if (entity instanceof Player player) {
            tickSteps(player, stack);
        }
    }

    private void tickSteps(Player player, ItemStack stack) {
        ticks++;
        if (ticks >= 100) {
            ticks = 0;
            repairItems(player, stack);
        }
    }

    private void repairItems(Player player, ItemStack stack) {
        if (!canUse(stack)) {
            return;
        }

        int repairedCount = repairInventoryItems(player.getInventory());
        repairedCount += repairCurioItems(player);

        if (repairedCount > 0) {
            brakeItem(stack, repairedCount, Mod_Config.REPAIR_GEM_BRAKES.getAsBoolean());
        }
    }

    private boolean canRepair(ItemStack stack) {
        if (stack.is(Mod_Tags.Items.REPAIR_NOT_ALLOWED)) return false;
        if (stack.is(Mod_Registrys.Mod_Items.REPAIR_GEM.get())) return false;
        if (stack.is(Mod_Registrys.Mod_Items.TOTEM_GEM.get())) return false;
        if (stack.isEmpty()) return false;
        if (!stack.isDamageableItem()) return false;
        return stack.getDamageValue() > 0;
    }

    private int repairInventoryItems(Inventory inventory) {
        int repairedCount = 0;

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack invStack = inventory.getItem(i);
            if (canRepair(invStack)) {
                repairedCount++;
                invStack.setDamageValue(Math.max(0, invStack.getDamageValue() - 4));
            }
        }

        return repairedCount;
    }

    private int repairCurioItems(Player player) {
        final int[] repairedCount = {0};

        CuriosApi.getCuriosInventory(player).ifPresent(curiosInventory ->
                curiosInventory.getCurios().values().forEach(curioStacksHandler -> {
                    for (int slot = 0; slot < curioStacksHandler.getSlots(); slot++) {
                        ItemStack curioStack = curioStacksHandler.getStacks().getStackInSlot(slot);
                        if (canRepair(curioStack)) {
                            repairedCount[0]++;
                            curioStack.setDamageValue(Math.max(0, curioStack.getDamageValue() - 4));
                        }
                    }
                })
        );

        return repairedCount[0];
    }

    public static ItemStack brakeItem(ItemStack itemStack, int damage, boolean canBreak) {
        if(canBreak){
            itemStack.setDamageValue(itemStack.getDamageValue() + damage);
        }
        if (canUse(itemStack) && !canBreak) {
            itemStack.setDamageValue(itemStack.getDamageValue() + damage);
        }
        return itemStack;
    }

    public static boolean canUse(ItemStack itemstack) {
        return itemstack.getDamageValue() < itemstack.getMaxDamage();
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        if (!canUse(itemStack)) {
            builder.accept(Component.translatable("tooltip.simpeladdmod.broken_item"));
        }
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}
