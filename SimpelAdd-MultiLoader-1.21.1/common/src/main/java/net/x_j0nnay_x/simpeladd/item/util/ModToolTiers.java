package net.x_j0nnay_x.simpeladd.item.util;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import java.util.function.Supplier;

public enum ModToolTiers implements Tier {

    OBSIDIANTIER(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1900, 8.9f, 3.5f, 14,
            () -> Ingredient.of(ModItems.OBSIDAININGOT)),
    OBSIDIRITETTIER(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2900, 11.1f, 4.5f, 22,
            () -> Ingredient.of(Items.NETHERITE_INGOT)),
    UNOBTAINNETHERITETIER(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3200, 11.8f, 4.8f, 22,
                            () -> Ingredient.of(ModItems.UNOBTIANIUMSCRAP)),
    UNOBTIANOBSIDIRITEATIER(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3900, 12.3f, 5.5f, 22,
                            () -> Ingredient.of(ModItems.UNOBTIANIUMSCRAP));

    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;
    private final TagKey<Block> incorrectBlocksForDrops;

    ModToolTiers(TagKey<Block> incorrectBlocksForDrops, int itemDurability, float miningSpeed, float attckDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
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
        return this.incorrectBlocksForDrops;
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