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
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.recipe.Grinder_Recipe;
import x_j0nnay_x.simpeladdmod.init.Mod_Constants;
import java.util.ArrayList;
import java.util.List;

public class JEI_Grinding_Catagory implements IRecipeCategory<RecipeHolder<Grinder_Recipe>> {

    //this is for arraylist
    ItemStack Grinder = Mod_Registrys.Mod_Items.GRINDER_HEAD.toStack();
    ItemStack GrinderN = Mod_Registrys.Mod_Items.GRINDER_HEAD_NETHERITE.toStack();
    ItemStack GrinderU = Mod_Registrys.Mod_Items.GRINDER_HEAD_UNOBTIANIUM.toStack();

    private final Component title;
    private final IDrawable overlay;
    private final IDrawable icon;

    public static final int width = 81;
    public static final int height = 50;

    public static final Identifier TEXTURE = Mod_Constants.id("textures/screens/simpeladd_jei.png");


    public JEI_Grinding_Catagory(IGuiHelper helper) {
        this.title = Component.translatable("simpeladdmod.jei.grinder");
        this.overlay = helper.createDrawable(TEXTURE, 0, 0, width, height);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Mod_Registrys.Mod_Items.GRINDER_HEAD.get()));
    }

    @Override
    public IRecipeType<RecipeHolder<Grinder_Recipe>> getRecipeType() {
        return JEI_Mod_Recipe_Types.GRINDER;
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
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<Grinder_Recipe> recipe, IFocusGroup focuses) {
        List<ItemStack> grindersList = new ArrayList<>();
        grindersList.add(Grinder);
        grindersList.add(GrinderN);
        grindersList.add(GrinderU);
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 10, 7).addItemStacks(grindersList);
        builder.addSlot(RecipeIngredientRole.INPUT, 10, 26).add(recipe.value().getRecipeItems());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 55, 17).add(recipe.value().getOutput());
    }
    @Override
    public void draw(RecipeHolder<Grinder_Recipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        this.overlay.draw(guiGraphics, 0, 0);
    }

}