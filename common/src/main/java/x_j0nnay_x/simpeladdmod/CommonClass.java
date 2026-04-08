package x_j0nnay_x.simpeladdmod;

import x_j0nnay_x.simpeladdmod.init.Mod_Blocks;
import x_j0nnay_x.simpeladdmod.init.Mod_Items;
import x_j0nnay_x.simpeladdmod.services.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;

public class CommonClass {

    public static void init() {

        Constants.LOG.info("This is Common init on {}! we are currently in a {} environment!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());

        if (Services.PLATFORM.isModLoaded(Constants.MOD_ID)) {
            Constants.LOG.info("Welcome to " + Constants.MOD_NAME);
        }

        Mod_Blocks.load();
        Mod_Items.load();
    }
}