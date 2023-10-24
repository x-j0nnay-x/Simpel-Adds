package net.x_j0nnay_x.simpeladdmod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.x_j0nnay_x.simpeladdmod.screen.grinder.GrinderScreen;
import net.x_j0nnay_x.simpeladdmod.simpeladdmod;
import net.x_j0nnay_x.simpeladdmod.recipe.GrinderRecipe;

import java.util.List;

@JeiPlugin
public class JEISimpeladdPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(simpeladdmod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new GrinderCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<GrinderRecipe> grinderrecipes = recipeManager.getAllRecipesFor(GrinderRecipe.Type.INSTANCE);
        registration.addRecipes(GrinderCategory.GRINDER_RECIPE_RECIPE_TYPE, grinderrecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(GrinderScreen.class, 58, 42, 55,23,
                GrinderCategory.GRINDER_RECIPE_RECIPE_TYPE);
    }
}
