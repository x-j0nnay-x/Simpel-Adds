package net.x_j0nnay_x.simpeladd.core;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;

import java.util.function.UnaryOperator;

public class ModDataComponentTypesFabric {


    public static final DataComponentType<BlockPos> HOMEWAND_COMPNENTS = register(ModNames.DataComponentTypes.HOMEWAND_COMPNENTS, builder -> builder.persistent(BlockPos.CODEC));

    private static <T> DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, name),
                builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void modDataComonet(){
        SimpelAddMod.LOG.info("Registering Mod Data Components for " + SimpelAddMod.MOD_NAME);}
}
