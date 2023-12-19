package net.x_j0nnay_x.simpeladdmod.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import net.minecraft.util.ExtraCodecs;
import java.util.List;



public class GrinderRecipe implements Recipe<SimpleContainer> {
    public static final ResourceLocation ID = new ResourceLocation(Simpeladd.MOD_ID, "grinder");
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;


    public GrinderRecipe(NonNullList<Ingredient> inputItems, ItemStack itemStack) {
        this.output = itemStack;
        this.recipeItems = inputItems;
    }

    @Override
    public boolean matches(SimpleContainer inv, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        }

        return recipeItems.get(0).test(inv.getItem(0));
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull SimpleContainer pContainer, @NotNull RegistryAccess pRegistryAccess) {
        return this.output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    public ItemStack getOutput() {
        return output.copy();
    }


    @Override
    public  NonNullList<Ingredient> getIngredients() {
        return this.recipeItems;
    }


    @Override
    public  RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public  RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<GrinderRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "grinder";
    }

    public static class Serializer implements RecipeSerializer<GrinderRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "grinder";

        private static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max) {
            return ExtraCodecs.validate(ExtraCodecs.validate(
                    delegate.listOf(), list -> list.size() > max ? DataResult.error(() -> "Recipe has too many ingredients!") : DataResult.success(list)
            ), list -> list.isEmpty() ? DataResult.error(() -> "Recipe has no ingredients!") : DataResult.success(list));
        }
        public static final Codec<GrinderRecipe> CODEC = RecordCodecBuilder.create(in -> in.group(
                validateAmount(Ingredient.CODEC_NONEMPTY, 1).fieldOf("ingredients").forGetter(GrinderRecipe::getIngredients),
                CraftingRecipeCodecs.ITEMSTACK_OBJECT_CODEC.fieldOf("output").forGetter(r -> r.output)
        ).apply(in,  (ingredients, result) -> {
            var nnIngredients = NonNullList.<Ingredient>create();
            nnIngredients.addAll(ingredients);
            return new GrinderRecipe(nnIngredients, result);
        }));
        @Override
        public Codec<GrinderRecipe> codec() {
            return CODEC;
        }

        @Override
        public @Nullable GrinderRecipe fromNetwork(FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new GrinderRecipe(inputs, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, GrinderRecipe recipe) {
            buf.writeInt(recipe.recipeItems.size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buf);
            }

            buf.writeItemStack(recipe.getResultItem(null), false);

        }
    }







}
