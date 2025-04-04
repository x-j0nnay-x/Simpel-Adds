package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.SimpelAddModForge;
import net.x_j0nnay_x.simpeladd.menu.*;

public class ModMenuTypeForge {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, SimpelAddMod.MOD_ID);

    public static final  RegistryObject<MenuType<ForgeBlockFactoryMenu>> BLOCKFACTORY_MENU =
            registerMenuType(ModNames.Menu.BLOCKFACTORYMENU, ForgeBlockFactoryMenu::new);

    public static final  RegistryObject<MenuType<ForgeGrinderMenu>> GRINDER_MENU =
            registerMenuType(ModNames.Menu.GRINDERMENU, ForgeGrinderMenu::new);

    public static final  RegistryObject<MenuType<ForgeGrinderMenu_up>> GRINDER_MENU_UP =
            registerMenuType(ModNames.Menu.GRINDERMENU_UP, ForgeGrinderMenu_up::new);

    public static final  RegistryObject<MenuType<ForgeGrindFactoryMenu>> GRIND_FACTORY_MENU =
            registerMenuType(ModNames.Menu.GRIND_FACTORY_MENU, ForgeGrindFactoryMenu::new);

    public static final  RegistryObject<MenuType<ForgeFurnaceMenu_up>> UPGRADED_FURNACE_MENU =
            registerMenuType(ModNames.Menu.UPGRADED_FURNACE_MENU, ForgeFurnaceMenu_up::new);

    public static final  RegistryObject<MenuType<ForgeChillerMenu>> Chiller_MENU =
            registerMenuType(ModNames.Menu.CHILLER_MENU, ForgeChillerMenu::new);

    public static final  RegistryObject<MenuType<ForgeNetheriteCrafterMenu>> Netherite_Menu =
            registerMenuType(ModNames.Menu.NETHERITE_MENU, ForgeNetheriteCrafterMenu::new);

    public static final  RegistryObject<MenuType<ForgeTickAcceleratorMenu>> TICK_ACCELERATOR_MENU =
            registerMenuType(ModNames.Menu.TICK_ACCELERATOR_MENU, ForgeTickAcceleratorMenu::new);

    public static final  RegistryObject<MenuType<ForgeToolRepairMenu>> TOOL_REPAIR_MENU =
            registerMenuType(ModNames.Menu.TOOLREPAIR_MENU, ForgeToolRepairMenu::new);

    public static final  RegistryObject<MenuType<ForgeHarvesterMenu>> HARVESTER_MENU =
            registerMenuType(ModNames.Menu.HARVESTER_MENU, ForgeHarvesterMenu::new);

    public static final  RegistryObject<MenuType<ForgeCropGrowthMenu>> CROP_GROWTH_MENU =
            registerMenuType(ModNames.Menu.CROP_GROWTH_MENU, ForgeCropGrowthMenu::new);

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory){
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void  register(IEventBus eventBus){
        SimpelAddMod.modMenuRegText();
        MENUS.register(eventBus);
    }


}
