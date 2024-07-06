package net.x_j0nnay_x.simpeladd.core;


import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.x_j0nnay_x.simpeladd.menu.*;
import net.x_j0nnay_x.simpeladd.SimpelAddModFabric;


public class ModMenuTypeFabric {

        public static final ExtendedScreenHandlerType<FabricBlockFactoryMenu> BLOCKFACTORY_MENU =
                 Registry.register(BuiltInRegistries.MENU, new ResourceLocation(SimpelAddModFabric.MODID, "blockfactorymenu"),
                         new ExtendedScreenHandlerType<>(FabricBlockFactoryMenu::new));
        public static final   ExtendedScreenHandlerType<FabricChillerMenu> Chiller_MENU =
                Registry.register(BuiltInRegistries.MENU, new ResourceLocation(SimpelAddModFabric.MODID, "chillermenu"),
                        new ExtendedScreenHandlerType<>(FabricChillerMenu::new));
        public static final ExtendedScreenHandlerType<FabricGrinderMenu> GRINDER_MENU =
                Registry.register(BuiltInRegistries.MENU, new ResourceLocation(SimpelAddModFabric.MODID, "grindermenu"),
                        new ExtendedScreenHandlerType<>(FabricGrinderMenu::new));
        public static final   ExtendedScreenHandlerType<FabricGrinderMenu_up> GRINDER_MENU_UP =
                Registry.register(BuiltInRegistries.MENU, new ResourceLocation(SimpelAddModFabric.MODID, "grindermenu_up"),
                        new ExtendedScreenHandlerType<>(FabricGrinderMenu_up::new));
        public static final   ExtendedScreenHandlerType<FabricNetheriteCrafterMenu> Netherite_Menu =
                Registry.register(BuiltInRegistries.MENU, new ResourceLocation(SimpelAddModFabric.MODID, "netheritemenu"),
                        new ExtendedScreenHandlerType<>(FabricNetheriteCrafterMenu::new));
        public static final   ExtendedScreenHandlerType<FabricFurnaceMenu_up> UPGRADED_FURNACE_MENU =
                Registry.register(BuiltInRegistries.MENU, new ResourceLocation(SimpelAddModFabric.MODID, "furnacemenu_up"),
                        new ExtendedScreenHandlerType<>(FabricFurnaceMenu_up::new));

   
    public static void registerScreenHandlers() {
        SimpelAddModFabric.LOGGER.info("Registering Screen Handlers for " + SimpelAddModFabric.MODID);
    }
}
