package net.x_j0nnay_x.simpeladd;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.x_j0nnay_x.simpeladd.core.*;
import net.x_j0nnay_x.simpeladd.platform.UpdateChecker;
import org.slf4j.Logger;

@Mod(SimpelAddMod.MOD_ID)
public class SimpelAddModForge {

    public SimpelAddModForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItemRegForge.register(modEventBus);
        ModBlockRegForge.register(modEventBus);
        ModRecipesForge.register(modEventBus);
        ModBlockEntitiesForge.register(modEventBus);
        ModMenuTypeForge.register(modEventBus);
        ModCreativeTabForge.register(modEventBus);
        SimpelAddMod.modWorldGenText();
        SimpelAddMod.init();
        MinecraftForge.EVENT_BUS.register(this);

    }
    @Mod.EventBusSubscriber(modid = SimpelAddMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
           ModScreensForge.registerScreens();
            new SimpelAddModForge().handleClientSetup(event);
        }


    }
    private void handleClientSetup(FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new UpdateChecker(SimpelAddMod.MOD_ID));
    }
}