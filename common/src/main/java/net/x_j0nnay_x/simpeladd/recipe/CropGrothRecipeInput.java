package net.x_j0nnay_x.simpeladd.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record CropGrothRecipeInput(ItemStack crop, ItemStack soil) implements RecipeInput {
    public CropGrothRecipeInput(ItemStack crop, ItemStack soil) {
        this.crop = crop;
        this.soil = soil;
    }


    @Override
    public ItemStack getItem(int i) {
        ItemStack var10000;
        switch (i) {
            case 0 -> var10000 = this.crop;
            case 1 -> var10000 = this.soil;
            default -> throw new IllegalArgumentException("Recipe does not contain slot " + i);
        }

        return var10000;
    }

    public int size() {
        return 3;
    }

    public boolean isEmpty() {
        return this.crop.isEmpty() && this.soil.isEmpty();
    }

    public ItemStack crop() {
        return this.crop;
    }

    public ItemStack soil() {
        return this.soil;
    }

}
