package net.x_j0nnay_x.simpeladd.item.util;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModItems;

import java.util.function.Supplier;

public enum ModArmmorTier implements ArmorMaterial {
    OBSIDIANA("obsidian", 35, new int[]{ 3, 8, 6, 3 }, 24,
    SoundEvents.ARMOR_EQUIP_GOLD, 2.7f, 0.07f, () -> Ingredient.of(ModItems.OBSIDAININGOT)),

    OBSIDIRITEA("obsidirite", 40, new int[]{ 3, 8, 6, 3 }, 24,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 3.6f, 0.17f, () -> Ingredient.of(Items.NETHERITE_INGOT.getDefaultInstance())),
    UNOBTAINNETHERITE("unobtainnetherite", 45, new int[]{ 3, 8, 6, 3 }, 24,
    SoundEvents.ARMOR_EQUIP_NETHERITE, 4.2f, 0.23f, () -> Ingredient.of(ModItems.UNOBTIANIUMSCRAP)),
    UNOBTIANOBSIDIRITEA("unobtainobsidirite", 48, new int[]{ 3, 8, 6, 3 }, 24,
    SoundEvents.ARMOR_EQUIP_NETHERITE, 4.9f, 0.3f, () -> Ingredient.of(ModItems.UNOBTIANIUMSCRAP));


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


    public int getDurabilityForType(ArmorItem.Type pType) {
        return BASE_DURABILITY[pType.ordinal()] * this.durabilityMultiplier;
    }

    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionAmounts[pType.ordinal()];
    }

    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return SimpelAddMod.MOD_ID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}