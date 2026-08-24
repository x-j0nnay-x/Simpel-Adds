package x_j0nnay_x.simpeladdmod.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jspecify.annotations.Nullable;
import x_j0nnay_x.simpeladdmod.init.Mod_Config;

import java.util.function.Consumer;

public class Grinder_Head_Item extends Item{

    public Grinder_Head_Item(Properties properties, int durability) {

        super(properties
                .stacksTo(1)
                .durability(durability)
        );
    }

    public static ItemStack brakeItem(ItemStack itemstack, boolean canBrake) {
        if (canBrake) {
            if (itemstack.getDamageValue() >= itemstack.getMaxDamage() - 1) {
                return ItemStack.EMPTY;
            }
            itemstack.setDamageValue(itemstack.getDamageValue() + 1);
            return itemstack;
        }

        if (canUse(itemstack)) {
            itemstack.setDamageValue(itemstack.getDamageValue() + 1);
        }
        return itemstack;
    }


    @Override
    public @Nullable ItemStackTemplate getCraftingRemainder(ItemInstance instance) {
        if (!(instance instanceof ItemStack itemStack) ) {
            return null;
        }
            ItemStack grinder = brakeItem(itemStack.copy(), Mod_Config.GRINDER_BRAKES_MANUAL_GRIND.getAsBoolean());
            if(!grinder.isEmpty()) {
                return ItemStackTemplate.fromNonEmptyStack(grinder);
            }
           return null;
    }
    

    public static boolean canUse(ItemStack itemstack) {
        return itemstack.getDamageValue() < itemstack.getMaxDamage();
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
         if(!canUse(itemStack)) {
            builder.accept(Component.translatable("tooltip.simpeladdmod.broken_item"));
         }
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}