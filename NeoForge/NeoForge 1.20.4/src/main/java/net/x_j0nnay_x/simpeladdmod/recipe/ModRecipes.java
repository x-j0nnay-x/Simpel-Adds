package net.x_j0nnay_x.simpeladdmod.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.data.internal.NeoForgeRecipeProvider;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.callback.RegistryCallback;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;

import java.util.function.Supplier;

public class ModRecipes {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Simpeladd.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.
                    NeoForgeRegistries.CONDITION_SERIALIZERS
                    ForgeRegistries.RECIPE_SERIALIZERS, Simpeladd.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Simpeladd.MOD_ID);

    public static final RegistryObject<RecipeSerializer<GrinderRecipe>> GRINDER_RECIPE_SERIALIZER = SERIALIZERS.register("grinder",
            () -> GrinderRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeType<GrinderRecipe>> GRINDER_RECIPE_TYPE = TYPES.register("grinder",
            () -> GrinderRecipe.Type.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);

    }
}

