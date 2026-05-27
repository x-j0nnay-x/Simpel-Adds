package x_j0nnay_x.simpeladdmod.compat.jei;

import mezz.jei.api.gui.builder.IIngredientAcceptor;
import mezz.jei.api.gui.ingredient.IRecipeSlotDrawable;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.extensions.vanilla.smithing.ISmithingCategoryExtension;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import x_j0nnay_x.simpeladdmod.recipe.Blank_Upgrade_Recipe;


public class JEI_Blank_Upgrade_Category implements ISmithingCategoryExtension<Blank_Upgrade_Recipe> {

    public JEI_Blank_Upgrade_Category() {
    }

    @Override
    public <T extends IIngredientAcceptor<T>> void setBase(Blank_Upgrade_Recipe recipe, T ingredientAcceptor) {

    }

    @Override
    public <T extends IIngredientAcceptor<T>> void setAddition(Blank_Upgrade_Recipe recipe, T ingredientAcceptor) {
        Ingredient ingredient = recipe.getAddition();
        ingredientAcceptor.add(ingredient);
    }

    @Override
    public <T extends IIngredientAcceptor<T>> void setTemplate(Blank_Upgrade_Recipe recipe, T ingredientAcceptor) {
        Ingredient ingredient = recipe.getTemplate();
        ingredientAcceptor.add(ingredient);
    }

    @Override
    public <T extends IIngredientAcceptor<T>> void setOutput(Blank_Upgrade_Recipe recipe, T ingredientAcceptor) {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel level = minecraft.level;
        assert level != null;
        RegistryAccess registryAccess = level.registryAccess();
        ItemStack resultItem = recipe.getResultItem(registryAccess);
        ingredientAcceptor.add(resultItem);
    }

    @Override
    public void onDisplayedIngredientsUpdate(Blank_Upgrade_Recipe recipe, IRecipeSlotDrawable templateSlot, IRecipeSlotDrawable baseSlot, IRecipeSlotDrawable additionSlot, IRecipeSlotDrawable outputSlot, IFocusGroup focuses) {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel level = minecraft.level;
        assert level != null;
        RegistryAccess registryAccess = level.registryAccess();

        SmithingRecipeInput input = new SmithingRecipeInput(
                templateSlot.getDisplayedItemStack().orElse(ItemStack.EMPTY),
                baseSlot.getDisplayedItemStack().orElse(ItemStack.EMPTY),
                additionSlot.getDisplayedItemStack().orElse(ItemStack.EMPTY)
        );
        ItemStack result = recipe.assemble(input);
        outputSlot.createDisplayOverrides().add(result);
    }
}