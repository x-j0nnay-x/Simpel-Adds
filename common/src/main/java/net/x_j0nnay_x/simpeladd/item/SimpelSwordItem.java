package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class SimpelSwordItem extends SwordItem {

    public SimpelSwordItem(Tier tier, int attackDamage, float attackSpeed) {
        super(tier,new Properties().attributes(createAttributes(tier, attackDamage, attackSpeed)).fireResistant());
    }

    @Override
    public boolean hurtEnemy(ItemStack $$0, LivingEntity $$1, LivingEntity $$2) {
        $$1.igniteForTicks(10);
        return super.hurtEnemy($$0, $$1, $$2);
    }
}
