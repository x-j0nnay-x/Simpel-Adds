package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.client.gui.screens.MenuScreens;
import net.x_j0nnay_x.simpeladd.SimpelAddModFabric;
import net.x_j0nnay_x.simpeladd.screens.*;

public class ModScreensFabric {

    public static void registerScreens() {
        SimpelAddModFabric.LOGGER.info("Loading GUI's for " + SimpelAddModFabric.MODID);
        init();
    }

    public static void init() {

        MenuScreens.register(ModMenuTypeFabric.BLOCKFACTORY_MENU, FabricBlockFactoryScreen::new);
        MenuScreens.register(ModMenuTypeFabric.Chiller_MENU, FabricChillerScreen::new);
        MenuScreens.register(ModMenuTypeFabric.GRINDER_MENU, FabricGrinderScreen::new);
        MenuScreens.register(ModMenuTypeFabric.GRINDER_MENU_UP, FabricGrinderScreen_up::new);
        MenuScreens.register(ModMenuTypeFabric.GRIND_FACTORY_MENU, FabricGrindFactoryScreen::new);
        MenuScreens.register(ModMenuTypeFabric.Netherite_Menu, FabricNetheriteCrafterScreen::new);
        MenuScreens.register(ModMenuTypeFabric.UPGRADED_FURNACE_MENU, FabricFurnaceScreen_up::new);
    }
}