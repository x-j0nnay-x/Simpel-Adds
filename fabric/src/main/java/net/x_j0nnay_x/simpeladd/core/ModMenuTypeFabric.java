package net.x_j0nnay_x.simpeladd.core;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.menu.*;
import net.x_j0nnay_x.simpeladd.SimpelAddModFabric;

public class ModMenuTypeFabric {

        public static final ExtendedScreenHandlerType<FabricBlockFactoryMenu> BLOCKFACTORY_MENU =
                 Registry.register(BuiltInRegistries.MENU, new ResourceLocation(SimpelAddMod.MOD_ID, ModNames.Menu.BLOCKFACTORYMENU),
                         new ExtendedScreenHandlerType<>(FabricBlockFactoryMenu::new));

        public static final   ExtendedScreenHandlerType<FabricChillerMenu> Chiller_MENU =
                Registry.register(BuiltInRegistries.MENU, new ResourceLocation(SimpelAddMod.MOD_ID, ModNames.Menu.CHILLER_MENU),
                        new ExtendedScreenHandlerType<>(FabricChillerMenu::new));

        public static final ExtendedScreenHandlerType<FabricGrinderMenu> GRINDER_MENU =
                Registry.register(BuiltInRegistries.MENU, new ResourceLocation(SimpelAddMod.MOD_ID, ModNames.Menu.GRINDERMENU),
                        new ExtendedScreenHandlerType<>(FabricGrinderMenu::new));

        public static final ExtendedScreenHandlerType<FabricGrinderMenu_up> GRINDER_MENU_UP =
                Registry.register(BuiltInRegistries.MENU, new ResourceLocation(SimpelAddMod.MOD_ID, ModNames.Menu.GRINDERMENU_UP),
                        new ExtendedScreenHandlerType<>(FabricGrinderMenu_up::new));

        public static final ExtendedScreenHandlerType<FabricGrindFactoryMenu> GRIND_FACTORY_MENU =
                Registry.register(BuiltInRegistries.MENU, new ResourceLocation(SimpelAddMod.MOD_ID, ModNames.Menu.GRIND_FACTORY_MENU),
                        new ExtendedScreenHandlerType<>(FabricGrindFactoryMenu::new));

        public static final   ExtendedScreenHandlerType<FabricNetheriteCrafterMenu> Netherite_Menu =
                Registry.register(BuiltInRegistries.MENU, new ResourceLocation(SimpelAddMod.MOD_ID, ModNames.Menu.NETHERITE_MENU),
                        new ExtendedScreenHandlerType<>(FabricNetheriteCrafterMenu::new));

        public static final   ExtendedScreenHandlerType<FabricFurnaceMenu_up> UPGRADED_FURNACE_MENU =
                Registry.register(BuiltInRegistries.MENU, new ResourceLocation(SimpelAddMod.MOD_ID, ModNames.Menu.UPGRADED_FURNACE_MENU),
                        new ExtendedScreenHandlerType<>(FabricFurnaceMenu_up::new));

        public static final ExtendedScreenHandlerType<FabricTickAcceleratorMenu> TICK_ACCELERATOR_MENU =
                Registry.register(BuiltInRegistries.MENU, new ResourceLocation(SimpelAddMod.MOD_ID, ModNames.Menu.TICK_ACCELERATOR_MENU),
                        new ExtendedScreenHandlerType<>(FabricTickAcceleratorMenu::new));

   
    public static void registerScreenHandlers() {
        SimpelAddMod.modMenuRegText();
    }
}
