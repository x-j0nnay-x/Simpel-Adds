package net.x_j0nnay_x.simpeladd.compact;

import com.google.common.collect.ImmutableList;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeManager;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.SimpelAddModFabric;
import net.x_j0nnay_x.simpeladd.core.ModBlockRegFabric;
import net.x_j0nnay_x.simpeladd.recipe.GrinderRecipe;
import net.x_j0nnay_x.simpeladd.screens.FabricGrinderScreen;
import net.x_j0nnay_x.simpeladd.screens.FabricGrinderScreen_up;

import javax.annotation.Nonnull;

@JeiPlugin
public class FabricSimpeladdJEIPlugin implements IModPlugin {

    @Override
    @Nonnull
    public ResourceLocation getPluginUid() {
        return SimpelAddMod.id(SimpelAddModFabric.MODID + "plugin_jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new FabricGrinderCategory(registration.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public void registerRecipes(@Nonnull IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        registration.addRecipes(FabricGrinderCategory.TYPE, recipeManager.getAllRecipesFor(GrinderRecipe.Type.INSTANCE));

    //    registration.addItemStackInfo(new ItemStack(Items.WATER_BUCKET), Component.translatable("simpeladdmod.jei.waterbucket"));
     //   registration.addItemStackInfo(new ItemStack(Items.LAVA_BUCKET), Component.translatable("simpeladdmod.jei.lavabucket"));
     //   registration.addItemStackInfo(new ItemStack(Items.SNOWBALL), Component.translatable("simpeladdmod.jei.chilling.snowball"));
     //   registration.addItemStackInfo(new ItemStack(Items.SNOW_BLOCK), Component.translatable("simpeladdmod.jei.chilling.snowblock"));
     //   registration.addItemStackInfo(new ItemStack(Items.ICE), Component.translatable("simpeladdmod.jei.chilling.ice"));
     //   registration.addItemStackInfo(new ItemStack(Items.PACKED_ICE), Component.translatable("simpeladdmod.jei.chilling.packedice"));
     //   registration.addItemStackInfo(new ItemStack(Items.BLUE_ICE), Component.translatable("simpeladdmod.jei.chilling.blueice"));

    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(FabricGrinderScreen.class, 58, 42, 55,23,
                FabricGrinderCategory.TYPE);
        registration.addRecipeClickArea(FabricGrinderScreen_up.class, 42, 34, 71,13,
                FabricGrinderCategory.TYPE);
    }

}