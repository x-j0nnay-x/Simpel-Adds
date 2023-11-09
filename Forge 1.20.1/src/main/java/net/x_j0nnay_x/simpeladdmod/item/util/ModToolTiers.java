package net.x_j0nnay_x.simpeladdmod.item.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.simpeladdmod;
import net.x_j0nnay_x.simpeladdmod.until.ModTags;

import java.util.List;


public class ModToolTiers {
    public static final ForgeTier OBSIDIANTIER = new ForgeTier(
            3,
            1900,
            4.08f,
            7,
            14,
            ModTags.Blocks.OBSIDIANTOOL,
            () -> Ingredient.of(ModItems.OBSIDAININGOT::get)
    );
    public static final ForgeTier OBSIDIRITETTIER = new ForgeTier(
            4,
            2900,
            5.25f,
            8,
            20,
            ModTags.Blocks.OBSIDIANTOOL,
            () -> Ingredient.of(Items.NETHERITE_INGOT)
    );
/*
    public static final Tier OBSIDIANTIER = TierSortingRegistry.registerTier(new ForgeTier
                    (3, 1900, 4.08f, 4.4f, 14, ModTags.Blocks.OBSIDIANTOOL, () -> Ingredient.of(ModItems.OBSIDAININGOT.get())),
            new ResourceLocation(simpeladdmod.MOD_ID, "obsidiantear"), List.of(Tiers.DIAMOND), List.of());

    public static final Tier OBSIDIRITETTIER = TierSortingRegistry.registerTier(new ForgeTier
                    (4, 2900, 5.25f, 3.5f, 22, ModTags.Blocks.OBSIDIANTOOL, () -> Ingredient.of(Items.NETHERITE_INGOT)),
            new ResourceLocation(simpeladdmod.MOD_ID, "obsidiritetear"), List.of(Tiers.NETHERITE), List.of());

 */




//ModTags.Blocks.OBSIDIANTOOL

}