package x_j0nnay_x.simpeladdmod.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.function.Consumer;

public class Totem_Gem extends Item implements ICurioItem {

    public Totem_Gem(Properties properties) {
        super(properties);
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
