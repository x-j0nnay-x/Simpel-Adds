package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.x_j0nnay_x.simpeladd.core.ModBlocks;

import java.util.List;
import java.util.Map;

public class SimpelSwordItem extends SwordItem {

   private static final List<EntityType> ENTITY_LIST = List.of(
           EntityType.COW,
           EntityType.CHICKEN,
           EntityType.PIG
   );

    public SimpelSwordItem(Tier tier, int attackDamage, float attackSpeed) {
        super(tier, attackDamage, attackSpeed, new Item.Properties().fireResistant());
    }

    @Override
    public boolean hurtEnemy(ItemStack $$0, LivingEntity $$1, LivingEntity $$2) {
        if (ENTITY_LIST.contains($$1.getType())) {
            $$1.setSecondsOnFire(4);
        }
        return super.hurtEnemy($$0, $$1, $$2);

    }


}
