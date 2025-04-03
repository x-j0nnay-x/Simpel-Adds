package net.x_j0nnay_x.simpeladd.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

public interface CropGrowthRecipesBuilder extends Recipe<CropGrothRecipeInput> {

    boolean isCropInput(ItemStack var1);

    boolean isSoilInput(ItemStack var1);
}
