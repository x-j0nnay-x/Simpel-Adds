package net.x_j0nnay_x.simpeladd.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> DEEPSLATE_DEBRI_ORE_PLACE_KEY = registerKey("place_deepslate_debri_ore");
    public static final ResourceKey<PlacedFeature> NETHER_DEBRI_ORE_PLACE_KEY = registerKey("place_nether_debri_ore");
    public static final ResourceKey<PlacedFeature> END_DEBRI_ORE_PLACE_KEY = registerKey("place_end_debri_ore");
    public static final ResourceKey<PlacedFeature> NETHER_COAL_ORE_PLACE_KEY = registerKey("place_nether_coal_ore");
    public static final ResourceKey<PlacedFeature> END_COAL_ORE_PLACE_KEY = registerKey("place_end_coal_ore");
    public static final ResourceKey<PlacedFeature> NETHER_COPPER_ORE_PLACE_KEY = registerKey("place_nether_copper_ore");
    public static final ResourceKey<PlacedFeature> END_COPPER_ORE_PLACE_KEY = registerKey("place_end_copper_ore");
    public static final ResourceKey<PlacedFeature> NETHER_DIAMOND_ORE_PLACE_KEY = registerKey("place_nether_diamond_ore");
    public static final ResourceKey<PlacedFeature> END_DIAMOND_ORE_PLACE_KEY = registerKey("place_end_diamond_ore");
    public static final ResourceKey<PlacedFeature> NETHER_EMERALD_ORE_PLACE_KEY = registerKey("place_nether_emerald_ore");
    public static final ResourceKey<PlacedFeature> END_EMERALD_ORE_PLACE_KEY = registerKey("place_end_emerald_ore");
    public static final ResourceKey<PlacedFeature> NETHER_GOLD_ORE_PLACE_KEY = registerKey("place_nether_gold_ore");
    public static final ResourceKey<PlacedFeature> END_GOLD_ORE_PLACE_KEY = registerKey("place_end_gold_ore");
    public static final ResourceKey<PlacedFeature> NETHER_IRON_ORE_PLACE_KEY = registerKey("place_nether_iron_ore");
    public static final ResourceKey<PlacedFeature> END_IRON_ORE_PLACE_KEY = registerKey("place_end_iron_ore");
    public static final ResourceKey<PlacedFeature> NETHER_LAPIS_ORE_PLACE_KEY = registerKey("place_nether_lapis_ore");
    public static final ResourceKey<PlacedFeature> END_LAPIS_ORE_PLACE_KEY = registerKey("place_end_lapis_ore");
    public static final ResourceKey<PlacedFeature> NETHER_REDSTONE_ORE_PLACE_KEY = registerKey("place_nether_redstone_ore");
    public static final ResourceKey<PlacedFeature> END_REDSTONE_ORE_PLACE_KEY = registerKey("place_end_redstone_ore");
    public static final ResourceKey<PlacedFeature> END_UNOBTANIUM_ORE_PLACE_KEY = registerKey("place_end_unobtanium_ore");

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(SimpelAddMod.MOD_ID, name));
    }
    
}
