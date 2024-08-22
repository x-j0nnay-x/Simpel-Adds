package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;
import net.x_j0nnay_x.simpeladd.recipe.GrinderRecipe;

import java.util.function.Supplier;

public class ModRecipesNeoForge {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, SimpelAddModNeoForge.MODID);

    public static final Supplier<RecipeSerializer<GrinderRecipe>> GRINDER_RECIPE_SERIALIZER =
            SERIALIZERS.register("grinder", () -> GrinderRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
    public static void registerModRecipes(){
        SimpelAddModNeoForge.LOGGER.info("Registering Custom Recipes for " + SimpelAddModNeoForge.MODID);
    }
}
