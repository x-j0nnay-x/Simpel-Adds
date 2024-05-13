package net.x_j0nnay_x.simpeladdmod.screen;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.IRegistryExtension;
import net.x_j0nnay_x.simpeladdmod.screen.BlockFactory.BlockFactoryMenu;
import net.x_j0nnay_x.simpeladdmod.screen.Chiller.ChillerMenu;
import net.x_j0nnay_x.simpeladdmod.screen.NetheriteCrafter.NetheriteCrafterMenu;
import net.x_j0nnay_x.simpeladdmod.screen.StoneSifter.StoneSifterMenu;
import net.x_j0nnay_x.simpeladdmod.screen.grinder.GrinderMenu;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.screen.grinder_up.GrinderMenu_up;

public class ModMenuType {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(
                    DeferredRegister.MENU_TYPES, Simpeladd.MOD_ID);

    public static final   ScreenHandlerType<BlockFactoryMenu> BLOCKFACTORY_MENU =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Simpeladd.MOD_ID, "blockfactorymenu"),
                    new ExtendedScreenHandlerType<>(BlockFactoryMenu::new));

    public static final  MenuType<GrinderMenu> GRINDER_MENU =
            registerMenuType("grindermenu", GrinderMenu::new);
    public static final  MenuType<GrinderMenu_up> GRINDER_MENU_UP =
            registerMenuType("grindermenu_up", GrinderMenu_up::new);
    public static final  MenuType<BlockFactoryMenu> BLOCKFACTORY_MENU =
            registerMenuType("blockfactorymenu", BlockFactoryMenu::new);
    public static final  MenuType<ChillerMenu> Chiller_MENU =
            registerMenuType("chillermenu", ChillerMenu::new);
    public static final  MenuType<NetheriteCrafterMenu> Netherite_Menu =
            registerMenuType("netheritemenu", NetheriteCrafterMenu::new);
    public static final  MenuType<StoneSifterMenu> STONESIFTER_MENU =
            registerMenuType("stonesiftermenu", StoneSifterMenu::new);
    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory){
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }
    public static void  register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
    public static void registerModMenus(){
        Simpeladd.LOGGER.info("Registering Mod Menus for " + Simpeladd.MOD_ID);
    }
}
