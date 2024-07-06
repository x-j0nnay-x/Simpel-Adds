package net.x_j0nnay_x.simpeladd;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.common.NeoForge;
import net.x_j0nnay_x.simpeladd.core.*;
import net.x_j0nnay_x.simpeladd.platform.UpdateCheckerNeoForge;
import net.x_j0nnay_x.simpeladd.screens.*;
import org.slf4j.Logger;

@Mod(SimpelAddMod.MOD_ID)
public class SimpelAddModNeoForge {
    public static final String MODID = SimpelAddMod.MOD_ID;
    public static final Logger LOGGER =  SimpelAddMod.LOG;
  public SimpelAddModNeoForge() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

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
        LOGGER.info("Hello NeoForge world! From " + MODID);
        SimpelAddMod.init();
        modEventBus.addListener(this::handleClientSetup);

    }
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("Loading GUI's for " + MODID);
            MenuScreens.register(ModMenuTypeNeoForge.UPGRADED_FURNACE_MENU.get(), NeoForgeFurnaceScreen_up::new);
            MenuScreens.register(ModMenuTypeNeoForge.GRINDER_MENU.get(), NeoForgeGrinderScreen::new);
            MenuScreens.register(ModMenuTypeNeoForge.GRINDER_MENU_UP.get(), NeoForgeGrinderScreen_up::new);
            MenuScreens.register(ModMenuTypeNeoForge.BLOCKFACTORY_MENU.get(), NeoForgeBlockFactoryScreen::new);
            MenuScreens.register(ModMenuTypeNeoForge.Chiller_MENU.get(), NeoForgeChillerScreen::new);
            MenuScreens.register(ModMenuTypeNeoForge.Netherite_Menu.get(), NeoForgeNetheriteCrafterScreen::new);
        }


    }
    private void handleClientSetup(FMLClientSetupEvent event) {
        NeoForge.EVENT_BUS.register(new UpdateCheckerNeoForge(MODID));
    }
}