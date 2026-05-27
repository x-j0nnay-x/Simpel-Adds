package x_j0nnay_x.simpeladdmod.recipe;


import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;


public record  Grinder_Recipe(Ingredient recipeItems, ItemStackTemplate output) implements Recipe<SingleRecipeInput> {


    @Override
    public boolean matches(SingleRecipeInput inv, Level pLevel) {
        if (pLevel.isClientSide()) {
            return false;
        }
        return recipeItems.test(inv.item());
    }

    @Override
    public ItemStack assemble(SingleRecipeInput simpleContainer) {
        return this.output.create();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "Grinder";
    }

    public Ingredient getRecipeItems() {
        return recipeItems;
    }

    public ItemStackTemplate getOutput() {
        return output;
    }

    @Override
    public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
        return Mod_Registrys.Mod_Recipes.GRINDER_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return Mod_Registrys.Mod_Recipes.GRINDER_RECIPE_TYPES.get();
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public static final MapCodec<Grinder_Recipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                            Ingredient.CODEC.fieldOf("ingredients").forGetter(recipe -> recipe.recipeItems),
                            ItemStackTemplate.CODEC.fieldOf("result").forGetter(recipe -> recipe.output)
                    ).apply(instance, Grinder_Recipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, Grinder_Recipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, Grinder_Recipe::getRecipeItems,
            ItemStackTemplate.STREAM_CODEC, Grinder_Recipe::output,
            Grinder_Recipe::new
    );

}


