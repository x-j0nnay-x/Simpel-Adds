package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;
import net.x_j0nnay_x.simpeladd.recipe.*;
import java.util.function.Supplier;

public class ModRecipesNeoForge {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, SimpelAddMod.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPE =
            DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, SimpelAddMod.MOD_ID);

    public static final Supplier<RecipeSerializer<GrinderRecipe>> GRINDER_RECIPE_SERIALIZER =
            SERIALIZERS.register(GrinderRecipe.GrinderSerializer.ID, () -> GrinderRecipe.GrinderSerializer.INSTANCE);
    public static final Supplier<RecipeType<GrinderRecipe>> GRINDER_RECIPE_TYPE=
            TYPE.register(GrinderRecipe.GrinderType.ID, () -> GrinderRecipe.GrinderType.INSTANCE);

    public static final Supplier<RecipeSerializer<ManualGrind>> MANUAL_GRINDER_RECIPE_SERIALIZER =
            SERIALIZERS.register(ManualGrind.Serializer.ID, () -> ManualGrind.Serializer.INSTANCE);
    public static final Supplier<RecipeType<ManualGrind>> MANUAL_GRINDER_RECIPE_TYPE =
            TYPE.register(ManualGrind.Type.ID, () -> ManualGrind.Type.INSTANCE);

    public static final Supplier<RecipeSerializer<SimpelCraftingRepair>> SIMPEL_REPAIR_RECIPE_SERIALIZER =
            SERIALIZERS.register(SimpelCraftingRepair.ID, () -> new SimpleCraftingRecipeSerializer<>(SimpelCraftingRepair::new));

    public static void register(IEventBus eventBus) {
        SimpelAddMod.modRecipeRegText();
        SERIALIZERS.register(eventBus);
        TYPE.register(eventBus);
    }

}
