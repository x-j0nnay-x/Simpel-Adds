package net.x_j0nnay_x.simpeladd.platform;


import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.x_j0nnay_x.simpeladd.platform.services.IPlatformHelper;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }
}