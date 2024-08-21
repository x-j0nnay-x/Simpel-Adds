package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;
import net.x_j0nnay_x.simpeladd.recipe.GrindFactoryRecipe;
import net.x_j0nnay_x.simpeladd.recipe.GrinderRecipe;
import net.x_j0nnay_x.simpeladd.recipe.GrinderRepair;
import net.x_j0nnay_x.simpeladd.recipe.ManualGrind;
import java.util.function.Supplier;

public class ModRecipesNeoForge {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, SimpelAddModNeoForge.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPE =
            DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, SimpelAddModNeoForge.MODID);

    public static final Supplier<RecipeSerializer<GrinderRecipe>> GRINDER_RECIPE_SERIALIZER =
            SERIALIZERS.register(GrinderRecipe.Serializer.ID, () -> GrinderRecipe.Serializer.INSTANCE);
    public static final Supplier<RecipeType<GrinderRecipe>> GRINDER_RECIPE_TYPE=
            TYPE.register(GrinderRecipe.Type.ID, () -> GrinderRecipe.Type.INSTANCE);

    public static final Supplier<RecipeSerializer<GrindFactoryRecipe>> GRINDFACTORY_RECIPE_SERIALIZER =
            SERIALIZERS.register(GrindFactoryRecipe.Serializer.ID, () -> GrindFactoryRecipe.Serializer.INSTANCE);
    public static final Supplier<RecipeType<GrindFactoryRecipe>> GRINDFACTORY_RECIPE_TYPE =
            TYPE.register(GrindFactoryRecipe.Type.ID, () -> GrindFactoryRecipe.Type.INSTANCE);

    public static final Supplier<RecipeSerializer<ManualGrind>> MANUAL_GRINDER_RECIPE_SERIALIZER =
            SERIALIZERS.register(ManualGrind.Serializer.ID, () -> ManualGrind.Serializer.INSTANCE);
    public static final Supplier<RecipeType<ManualGrind>> MANUAL_GRINDER_RECIPE_TYPE =
            TYPE.register(ManualGrind.Type.ID, () -> ManualGrind.Type.INSTANCE);

    public static final Supplier<RecipeSerializer<GrinderRepair>> GRINDER_REPAIR_RECIPE_SERIALIZER =
            SERIALIZERS.register(GrinderRepair.ID, () -> new SimpleCraftingRecipeSerializer<>(GrinderRepair::new));

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPE.register(eventBus);
    }

    public static void registerModRecipes(){
        SimpelAddModNeoForge.LOGGER.info("Registering Custom Recipes for " + SimpelAddModNeoForge.MODID);
    }
}
