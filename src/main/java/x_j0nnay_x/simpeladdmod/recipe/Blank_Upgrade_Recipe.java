package x_j0nnay_x.simpeladdmod.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;

import java.util.Optional;

public class Blank_Upgrade_Recipe implements SmithingRecipe {
    private final Ingredient template;
    private final Optional<Ingredient> base;
    private final Ingredient addition;
    private final ItemStackTemplate  result;

    public Blank_Upgrade_Recipe(Ingredient template, Optional<Ingredient> base, Ingredient addition, ItemStackTemplate result) {
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    @Override
    public boolean matches(SmithingRecipeInput input, Level level) {
        return this.template.test(input.template())  && this.addition.test(input.addition());
    }

    @Override
    public ItemStack assemble(SmithingRecipeInput smithingRecipeInput) {
        return this.result.create();
    }

    public ItemStackTemplate  getResult() {
        return this.result;
    }

    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return result.create();
    }

    public Ingredient getTemplate() {
        return template;
    }

    public Optional<Ingredient> getBase() {
        return base;
    }

    public Ingredient getAddition() {
        return addition;
    }

    @Override
    public Optional<Ingredient> templateIngredient() {
        return Optional.of(template);
    }

    @Override
    public Ingredient baseIngredient() {
        return base.orElse(template);
    }

    @Override
    public Optional<Ingredient> additionIngredient() {
        return addition.isEmpty() ? Optional.empty() : Optional.of(addition);
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public String group() {
        return "Blank Upgrade Smithing";
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public RecipeSerializer<? extends SmithingRecipe> getSerializer() {
        return Mod_Registrys.Mod_Recipes.BLANK_UPGRADE_SMITHING_SERIALIZER.get();
    }

    public static final MapCodec<Blank_Upgrade_Recipe> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            Ingredient.CODEC.fieldOf("template").forGetter(r -> r.template),
                            Ingredient.CODEC.optionalFieldOf("base").forGetter(r -> r.base),
                            Ingredient.CODEC.fieldOf("addition").forGetter(r -> r.addition),
                            ItemStackTemplate.CODEC.fieldOf("result").forGetter(r -> r.result)
                    )
                    .apply(instance, Blank_Upgrade_Recipe::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, Blank_Upgrade_Recipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, Blank_Upgrade_Recipe::getTemplate,
            Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC, Blank_Upgrade_Recipe::getBase,
            Ingredient.CONTENTS_STREAM_CODEC, Blank_Upgrade_Recipe::getAddition,
            ItemStackTemplate.STREAM_CODEC, Blank_Upgrade_Recipe::getResult,
            Blank_Upgrade_Recipe::new
    );


}