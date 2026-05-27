package x_j0nnay_x.simpeladdmod.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.category.extensions.vanilla.smithing.IExtendableSmithingRecipeCategory;
import mezz.jei.api.registration.*;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.menu.screen.Crop_Growth_Screen;
import x_j0nnay_x.simpeladdmod.menu.screen.Grinder_Netherite_Screen;
import x_j0nnay_x.simpeladdmod.menu.screen.Grinder_Screen;
import x_j0nnay_x.simpeladdmod.recipe.Blank_Upgrade_Recipe;
import x_j0nnay_x.simpeladdmod.init.Mod_Constants;


import java.util.List;

@JeiPlugin
public class JEI_Plugin_Simpeladd implements IModPlugin {
    private static RecipeMap syncedRecipes = RecipeMap.EMPTY;
    @Override
    public Identifier getPluginUid() {
        return Mod_Constants.id("_jei_plugin");
    }

    // From Occultism
    // Under MIT License
    @SuppressWarnings({"unchecked", "rawtypes"})
    private <I extends RecipeInput, T extends Recipe<I>> List<RecipeHolder<T>> getRecipes(RecipeMap recipeMap, RecipeType<T> type) {
        return (List) recipeMap.byType(type);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new JEI_Grinding_Catagory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new JEI_Crop_Growth_Catagory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(JEI_Mod_Recipe_Types.GRINDER, this.getRecipes(syncedRecipes, Mod_Registrys.Mod_Recipes.GRINDER_RECIPE_TYPES.get()));
        registration.addRecipes(JEI_Mod_Recipe_Types.CROP_GROWTH, this.getRecipes(syncedRecipes, Mod_Registrys.Mod_Recipes.CROP_GROWTH_RECIPE_TYPES.get()));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(Grinder_Screen.class, 58, 42, 55,23, JEI_Mod_Recipe_Types.GRINDER);
        registration.addRecipeClickArea(Grinder_Netherite_Screen.class, 58, 34, 65,13, JEI_Mod_Recipe_Types.GRINDER);
        registration.addRecipeClickArea(Crop_Growth_Screen.class, 31, 25, 10, 40, JEI_Mod_Recipe_Types.CROP_GROWTH);
    }
    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(JEI_Mod_Recipe_Types.GRINDER, new ItemStack(Mod_Registrys.Mod_Items.GRINDER_HEAD.get()));
        registration.addCraftingStation(JEI_Mod_Recipe_Types.GRINDER, new ItemStack(Mod_Registrys.Mod_Items.GRINDER_HEAD_NETHERITE.get()));
        registration.addCraftingStation(JEI_Mod_Recipe_Types.GRINDER, new ItemStack(Mod_Registrys.Mod_Items.GRINDER_HEAD_UNOBTIANIUM.get()));
        registration.addCraftingStation(JEI_Mod_Recipe_Types.GRINDER, new ItemStack(Mod_Registrys.Mod_Blocks.GRINDER_BLOCK.asItem()));
        registration.addCraftingStation(JEI_Mod_Recipe_Types.GRINDER, new ItemStack(Mod_Registrys.Mod_Blocks.GRINDER_NETHERITE_BLOCK.asItem()));
        registration.addCraftingStation(JEI_Mod_Recipe_Types.CROP_GROWTH, new ItemStack(Mod_Registrys.Mod_Blocks.CROP_GROWTH_BLOCK.asItem()));
    }

    @Override
    public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
        IExtendableSmithingRecipeCategory smithingRecipeCategory = registration.getSmithingCategory();
        smithingRecipeCategory.addExtension(Blank_Upgrade_Recipe.class, new JEI_Blank_Upgrade_Category());

    }

    @EventBusSubscriber(modid = Mod_Constants.MOD_ID)
    public static class ServerRecipeSync {
        @SubscribeEvent
        public static void onDatapackSync(OnDatapackSyncEvent event) {
            event.sendRecipes(
                    Mod_Registrys.Mod_Recipes.GRINDER_RECIPE_TYPES.get(),
                    Mod_Registrys.Mod_Recipes.CROP_GROWTH_RECIPE_TYPES.get()
            );
        }
    }

    @EventBusSubscriber(modid = Mod_Constants.MOD_ID, value = Dist.CLIENT)
    public static class ClientRecipeSync {
        @SubscribeEvent
        public static void onRecipeReceived(RecipesReceivedEvent event) {
            syncedRecipes = event.getRecipeMap();
        }
    }


}
