package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.x_j0nnay_x.simpeladd.SimpelAddModForge;
import net.x_j0nnay_x.simpeladd.recipe.GrinderRecipe;
import net.x_j0nnay_x.simpeladd.recipe.GrinderRepair;

import net.x_j0nnay_x.simpeladd.recipe.ManualGrind;

public class ModRecipesForge {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SimpelAddModForge.MODID);

    public static final RegistryObject<RecipeSerializer<GrinderRecipe>> GRINDER_RECIPE_SERIALIZER =
            SERIALIZERS.register(GrinderRecipe.Serializer.ID, () -> GrinderRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<ManualGrind>> MANUAL_GRINDER_RECIPE_SERIALIZER =
            SERIALIZERS.register(ManualGrind.Serializer.ID, () -> ManualGrind.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<GrinderRepair>> GRINDER_REPAIR_RECIPE_SERIALIZER =
            SERIALIZERS.register(GrinderRepair.ID, () -> new SimpleCraftingRecipeSerializer<>(GrinderRepair::new));


    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
    public static void registerModRecipes(){
        SimpelAddModForge.LOGGER.info("Registering Custom Recipes for " + SimpelAddModForge.MODID);
    }
}
