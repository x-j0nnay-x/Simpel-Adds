package x_j0nnay_x.simpeladdmod.recipe;


import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.renderer.item.properties.numeric.Count;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.recipe.util.Crop_Groth_Recipe_Input;
import x_j0nnay_x.simpeladdmod.recipe.util.Crop_Growth_Recipes_Builder;

public record Crop_Growth_Recipe(Ingredient recipeItems, Ingredient soil, ItemStackTemplate output) implements Crop_Growth_Recipes_Builder {

    @Override
    public boolean isCropInput(ItemStack var1) {
        return this.recipeItems.test(var1);
    }

    @Override
    public boolean isSoilInput(ItemStack var1) {
        return this.soil.test(var1);
    }

    @Override
    public boolean matches(Crop_Groth_Recipe_Input cropGrothRecipeInput, Level level) {
        return this.soil.test(cropGrothRecipeInput.soil()) && this.recipeItems.test(cropGrothRecipeInput.crop());
    }

    @Override
    public ItemStack assemble(Crop_Groth_Recipe_Input cropGrothRecipeInput) {
        return this.output.create();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public RecipeSerializer<? extends Recipe<Crop_Groth_Recipe_Input>> getSerializer() {
        return Mod_Registrys.Mod_Recipes.CROP_GROWTH_RECIPE.get();
    }

    @Override
    public RecipeType<? extends Recipe<Crop_Groth_Recipe_Input>> getType() {
        return Mod_Registrys.Mod_Recipes.CROP_GROWTH_RECIPE_TYPES.get();
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public Ingredient getRecipeItems() {
        return recipeItems;
    }

    public Ingredient getSoil(){
        return soil;
    }

    public ItemStackTemplate getOutput() {
        return output;
    }

    public static final MapCodec<Crop_Growth_Recipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Ingredient.CODEC.fieldOf("ingredients").forGetter(recipe -> recipe.recipeItems),
            Ingredient.CODEC.fieldOf("soil").forGetter(recipe -> recipe.soil),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(recipe -> recipe.output)
    ).apply(instance, Crop_Growth_Recipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, Crop_Growth_Recipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, Crop_Growth_Recipe::recipeItems,
            Ingredient.CONTENTS_STREAM_CODEC, Crop_Growth_Recipe::soil,
            ItemStackTemplate.STREAM_CODEC, Crop_Growth_Recipe::output,
            Crop_Growth_Recipe::new
    );
}


