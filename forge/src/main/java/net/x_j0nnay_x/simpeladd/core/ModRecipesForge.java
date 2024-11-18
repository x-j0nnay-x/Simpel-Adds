package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.SimpelAddModForge;
import net.x_j0nnay_x.simpeladd.recipe.GrinderRecipe;
import net.x_j0nnay_x.simpeladd.recipe.ManualGrind;
import net.x_j0nnay_x.simpeladd.recipe.SimpelCraftingRepair;

public class ModRecipesForge {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SimpelAddMod.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPE =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, SimpelAddMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<GrinderRecipe>> GRINDER_RECIPE_SERIALIZER =
            SERIALIZERS.register(GrinderRecipe.GrinderSerializer.IDNAME, () -> GrinderRecipe.GrinderSerializer.INSTANCE);
    public static final RegistryObject<RecipeType<GrinderRecipe>> GRINDER_RECIPE_TYPE =
            TYPE.register(GrinderRecipe.GrinderType.IDNAME, () -> GrinderRecipe.GrinderType.INSTANCE);

    public static final RegistryObject<RecipeSerializer<ManualGrind>> MANUAL_GRINDER_RECIPE_SERIALIZER =
            SERIALIZERS.register(ManualGrind.ManualGrindSerializer.IDNAME, () -> ManualGrind.ManualGrindSerializer.INSTANCE);
    public static final RegistryObject<RecipeType<ManualGrind>> MANUAL_GRINDER_RECIPE_TYPE =
            TYPE.register(ManualGrind.ManualGrindType.IDNAME, () -> ManualGrind.ManualGrindType.INSTANCE);


    public static final RegistryObject<RecipeSerializer<SimpelCraftingRepair>> SIMPELCRAFTING_REPAIR =
            SERIALIZERS.register(SimpelCraftingRepair.IDNAME, () -> new SimpleCraftingRecipeSerializer<>(SimpelCraftingRepair::new));

    public static void register(IEventBus eventBus) {
        SimpelAddMod.modRecipeRegText();
        SERIALIZERS.register(eventBus);
        TYPE.register(eventBus);
    }

}
