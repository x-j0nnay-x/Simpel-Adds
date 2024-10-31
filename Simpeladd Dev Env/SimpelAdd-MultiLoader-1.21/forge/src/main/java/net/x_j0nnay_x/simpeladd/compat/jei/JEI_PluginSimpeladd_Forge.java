package net.x_j0nnay_x.simpeladd.compat.jei;

import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.x_j0nnay_x.simpeladd.core.ModBlockRegForge;
import net.x_j0nnay_x.simpeladd.screens.ForgeGrindFactoryScreen;
import net.x_j0nnay_x.simpeladd.screens.ForgeGrinderScreen;
import net.x_j0nnay_x.simpeladd.screens.ForgeGrinderScreen_up;

@JeiPlugin
public class JEI_PluginSimpeladd_Forge extends JEI_PluginSimpeladd_ABST {

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ModBlockRegForge.GRINDER_BLOCK.get().asItem().getDefaultInstance(), JEI_GrindingCatagory.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeCatalyst(ModBlockRegForge.GRINDER_BLOCK_UP.get().asItem().getDefaultInstance(), JEI_GrindingCatagory.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeCatalyst(ModBlockRegForge.GRIND_FACTORY_BLOCK.get().asItem().getDefaultInstance(), JEI_GrindingCatagory.GRINDER_RECIPE_RECIPE_TYPE);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(ForgeGrinderScreen.class, 58, 42, 55,23,
                JEI_GrindingCatagory.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeClickArea(ForgeGrinderScreen_up.class, 42, 34, 71,13,
                JEI_GrindingCatagory.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeClickArea(ForgeGrindFactoryScreen.class, 42, 29, 72, 4,
                JEI_GrindingCatagory.GRINDER_RECIPE_RECIPE_TYPE);
    }

}
