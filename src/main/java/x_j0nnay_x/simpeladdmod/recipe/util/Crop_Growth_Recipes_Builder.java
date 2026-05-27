package x_j0nnay_x.simpeladdmod.recipe.util;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

public interface Crop_Growth_Recipes_Builder extends Recipe<Crop_Groth_Recipe_Input> {

    boolean isCropInput(ItemStack var1);

    boolean isSoilInput(ItemStack var1);
}
