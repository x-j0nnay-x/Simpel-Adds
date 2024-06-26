package net.x_j0nnay_x.simpeladdmod.item.util;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;

import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum ModArmmorTier {
    OBSIDIANA("obsidian", 30, new int[]{ 3, 8, 6, 3 }, 24,
            (SoundEvent) SoundEvents.ARMOR_EQUIP_GOLD, 2.7f, 0.07f, () -> Ingredient.of(ModItems.OBSIDAININGOT.get())),

    OBSIDIRITEA("obsidirite", 30, new int[]{ 3, 8, 6, 3 }, 24,
            (SoundEvent) SoundEvents.ARMOR_EQUIP_GOLD, 2.7f, 0.07f, () -> Ingredient.of(Items.NETHERITE_INGOT.getDefaultInstance())),
    UNOBTAINNETHERITE("unobtainnetherite", 45, new int[]{ 3, 8, 6, 3 }, 24,
            (SoundEvent) SoundEvents.ARMOR_EQUIP_NETHERITE, 4.2f, 0.23f, () -> Ingredient.of(ModItems.UNOBTIANIUMSCRAP.get())),
    UNOBTIANOBSIDIRITEA("unobtainobsidirite", 48, new int[]{ 3, 8, 6, 3 }, 24,
            (SoundEvent) SoundEvents.ARMOR_EQUIP_NETHERITE, 4.9f, 0.3f, () -> Ingredient.of(ModItems.UNOBTIANIUMSCRAP.get()));


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


    public int getDefense(ArmorItem.Type pType) {
        return BASE_DURABILITY[pType.ordinal()] * this.durabilityMultiplier;
    }

    public int defense(ArmorItem.Type pType) {
        return this.protectionAmounts[pType.ordinal()];
    }

    public int enchantmentValue() {
        return enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return equipSound;
    }



    public @NotNull Supplier<Ingredient> repairIngredient() {
        return this.repairIngredient;
    }

    public String getName() {
        return Simpeladd.MOD_ID + ":" + this.name;
    }

    public float toughness() {
        return this.toughness;
    }

    public float knockbackResistance() {
        return this.knockbackResistance;
    }
}