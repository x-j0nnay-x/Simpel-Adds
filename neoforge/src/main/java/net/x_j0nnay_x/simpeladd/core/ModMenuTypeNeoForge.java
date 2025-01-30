package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;
import net.x_j0nnay_x.simpeladd.menu.*;
import java.util.function.Supplier;

public class ModMenuTypeNeoForge {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, SimpelAddMod.MOD_ID);

    public static final Supplier<MenuType<NeoForgeBlockFactoryMenu>> BLOCKFACTORY_MENU =
            registerMenuType(ModNames.Menu.BLOCKFACTORYMENU, NeoForgeBlockFactoryMenu::new) ;

    public static final  Supplier<MenuType<NeoForgeGrinderMenu>> GRINDER_MENU =
            registerMenuType(ModNames.Menu.GRINDERMENU, NeoForgeGrinderMenu::new);

    public static final  Supplier<MenuType<NeoForgeGrinderMenu_up>> GRINDER_MENU_UP =
            registerMenuType(ModNames.Menu.GRINDERMENU_UP, NeoForgeGrinderMenu_up::new);

    public static final  Supplier<MenuType<NeoForgeGrindFactoryMenu>> GRIND_FACTORY_MENU =
            registerMenuType(ModNames.Menu.GRIND_FACTORY_MENU, NeoForgeGrindFactoryMenu::new);

    public static final  Supplier<MenuType<NeoForgeFurnaceMenu_up>> UPGRADED_FURNACE_MENU =
            registerMenuType(ModNames.Menu.UPGRADED_FURNACE_MENU, NeoForgeFurnaceMenu_up::new);

    public static final  Supplier<MenuType<NeoForgeChillerMenu>> Chiller_MENU =
            registerMenuType(ModNames.Menu.CHILLER_MENU, NeoForgeChillerMenu::new);

    public static final  Supplier<MenuType<NeoForgeNetheriteCrafterMenu>> Netherite_Menu =
            registerMenuType(ModNames.Menu.NETHERITE_MENU, NeoForgeNetheriteCrafterMenu::new);

    public static final  Supplier<MenuType<NeoForgeTickAcceleratorMenu>> TICK_ACCELERATOR_MENU =
            registerMenuType(ModNames.Menu.TICK_ACCELERATOR_MENU, NeoForgeTickAcceleratorMenu::new);

    public static final  Supplier<MenuType<NeoForgeToolRepairMenu>> TOOLREPAIR_MENU =
            registerMenuType(ModNames.Menu.TOOLREPAIR_MENU, NeoForgeToolRepairMenu::new);

    public static final  Supplier<MenuType<NeoForgeHarvesterMenu>> HARVESTER_MENU =
            registerMenuType(ModNames.Menu.HARVESTER_MENU, NeoForgeHarvesterMenu::new);

    private static <T extends AbstractContainerMenu>Supplier<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory){
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void  register(IEventBus eventBus){
        SimpelAddMod.modMenuRegText();
        MENUS.register(eventBus);
    }

}
