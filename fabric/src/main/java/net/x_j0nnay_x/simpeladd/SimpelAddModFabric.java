package net.x_j0nnay_x.simpeladd;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.x_j0nnay_x.simpeladd.core.*;

import net.x_j0nnay_x.simpeladd.screens.*;

import net.x_j0nnay_x.simpeladd.worldgen.ModWorldGenerationFabric;
import org.slf4j.Logger;

public class SimpelAddModFabric implements ModInitializer, ClientModInitializer {

    @Override
    public void onInitialize() {

        ModCreativeTabFabric.registerTab();
        ModItemRegFabric.registerModItems();
        ModBlockRegFabric.registerModBlocks();
        ModBlockEntitiesFabric.registerBlockEntities();
        ModWorldGenerationFabric.generateModWorldGen();
        ModMenuTypeFabric.registerScreenHandlers();
        ModRecipesRegFabric.registerRecipes();
        FuelRegistry.INSTANCE.add(ModItemRegFabric.FULECHUNKS, 50);
        SimpelAddMod.init();
    }
        @Override
        public void onInitializeClient() {
            ModScreensFabric.registerScreens();
        }
}

