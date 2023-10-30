package net.x_j0nnay_x.simpeladdmod.until;


import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.x_j0nnay_x.simpeladdmod.world.features.ores.deepslate_debri_ore;
import net.x_j0nnay_x.simpeladdmod.simpeladdmod;

@Mod.EventBusSubscriber
public class simpeladdmodFeatures {
    public static final DeferredRegister<Feature<?>> MODFEATUR = DeferredRegister.create(ForgeRegistries.FEATURES, simpeladdmod.MOD_ID);
    public static final RegistryObject<Feature<?>> DEEPSLATE_DEBRI_ORE = MODFEATUR.register("deepslate_debri_ore", deepslate_debri_ore::new);

    public static void register(IEventBus eventBus){
        MODFEATUR.register(eventBus);
    }
}



