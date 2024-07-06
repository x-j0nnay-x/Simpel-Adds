package net.x_j0nnay_x.simpeladdmod.screen;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.registries.BuiltInRegistries;
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

import java.util.function.Supplier;

public class ModMenuType {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(BuiltInRegistries.MENU, Simpeladd.MOD_ID);

    public static final Supplier<MenuType<GrinderMenu>> GRINDER_MENU =
            registerMenuType("grindermenu", GrinderMenu::new);

    public static final Supplier<MenuType<GrinderMenu_up>> GRINDER_MENU_UP =
            registerMenuType("grindermenu_up", GrinderMenu_up::new);
    public static final Supplier<MenuType<FurnaceMenu_up>> UPGRADED_FURNACE_MENU =
            registerMenuType("furnacemenu_up", FurnaceMenu_up::new);
    public static final Supplier<MenuType<BlockFactoryMenu>> BLOCKFACTORY_MENU = MENUS.register("blockfactorymenu",
            () -> IMenuTypeExtension.create((windowId, inv, data) -> new BlockFactoryMenu(windowId, inv.player, data.readBlockPos())));

    public static final Supplier<MenuType<ChillerMenu>> Chiller_MENU =
            registerMenuType("chillermenu", ChillerMenu::new);
    public static final Supplier<MenuType<NetheriteCrafterMenu>> Netherite_Menu =
            registerMenuType("netheritemenu", NetheriteCrafterMenu::new);
    public static final Supplier<MenuType<StoneSifterMenu>> STONESIFTER_MENU =
            registerMenuType("stonesiftermenu", StoneSifterMenu::new);


    public static void  register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
    public static void registerModMenus(){
        Simpeladd.LOGGER.info("Registering Mod Menus for " + Simpeladd.MOD_ID);
    }

}
