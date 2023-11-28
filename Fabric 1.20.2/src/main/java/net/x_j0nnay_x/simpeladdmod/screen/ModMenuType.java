package net.x_j0nnay_x.simpeladdmod.screen;


import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.screen.BlockFactory.BlockFactoryMenu;
import net.x_j0nnay_x.simpeladdmod.screen.Chiller.ChillerMenu;
import net.x_j0nnay_x.simpeladdmod.screen.NetheriteCrafter.NetheriteCrafterMenu;
import net.x_j0nnay_x.simpeladdmod.screen.StoneSifter.StoneSifterMenu;
import net.x_j0nnay_x.simpeladdmod.screen.grinder.GrinderMenu;


public class ModMenuType {

    public static final   ScreenHandlerType<BlockFactoryMenu> BLOCKFACTORY_MENU =
             Registry.register(Registries.SCREEN_HANDLER, new Identifier(Simpeladd.MOD_ID, "blockfactorymenu"),
                    new ExtendedScreenHandlerType<>(BlockFactoryMenu::new));
    public static final   ScreenHandlerType<ChillerMenu> Chiller_MENU =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Simpeladd.MOD_ID, "chillermenu"),
                    new ExtendedScreenHandlerType<>(ChillerMenu::new));
    public static final   ScreenHandlerType<GrinderMenu> GRINDER_MENU =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Simpeladd.MOD_ID, "grindermenu"),
                    new ExtendedScreenHandlerType<>(GrinderMenu::new));
    public static final   ScreenHandlerType<NetheriteCrafterMenu> Netherite_Menu =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Simpeladd.MOD_ID, "netheritemenu"),
                    new ExtendedScreenHandlerType<>(NetheriteCrafterMenu::new));
    public static final   ScreenHandlerType<StoneSifterMenu> STONESIFTER_MENU =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Simpeladd.MOD_ID, "stonesiftermenu"),
                    new ExtendedScreenHandlerType<>(StoneSifterMenu::new));

   
    public static void registerScreenHandlers() {
        Simpeladd.LOGGER.info("Registering Screen Handlers for " + Simpeladd.MOD_ID);
    }
}