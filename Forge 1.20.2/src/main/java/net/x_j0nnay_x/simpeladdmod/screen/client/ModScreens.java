package net.x_j0nnay_x.simpeladdmod.screen.client;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;
import net.x_j0nnay_x.simpeladdmod.screen.client.gui.BlockfactoryScreen;
import net.x_j0nnay_x.simpeladdmod.screen.client.gui.ChillerBlockGUIScreen;
import net.x_j0nnay_x.simpeladdmod.screen.client.gui.GrinderGuiScreen;
import net.x_j0nnay_x.simpeladdmod.world.ModMenus;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(ModMenus.GRINDER_GUI.get(), GrinderGuiScreen::new);
			MenuScreens.register(ModMenus.BLOCKFACTORY.get(), BlockfactoryScreen::new);
			MenuScreens.register(ModMenus.CHILLER_BLOCK_GUI.get(), ChillerBlockGUIScreen::new);
		});
	}
}
