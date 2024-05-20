package net.x_j0nnay_x.simpeladdmod.recipe;


import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;

public class ModRecipes {

    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Simpeladd.MOD_ID, GrinderRecipe.Serializer.ID),
                GrinderRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(Simpeladd.MOD_ID, GrinderRecipe.Type.ID),
                GrinderRecipe.Type.INSTANCE);
    }

}
