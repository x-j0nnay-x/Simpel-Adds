package net.x_j0nnay_x.simpeladd;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.x_j0nnay_x.simpeladd.core.*;
import net.x_j0nnay_x.simpeladd.network.NeoForgeNetworkMessage;
import net.x_j0nnay_x.simpeladd.network.NeoForgeNetworkReg;
import net.x_j0nnay_x.simpeladd.platform.UpdateCheckerNeoForge;
import net.x_j0nnay_x.simpeladd.screens.*;
import org.slf4j.Logger;

@Mod(SimpelAddMod.MOD_ID)
public class SimpelAddModNeoForge {

    public SimpelAddModNeoForge(IEventBus modEventBus) {
        ModItemRegNeoForge.register(modEventBus);
        ModBlockRegNeoForge.register(modEventBus);
        SimpelAddMod.modWorldGenText();
        ModRecipesNeoForge.register(modEventBus);
        modEventBus.addListener(this::registerCapabilities);
        ModBlockEntitiesNeoForge.register(modEventBus);
        ModMenuTypeNeoForge.register(modEventBus);
        ModCreativeTabNeoForge.register(modEventBus);
        NeoForgeNetworkReg.register(modEventBus);
        modEventBus.addListener(NeoForgeNetworkMessage::onRegisterPayloadHandler);
        ModDataComponentTypesNeoForge.register(modEventBus);
        modEventBus.addListener(this::registerScreens);
        modEventBus.addListener(this::handleClientSetup);
    }

    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        SimpelAddMod.modBlockCapablityRegText();
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntitiesNeoForge.BLOCK_FACTORY.get(), SidedInvWrapper::new);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntitiesNeoForge.CHILLER.get(), SidedInvWrapper::new);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntitiesNeoForge.GRINDER.get(), SidedInvWrapper::new);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntitiesNeoForge.GRINDER_UP.get(), SidedInvWrapper::new);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntitiesNeoForge.GRIND_FACTORY.get(), SidedInvWrapper::new);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntitiesNeoForge.NETHERITE_CRAFTER.get(), SidedInvWrapper::new);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntitiesNeoForge.UPGRADED_FURNACE.get(), SidedInvWrapper::new);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntitiesNeoForge.TICK_ACCELERATOR.get(), SidedInvWrapper::new);
    }

    private void registerScreens(RegisterMenuScreensEvent event) {
        SimpelAddMod.modScreenRegText();
        event.register(ModMenuTypeNeoForge.UPGRADED_FURNACE_MENU.get(), NeoForgeFurnaceScreen_up::new);
        event.register(ModMenuTypeNeoForge.GRINDER_MENU.get(), NeoForgeGrinderScreen::new);
        event.register(ModMenuTypeNeoForge.GRINDER_MENU_UP.get(), NeoForgeGrinderScreen_up::new);
        event.register(ModMenuTypeNeoForge.BLOCKFACTORY_MENU.get(), NeoForgeBlockFactoryScreen::new);
        event.register(ModMenuTypeNeoForge.Chiller_MENU.get(), NeoForgeChillerScreen::new);
        event.register(ModMenuTypeNeoForge.Netherite_Menu.get(), NeoForgeNetheriteCrafterScreen::new);
        event.register(ModMenuTypeNeoForge.GRIND_FACTORY_MENU.get(), NeoForgeGrindFactoryScreen::new);
        event.register(ModMenuTypeNeoForge.TICK_ACCELERATOR_MENU.get(), NeoForgeTickAcceleratorScreen::new);
    }

    private void handleClientSetup(FMLClientSetupEvent event) {
        NeoForge.EVENT_BUS.register(new UpdateCheckerNeoForge(SimpelAddMod.MOD_ID));
    }
}