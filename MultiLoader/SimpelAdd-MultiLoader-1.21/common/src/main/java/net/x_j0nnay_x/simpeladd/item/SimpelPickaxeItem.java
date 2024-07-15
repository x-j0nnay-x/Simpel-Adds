package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class SimpelPickaxeItem extends PickaxeItem {
    public SimpelPickaxeItem(Tier tier, int attackDamage, float attackSpeed) {
        super(tier,new Properties().attributes(createAttributes(tier, attackDamage, attackSpeed)).fireResistant());
    }
}
