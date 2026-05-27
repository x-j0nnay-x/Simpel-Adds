package x_j0nnay_x.simpeladdmod;

import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import x_j0nnay_x.simpeladdmod.block.entitys.*;
import x_j0nnay_x.simpeladdmod.events.Player_Interaction_Events;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.menu.screen.*;
import x_j0nnay_x.simpeladdmod.init.Mod_Config;
import x_j0nnay_x.simpeladdmod.init.Mod_Constants;

@Mod(Mod_Constants.MOD_ID)
public class SimpelAdd {
    public static final Logger LOGGER = LogUtils.getLogger();

    public SimpelAdd(IEventBus modEventBus, ModContainer modContainer) {
        Mod_Config.register(modContainer);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerCapabilities);
        Mod_Registrys.register(modEventBus);
        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(Player_Interaction_Events.class);

    }


    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.Item.BLOCK, Mod_Registrys.Mod_Block_Entity.GRINDER_BLOCK_ENTITY.get(), Grinder_Block_Entity::getItemHandler);
        event.registerBlockEntity(Capabilities.Item.BLOCK, Mod_Registrys.Mod_Block_Entity.GRINDER_NETHERITE_BLOCK_ENTITY.get(), Grinder_Netherite_Block_Entity::getItemHandler);
        event.registerBlockEntity(Capabilities.Item.BLOCK, Mod_Registrys.Mod_Block_Entity.NETHERITE_CRAFTER_BLOCK_ENTITY.get(), Netherite_Crafter_Block_Entity::getItemHandler);
        event.registerBlockEntity(Capabilities.Item.BLOCK, Mod_Registrys.Mod_Block_Entity.CROP_GROWTH_BLOCK_ENTITY.get(), Crop_Growth_Block_Entity::getItemHandler);
        event.registerBlockEntity(Capabilities.Item.BLOCK, Mod_Registrys.Mod_Block_Entity.HARVESTER_BLOCK_ENTITY.get(), Harvester_Block_Entity::getItemHandler);
        event.registerBlockEntity(Capabilities.Item.BLOCK, Mod_Registrys.Mod_Block_Entity.TICK_ACCELERATOR_BLOCK_ENTITY.get(), Tick_Accelerator_Block_Entity::getItemHandler);
        event.registerBlockEntity(Capabilities.Item.BLOCK, Mod_Registrys.Mod_Block_Entity.TOOL_REPAIR_BLOCK_ENTTY.get(), Tool_Repair_Block_Entity::getItemHandler);
        event.registerBlockEntity(Capabilities.Item.BLOCK, Mod_Registrys.Mod_Block_Entity.SIMPEL_SPAWNER_BLOCK_ENTTY.get(), Simpel_Spawner_Block_Entity::getItemHandler);

    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    //server
    @Mod(value = Mod_Constants.MOD_ID, dist = Dist.CLIENT)
    @EventBusSubscriber(modid = Mod_Constants.MOD_ID, value = Dist.CLIENT)
    public static class SimpelAddClient {
        public SimpelAddClient(ModContainer container) {

        }
        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(Mod_Registrys.Mod_Menu.GRINDER_MENU.get(), Grinder_Screen::new);
            event.register(Mod_Registrys.Mod_Menu.GRINDER_NETHERITE_MENU.get(), Grinder_Netherite_Screen::new);
            event.register(Mod_Registrys.Mod_Menu.NETHERITE_CRAFTER_MENU.get(), Netherite_Crafter_Screen::new);
            event.register(Mod_Registrys.Mod_Menu.CROP_GROWTH_MENU.get(), Crop_Growth_Screen::new);
            event.register(Mod_Registrys.Mod_Menu.HARVESTER_MENU.get(), Harvester_Screen::new);
            event.register(Mod_Registrys.Mod_Menu.TICK_ACCELERATOR.get(), Tick_Accelerator_Screen::new);
            event.register(Mod_Registrys.Mod_Menu.TOOL_REPAIR.get(), Tool_Repair_Screen::new);
            event.register(Mod_Registrys.Mod_Menu.SIMPEL_SPAWNER.get(), Simpel_Spawner_Screen::new);
        }

        @SubscribeEvent
        static void onClientSetup(FMLClientSetupEvent event) {

            SimpelAdd.LOGGER.info("SIMPEL ADD CLIENT SETUP");
        }


    }

}
