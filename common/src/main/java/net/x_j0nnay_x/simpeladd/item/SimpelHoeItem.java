package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

public class SimpelHoeItem extends HoeItem {
    public SimpelHoeItem(Tier tire, int attackDamage, float attackSpeed) {
        super(tire, attackDamage, attackSpeed, new Item.Properties().fireResistant());
    }
}
