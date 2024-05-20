package net.x_j0nnay_x.simpeladdmod.screen;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.x_j0nnay_x.simpeladdmod.screen.BlockFactory.BlockFactoryMenu;
import net.x_j0nnay_x.simpeladdmod.screen.Chiller.ChillerMenu;
import net.x_j0nnay_x.simpeladdmod.screen.Furnace_Up.FurnaceMenu_up;
import net.x_j0nnay_x.simpeladdmod.screen.NetheriteCrafter.NetheriteCrafterMenu;
import net.x_j0nnay_x.simpeladdmod.screen.StoneSifter.StoneSifterMenu;
import net.x_j0nnay_x.simpeladdmod.screen.grinder.GrinderMenu;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.screen.grinder_up.GrinderMenu_up;

public class ModMenuType {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Simpeladd.MOD_ID);

    public static final  RegistryObject<MenuType<GrinderMenu>> GRINDER_MENU =
            registerMenuType("grindermenu", GrinderMenu::new);
    public static final  RegistryObject<MenuType<GrinderMenu_up>> GRINDER_MENU_UP =
            registerMenuType("grindermenu_up", GrinderMenu_up::new);
    public static final  RegistryObject<MenuType<FurnaceMenu_up>> UPGRADED_FURNACE_MENU =
            registerMenuType("furnacemenu_up", FurnaceMenu_up::new);
    public static final  RegistryObject<MenuType<BlockFactoryMenu>> BLOCKFACTORY_MENU =
            registerMenuType("blockfactorymenu", BlockFactoryMenu::new);
    public static final  RegistryObject<MenuType<ChillerMenu>> Chiller_MENU =
            registerMenuType("chillermenu", ChillerMenu::new);
    public static final  RegistryObject<MenuType<NetheriteCrafterMenu>> Netherite_Menu =
            registerMenuType("netheritemenu", NetheriteCrafterMenu::new);
    public static final  RegistryObject<MenuType<StoneSifterMenu>> STONESIFTER_MENU =
            registerMenuType("stonesiftermenu", StoneSifterMenu::new);
    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory){
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }
    public static void  register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
    public static void registerModMenus(){
        Simpeladd.LOGGER.info("Registering Mod Menus for " + Simpeladd.MOD_ID);
    }
}
