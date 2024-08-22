package net.x_j0nnay_x.simpeladd.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;

import net.minecraft.world.level.levelgen.GenerationStep;
import net.x_j0nnay_x.simpeladd.worldgen.ModPlacedFeatures;


public class ModOreGenerationFabric {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.DEEPSLATE_DEBRI_ORE_PLACE_KEY);


        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.UNOBTANIUM_ORE_PLACED_KEY);
    }
}