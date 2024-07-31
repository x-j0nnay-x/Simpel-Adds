package net.x_j0nnay_x.simpeladd.recipe;


import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;


public class GrindFactoryRecipe implements Recipe<SingleRecipeInput> {
    public static final ResourceLocation ID =  ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, "grindfactory");
    private final ItemStack output;
    private final Ingredient recipeItems;


    public GrindFactoryRecipe(Ingredient inputItems, ItemStack itemStack) {
        this.output = itemStack;
        this.recipeItems = inputItems;
    }

    @Override
    public boolean matches(SingleRecipeInput inv, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        }

        return recipeItems.test(inv.item());
    }

    @Override
    public ItemStack assemble(SingleRecipeInput simpleContainer, HolderLookup.Provider provider) {
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



    @Override
    public  RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public  RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<GrindFactoryRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "grindfactory";
    }

    public static class Serializer implements RecipeSerializer<GrindFactoryRecipe> {
        private Serializer() {}
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "grindfactory";


        private static final MapCodec<ItemStack> RESULT_CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                BuiltInRegistries.ITEM.holderByNameCodec().fieldOf("item")
                        .orElse(BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR))
                        .forGetter(ItemStack::getItemHolder),
                ExtraCodecs.POSITIVE_INT.fieldOf("count").orElse(2).forGetter(ItemStack::getCount),
                DataComponentPatch.CODEC.optionalFieldOf("components", DataComponentPatch.EMPTY).forGetter(ItemStack::getComponentsPatch)
        ).apply(instance, ItemStack::new));

        private static final MapCodec<GrindFactoryRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Ingredient.CODEC.fieldOf("ingredients").forGetter(recipe -> recipe.recipeItems),
                RESULT_CODEC.fieldOf("output").forGetter(recipe -> recipe.output)
        ).apply(instance, GrindFactoryRecipe::new));

        private static final StreamCodec<RegistryFriendlyByteBuf, GrindFactoryRecipe> STREAM_CODEC = StreamCodec.of(
                Serializer::toNetwork,
                Serializer::fromNetwork
        );



        @Override
        public MapCodec<GrindFactoryRecipe> codec() {
            return CODEC;
        }
        @Override
        public StreamCodec<RegistryFriendlyByteBuf, GrindFactoryRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        public static GrindFactoryRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
            final var ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            final var resultItem = ItemStack.OPTIONAL_STREAM_CODEC.decode(buf);
            return new GrindFactoryRecipe(ingredient, resultItem);
        }

        public static void toNetwork(RegistryFriendlyByteBuf buf, GrindFactoryRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.recipeItems);
            ItemStack.OPTIONAL_STREAM_CODEC.encode(buf, recipe.output);
        }
    }

}
