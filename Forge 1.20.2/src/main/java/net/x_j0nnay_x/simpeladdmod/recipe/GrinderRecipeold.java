package net.x_j0nnay_x.simpeladdmod.recipe;

import com.mojang.serialization.Codec;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.block.entity.GrinderBlockEntity;

import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class GrinderRecipeold implements Recipe<SimpleContainer> {
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public GrinderRecipeold(NonNullList<Ingredient> ingredients, ItemStack itemStack) {
        this.output = itemStack;
        this.recipeItems = ingredients;
    }

    @Override
    public boolean matches(SimpleContainer inventory, Level world) {
        if(world.isClientSide()) {
            return false;
        }

        return recipeItems.get(0).test(inventory.getItem(GrinderBlockEntity.INPUTSLOT));
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }
    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<GrinderRecipeold> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "grinder";
    }

    public static class Serializer implements RecipeSerializer<GrinderRecipeold> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(Simpeladd.MOD_ID, "grinder");

        @Override
        public Codec<GrinderRecipeold> codec() {
            return null;
        }

        @Override
        public @Nullable GrinderRecipeold fromNetwork(FriendlyByteBuf pBuffer) {
            return null;
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, GrinderRecipeold pRecipe) {

        }


    /*    public static final Codec<GrinderRecipe> CODEC = RecordCodecBuilder.create(in -> in.group(
                validateAmount(Ingredient.CODEC_NONEMPTY, 9).fieldOf("ingredients").forGetter(GrinderRecipe::getIngredients),
                CraftingRecipeCodecs.ITEMSTACK_OBJECT_CODEC.fieldOf("output").forGetter(r -> r.output)
        ).and(INSTANCE, in));


        private static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max) {
            return GrinderRecipe.Serializer.validateAmount(GrinderRecipe.Serializer.validateAmount(
                    delegate.listOf(), list ->  NonNullList.create().size() > max ? DataResult.error(() -> "Recipe has too many Ingredients!") : DataResult.success(list)
            ), list -> NonNullList.create().isEmpty() ? DataResult.error(() -> "Recipe has no ingredients!") : DataResult.success(list));
    */

    }
    /*
        @Override
        public Codec<GrinderRecipe> codec() {
            return CODEC;
        }

        @Override
        public @Nullable GrinderRecipe fromNetwork(FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack output = pBuffer.readItem();
            return new GrinderRecipe(inputs, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, GrinderRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.getIngredients().size());

            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
        }
    }

     */
}