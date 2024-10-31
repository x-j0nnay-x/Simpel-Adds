package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.x_j0nnay_x.simpeladd.recipe.GrinderRecipe;
import net.x_j0nnay_x.simpeladd.recipe.ManualGrind;
import net.x_j0nnay_x.simpeladd.recipe.SimpelCraftingRepair;

public class ModRecipesRegFabric {

    public static void registerRecipes() {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, GrinderRecipe.GrinderSerializer.ID, GrinderRecipe.GrinderSerializer.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_TYPE,  GrinderRecipe.GrinderType.IDNAME, GrinderRecipe.GrinderType.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ManualGrind.ManualGrindSerializer.ID, ManualGrind.ManualGrindSerializer.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_TYPE,  ManualGrind.ManualGrindType.IDNAME, ManualGrind.ManualGrindType.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, SimpelCraftingRepair.ID, new SimpleCraftingRecipeSerializer<>(SimpelCraftingRepair::new));
    }
}
