package net.x_j0nnay_x.simpeladd.recipe;


import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.x_j0nnay_x.simpeladd.core.ModTags;
import net.x_j0nnay_x.simpeladd.item.GrinderHeadItem;


public class ManualGrind implements CraftingRecipe {
    final ShapedRecipePattern pattern;
    final ItemStack result;

    final String group;
    final CraftingBookCategory category;
    final boolean showNotification;

    public ManualGrind(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack result, boolean showNotification) {
        this.pattern = pattern;
        this.result = result;
        this.group = group;
        this.category = category;
        this.showNotification = showNotification;
    }
    public ManualGrind(String $$0, CraftingBookCategory $$1, ShapedRecipePattern $$2, ItemStack $$3) {
        this($$0, $$1, $$2, $$3, true);
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
        return this.pattern.matches($$0);
    }
    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider $$1) {
        return this.getResultItem($$1).copy();
    }
    public NonNullList<Ingredient> getIngredients() {
        return this.pattern.ingredients();
    }
    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return i >= this.pattern.width() && i1 >= this.pattern.height();
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
                $$1.set($$2, GrinderHeadItem.getRemainderItem(grinder));
            }
        }

        return $$1;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }
    public int getWidth() {
        return this.pattern.width();
    }

    public int getHeight() {
        return this.pattern.height();
    }


    public static class Type implements RecipeType<ManualGrind> {
        private Type() {}
        public static final ManualGrind.Type INSTANCE = new ManualGrind.Type();
        public static final String ID = "manualgrind";
    }
    public static class Serializer implements RecipeSerializer<ManualGrind> {
        public static final ManualGrind.Serializer INSTANCE = new ManualGrind.Serializer();
        public static final String ID = "manualgrind";

        public static final MapCodec<ManualGrind> CODEC = RecordCodecBuilder.mapCodec(($$0) -> {
            return $$0.group(Codec.STRING.optionalFieldOf("group", "").forGetter(($$0x) -> {
                return $$0x.group;
            }), CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(($$0x) -> {
                return $$0x.category;
            }), ShapedRecipePattern.MAP_CODEC.forGetter(($$0x) -> {
                return $$0x.pattern;
            }), ItemStack.STRICT_CODEC.fieldOf("result").forGetter(($$0x) -> {
                return $$0x.result;
            }), Codec.BOOL.optionalFieldOf("show_notification", true).forGetter(($$0x) -> {
                return $$0x.showNotification;
            })).apply($$0, ManualGrind::new);
        });
        public static final StreamCodec<RegistryFriendlyByteBuf, ManualGrind> STREAM_CODEC = StreamCodec.of(ManualGrind.Serializer::toNetwork, ManualGrind.Serializer::fromNetwork);

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
            ShapedRecipePattern $$3 = (ShapedRecipePattern)ShapedRecipePattern.STREAM_CODEC.decode($$0);
            ItemStack $$4 = (ItemStack)ItemStack.STREAM_CODEC.decode($$0);
            boolean $$5 = $$0.readBoolean();
            return new ManualGrind($$1, $$2, $$3, $$4, $$5);
        }

        private static void toNetwork(RegistryFriendlyByteBuf $$0, ManualGrind $$1) {
            $$0.writeUtf($$1.group);
            $$0.writeEnum($$1.category);
            ShapedRecipePattern.STREAM_CODEC.encode($$0, $$1.pattern);
            ItemStack.STREAM_CODEC.encode($$0, $$1.result);
            $$0.writeBoolean($$1.showNotification);
        }
    }
}


