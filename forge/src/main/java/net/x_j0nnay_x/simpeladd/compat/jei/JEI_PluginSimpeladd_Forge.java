package net.x_j0nnay_x.simpeladd.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModBlockRegForge;
import net.x_j0nnay_x.simpeladd.screens.ForgeGrindFactoryrScreen;
import net.x_j0nnay_x.simpeladd.screens.ForgeGrinderScreen;
import net.x_j0nnay_x.simpeladd.screens.ForgeGrinderScreen_up;

@JeiPlugin
public class JEI_PluginSimpeladd_Forge implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(SimpelAddMod.MOD_ID, "_jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new JEI_GrindingCatagory_Forge(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(JEI_GrindingCatagory_Forge.GRINDER_RECIPE_RECIPE_TYPE, JEI_GrindingCatagory_Forge.getAllRecipes());
        registration.addItemStackInfo(new ItemStack(Items.WATER_BUCKET), Component.translatable("simpeladdmod.jei.waterbucket"));
        registration.addItemStackInfo(new ItemStack(Items.LAVA_BUCKET), Component.translatable("simpeladdmod.jei.lavabucket"));
        registration.addItemStackInfo(new ItemStack(Items.SNOWBALL), Component.translatable("simpeladdmod.jei.chilling.snowball"));
        registration.addItemStackInfo(new ItemStack(Items.SNOW_BLOCK), Component.translatable("simpeladdmod.jei.chilling.snowblock"));
        registration.addItemStackInfo(new ItemStack(Items.ICE), Component.translatable("simpeladdmod.jei.chilling.ice"));
        registration.addItemStackInfo(new ItemStack(Items.PACKED_ICE), Component.translatable("simpeladdmod.jei.chilling.packedice"));
        registration.addItemStackInfo(new ItemStack(Items.BLUE_ICE), Component.translatable("simpeladdmod.jei.chilling.blueice"));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ModBlockRegForge.GRINDER_BLOCK.get().asItem().getDefaultInstance(), JEI_GrindingCatagory_Forge.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeCatalyst(ModBlockRegForge.GRINDER_BLOCK_UP.get().asItem().getDefaultInstance(), JEI_GrindingCatagory_Forge.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeCatalyst(ModBlockRegForge.GRIND_FACTORY_BLOCK.get().asItem().getDefaultInstance(), JEI_GrindingCatagory_Forge.GRINDER_RECIPE_RECIPE_TYPE);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(ForgeGrinderScreen.class, 58, 42, 55,23,
                JEI_GrindingCatagory_Forge.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeClickArea(ForgeGrinderScreen_up.class, 42, 34, 71,13,
                JEI_GrindingCatagory_Forge.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeClickArea(ForgeGrindFactoryrScreen.class, 42, 29, 72, 4,
                JEI_GrindingCatagory_Forge.GRINDER_RECIPE_RECIPE_TYPE);
    }
}
