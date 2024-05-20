package net.x_j0nnay_x.simpeladdmod.world;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;

import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_DEEPSLATE_DEBRI_ORE = registerKey("add_deepslate_debri_ore");
    public static final ResourceKey<BiomeModifier> ADD_UNOBTANIUM_ORE = registerKey("add_unobtanium_ore");



    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        context.register(
                ADD_DEEPSLATE_DEBRI_ORE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.DEEPSLATE_DEBRI_ORE_PLACE_KEY)),
                        GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(
                ADD_UNOBTANIUM_ORE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_END),
                        HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.UNOBTANIUM_ORE_PLACED_KEY)),
                        GenerationStep.Decoration.UNDERGROUND_ORES));


    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Simpeladd.MOD_ID, name));
    }
}