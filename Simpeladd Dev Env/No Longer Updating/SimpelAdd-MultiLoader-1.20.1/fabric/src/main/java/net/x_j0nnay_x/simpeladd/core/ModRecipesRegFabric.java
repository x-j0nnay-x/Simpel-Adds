package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.x_j0nnay_x.simpeladd.recipe.GrindFactoryRecipe;
import net.x_j0nnay_x.simpeladd.recipe.GrinderRecipe;
import net.x_j0nnay_x.simpeladd.SimpelAddModFabric;
import net.x_j0nnay_x.simpeladd.recipe.SimpelCraftingRepair;

public class ModRecipesRegFabric {
    public static void registerRecipes() {

        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, new ResourceLocation(SimpelAddModFabric.MODID, GrinderRecipe.Serializer.IDNAME),
                GrinderRecipe.Serializer.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_TYPE, new ResourceLocation(SimpelAddModFabric.MODID, GrinderRecipe.Type.ID),
                GrinderRecipe.Type.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, new ResourceLocation(SimpelAddModFabric.MODID, GrindFactoryRecipe.Serializer.IDNAME),
                GrinderRecipe.Serializer.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_TYPE, new ResourceLocation(SimpelAddModFabric.MODID, GrindFactoryRecipe.Type.ID),
                GrinderRecipe.Type.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, new ResourceLocation(SimpelAddModFabric.MODID, SimpelCraftingRepair.ID),
                GrinderRecipe.Serializer.INSTANCE);
    }
}
