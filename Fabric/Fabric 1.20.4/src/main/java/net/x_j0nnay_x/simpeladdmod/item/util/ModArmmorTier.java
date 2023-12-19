package net.x_j0nnay_x.simpeladdmod.item.util;


import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;

import java.util.function.Supplier;


public enum ModArmmorTier implements ArmorMaterial {
    OBSIDIANA("obsidian", 30, new int[]{ 3, 8, 6, 3 }, 24,
    SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.7f, 0.07f, () -> Ingredient.ofItems(ModItems.OBSIDAININGOT)),

    OBSIDIRITEA("obsidirite", 30, new int[]{ 3, 8, 6, 3 }, 24,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.7f, 0.07f, () -> Ingredient.ofItems(Items.NETHERITE_INGOT)),
    UNOBTAINNETHERITE("unobtainnetherite", 45, new int[]{ 3, 8, 6, 3 }, 24,
    SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4.2f, 0.23f, () -> Ingredient.ofItems(ModItems.UNOBTIANIUMSCRAP)),
    UNOBTIANOBSIDIRITEA("unobtainobsidirite", 48, new int[]{ 3, 8, 6, 3 }, 24,
    SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4.9f, 0.3f, () -> Ingredient.ofItems(ModItems.UNOBTIANIUMSCRAP));



    public final String name;
    public final int durabilityMultiplier;
    public final int[] protectionAmounts;
    public final int enchantmentValue;
    public final SoundEvent equipSound;
    public final float toughness;
    public final float knockbackResistance;
    public final Supplier<Ingredient> repairIngredient;

    public static final int[] BASE_DURABILITY = { 11, 16, 16, 13 };

    ModArmmorTier(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantmentValue, SoundEvent equipSound,
                              float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }


    @Override
    public int getDurability(ArmorItem.Type pType) {
        return BASE_DURABILITY[pType.ordinal()] * this.durabilityMultiplier;
    }
    @Override
    public int getProtection(ArmorItem.Type pType) {
        return this.protectionAmounts[pType.ordinal()];
    }
    @Override
    public int getEnchantability() {
        return enchantmentValue;
    }



    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return Simpeladd.MOD_ID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}