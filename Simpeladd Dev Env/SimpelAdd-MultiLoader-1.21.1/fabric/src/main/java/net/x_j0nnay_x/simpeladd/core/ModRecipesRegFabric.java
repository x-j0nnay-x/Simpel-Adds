package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.x_j0nnay_x.simpeladd.recipe.*;
import net.x_j0nnay_x.simpeladd.SimpelAddModFabric;

public class ModRecipesRegFabric {

    public static void registerRecipes() {

        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,  ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, GrinderRecipe.Serializer.ID),
                GrinderRecipe.Serializer.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_TYPE,  ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, GrinderRecipe.Type.ID),
                GrinderRecipe.Type.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,  ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, GrindFactoryRecipe.Serializer.ID),
                GrindFactoryRecipe.Serializer.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_TYPE,  ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, GrindFactoryRecipe.Type.ID),
                GrindFactoryRecipe.Type.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,  ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, ManualGrind.Serializer.ID),
                ManualGrind.Serializer.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_TYPE,  ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, ManualGrind.Type.ID),
                ManualGrind.Type.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, SimpelCraftingRepair.ID),
                new SimpleCraftingRecipeSerializer<>(SimpelCraftingRepair::new));

    }
}
