package x_j0nnay_x.simpeladdmod;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import x_j0nnay_x.simpeladdmod.services.NeoForgeRegistryHelper;

@Mod(Constants.MOD_ID)
public class SimpelAddModNeoForge {

    public SimpelAddModNeoForge(IEventBus eventBus) {

        Constants.LOG.info("Hello NeoForge world!");
        CommonClass.init();
        NeoForgeRegistryHelper.register(eventBus);

    }
}