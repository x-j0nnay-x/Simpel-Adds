package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import java.util.List;

public class SimpelSwordItem extends SwordItem {

    private static final List<EntityType> ENTITY_LIST = List.of(
            EntityType.COW,
            EntityType.CHICKEN,
            EntityType.PIG
    );

    public SimpelSwordItem(Tier tier, int attackDamage, float attackSpeed) {
        super(tier,new Properties().attributes(createAttributes(tier, attackDamage, attackSpeed)).fireResistant());
    }

    @Override
    public boolean hurtEnemy(ItemStack $$0, LivingEntity $$1, LivingEntity $$2) {
        if (ENTITY_LIST.contains($$1.getType())) {
            $$1.igniteForTicks(4);
        }
        //$$1.igniteForTicks(20);
        return super.hurtEnemy($$0, $$1, $$2);
    }
}
