package net.x_j0nnay_x.simpeladdmod.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.block.ModBlocks;

import net.x_j0nnay_x.simpeladdmod.recipe.GrinderRecipeold;


public class GrinderCategory implements IRecipeCategory<GrinderRecipeold> {
    public static final ResourceLocation UID = new ResourceLocation(Simpeladd.MOD_ID, "grinder");
    public static final ResourceLocation TEXTURE = new ResourceLocation(Simpeladd.MOD_ID,
            "textures/screens/sprites/jei/grinder.png");
    public static final RecipeType<GrinderRecipeold> GRINDER_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, GrinderRecipeold.class);

    private final IDrawable background;
    private final IDrawable icon;


    public GrinderCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 78);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.GRINDER_BLOCK.get()));
    }

    @Override
    public RecipeType<GrinderRecipeold> getRecipeType() {
        return GRINDER_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.simpeladdmod.grinder_block");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, GrinderRecipeold recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 34, 44).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 124, 44).addItemStack(recipe.getResultItem(null));
    }
}