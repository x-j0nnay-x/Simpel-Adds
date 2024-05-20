package net.x_j0nnay_x.simpeladdmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.x_j0nnay_x.simpeladdmod.block.ModBlockEntities;
import net.x_j0nnay_x.simpeladdmod.block.ModBlocks;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.recipe.ModRecipes;
import net.x_j0nnay_x.simpeladdmod.screen.BlockFactory.BlockFactoryScreen;
import net.x_j0nnay_x.simpeladdmod.screen.Chiller.ChillerScreen;
import net.x_j0nnay_x.simpeladdmod.screen.Furnace_Up.FrunaceScreen_up;
import net.x_j0nnay_x.simpeladdmod.screen.NetheriteCrafter.NetheriteCrafterScreen;
import net.x_j0nnay_x.simpeladdmod.screen.StoneSifter.StoneSifterScreen;
import net.x_j0nnay_x.simpeladdmod.screen.grinder.GrinderScreen;
import net.x_j0nnay_x.simpeladdmod.screen.ModMenuType;
import net.x_j0nnay_x.simpeladdmod.screen.grinder_up.GrinderScreen_up;
import net.x_j0nnay_x.simpeladdmod.until.*;
import org.slf4j.Logger;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

@Mod(Simpeladd.MOD_ID)
public class Simpeladd {
    public static final String MOD_ID = "simpeladdmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Simpeladd(IEventBus modEventBus) {

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        ModCreativeTab.register(modEventBus);
        ModRecipes.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        ModMenuType.register(modEventBus);


        modEventBus.register(this);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::handleClientSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Hello NeoForge world! from " + MOD_ID);
        ModCreativeTab.registerCreativeTab();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModBlockEntities.registerModBlockEntities();
        ModMenuType.registerModMenus();
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

    public static void queueServerWork(int tick, Runnable action) {
        workQueue.add(new AbstractMap.SimpleEntry(action, tick));
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("Loading GUI's For " + MOD_ID);
            MenuScreens.register(ModMenuType.UPGRADED_FURNACE_MENU, FrunaceScreen_up::new);
            MenuScreens.register(ModMenuType.GRINDER_MENU, GrinderScreen::new);
            MenuScreens.register(ModMenuType.GRINDER_MENU_UP, GrinderScreen_up::new);
            MenuScreens.register(ModMenuType.BLOCKFACTORY_MENU, BlockFactoryScreen::new);
            MenuScreens.register(ModMenuType.Chiller_MENU, ChillerScreen::new);
            MenuScreens.register(ModMenuType.Netherite_Menu, NetheriteCrafterScreen::new);
            MenuScreens.register(ModMenuType.STONESIFTER_MENU, StoneSifterScreen::new);
        }

    }
    private void handleClientSetup(FMLClientSetupEvent event) {
        new UpdateChecker(MOD_ID);
    }

}