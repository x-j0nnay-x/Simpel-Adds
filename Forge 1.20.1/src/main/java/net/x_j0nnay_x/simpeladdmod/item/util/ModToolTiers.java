package net.x_j0nnay_x.simpeladdmod.item.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.simpeladdmod;
import net.x_j0nnay_x.simpeladdmod.until.ModTags;
import java.util.List;

public class ModToolTiers {


    public static Tier
            OBSIDIANT = TierSortingRegistry.registerTier(
            new ForgeTier(4, 1900, 4.08f, 4.4f, 25,
                    ModTags.Blocks.OBSIDIAN, () -> Ingredient.of(ModItems.OBSIDAININGOT.get())),
            new ResourceLocation(simpeladdmod.MOD_ID, "obsidian"), List.of(Tiers.NETHERITE), List.of()),

    OBSIDIRITET = TierSortingRegistry.registerTier(
            new ForgeTier(4, 2900, 5.25f, 3.5f, 30,
                    ModTags.Blocks.OBSIDIANRITE, () -> Ingredient.of(Items.NETHERITE_INGOT)),
            new ResourceLocation(simpeladdmod.MOD_ID, "obsidirite"), List.of(Tiers.NETHERITE), List.of());

}
