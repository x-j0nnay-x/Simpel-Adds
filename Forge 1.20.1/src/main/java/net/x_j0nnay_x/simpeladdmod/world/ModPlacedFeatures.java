package net.x_j0nnay_x.simpeladdmod.world;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> DEEPSLATE_DEBRI_ORE_PLACE_KEY = registerKey("place_deepslate_debri_ore");
    public static final ResourceKey<PlacedFeature> UNOBTANIUM_ORE_PLACED_KEY = registerKey("place_unobtanium_ore");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, DEEPSLATE_DEBRI_ORE_PLACE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DEEPSLATE_DEBRI_ORE_KEY),
                ModOrePlacement.commonOrePlacement(16,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(-5))));

        register(context, UNOBTANIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.UNOBTANIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(1,
                         HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));


    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Simpeladd.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
