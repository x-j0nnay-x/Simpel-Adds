package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.client.gui.screens.MenuScreens;
import net.x_j0nnay_x.simpeladd.SimpelAddModForge;
import net.x_j0nnay_x.simpeladd.screens.*;

public class ModScreensForge {
    public static void registerScreens() {
        SimpelAddModForge.LOGGER.info("Loading GUI's for " + SimpelAddModForge.MODID);
        init();
    }

    public static void init() {
        MenuScreens.register(ModMenuTypeForge.UPGRADED_FURNACE_MENU.get(), ForgeFurnaceScreen_up::new);
        MenuScreens.register(ModMenuTypeForge.GRINDER_MENU.get(), ForgeGrinderScreen::new);
        MenuScreens.register(ModMenuTypeForge.GRINDER_MENU_UP.get(), ForgeGrinderScreen_up::new);
        MenuScreens.register(ModMenuTypeForge.GRIND_FACTORY_MENU.get(), ForgeGrindFactoryrScreen::new);
        MenuScreens.register(ModMenuTypeForge.BLOCKFACTORY_MENU.get(), ForgeBlockFactoryScreen::new);
        MenuScreens.register(ModMenuTypeForge.Chiller_MENU.get(), ForgeChillerScreen::new);
        MenuScreens.register(ModMenuTypeForge.Netherite_Menu.get(), ForgeNetheriteCrafterScreen::new);
    }

}