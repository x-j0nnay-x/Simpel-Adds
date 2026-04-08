package x_j0nnay_x.simpeladdmod;

import net.fabricmc.api.ModInitializer;

public class SimpelAddModFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {

        Constants.LOG.info("Hello Fabric world!");
        CommonClass.init();
    }
}
