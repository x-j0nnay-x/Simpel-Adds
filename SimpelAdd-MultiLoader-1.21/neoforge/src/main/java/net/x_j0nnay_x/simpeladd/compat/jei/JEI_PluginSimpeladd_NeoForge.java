package net.x_j0nnay_x.simpeladd.compat.jei;


import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.x_j0nnay_x.simpeladd.core.ModBlockRegNeoForge;
import net.x_j0nnay_x.simpeladd.screens.NeoForgeGrindFactoryScreen;
import net.x_j0nnay_x.simpeladd.screens.NeoForgeGrinderScreen;
import net.x_j0nnay_x.simpeladd.screens.NeoForgeGrinderScreen_up;

@JeiPlugin
public class JEI_PluginSimpeladd_NeoForge extends JEI_PluginSimpeladd_ABST {

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ModBlockRegNeoForge.GRINDER_BLOCK.asItem().getDefaultInstance(), JEI_GrindingCatagory.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeCatalyst(ModBlockRegNeoForge.GRINDER_BLOCK_UP.asItem().getDefaultInstance(), JEI_GrindingCatagory.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeCatalyst(ModBlockRegNeoForge.GRIND_FACTORY_BLOCK.asItem().getDefaultInstance(), JEI_GrindingCatagory.GRINDER_RECIPE_RECIPE_TYPE);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(NeoForgeGrinderScreen.class, 58, 42, 55,23,
                JEI_GrindingCatagory.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeClickArea(NeoForgeGrinderScreen_up.class, 42, 34, 71,13,
                JEI_GrindingCatagory.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeClickArea(NeoForgeGrindFactoryScreen.class, 42, 29, 72, 4,
                JEI_GrindingCatagory.GRINDER_RECIPE_RECIPE_TYPE);
    }

}
