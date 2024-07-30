package net.x_j0nnay_x.simpeladd;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.x_j0nnay_x.simpeladd.core.*;
import net.x_j0nnay_x.simpeladd.platform.UpdateCheckerNeoForge;
import net.x_j0nnay_x.simpeladd.screens.*;
import org.slf4j.Logger;

@Mod(SimpelAddMod.MOD_ID)
public class SimpelAddModNeoForge {
    public static final String MODID = SimpelAddMod.MOD_ID;
    public static final Logger LOGGER =  SimpelAddMod.LOG;
  public SimpelAddModNeoForge(IEventBus modEventBus) {


        ModItemRegNeoForge.registerModItems();
        ModItemRegNeoForge.register(modEventBus);

        ModBlockRegNeoForge.registerModBlocks();
        ModBlockRegNeoForge.register(modEventBus);

        ModRecipesNeoForge.registerModRecipes();
        ModRecipesNeoForge.register(modEventBus);

        ModBlockEntitiesNeoForge.registerModBlockEntities();
        ModBlockEntitiesNeoForge.register(modEventBus);

        ModMenuTypeNeoForge.registerScreenHandlers();
        ModMenuTypeNeoForge.register(modEventBus);

        ModCreativeTabNeoForge.registerCreativeTab();
        ModCreativeTabNeoForge.register(modEventBus);
        modEventBus.addListener(this::registerScreens);
        LOGGER.info("Hello NeoForge world! From " + MODID);
        modEventBus.addListener(this::handleClientSetup);

    }
    private void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypeNeoForge.UPGRADED_FURNACE_MENU.get(), NeoForgeFurnaceScreen_up::new);
        event.register(ModMenuTypeNeoForge.GRINDER_MENU.get(), NeoForgeGrinderScreen::new);
        event.register(ModMenuTypeNeoForge.GRINDER_MENU_UP.get(), NeoForgeGrinderScreen_up::new);
        event.register(ModMenuTypeNeoForge.BLOCKFACTORY_MENU.get(), NeoForgeBlockFactoryScreen::new);
        event.register(ModMenuTypeNeoForge.Chiller_MENU.get(), NeoForgeChillerScreen::new);
        event.register(ModMenuTypeNeoForge.Netherite_Menu.get(), NeoForgeNetheriteCrafterScreen::new);
        event.register(ModMenuTypeNeoForge.GRIND_FACTORY_MENU.get(), NeoForgeGrindFactoryScreen::new);
    }

    private void handleClientSetup(FMLClientSetupEvent event) {
        NeoForge.EVENT_BUS.register(new UpdateCheckerNeoForge(MODID));
    }
}