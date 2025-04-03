package net.x_j0nnay_x.simpeladd.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.x_j0nnay_x.simpeladd.core.ModNames;

public class CropGrowthRecipe implements CropGrowthRecipesBuilder {

    private final ItemStack output;
    private final Ingredient recipeItems;
    private final Ingredient soil;



    public CropGrowthRecipe(Ingredient inputItems, Ingredient soil, ItemStack itemStack) {
        this.output = itemStack;
        this.recipeItems = inputItems;
        this.soil = soil;

    }

    @Override
    public boolean matches(CropGrothRecipeInput inv, Level pLevel) {
        return this.soil.test(inv.soil()) && this.recipeItems.test(inv.crop());
    }

    @Override
    public ItemStack assemble(CropGrothRecipeInput simpleContainer, HolderLookup.Provider provider) {
        return this.output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output.copy();
    }


    public ItemStack getOutput() {
        return output.copy();
    }


    public Ingredient getRecipeItems() {
        return recipeItems;
    }
    public Ingredient getRecipeSoil() {
        return soil;
    }

    @Override
    public  RecipeSerializer<?> getSerializer() {
        return CropGrowthSerializer.INSTANCE;
    }

    @Override
    public  RecipeType<?> getType() {
        return CropGrowthType.INSTANCE;
    }

    @Override
    public boolean isCropInput(ItemStack var1) {
        return this.recipeItems.test(var1);
    }

    @Override
    public boolean isSoilInput(ItemStack var1) {
        return this.soil.test(var1);
    }

    public static class CropGrowthType implements RecipeType<CropGrowthRecipe> {

        private CropGrowthType() {}

        public static final CropGrowthType INSTANCE = new CropGrowthType();

        public static final String ID = ModNames.Recipe.CropGrowth;
    }

    public static class CropGrowthSerializer implements RecipeSerializer<CropGrowthRecipe> {

        private CropGrowthSerializer() {}

        public static final CropGrowthSerializer INSTANCE = new CropGrowthSerializer();

        public static final String ID = ModNames.Recipe.CropGrowth;

        private static final MapCodec<ItemStack> RESULT_CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                BuiltInRegistries.ITEM.holderByNameCodec().fieldOf("item").orElse(BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR)).forGetter(ItemStack::getItemHolder),
                ExtraCodecs.POSITIVE_INT.optionalFieldOf("count", 1).forGetter(ItemStack::getCount),
                DataComponentPatch.CODEC.optionalFieldOf("components", DataComponentPatch.EMPTY).forGetter(ItemStack::getComponentsPatch)
        ).apply(instance, ItemStack::new));

        private static final MapCodec<CropGrowthRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Ingredient.CODEC.fieldOf("ingredients").forGetter(recipe -> recipe.recipeItems),
                Ingredient.CODEC.fieldOf("soil").forGetter(recipe -> recipe.soil),
                RESULT_CODEC.fieldOf("output").forGetter(recipe -> recipe.output)
        ).apply(instance, CropGrowthRecipe::new));

        private static final StreamCodec<RegistryFriendlyByteBuf, CropGrowthRecipe> STREAM_CODEC = StreamCodec.of(
                CropGrowthSerializer::toNetwork,
                CropGrowthSerializer::fromNetwork
        );

        @Override
        public MapCodec<CropGrowthRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CropGrowthRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        public static CropGrowthRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
            final var ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            final var soil = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            final var resultItem = ItemStack.OPTIONAL_STREAM_CODEC.decode(buf);

            return new CropGrowthRecipe(ingredient, soil, resultItem);
        }

        public static void toNetwork(RegistryFriendlyByteBuf buf, CropGrowthRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.recipeItems);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.soil);
            ItemStack.OPTIONAL_STREAM_CODEC.encode(buf, recipe.output);
        }
    }
}
