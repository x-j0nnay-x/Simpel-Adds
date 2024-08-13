package net.x_j0nnay_x.simpeladd.worldgen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.registries.DataPackRegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProviderForge extends DataPackRegistryEvent {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiersForge::bootstrap);

    public ModWorldGenProviderForge(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        //super(output, registries, BUILDER, Set.of(SimpelAddModForge.MODID));
    }
}