package x_j0nnay_x.simpeladdmod.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;
import x_j0nnay_x.simpeladdmod.init.Mod_Constants;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.recipe.Crop_Growth_Recipe;


public class JEI_Crop_Growth_Catagory implements IRecipeCategory<RecipeHolder<Crop_Growth_Recipe>> {


    private final Component title;
    private final IDrawable overlay;
    private final IDrawable icon;

    public static final int width = 81;
    public static final int height = 50;

    public static final Identifier TEXTURE = Mod_Constants.id("textures/screens/simpeladd_jei.png");


    public JEI_Crop_Growth_Catagory(IGuiHelper helper) {
        this.title = Component.translatable("simpeladdmod.jei.crop_growth");
        this.overlay = helper.createDrawable(TEXTURE, 0, 0, width, height);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Mod_Registrys.Mod_Blocks.CROP_GROWTH_BLOCK.get()));
    }

    @Override
    public IRecipeType<RecipeHolder<Crop_Growth_Recipe>> getRecipeType() {
        return JEI_Mod_Recipe_Types.CROP_GROWTH;
    }

    @Override
    public @NotNull Component getTitle() {
        return this.title;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }


    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<Crop_Growth_Recipe> recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 10, 7).add(recipe.value().getRecipeItems());
        builder.addSlot(RecipeIngredientRole.INPUT, 10, 26).add(recipe.value().getSoil());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 55, 17).add(recipe.value().getOutput());
    }
    @Override
    public void draw(RecipeHolder<Crop_Growth_Recipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        this.overlay.draw(guiGraphics, 0, 0);
    }

}