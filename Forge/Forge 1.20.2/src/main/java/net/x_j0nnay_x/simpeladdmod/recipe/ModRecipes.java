package net.x_j0nnay_x.simpeladdmod.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;

import java.util.function.Supplier;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Simpeladd.MOD_ID);

    public static final RegistryObject<RecipeSerializer<GrinderRecipe>> GRINDER_RECIPE_SERIALIZER =
            SERIALIZERS.register("grinder", () -> GrinderRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);

    }
}
