package net.x_j0nnay_x.simpeladd;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.x_j0nnay_x.simpeladd.core.*;
import net.x_j0nnay_x.simpeladd.network.ForgeNetworkHandler;
import net.x_j0nnay_x.simpeladd.platform.UpdateCheckerForge;
import net.x_j0nnay_x.simpeladd.screens.*;
import org.slf4j.Logger;

@Mod(SimpelAddMod.MOD_ID)
public class SimpelAddModForge {

    public static final String MODID = SimpelAddMod.MOD_ID;
    public static final Logger LOGGER =  SimpelAddMod.LOG;

    public SimpelAddModForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItemRegForge.registerModItems();
        ModItemRegForge.register(modEventBus);
        ModBlockRegForge.registerModBlocks();
        ModBlockRegForge.register(modEventBus);
        ModRecipesForge.registerModRecipes();
        ModRecipesForge.register(modEventBus);
        ModBlockEntitiesForge.registerModBlockEntities();
        ModBlockEntitiesForge.register(modEventBus);
        ModMenuTypeForge.registerModMenus();
        ModMenuTypeForge.register(modEventBus);
        ModCreativeTabForge.registerCreativeTab();
        ModCreativeTabForge.register(modEventBus);
        modEventBus.addListener(ForgeNetworkHandler::registerNetworkHandler);
        LOGGER.info("Hello Forge world! From " + MODID);
        SimpelAddMod.init();
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::handleClientSetup);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
           ModScreensForge.registerScreens();
        }
    }

    private void handleClientSetup(FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new UpdateCheckerForge(MODID));
    }
}