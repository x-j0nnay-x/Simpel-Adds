package net.x_j0nnay_x.simpeladdmod.item.util;



import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;

import java.util.function.Supplier;


public enum ModToolTiers implements Tier {


    OBSIDIANTIER(3, 1900, 8.9f, 3.5f, 14,
            () -> Ingredient.of(ModItems.OBSIDAININGOT.get())),
    OBSIDIRITETTIER(4, 2900, 11.1f, 4.5f, 22,
            () -> Ingredient.of(Items.NETHERITE_INGOT));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;


    ModToolTiers(int miningLevel, int itemDurability, float miningSpeed, float attckDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attckDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getUses() {
        return this.itemDurability;
    }

    @Override
    public float getSpeed() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }



    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}