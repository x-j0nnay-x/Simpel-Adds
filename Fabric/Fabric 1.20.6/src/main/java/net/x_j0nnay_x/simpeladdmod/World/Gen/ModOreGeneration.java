package net.x_j0nnay_x.simpeladdmod.World.Gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;

import net.minecraft.world.gen.GenerationStep;
import net.x_j0nnay_x.simpeladdmod.World.ModPlacedFeatures;

public class ModOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.DEEPSLATE_DEBRI_ORE_KEY);


        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.UNOBTANIUM_ORE_PLACED_KEY);
    }
}