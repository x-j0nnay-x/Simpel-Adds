package net.x_j0nnay_x.simpeladd;

import net.x_j0nnay_x.simpeladd.platform.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpelAddMod {

    public static final String MOD_ID = "simpeladdmod";
    public static final String MOD_NAME = "SimpelAdd";
    public static final String MODCUSTOM = MOD_ID + ":";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static final String LOGNAME = "Simpel add mod log name";
    public static final String LOG_REGISTER_PREFIX = "Register " + LOGNAME;

    public static void init() {
        LOG.info("Hello from Common init on {}! we are currently in a {} environment!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());
        if (Services.PLATFORM.isModLoaded(MOD_ID)) {
            LOG.info("Hello to SimpelAdd");
        }
    }
}