package net.x_j0nnay_x.simpeladdmod;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.x_j0nnay_x.simpeladdmod.World.ModConfiguredFeatures;
import net.x_j0nnay_x.simpeladdmod.World.ModPlacedFeatures;


public class SimpeladdDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

	}
	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {

	}
}
