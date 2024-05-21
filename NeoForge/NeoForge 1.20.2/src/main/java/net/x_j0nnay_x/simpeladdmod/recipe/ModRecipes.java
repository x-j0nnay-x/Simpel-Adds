package net.x_j0nnay_x.simpeladdmod.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.*;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.screen.grinder.GrinderMenu;


public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, GrinderRecipe.ID.getNamespace() );
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, GrinderRecipe.ID.getNamespace());

    public static final DeferredHolder<RecipeSerializer<?>, GrinderRecipe.Serializer> GRINDER_RECIPE_SERIALIZER = SERIALIZERS.register(GrinderRecipe.Serializer.ID,
            () -> GrinderRecipe.Serializer.INSTANCE);
    public static final DeferredHolder<RecipeType<?>, GrinderRecipe.Type> GRINDER_RECIPE_TYPE = TYPES.register(GrinderRecipe.Type.ID,
            () -> GrinderRecipe.Type.INSTANCE);

    public static void register(IEventBus eventBus) {

        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);

    }
}