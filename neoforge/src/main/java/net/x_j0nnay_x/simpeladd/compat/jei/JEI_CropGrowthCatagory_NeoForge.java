package net.x_j0nnay_x.simpeladd.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModBlockRegNeoForge;
import net.x_j0nnay_x.simpeladd.recipe.CropGrowthRecipe;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class JEI_CropGrowthCatagory_NeoForge implements IRecipeCategory<RecipeHolder<CropGrowthRecipe>> {

    private final Component title;
    private final IDrawable background;
    private final IDrawable icon;

    public static final int width = 81;
    public static final int height = 50;

    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, "textures/screens/simpeladd_jei.png");

    public static final RecipeType<RecipeHolder<CropGrowthRecipe>> CROP_GROWTH_RECIPE_RECIPE_TYPE = RecipeType.createFromVanilla(CropGrowthRecipe.CropGrowthType.INSTANCE);

    public JEI_CropGrowthCatagory_NeoForge(IGuiHelper helper) {
        this.title = Component.translatable("simpeladdmod.jei.crop_growth");
        this.background = helper.createDrawable(TEXTURE, 0, 0, width, height);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlockRegNeoForge.CROP_GROWTH));
    }

    @Override
    public @NotNull RecipeType<RecipeHolder<CropGrowthRecipe>> getRecipeType() {
        return CROP_GROWTH_RECIPE_RECIPE_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return this.title;
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
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<CropGrowthRecipe> recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.CATALYST, 10, 26).addIngredients(recipe.value().getRecipeSoil());
        builder.addSlot(RecipeIngredientRole.INPUT, 10, 7).addIngredients(recipe.value().getRecipeItems());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 55, 17).addItemStack(recipe.value().getOutput());
    }

    public static List<RecipeHolder<CropGrowthRecipe>> getAllRecipes() {
        var minecraft = Minecraft.getInstance();
        var level = minecraft.level;
        assert level != null;
        var recipeManager = level.getRecipeManager();

        return recipeManager.getAllRecipesFor(CropGrowthRecipe.CropGrowthType.INSTANCE);
    }
}