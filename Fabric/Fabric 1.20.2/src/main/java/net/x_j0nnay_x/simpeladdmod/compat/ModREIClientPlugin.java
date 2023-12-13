package net.x_j0nnay_x.simpeladdmod.compat;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.x_j0nnay_x.simpeladdmod.block.ModBlocks;
import net.x_j0nnay_x.simpeladdmod.recipe.GrinderRecipe;
import net.x_j0nnay_x.simpeladdmod.screen.grinder.GrinderScreen;

public class ModREIClientPlugin implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new GrinderCategory());

        registry.addWorkstations(GrinderCategory.GRINDER, EntryStacks.of(ModBlocks.GRINDER_BLOCK));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(GrinderRecipe.class, GrinderRecipe.Type.INSTANCE,
                GrinderDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(75, 30, 20, 30), GrinderScreen.class,
                GrinderCategory.GRINDER);
    }
}