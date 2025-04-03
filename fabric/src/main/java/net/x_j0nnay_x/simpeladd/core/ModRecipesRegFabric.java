package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.recipe.*;
import net.x_j0nnay_x.simpeladd.SimpelAddModFabric;

public class ModRecipesRegFabric {

    public static void registerRecipes() {
        SimpelAddMod.modRecipeRegText();

        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,  ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, GrinderRecipe.GrinderSerializer.ID),
                GrinderRecipe.GrinderSerializer.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_TYPE,  ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, GrinderRecipe.GrinderType.ID),
                GrinderRecipe.GrinderType.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,  ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ManualGrind.Serializer.ID),
                ManualGrind.Serializer.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_TYPE,  ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ManualGrind.Type.ID),
                ManualGrind.Type.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,  ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, CropGrowthRecipe.CropGrowthSerializer.ID),
                CropGrowthRecipe.CropGrowthSerializer.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_TYPE,  ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, CropGrowthRecipe.CropGrowthType.ID),
                CropGrowthRecipe.CropGrowthType.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, SimpelCraftingRepair.ID),
                new SimpleCraftingRecipeSerializer<>(SimpelCraftingRepair::new));


    }
}
