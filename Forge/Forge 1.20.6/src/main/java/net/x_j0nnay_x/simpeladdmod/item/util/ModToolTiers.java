package net.x_j0nnay_x.simpeladdmod.item.util;



import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;

import java.util.function.Supplier;


public enum ModToolTiers implements Tier {


    OBSIDIANTIER(3, 1900, 8.9f, 3.5f, 14,
            () -> Ingredient.of(ModItems.OBSIDAININGOT.get())),
    OBSIDIRITETTIER(4, 2900, 11.1f, 4.5f, 22,
            () -> Ingredient.of(Items.NETHERITE_INGOT)),
    UNOBTAINNETHERITETIER(4, 3200, 11.8f, 4.8f, 22,
            () -> Ingredient.of(ModItems.UNOBTIANIUMSCRAP.get())),
    UNOBTIANOBSIDIRITEATIER(4, 3900, 12.3f, 5.5f, 22,
            () -> Ingredient.of(ModItems.UNOBTIANIUMSCRAP.get()));
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
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return null;
    }


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

    @Override
    public Tool createToolProperties(TagKey<Block> p_331434_) {
        return Tier.super.createToolProperties(p_331434_);
    }
}