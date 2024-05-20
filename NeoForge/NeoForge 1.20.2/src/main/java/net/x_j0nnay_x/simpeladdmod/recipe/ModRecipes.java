package net.x_j0nnay_x.simpeladdmod.recipe;


import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import net.neoforged.neoforge.common.data.internal.NeoForgeRecipeProvider;
import net.neoforged.neoforge.registries.*;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;



public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(
                    ForgeRegistries.RECIPE_SERIALIZERS, Simpeladd.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(
                    ForgeRegistries.RECIPE_TYPES, Simpeladd.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<GrinderRecipe>> GRINDER_RECIPE_SERIALIZER = SERIALIZERS.register("grinder",
            () -> GrinderRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeType<GrinderRecipe>> GRINDER_RECIPE_TYPE = TYPES.register("grinder",
            () -> GrinderRecipe.Type.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);

    }
}
