package net.x_j0nnay_x.simpeladdmod.screen;



import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.screen.BlockFactory.BlockFactoryMenu;
import net.x_j0nnay_x.simpeladdmod.screen.Chiller.ChillerMenu;
import net.x_j0nnay_x.simpeladdmod.screen.Furnace_Up.FurnaceMenu_up;
import net.x_j0nnay_x.simpeladdmod.screen.NetheriteCrafter.NetheriteCrafterMenu;
import net.x_j0nnay_x.simpeladdmod.screen.grinder.GrinderMenu;
import net.x_j0nnay_x.simpeladdmod.screen.grinder_up.GrinderMenu_up;


public class ModMenuType {


    public static final ScreenHandlerType<BlockFactoryMenu> BLOCKFACTORY_MENU =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Simpeladd.MOD_ID, "blockfactorymenut"),
                new ScreenHandlerType<>(BlockFactoryMenu::new));


    public static final   ScreenHandlerType<FurnaceMenu_up> UPGRADED_FURNACE_MENU =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Simpeladd.MOD_ID, "furnacemenu_up"),
                    new ScreenHandlerType<>(FurnaceMenu_up::new));
    public static final   ScreenHandlerType<ChillerMenu> Chiller_MENU =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Simpeladd.MOD_ID, "chillermenu"),
                    new ScreenHandlerType<>(ChillerMenu::new));
    public static final   ScreenHandlerType<GrinderMenu> GRINDER_MENU =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Simpeladd.MOD_ID, "grindermenu"),
                    new ScreenHandlerType<>(GrinderMenu::new));
    public static final   ScreenHandlerType<GrinderMenu_up> GRINDER_MENU_UP =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Simpeladd.MOD_ID, "grindermenu_up"),
                    new ExtendedScreenHandlerType<>(GrinderMenu_up::new));
    public static final   ScreenHandlerType<NetheriteCrafterMenu> Netherite_Menu =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Simpeladd.MOD_ID, "netheritemenu"),
                    new ScreenHandlerType<>(NetheriteCrafterMenu::new));


   
    public static void registerScreenHandlers() {
        Simpeladd.LOGGER.info("Registering Screen Handlers for " + Simpeladd.MOD_ID);
    }
}
