package net.x_j0nnay_x.simpeladd.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModBlockRegFabric;
import net.x_j0nnay_x.simpeladd.screens.FabricCropGrowthScreen;
import net.x_j0nnay_x.simpeladd.screens.FabricGrindFactoryScreen;
import net.x_j0nnay_x.simpeladd.screens.FabricGrinderScreen;
import net.x_j0nnay_x.simpeladd.screens.FabricGrinderScreen_up;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class JEI_PluginSimpeladd_Fabric implements IModPlugin {
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(
                new JEI_GrindingCatagory_Fabric(helper),
                new JEI_CropGrowthCatagory_Fabric(helper)
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(JEI_GrindingCatagory_Fabric.GRINDER_RECIPE_RECIPE_TYPE, JEI_GrindingCatagory_Fabric.getAllRecipes());
        registration.addRecipes(JEI_CropGrowthCatagory_Fabric.CROP_GROWTH_RECIPE_RECIPE_TYPE, JEI_CropGrowthCatagory_Fabric.getAllRecipes());
        registration.addItemStackInfo(new ItemStack(Items.WATER_BUCKET), Component.translatable("simpeladdmod.jei.waterbucket"));
        registration.addItemStackInfo(new ItemStack(Items.LAVA_BUCKET), Component.translatable("simpeladdmod.jei.lavabucket"));
        registration.addItemStackInfo(new ItemStack(Items.SNOWBALL), Component.translatable("simpeladdmod.jei.chilling.snowball"));
        registration.addItemStackInfo(new ItemStack(Items.SNOW_BLOCK), Component.translatable("simpeladdmod.jei.chilling.snowblock"));
        registration.addItemStackInfo(new ItemStack(Items.ICE), Component.translatable("simpeladdmod.jei.chilling.ice"));
        registration.addItemStackInfo(new ItemStack(Items.PACKED_ICE), Component.translatable("simpeladdmod.jei.chilling.packedice"));
        registration.addItemStackInfo(new ItemStack(Items.BLUE_ICE), Component.translatable("simpeladdmod.jei.chilling.blueice"));
    }

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, SimpelAddMod.MOD_ID +"_jei_plugin");
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ModBlockRegFabric.GRINDER_BLOCK.asItem().getDefaultInstance(), JEI_GrindingCatagory_Fabric.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeCatalyst(ModBlockRegFabric.GRINDER_BLOCK_UP.asItem().getDefaultInstance(), JEI_GrindingCatagory_Fabric.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeCatalyst(ModBlockRegFabric.GRIND_FACTORY_BLOCK.asItem().getDefaultInstance(), JEI_GrindingCatagory_Fabric.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeCatalyst(ModBlockRegFabric.CROP_GROWTH.asItem().getDefaultInstance(), JEI_CropGrowthCatagory_Fabric.CROP_GROWTH_RECIPE_RECIPE_TYPE);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(FabricGrinderScreen.class, 58, 42, 55,23,
                JEI_GrindingCatagory_Fabric.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeClickArea(FabricGrinderScreen_up.class, 42, 34, 71,13,
                JEI_GrindingCatagory_Fabric.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeClickArea(FabricGrindFactoryScreen.class, 42, 29, 72, 4,
                JEI_GrindingCatagory_Fabric.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeClickArea(FabricCropGrowthScreen.class, 31, 25, 10, 40,
                JEI_CropGrowthCatagory_Fabric.CROP_GROWTH_RECIPE_RECIPE_TYPE);
    }

}
