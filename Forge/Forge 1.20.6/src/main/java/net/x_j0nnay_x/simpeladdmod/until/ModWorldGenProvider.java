package net.x_j0nnay_x.simpeladdmod.until;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;

import net.minecraftforge.registries.DataPackRegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.world.ModBiomeModifiers;
import net.x_j0nnay_x.simpeladdmod.world.ModConfiguredFeatures;
import net.x_j0nnay_x.simpeladdmod.world.ModPlacedFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DataPackRegistryEvent {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
       // super(output, registries, BUILDER, Set.of(Simpeladd.MOD_ID));
    }


}