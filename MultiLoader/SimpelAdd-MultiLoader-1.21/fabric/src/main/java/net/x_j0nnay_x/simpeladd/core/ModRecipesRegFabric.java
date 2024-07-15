package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.x_j0nnay_x.simpeladd.recipe.GrinderRecipe;
import net.x_j0nnay_x.simpeladd.SimpelAddModFabric;

public class ModRecipesRegFabric {
    public static void registerRecipes() {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,  ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, GrinderRecipe.Serializer.ID),
                GrinderRecipe.Serializer.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_TYPE,  ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, GrinderRecipe.Type.ID),
                GrinderRecipe.Type.INSTANCE);
    }
}
