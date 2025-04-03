package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.client.gui.screens.MenuScreens;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.screens.*;

public class ModScreensForge {
    public static void registerScreens() {
        SimpelAddMod.modScreenRegText();
        scrennReg();
    }

    public static void scrennReg() {
        MenuScreens.register(ModMenuTypeForge.UPGRADED_FURNACE_MENU.get(), ForgeFurnaceScreen_up::new);
        MenuScreens.register(ModMenuTypeForge.GRINDER_MENU.get(), ForgeGrinderScreen::new);
        MenuScreens.register(ModMenuTypeForge.GRINDER_MENU_UP.get(), ForgeGrinderScreen_up::new);
        MenuScreens.register(ModMenuTypeForge.GRIND_FACTORY_MENU.get(), ForgeGrindFactoryScreen::new);
        MenuScreens.register(ModMenuTypeForge.BLOCKFACTORY_MENU.get(), ForgeBlockFactoryScreen::new);
        MenuScreens.register(ModMenuTypeForge.Chiller_MENU.get(), ForgeChillerScreen::new);
        MenuScreens.register(ModMenuTypeForge.Netherite_Menu.get(), ForgeNetheriteCrafterScreen::new);
        MenuScreens.register(ModMenuTypeForge.TICK_ACCELERATOR_MENU.get(), ForgeTickAcceleratorScreen::new);
        MenuScreens.register(ModMenuTypeForge.TOOL_REPAIR_MENU.get(), ForgeToolRepairScreen::new);
        MenuScreens.register(ModMenuTypeForge.HARVESTER_MENU.get(), ForgeHarvesterScreen::new);
        MenuScreens.register(ModMenuTypeForge.CROP_GROWTH_MENU.get(), ForgeCropGrowthScreen::new);
    }
}
