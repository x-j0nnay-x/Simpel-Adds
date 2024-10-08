package net.x_j0nnay_x.simpeladd.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
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

    public static final ResourceKey<ConfiguredFeature<?, ?>> UNOBTANIUM_ORE_KEY = registerKey("config_unobtanium_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest endstoneReplaceables = new BlockMatchTest(Blocks.END_STONE);
        register(context, DEEPSLATE_DEBRI_ORE_KEY, Feature.ORE, new OreConfiguration(deepslateReplaceables, ModBlocks.DEEPSLATE_DEBRI_ORE.defaultBlockState(), 6));
        register(context, UNOBTANIUM_ORE_KEY, Feature.ORE, new OreConfiguration(endstoneReplaceables, ModBlocks.UNOBTANIUM_ORE.defaultBlockState(), 4));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,  ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}