package net.x_j0nnay_x.simpeladd.core;



import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;
import net.x_j0nnay_x.simpeladd.menu.*;

import java.util.function.Supplier;


public class ModMenuTypeNeoForge {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, SimpelAddModNeoForge.MODID);

    public static final Supplier<MenuType<NeoForgeBlockFactoryMenu>> BLOCKFACTORY_MENU =
            registerMenuType("blockfactorymenu",NeoForgeBlockFactoryMenu::new) ;
    public static final  Supplier<MenuType<NeoForgeGrinderMenu>> GRINDER_MENU =
            registerMenuType("grindermenu", NeoForgeGrinderMenu::new);
    public static final  Supplier<MenuType<NeoForgeGrinderMenu_up>> GRINDER_MENU_UP =
            registerMenuType("grindermenu_up", NeoForgeGrinderMenu_up::new);
    public static final  Supplier<MenuType<NeoForgeFurnaceMenu_up>> UPGRADED_FURNACE_MENU =
            registerMenuType("furnacemenu_up", NeoForgeFurnaceMenu_up::new);

    public static final  Supplier<MenuType<NeoForgeChillerMenu>> Chiller_MENU =
            registerMenuType("chillermenu", NeoForgeChillerMenu::new);
    public static final  Supplier<MenuType<NeoForgeNetheriteCrafterMenu>> Netherite_Menu =
            registerMenuType("netheritemenu", NeoForgeNetheriteCrafterMenu::new);

    private static <T extends AbstractContainerMenu>Supplier<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory){
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }


    public static void  register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
    public static void registerScreenHandlers() {
        SimpelAddModNeoForge.LOGGER.info("Registering Screen Handlers for " + SimpelAddModNeoForge.MODID);
    }
}
