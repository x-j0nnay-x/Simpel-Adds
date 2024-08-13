package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;

public class SimpelAxeItem extends AxeItem {

    public SimpelAxeItem(Tier tier, float attackDamage, float attackSpeed ) {
        super(tier,new Properties().attributes(createAttributes(tier, attackDamage, attackSpeed)).fireResistant());

    }
}
