package net.x_j0nnay_x.simpeladd;

import net.minecraft.resources.ResourceLocation;
import net.x_j0nnay_x.simpeladd.core.ModNames;
import net.x_j0nnay_x.simpeladd.platform.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpelAddMod {

    public static final String MOD_ID = ModNames.MOD_ID;
    public static final String MOD_NAME = ModNames.MOD_NAME;
    public static final String MODCUSTOM = MOD_ID + ":";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static final String LOGNAME = "Simpel add mod log name";
    public static final String LOG_REGISTER_PREFIX = "Register " + LOGNAME;

    public static void init() {
        LOG.info("Hello from Common init on {}! we are currently in a {} environment!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());
        if (Services.PLATFORM.isModLoaded(MOD_ID)) {
            LOG.info("Hello from SimpelAdd");
        }
    }
    public static void modBlockRegText(){
        LOG.info("Registering Mod Blocks for " + MOD_NAME);
    }
    public static void modBlockEntRegText(){
        LOG.info("Registering Mod Blocks Entities for " + MOD_NAME);
    }
    public static void modBlockCapablityRegText(){
        LOG.info("Registering Mod Block Capabilities for " + MOD_NAME);
    }
    public static void modItemRegText(){
        LOG.info("Registering Mod Items for " + MOD_NAME);
    }
    public static void modScreenRegText(){
        LOG.info("Registering Mod screens for " + MOD_NAME);
    }
    public static void modMenuRegText(){
        LOG.info("Registering Mod Menus for " + MOD_NAME);
    }
    public static void modRecipeRegText(){
        LOG.info("Registering Mod recipes for " + MOD_NAME);
    }
    public static void modtabRegText(){
        LOG.info("Registering Mod creative Tab for " + MOD_NAME);
    }

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }
}