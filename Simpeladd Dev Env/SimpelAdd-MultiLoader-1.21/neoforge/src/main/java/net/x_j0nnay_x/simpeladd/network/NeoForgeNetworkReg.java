package net.x_j0nnay_x.simpeladd.network;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;

import java.util.function.Supplier;

public class NeoForgeNetworkReg {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, SimpelAddModNeoForge.MODID);

    public static final Supplier<DataComponentType<CustomData>> BLOCKFACTORYSLOTDATA = DATA_COMPONENT_TYPES.register("blockfactory_data_slot",
            () -> DataComponentType.<CustomData>builder()
                    .cacheEncoding()
                    .persistent(CustomData.CODEC)
                    .networkSynchronized(CustomData.STREAM_CODEC)
                    .build()
    );

    public static void  register(IEventBus eventBus){
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
