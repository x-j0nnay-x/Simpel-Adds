package net.x_j0nnay_x.simpeladdmod.World;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.block.ModBlocks;

import java.util.List;

    public class ModConfiguredFeatures {
         public static final RegistryKey<ConfiguredFeature<?, ?>> DEEPSLATE_DEBRI_ORE_KEY = registerKey("config_deepslate_debri_ore");
         public static final RegistryKey<ConfiguredFeature<?, ?>> UNOBTANIUM_ORE_KEY = registerKey("config_unobtanium_ore");

         public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {
             RuleTest deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
             RuleTest endReplacables = new BlockMatchRuleTest(Blocks.END_STONE);

             List<OreFeatureConfig.Target> overworldDebriOres =
                 List.of(OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_DEBRI_ORE.getDefaultState()));
             List<OreFeatureConfig.Target> endUnobtaniumOres =
                List.of(OreFeatureConfig.createTarget(endReplacables, ModBlocks.UNOBTANIUM_ORE.getDefaultState()));

        register(context, DEEPSLATE_DEBRI_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldDebriOres, 8));
        register(context, UNOBTANIUM_ORE_KEY, Feature.ORE, new OreFeatureConfig(endUnobtaniumOres, 4));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Simpeladd.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}