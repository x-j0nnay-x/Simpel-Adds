package net.x_j0nnay_x.simpeladd.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModNames;
import net.x_j0nnay_x.simpeladd.core.ModTags;
import net.x_j0nnay_x.simpeladd.item.GrinderHeadItem;

import java.util.Iterator;

public class ManualGrind implements CraftingRecipe {

    final ItemStack result;
    final String group;
    final CraftingBookCategory category;
    final NonNullList<Ingredient> ingredients;
    private final ResourceLocation id;

    public ManualGrind(String group, CraftingBookCategory category, ItemStack result, NonNullList<Ingredient> input, ResourceLocation id) {
        this.result = result;
        this.group = group;
        this.category = category;
        this.ingredients = input;
        this.id = id;
    }


    @Override
    public CraftingBookCategory category() {
        return this.category;
    }

    public String getGroup() {
        return this.group;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public boolean matches(CraftingContainer $$0, Level $$1) {
        StackedContents $$2 = new StackedContents();
        int $$3 = 0;

        for(int $$4 = 0; $$4 < $$0.getContainerSize(); ++$$4) {
            ItemStack $$5 = $$0.getItem($$4);
            if (!$$5.isEmpty()) {
                ++$$3;
                $$2.accountStack($$5, 1);
            }
        }

        return $$3 == this.ingredients.size() && $$2.canCraft(this, (IntList)null);
    }

    @Override
    public ItemStack assemble(CraftingContainer input,  RegistryAccess $$1) {
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
    public ItemStack getResultItem(RegistryAccess provider) {
        return this.result;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer $$0) {
        NonNullList<ItemStack> $$1 = NonNullList.withSize($$0.getContainerSize(), ItemStack.EMPTY);
        ItemStack grinder = null;
        for (int i = 0; i < $$0.getContainerSize(); ++i) {
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
                $$1.set($$2, GrinderHeadItem.damageItem(grinder));
            }
        }

        return $$1;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ManualGrindSerializer.INSTANCE;
    }


    public static class ManualGrindType implements RecipeType<ManualGrind> {
        private ManualGrindType(){}

        public static final ManualGrindType INSTANCE = new ManualGrindType();

        public static final String IDNAME = ModNames.Recipe.MANUALGRIND;
        public static final ResourceLocation ID = new ResourceLocation(SimpelAddMod.MOD_ID,  IDNAME);
    }
    public static class ManualGrindSerializer implements RecipeSerializer<ManualGrind> {
        private ManualGrindSerializer(){}

        public static final ManualGrindSerializer INSTANCE = new ManualGrindSerializer();

        public static final String IDNAME = ModNames.Recipe.MANUALGRIND;
        public static final ResourceLocation ID = new ResourceLocation(SimpelAddMod.MOD_ID, IDNAME);


        public ManualGrind fromJson(ResourceLocation $$0, JsonObject $$1) {
            String $$2 = GsonHelper.getAsString($$1, "group", "");
            CraftingBookCategory $$3 = (CraftingBookCategory)CraftingBookCategory.CODEC.byName(GsonHelper.getAsString($$1, "category", (String)null), CraftingBookCategory.MISC);
            NonNullList<Ingredient> $$4 = itemsFromJson(GsonHelper.getAsJsonArray($$1, "ingredients"));
            if ($$4.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else if ($$4.size() > 9) {
                throw new JsonParseException("Too many ingredients for shapeless recipe");
            } else {
                ItemStack $$5 = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject($$1, "result"));
                return new ManualGrind($$2, $$3, $$5, $$4, $$0);
            }
        }

        private static NonNullList<Ingredient> itemsFromJson(JsonArray $$0) {
            NonNullList<Ingredient> $$1 = NonNullList.create();

            for(int $$2 = 0; $$2 < $$0.size(); ++$$2) {
                Ingredient $$3 = Ingredient.fromJson($$0.get($$2), false);
                if (!$$3.isEmpty()) {
                    $$1.add($$3);
                }
            }

            return $$1;
        }

        public ManualGrind fromNetwork(ResourceLocation $$0, FriendlyByteBuf $$1) {
            String $$2 = $$1.readUtf();
            CraftingBookCategory $$3 = (CraftingBookCategory)$$1.readEnum(CraftingBookCategory.class);
            int $$4 = $$1.readVarInt();
            NonNullList<Ingredient> $$5 = NonNullList.withSize($$4, Ingredient.EMPTY);

            for(int $$6 = 0; $$6 < $$5.size(); ++$$6) {
                $$5.set($$6, Ingredient.fromNetwork($$1));
            }

            ItemStack $$7 = $$1.readItem();
            return new ManualGrind($$2, $$3, $$7, $$5, $$0);
        }

        public void toNetwork(FriendlyByteBuf $$0, ManualGrind $$1) {
            $$0.writeUtf($$1.group);
            $$0.writeEnum($$1.category);
            $$0.writeVarInt($$1.ingredients.size());
            Iterator var3 = $$1.ingredients.iterator();

            while(var3.hasNext()) {
                Ingredient $$2 = (Ingredient)var3.next();
                $$2.toNetwork($$0);
            }

            $$0.writeItem($$1.result);
        }
    }

}


