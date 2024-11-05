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
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import net.x_j0nnay_x.simpeladd.recipe.GrinderRecipe;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class JEI_GrindingCatagory_Forge implements IRecipeCategory<GrinderRecipe> {

    //this is for arraylist
    ItemStack Grinder = ModItems.GRINDERHEAD.getDefaultInstance();
    ItemStack GrinderN = ModItems.GRINDERHEADNEHTERITE.getDefaultInstance();
    ItemStack GrinderU = ModItems.GRINDERHEADUNOBTIANIUM.getDefaultInstance();

    private final Component title;
    private final IDrawable background;
    private final IDrawable icon;

    public static final int width = 176;
    public static final int height = 78;

    public static final ResourceLocation TEXTURE = new ResourceLocation(SimpelAddMod.MOD_ID, "textures/screens/grinding_jei.png");

    public static final RecipeType<GrinderRecipe> GRINDER_RECIPE_RECIPE_TYPE = RecipeType.create(SimpelAddMod.MOD_ID, GrinderRecipe.GrinderType.IDNAME, GrinderRecipe.class);

    public JEI_GrindingCatagory_Forge(IGuiHelper helper) {
        this.title = Component.translatable("simpeladdmod.jei.grinder");
        this.background = helper.createDrawable(TEXTURE, 0, 0, width, height);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModItems.GRINDERHEAD));
    }

    @Override
    public @NotNull RecipeType<GrinderRecipe> getRecipeType() {
        return GRINDER_RECIPE_RECIPE_TYPE;
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
    public void setRecipe(IRecipeLayoutBuilder builder, GrinderRecipe recipe, IFocusGroup focuses) {
        List<ItemStack> grindersList = new ArrayList<>();
        grindersList.add(Grinder);
        grindersList.add(GrinderN);
        grindersList.add(GrinderU);
        builder.addSlot(RecipeIngredientRole.CATALYST, 79, 17).addItemStacks(grindersList);
        builder.addSlot(RecipeIngredientRole.INPUT, 34, 44).addIngredients(recipe.getInputItems().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124, 44).addItemStack(recipe.getOutput());
    }

    public static List<GrinderRecipe> getAllRecipes() {
        var minecraft = Minecraft.getInstance();
        var level = minecraft.level;
        assert level != null;
        var recipeManager = level.getRecipeManager();

        return recipeManager.getAllRecipesFor(GrinderRecipe.GrinderType.INSTANCE);
    }
}