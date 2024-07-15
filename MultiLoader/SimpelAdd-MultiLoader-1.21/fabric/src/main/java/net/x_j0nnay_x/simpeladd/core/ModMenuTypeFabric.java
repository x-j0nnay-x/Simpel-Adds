package net.x_j0nnay_x.simpeladd.core;



import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.x_j0nnay_x.simpeladd.menu.*;
import net.x_j0nnay_x.simpeladd.SimpelAddModFabric;

import org.intellij.lang.annotations.Identifier;

import java.awt.*;


public class ModMenuTypeFabric {

        public static MenuType<FabricBlockFactoryMenu> BLOCKFACTORY_MENU =
                 Registry.register(BuiltInRegistries.MENU,  ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, "blockfactorymenu"),
                         new ExtendedScreenHandlerType.ExtendedFactory<>(FabricBlockFactoryMenu::new, FeatureFlags.VANILLA_SET));
        public static final MenuType<FabricChillerMenu> Chiller_MENU =
                Registry.register(BuiltInRegistries.MENU,  ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, "chillermenu"),
                        new ExtendedScreenHandlerType<>(FabricChillerMenu::new));
        public static final MenuType<FabricGrinderMenu> GRINDER_MENU =
                Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, "grindermenu"),
                        new ExtendedScreenHandlerType<>(FabricGrinderMenu::new));
        public static final   MenuType<FabricGrinderMenu_up> GRINDER_MENU_UP =
                Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, "grindermenu_up"),
                        new ExtendedScreenHandlerType<>(FabricGrinderMenu_up::new));
        public static final   MenuType<FabricNetheriteCrafterMenu> Netherite_Menu =
                Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, "netheritemenu"),
                        new ExtendedScreenHandlerType<>(FabricNetheriteCrafterMenu::new));
        public static final   MenuType<FabricFurnaceMenu_up> UPGRADED_FURNACE_MENU =
                Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, "furnacemenu_up"),
                        new ExtendedScreenHandlerType<>(FabricFurnaceMenu_up::new));


    public static void registerScreenHandlers() {
        SimpelAddModFabric.LOGGER.info("Registering Screen Handlers for " + SimpelAddModFabric.MODID);
    }
}
