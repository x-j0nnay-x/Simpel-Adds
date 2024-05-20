package net.x_j0nnay_x.simpeladdmod;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.x_j0nnay_x.simpeladdmod.screen.BlockFactory.BlockFactoryScreen;
import net.x_j0nnay_x.simpeladdmod.screen.Chiller.ChillerMenu;
import net.x_j0nnay_x.simpeladdmod.screen.Chiller.ChillerScreen;
import net.x_j0nnay_x.simpeladdmod.screen.Furnace_Up.FrunaceScreen_up;
import net.x_j0nnay_x.simpeladdmod.screen.ModMenuType;
import net.x_j0nnay_x.simpeladdmod.screen.NetheriteCrafter.NetheriteCrafterScreen;
import net.x_j0nnay_x.simpeladdmod.screen.StoneSifter.StoneSifterScreen;
import net.x_j0nnay_x.simpeladdmod.screen.grinder.GrinderScreen;
import net.x_j0nnay_x.simpeladdmod.screen.grinder_up.GrinderScreen_up;

public class SimpeladdClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModMenuType.BLOCKFACTORY_MENU, BlockFactoryScreen::new);
        HandledScreens.register(ModMenuType.Chiller_MENU, ChillerScreen::new);
        HandledScreens.register(ModMenuType.GRINDER_MENU, GrinderScreen::new);
        HandledScreens.register(ModMenuType.GRINDER_MENU_UP, GrinderScreen_up::new);
        HandledScreens.register(ModMenuType.Netherite_Menu, NetheriteCrafterScreen::new);
        HandledScreens.register(ModMenuType.STONESIFTER_MENU, StoneSifterScreen::new);
        HandledScreens.register(ModMenuType.UPGRADED_FURNACE_MENU, FrunaceScreen_up::new);
    }
}
