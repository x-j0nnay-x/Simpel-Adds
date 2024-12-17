package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.client.multiplayer.chat.LoggedChatMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;

import java.util.function.UnaryOperator;
import java.util.logging.Level;

public class ModDataComponentTypesForge {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, SimpelAddMod.MOD_ID);

    public static final RegistryObject<DataComponentType<BlockPos>> HOMEWAND_COMPNENTS = register(ModNames.DataComponentTypes.HOMEWAND_COMPNENTS,
            builder -> builder.persistent(BlockPos.CODEC));





    private static <T>RegistryObject<DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        SimpelAddMod.modDataComonet();
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
