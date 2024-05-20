package net.x_j0nnay_x.simpeladdmod.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.*;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;



public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Simpeladd.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, Simpeladd.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, GrinderRecipe.Serializer> GRINDER_RECIPE_SERIALIZER = SERIALIZERS.register("grinder",
            () -> GrinderRecipe.Serializer.INSTANCE);
    public static final DeferredHolder<RecipeType<?>, GrinderRecipe.Type> GRINDER_RECIPE_TYPE = TYPES.register("grinder",
            () -> GrinderRecipe.Type.INSTANCE);

    public static void register(IEventBus eventBus) {

        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);

    }
}