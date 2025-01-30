package net.x_j0nnay_x.simpeladd.core;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;

import net.minecraft.util.ExtraCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;

import java.util.function.UnaryOperator;

public class ModDataComponentTypesNeoForge {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, SimpelAddMod.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> HOMEWAND_COMPNENTS = register(ModNames.DataComponentTypes.HOMEWAND_COMPNENTS,
            builder -> builder.persistent(BlockPos.CODEC));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> XP_CRYSTAL_LEVEL = register(ModNames.DataComponentTypes.XP_CRYSTAL_LEVEL,
            builder -> builder.persistent(Codec.INT));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> XP_CRYSTAL_PROGRESS = register(ModNames.DataComponentTypes.XP_CRYSTAL_PROGRESS,
            builder -> builder.persistent(Codec.FLOAT));


    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        SimpelAddMod.modDataComonet();
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
