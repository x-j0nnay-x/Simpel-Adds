package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

public class SimpelShovelItem extends ShovelItem {
    public SimpelShovelItem(Tier tier, float attackDamage, float attackSpeed) {
        super(tier, attackDamage, attackSpeed, new Item.Properties().fireResistant());
    }
}
