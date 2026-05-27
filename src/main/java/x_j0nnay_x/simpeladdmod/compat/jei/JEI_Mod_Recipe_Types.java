package x_j0nnay_x.simpeladdmod.compat.jei;

import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import x_j0nnay_x.simpeladdmod.recipe.Crop_Growth_Recipe;
import x_j0nnay_x.simpeladdmod.recipe.Grinder_Recipe;
import x_j0nnay_x.simpeladdmod.init.Mod_Constants;

public class JEI_Mod_Recipe_Types {

    public static final IRecipeType<RecipeHolder<Grinder_Recipe>> GRINDER =
            create(Mod_Constants.MOD_ID, Mod_Constants.getMenuName(Mod_Constants.Blocks.GRINDER), Grinder_Recipe.class);
    public static final IRecipeType<RecipeHolder<Crop_Growth_Recipe>> CROP_GROWTH =
            create(Mod_Constants.MOD_ID, Mod_Constants.getMenuName(Mod_Constants.Blocks.CROP_GROWTH), Crop_Growth_Recipe.class);



    // From Occultism: https://github.com/klikli-dev/occultism/blob/version/26.1.2/src/main/java/com/klikli_dev/occultism/integration/jei/impl/JeiRecipeTypes.java
    // Under MIT-License
    public static <R extends Recipe<?>> IRecipeType<RecipeHolder<R>> create(String modid, String name, Class<? extends R> recipeClass) {
        Identifier uid = Identifier.fromNamespaceAndPath(modid, name);
        @SuppressWarnings({"unchecked", "RedundantCast"})
        Class<? extends RecipeHolder<R>> holderClass = (Class<? extends RecipeHolder<R>>) (Object) RecipeHolder.class;
        return IRecipeType.create(uid, holderClass);
    }
}
