package net.x_j0nnay_x.simpeladd.worldgen;

import net.minecraft.core.registries.Registries;

import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModBlocks;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_DEBRI_ORE_KEY = registerKey("config_deepslate_debri_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_DEBRI_ORE_KEY = registerKey("config_nether_debri_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_DEBRI_ORE_KEY = registerKey("config_end_debri_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_COAL_ORE_KEY = registerKey("config_nether_coal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_COAL_ORE_KEY = registerKey("config_end_coal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_COPPER_ORE_KEY = registerKey("config_nether_copper_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_COPPER_ORE_KEY = registerKey("config_end_copper_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_DIAMOND_ORE_KEY = registerKey("config_nether_diamond_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_DIAMOND_ORE_KEY = registerKey("config_end_diamond_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_EMERALD_ORE_KEY = registerKey("config_nether_emerald_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_EMERALD_ORE_KEY = registerKey("config_end_emerald_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_GOLD_ORE_KEY = registerKey("config_nether_gold_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_GOLD_ORE_KEY = registerKey("config_end_gold_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_IRON_ORE_KEY = registerKey("config_nether_iron_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_IRON_ORE_KEY = registerKey("config_end_iron_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_LAPIS_ORE_KEY = registerKey("config_nether_lapis_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_LAPIS_ORE_KEY = registerKey("config_end_lapis_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_REDSTONE_ORE_KEY = registerKey("config_nether_redstone_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_REDSTONE_ORE_KEY = registerKey("config_end_redstone_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_UNOBTANIUM_ORE_KEY = registerKey("config_end_unobtanium_ore");

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(SimpelAddMod.MOD_ID, name));
    }
}