package net.x_j0nnay_x.simpeladdmod.item.util;


import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;

import java.util.function.Supplier;

public enum ModToolTiers implements ToolMaterial {


    OBSIDIANTIER(3, 1900, 8.9f, 3.5f, 14,
                         ModItems.OBSIDAININGOT),
    OBSIDIRITETTIER(4, 2900, 11.1f, 4.5f, 22,
                            Items.NETHERITE_INGOT),
    UNOBTAINNETHERITETIER(4, 3200, 11.8f, 4.8f, 22,
            ModItems.UNOBTIANIUMSCRAP),
    UNOBTIANOBSIDIRITEATIER(4, 3900, 12.3f, 5.5f, 22,
           ModItems.UNOBTIANIUMSCRAP);

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Item repairIngredient;


    ModToolTiers(int miningLevel, int itemDurability, float miningSpeed, float attckDamage, int enchantability, Item repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attckDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return  Ingredient.ofItems(this.repairIngredient);
    }
}