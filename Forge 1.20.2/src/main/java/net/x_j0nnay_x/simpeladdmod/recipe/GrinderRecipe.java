package net.x_j0nnay_x.simpeladdmod.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.*;
import net.minecraft.Util;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ConditionCodec;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import net.x_j0nnay_x.simpeladdmod.simpeladdmod;

public class GrinderRecipe implements Recipe<SimpleContainer> {

    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;

    public GrinderRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        }

        return inputItems.get(0).test(pContainer.getItem(0));
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    public ResourceLocation getId() {
        return id;
    }


    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<GrinderRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "grinder";
    }

    public static class Serializer implements RecipeSerializer<GrinderRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(simpeladdmod.MOD_ID, "grinder");


        public GrinderRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"), pSerializedRecipe.isJsonArray());
            String s = GsonHelper.getAsString(pSerializedRecipe, "type");
            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.of((ItemLike) ingredients.getAsJsonArray().get(i)));
            }
            Codec<? extends Recipe<?>> codec = BuiltInRegistries.RECIPE_SERIALIZER.getOptional(new ResourceLocation(s)).orElseThrow(() -> {
                return new JsonSyntaxException("Invalid or unsupported recipe type '" + s + "'");
            }).codec();
            return new GrinderRecipe(inputs, output, pRecipeId);
        }



        @Override
        public Codec<GrinderRecipe> codec() {
            return new Codec<GrinderRecipe>() {
                @Override
                public <T> DataResult<Pair<GrinderRecipe, T>> decode(DynamicOps<T> ops, T input) {
                    throw new UnsupportedOperationException("ConditionRecipe.CODEC does not support encoding");
                }

                @Override
                public <T> DataResult<T> encode(GrinderRecipe input, DynamicOps<T> ops, T prefix) {
                    var context = ConditionCodec.getContext(ops);
                    var json = new Dynamic<T>(ops, (T) input).convert(JsonOps.INSTANCE).getValue();
                    try {
                        var recipes = GsonHelper.getAsJsonArray(GsonHelper.convertToJsonObject(json.getAsJsonObject(), "root"), "recipes");
                        int idx = 0;
                        for (var entry : recipes) {
                            var object = GsonHelper.convertToJsonObject(entry, "recipe[" + idx++ + "]");
                            var conditionjson = GsonHelper.getAsJsonObject(object, ICondition.DEFAULT_FIELD);
                            var condition = Util.getOrThrow(ICondition.SAFE_CODEC.parse(JsonOps.INSTANCE, conditionjson), JsonSyntaxException::new);
                            if (!condition.test(context))
                                continue;
                            var type = new ResourceLocation(GsonHelper.getAsString(object, "type"));
                            var serializer = ForgeRegistries.RECIPE_SERIALIZERS.getValue(type);
                            if (serializer == null)
                                return DataResult.error(() -> "Invalid or unsupported recipe type '" + type + "' Conditions were successful, but unknown type");
                            var parsed = serializer.codec().parse(JsonOps.INSTANCE, object);
                            if (parsed.error().isPresent())
                                return DataResult.error(parsed.error().get()::message);
                            else if (!parsed.result().isPresent())
                                return DataResult.error(() -> "Recipe passed all conditions but did not parse a valid return");
                            else
                                return (DataResult<T>) DataResult.success(Pair.of(parsed.result().get(), ops.empty()));
                        }
                        return DataResult.error(() -> "No recipe passed conditions, if this is the case, you should have an outer condition.");
                    } catch (JsonSyntaxException e) {
                        return DataResult.error(() -> e.getMessage());
                    }
                }
            }.stable();
        }

        @Override
        public @Nullable GrinderRecipe fromNetwork(FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack output = pBuffer.readItem();
            ResourceLocation pRecipeId = ID;
            return new GrinderRecipe(inputs, output, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, GrinderRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.inputItems.size());

            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
        }
    }
}