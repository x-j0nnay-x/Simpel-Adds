package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class SimpelSwordItem extends SwordItem {

    public SimpelSwordItem(Tier tier, int attackDamage, float attackSpeed) {
        super(tier,new Properties().attributes(createAttributes(tier, attackDamage, attackSpeed)).fireResistant());
    }
}
