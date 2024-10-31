package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import org.jetbrains.annotations.NotNull;

public class SimpelFeedingTool extends Item {

    int cooldown = 250;
    public SimpelFeedingTool(int uses) {
        super(new Properties().stacksTo(1).durability(uses).fireResistant());
    }
    @Override
    public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull Entity entity, int i, boolean bl){
        if(level.isClientSide()) return;
        if (entity instanceof Player player) {
            if(itemStack.getDamageValue() < itemStack.getMaxDamage()) {
                cooldown --;
                if(cooldown <= 1) {
                    if(player.getHealth() < player.getMaxHealth()) {
                        player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() + 6);
                        player.getFoodData().setSaturation(player.getFoodData().getSaturationLevel() + 6);
                        itemStack.setDamageValue(itemStack.getDamageValue() + 1);
                    }
                    cooldown = 500;
                }
            }
        }
    }

    @Override
    public boolean isValidRepairItem(ItemStack $$0, ItemStack $$1) {
        return $$1.is(ModItems.UNOBTIANIUMSCRAP);
    }
}
