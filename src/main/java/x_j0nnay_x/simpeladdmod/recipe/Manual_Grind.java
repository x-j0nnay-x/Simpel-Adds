package x_j0nnay_x.simpeladdmod.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.init.Mod_Tags;
import x_j0nnay_x.simpeladdmod.items.Grinder_Head_Item;
import java.util.ArrayList;
import java.util.List;

public class Manual_Grind implements CraftingRecipe {

    final ItemStackTemplate result;
    final List<Ingredient> ingredients;
    private final boolean isSimple;

    public Manual_Grind( ItemStackTemplate result, List<Ingredient> input) {
        this.result = result;
        this.ingredients = input;
        this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
    }


    @Override
    public RecipeSerializer<? extends CraftingRecipe> getSerializer() {
        return Mod_Registrys.Mod_Recipes.MANUAL_GRIND_SERIALIZER.get();
    }

    @Override
    public CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(this.ingredients);
    }

   

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (input.ingredientCount() != this.ingredients.size()) {
            return false;
        } else if (!this.isSimple) {
            ArrayList<ItemStack> nonEmptyItems = new ArrayList(input.ingredientCount());
            for(ItemStack item : input.items()) {
                if (!item.isEmpty()) {
                    nonEmptyItems.add(item);
                }
            }

            return RecipeMatcher.findMatches(nonEmptyItems, this.ingredients) != null;
        } else {
            return input.size() == 1 && this.ingredients.size() == 1 ? ((Ingredient)this.ingredients.getFirst()).test(input.getItem(0)) : input.stackedContents().canCraft(this, (StackedContents.Output)null);
        }
    }

    @Override
    public ItemStack assemble(CraftingInput craftingInput) {
        for(ItemStack item : craftingInput.items()) {
            if (item.is(Mod_Tags.Items.GRINDERS)) {
                if (Grinder_Head_Item.canUse(item)) {
                    return this.result.create();
                }
            }
        }
            return ItemStack.EMPTY;
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "Manual Grind";
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.result.create();
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> result = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        for(int slot = 0; slot < result.size(); ++slot) {
            ItemStack item = input.getItem(slot);
            ItemStackTemplate remainder = input.getItem(slot).getCraftingRemainder();
            result.set(slot, remainder != null ? remainder.create() : ItemStack.EMPTY);
        }
        return result;
    }

    public ItemStackTemplate getResultTemplate() {
        return this.result;
    }

    public List<RecipeDisplay> display() {
        return List.of(new ShapelessCraftingRecipeDisplay(this.ingredients.stream().map(Ingredient::display).toList(), new SlotDisplay.ItemStackSlotDisplay(this.result), new SlotDisplay.ItemSlotDisplay(Items.CRAFTING_TABLE)));
    }

    public static final MapCodec<Manual_Grind> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Codec.lazyInitialized(() -> Ingredient.CODEC.listOf(1, ShapedRecipePattern.getMaxHeight() * ShapedRecipePattern.getMaxWidth())).fieldOf("ingredients").forGetter((o) -> o.ingredients),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter((o) -> o.result)
            ).apply(instance, (ingredients, result) -> new Manual_Grind(result, ingredients))
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, Manual_Grind> STREAM_CODEC = StreamCodec.composite(
            ItemStackTemplate.STREAM_CODEC, (o) -> o.result,
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), (o) -> o.ingredients,
            Manual_Grind::new);


}


