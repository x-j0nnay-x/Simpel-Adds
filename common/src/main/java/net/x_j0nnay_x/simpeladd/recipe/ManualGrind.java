package net.x_j0nnay_x.simpeladd.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.x_j0nnay_x.simpeladd.core.ModNames;
import net.x_j0nnay_x.simpeladd.core.ModTags;
import net.x_j0nnay_x.simpeladd.item.GrinderHeadItem;

import java.util.Iterator;

public class ManualGrind implements CraftingRecipe {

    final ItemStack result;
    final String group;
    final CraftingBookCategory category;
    final NonNullList<Ingredient> ingredients;

    public ManualGrind(String group, CraftingBookCategory category, ItemStack result, NonNullList<Ingredient> input) {
        this.result = result;
        this.group = group;
        this.category = category;
        this.ingredients = input;
    }


    @Override
    public CraftingBookCategory category() {
        return this.category;
    }

    public String getGroup() {
        return this.group;
    }

    @Override
    public boolean matches(CraftingInput $$0, Level $$1) {
        if ($$0.ingredientCount() != this.ingredients.size()) {
            return false;
        } else {
            return $$0.size() == 1 && this.ingredients.size() == 1 ? ((Ingredient)this.ingredients.getFirst()).test($$0.getItem(0)) : $$0.stackedContents().canCraft(this, (IntList)null);
        }
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider $$1) {
        return this.getResultItem($$1).copy();
    }

    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public boolean canCraftInDimensions(int $$0, int $$1) {
        return $$0 * $$1 >= this.ingredients.size();
    }


    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.result;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput $$0) {
        NonNullList<ItemStack> $$1 = NonNullList.withSize($$0.size(), ItemStack.EMPTY);
        ItemStack grinder = null;
        for (int i = 0; i < $$0.size(); ++i) {
        ItemStack itemstack = $$0.getItem(i);
        if (!itemstack.isEmpty()) {
            if (itemstack.is(ModTags.Items.GRINDERS)) {
                if (grinder == null) {
                    grinder = itemstack;
                }
            }
        }
        }
        for(int $$2 = 0; $$2 < $$1.size(); ++$$2) {
            Item $$3 = $$0.getItem($$2).getItem();
            if ($$3.getDefaultInstance().is(ModTags.Items.GRINDERS)) {
                if(grinder.getDamageValue() < grinder.getMaxDamage()) {
                    $$1.set($$2, GrinderHeadItem.brakeItem(grinder));
                }
            }
        }

        return $$1;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }


    public static class Type implements RecipeType<ManualGrind> {

        private Type() {}

        public static final Type INSTANCE = new Type();

        public static final String ID = ModNames.Recipe.MANUALGRIND;
    }
    public static class Serializer implements RecipeSerializer<ManualGrind> {

        public static final Serializer INSTANCE = new Serializer();

        public static final String ID = ModNames.Recipe.MANUALGRIND;

        private static final MapCodec<ManualGrind> CODEC = RecordCodecBuilder.mapCodec(($$0) -> {
            return $$0.group(Codec.STRING.optionalFieldOf("group", "").forGetter(($$0x) -> {
                return $$0x.group;
            }), CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(($$0x) -> {
                return $$0x.category;
            }), ItemStack.STRICT_CODEC.fieldOf("result").forGetter(($$0x) -> {
                return $$0x.result;
            }), Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").flatXmap(($$0x) -> {
                Ingredient[] $$1 = (Ingredient[])$$0x.stream().filter(($$0xx) -> {
                    return !$$0xx.isEmpty();
                }).toArray(($$0xx) -> {
                    return new Ingredient[$$0xx];
                });
                if ($$1.length == 0) {
                    return DataResult.error(() -> {
                        return "No ingredients for Manual Grind recipe";
                    });
                } else {
                    return $$1.length > 9 ? DataResult.error(() -> {
                        return "Too many ingredients for Manual Grind recipe";
                    }) : DataResult.success(NonNullList.of(Ingredient.EMPTY, $$1));
                }
            }, DataResult::success).forGetter(($$0x) -> {
                return $$0x.ingredients;
            })).apply($$0, ManualGrind::new);
        });

        public static final StreamCodec<RegistryFriendlyByteBuf, ManualGrind> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

        public Serializer() {
        }

        public MapCodec<ManualGrind> codec() {
            return CODEC;
        }

        public StreamCodec<RegistryFriendlyByteBuf, ManualGrind> streamCodec() {
            return STREAM_CODEC;
        }

        private static ManualGrind fromNetwork(RegistryFriendlyByteBuf $$0) {
            String $$1 = $$0.readUtf();
            CraftingBookCategory $$2 = (CraftingBookCategory)$$0.readEnum(CraftingBookCategory.class);
            int $$3 = $$0.readVarInt();
            NonNullList<Ingredient> $$4 = NonNullList.withSize($$3, Ingredient.EMPTY);
            $$4.replaceAll(($$1x) -> {
                return (Ingredient)Ingredient.CONTENTS_STREAM_CODEC.decode($$0);
            });
            ItemStack $$5 = (ItemStack)ItemStack.STREAM_CODEC.decode($$0);
            return new ManualGrind($$1, $$2, $$5, $$4);
        }

        private static void toNetwork(RegistryFriendlyByteBuf $$0, ManualGrind $$1) {
            $$0.writeUtf($$1.group);
            $$0.writeEnum($$1.category);
            $$0.writeVarInt($$1.ingredients.size());
            Iterator var2 = $$1.ingredients.iterator();

            while(var2.hasNext()) {
                Ingredient $$2 = (Ingredient)var2.next();
                Ingredient.CONTENTS_STREAM_CODEC.encode($$0, $$2);
            }

            ItemStack.STREAM_CODEC.encode($$0, $$1.result);
        }
    }
}


