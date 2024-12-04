package net.x_j0nnay_x.simpeladd.worldgen;

import net.x_j0nnay_x.simpeladd.SimpelAddMod;

public class ModWorldGenerationFabric {
    public static void generateModWorldGen() {
        SimpelAddMod.modWorldGenText();
        ModOreGenerationFabric.generateOres();
    }
}
