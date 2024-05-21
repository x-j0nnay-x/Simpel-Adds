package net.x_j0nnay_x.simpeladdmod.screen;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.NeoForgeRegistriesSetup;
import net.x_j0nnay_x.simpeladdmod.screen.BlockFactory.BlockFactoryMenu;
import net.x_j0nnay_x.simpeladdmod.screen.Chiller.ChillerMenu;
import net.x_j0nnay_x.simpeladdmod.screen.Furnace_Up.FurnaceMenu_up;
import net.x_j0nnay_x.simpeladdmod.screen.NetheriteCrafter.NetheriteCrafterMenu;
import net.x_j0nnay_x.simpeladdmod.screen.StoneSifter.StoneSifterMenu;
import net.x_j0nnay_x.simpeladdmod.screen.grinder.GrinderMenu;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.screen.grinder.GrinderScreen;
import net.x_j0nnay_x.simpeladdmod.screen.grinder_up.GrinderMenu_up;

public class ModMenuType {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, Simpeladd.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<GrinderMenu>> GRINDER_MENU =
            registerMenuType("grindermenu", GrinderMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<GrinderMenu_up>> GRINDER_MENU_UP =
            registerMenuType("grindermenu_up", GrinderMenu_up::new);
    public static final DeferredHolder<MenuType<?>, MenuType<FurnaceMenu_up>> UPGRADED_FURNACE_MENU =
            registerMenuType("furnacemenu_up", FurnaceMenu_up::new);
    public static final DeferredHolder<MenuType<?>, MenuType<BlockFactoryMenu>> BLOCKFACTORY_MENU =
            registerMenuType("blockfactorymenu", BlockFactoryMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<ChillerMenu>> Chiller_MENU =
            registerMenuType("chillermenu", ChillerMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<NetheriteCrafterMenu>> Netherite_Menu =
            registerMenuType("netheritemenu", NetheriteCrafterMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<StoneSifterMenu>> STONESIFTER_MENU =
            registerMenuType("stonesiftermenu", StoneSifterMenu::new);

    public static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory){
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));

    }

    public static void  register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
    public static void registerModMenus(){
        Simpeladd.LOGGER.info("Registering Mod Menus for " + Simpeladd.MOD_ID);
    }

}
