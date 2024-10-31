package net.x_j0nnay_x.simpeladd.core;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.x_j0nnay_x.simpeladd.menu.*;
import net.x_j0nnay_x.simpeladd.SimpelAddModFabric;
import net.x_j0nnay_x.simpeladd.util.data.*;

public class ModMenuTypeFabric {

    public static final MenuType<FabricBlockFactoryMenu> BLOCKFACTORY_MENU =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, "blockfactorymenu"),
                new ExtendedScreenHandlerType<>((pWindowID, pInventory, pData) ->
                    new FabricBlockFactoryMenu(pWindowID,pInventory), BlockFactroyData.CODEC));

    public static final MenuType<FabricChillerMenu> Chiller_MENU =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, "chillermenu"),
                new ExtendedScreenHandlerType<>((pWindowID, pInventory, pData) ->
                    new FabricChillerMenu(pWindowID,pInventory), ChillerData.CODEC));

    public static final MenuType<FabricGrinderMenu> GRINDER_MENU =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, "grindermenu"),
                new ExtendedScreenHandlerType<>((pWindowID, pInventory, pData) ->
                    new FabricGrinderMenu(pWindowID,pInventory), GrinderData.CODEC));

    public static final MenuType<FabricGrinderMenu_up> GRINDER_MENU_UP =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, "grindermenu_up"),
                new ExtendedScreenHandlerType<>((pWindowID, pInventory, pData) ->
                    new FabricGrinderMenu_up(pWindowID,pInventory), Grinder_upData.CODEC));

    public static final MenuType<FabricGrindFactoryMenu> GRIND_FACTORY_MENU =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, "grind_factory_menu"),
                    new ExtendedScreenHandlerType<>((pWindowID, pInventory, pData) ->
                            new FabricGrindFactoryMenu(pWindowID,pInventory), Grind_FactoryData.CODEC));

    public static final MenuType<FabricNetheriteCrafterMenu> Netherite_Menu =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, "netheritemenu"),
                new ExtendedScreenHandlerType<>((pWindowID, pInventory, pData) ->
                    new FabricNetheriteCrafterMenu(pWindowID,pInventory), NetheriteCrafterData.CODEC));

    public static final MenuType<FabricFurnaceMenu_up> UPGRADED_FURNACE_MENU =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, "furnacemenu_up"),
                new ExtendedScreenHandlerType<>((pWindowID, pInventory, pData) ->
                    new FabricFurnaceMenu_up(pWindowID,pInventory), Up_furnaceData.CODEC));

    public static void registerScreenHandlers() {
        SimpelAddModFabric.LOGGER.info("Registering Screen Handlers for " + SimpelAddModFabric.MODID);
    }
}
