package net.x_j0nnay_x.simpeladdmod.world;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;
import net.x_j0nnay_x.simpeladdmod.simpeladdmod;
import net.x_j0nnay_x.simpeladdmod.world.inventory.BlockfactoryMenu;
import net.x_j0nnay_x.simpeladdmod.world.inventory.ChillerBlockGUIMenu;
import net.x_j0nnay_x.simpeladdmod.world.inventory.GrinderGuiMenu;



public class ModMenus {
	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, simpeladdmod.MOD_ID);
	public static final RegistryObject<MenuType<GrinderGuiMenu>> GRINDER_GUI = MENUS.register("grinder_gui", () -> IForgeMenuType.create(GrinderGuiMenu::new));
	public static final RegistryObject<MenuType<BlockfactoryMenu>> BLOCKFACTORY = MENUS.register("blockfactory", () -> IForgeMenuType.create(BlockfactoryMenu::new));
	public static final RegistryObject<MenuType<ChillerBlockGUIMenu>> CHILLER_BLOCK_GUI = MENUS.register("chiller_block_gui", () -> IForgeMenuType.create(ChillerBlockGUIMenu::new));
	public static void register(IEventBus eventBus) {
		MENUS.register(eventBus);
	}
}
