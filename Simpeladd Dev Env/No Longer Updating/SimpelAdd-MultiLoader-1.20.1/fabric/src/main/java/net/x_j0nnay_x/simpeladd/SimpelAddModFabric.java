package net.x_j0nnay_x.simpeladd;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.x_j0nnay_x.simpeladd.core.*;

import net.x_j0nnay_x.simpeladd.screens.*;

import net.x_j0nnay_x.simpeladd.worldgen.ModWorldGenerationFabric;
import org.slf4j.Logger;

public class SimpelAddModFabric implements ModInitializer, ClientModInitializer {

    public static final String MODID = SimpelAddMod.MOD_ID;
    public static final Logger LOGGER = SimpelAddMod.LOG;


    @Override
    public void onInitialize() {

        ModCreativeTabFabric.registerCreativeTab();
        ModCreativeTabFabric.registerTab();
        ModItemRegFabric.registerModItems();
        ModBlockRegFabric.registerModBlocks();
        ModBlockEntitiesFabric.registerBlockEntities();
        ModWorldGenerationFabric.generateModWorldGen();
        ModMenuTypeFabric.registerScreenHandlers();
        ModRecipesRegFabric.registerRecipes();
        LOGGER.info("Hello Fabric world!");
        SimpelAddMod.init();
    }
        @Override
        public void onInitializeClient() {

            MenuScreens.register(ModMenuTypeFabric.BLOCKFACTORY_MENU, FabricBlockFactoryScreen::new);
            MenuScreens.register(ModMenuTypeFabric.Chiller_MENU, FabricChillerScreen::new);
            MenuScreens.register(ModMenuTypeFabric.GRINDER_MENU, FabricGrinderScreen::new);
            MenuScreens.register(ModMenuTypeFabric.GRINDER_MENU_UP, FabricGrinderScreen_up::new);
            MenuScreens.register(ModMenuTypeFabric.Netherite_Menu, FabricNetheriteCrafterScreen::new);
            MenuScreens.register(ModMenuTypeFabric.UPGRADED_FURNACE_MENU, FabricFurnaceScreen_up::new);


        }

}

