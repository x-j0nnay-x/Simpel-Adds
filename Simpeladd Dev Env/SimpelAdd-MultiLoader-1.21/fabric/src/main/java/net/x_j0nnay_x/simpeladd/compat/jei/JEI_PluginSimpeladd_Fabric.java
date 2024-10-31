package net.x_j0nnay_x.simpeladd.compat.jei;

import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.x_j0nnay_x.simpeladd.core.ModBlockRegFabric;
import net.x_j0nnay_x.simpeladd.screens.FabricGrindFactoryScreen;
import net.x_j0nnay_x.simpeladd.screens.FabricGrinderScreen;
import net.x_j0nnay_x.simpeladd.screens.FabricGrinderScreen_up;

@JeiPlugin
public class JEI_PluginSimpeladd_Fabric extends JEI_PluginSimpeladd_ABST {

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ModBlockRegFabric.GRINDER_BLOCK.asItem().getDefaultInstance(), JEI_GrindingCatagory.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeCatalyst(ModBlockRegFabric.GRINDER_BLOCK_UP.asItem().getDefaultInstance(), JEI_GrindingCatagory.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeCatalyst(ModBlockRegFabric.GRIND_FACTORY_BLOCK.asItem().getDefaultInstance(), JEI_GrindingCatagory.GRINDER_RECIPE_RECIPE_TYPE);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(FabricGrinderScreen.class, 58, 42, 55,23,
                JEI_GrindingCatagory.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeClickArea(FabricGrinderScreen_up.class, 42, 34, 71,13,
                JEI_GrindingCatagory.GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeClickArea(FabricGrindFactoryScreen.class, 42, 29, 72, 4,
                JEI_GrindingCatagory.GRINDER_RECIPE_RECIPE_TYPE);

    }

}
