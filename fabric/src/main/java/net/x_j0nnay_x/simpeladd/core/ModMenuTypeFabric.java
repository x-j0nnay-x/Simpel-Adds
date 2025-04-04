package net.x_j0nnay_x.simpeladd.core;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.menu.*;
import net.x_j0nnay_x.simpeladd.util.data.*;

public class ModMenuTypeFabric {

    public static final MenuType<FabricBlockFactoryMenu> BLOCKFACTORY_MENU =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Menu.BLOCKFACTORYMENU),
                new ExtendedScreenHandlerType<>((pWindowID, pInventory, pData) ->
                    new FabricBlockFactoryMenu(pWindowID,pInventory), BlockFactroyData.CODEC));

    public static final MenuType<FabricChillerMenu> Chiller_MENU =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Menu.CHILLER_MENU),
                new ExtendedScreenHandlerType<>((pWindowID, pInventory, pData) ->
                    new FabricChillerMenu(pWindowID,pInventory), ChillerData.CODEC));

    public static final MenuType<FabricGrinderMenu> GRINDER_MENU =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Menu.GRINDERMENU),
                new ExtendedScreenHandlerType<>((pWindowID, pInventory, pData) ->
                    new FabricGrinderMenu(pWindowID,pInventory), GrinderData.CODEC));

    public static final MenuType<FabricGrinderMenu_up> GRINDER_MENU_UP =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Menu.GRINDERMENU_UP),
                new ExtendedScreenHandlerType<>((pWindowID, pInventory, pData) ->
                    new FabricGrinderMenu_up(pWindowID,pInventory), Grinder_upData.CODEC));

    public static final MenuType<FabricGrindFactoryMenu> GRIND_FACTORY_MENU =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Menu.GRIND_FACTORY_MENU),
                    new ExtendedScreenHandlerType<>((pWindowID, pInventory, pData) ->
                            new FabricGrindFactoryMenu(pWindowID,pInventory), Grind_FactoryData.CODEC));

    public static final MenuType<FabricNetheriteCrafterMenu> Netherite_Menu =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Menu.NETHERITE_MENU),
                new ExtendedScreenHandlerType<>((pWindowID, pInventory, pData) ->
                    new FabricNetheriteCrafterMenu(pWindowID,pInventory), NetheriteCrafterData.CODEC));

    public static final MenuType<FabricFurnaceMenu_up> UPGRADED_FURNACE_MENU =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Menu.UPGRADED_FURNACE_MENU),
                new ExtendedScreenHandlerType<>((pWindowID, pInventory, pData) ->
                    new FabricFurnaceMenu_up(pWindowID,pInventory), Up_furnaceData.CODEC));

    public static final MenuType<FabricTickAcceleratorMenu> TICK_ACCELERATOR_MENU =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Menu.TICK_ACCELERATOR_MENU),
                    new ExtendedScreenHandlerType<>((pWindowID, pInventory, pData) ->
                       new FabricTickAcceleratorMenu(pWindowID,pInventory), TickAcceleratorData.CODEC));

    public static final MenuType<FabricToolRepairMenu> TOOLREPAIR_MENU =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Menu.TOOLREPAIR_MENU),
                    new ExtendedScreenHandlerType<>((pWindowID, pInventory, pData) ->
                            new FabricToolRepairMenu(pWindowID,pInventory), ToolRepairData.CODEC));

    public static final MenuType<FabricHarvesterMenu> HARVESTER_MENU =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Menu.HARVESTER_MENU),
                    new ExtendedScreenHandlerType<>((pWindowID, pInventory, pData) ->
                            new FabricHarvesterMenu(pWindowID,pInventory), HarvesterData.CODEC));

    public static final MenuType<FabricCropGrowthMenu> CROP_GROWTH_MENU =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Menu.CROP_GROWTH_MENU),
                    new ExtendedScreenHandlerType<>((pWindowID, pInventory, pData) ->
                            new FabricCropGrowthMenu(pWindowID,pInventory), CropGrowthData.CODEC));


    public static void registerScreenHandlers() {
        SimpelAddMod.modMenuRegText();
    }
}
