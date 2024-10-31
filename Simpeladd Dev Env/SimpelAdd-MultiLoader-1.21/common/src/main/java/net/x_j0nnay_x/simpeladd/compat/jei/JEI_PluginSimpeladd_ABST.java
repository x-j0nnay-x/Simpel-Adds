package net.x_j0nnay_x.simpeladd.compat.jei;


import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import org.jetbrains.annotations.NotNull;


@JeiPlugin
public abstract class JEI_PluginSimpeladd_ABST implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(
                new JEI_GrindingCatagory(helper)
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(JEI_GrindingCatagory.GRINDER_RECIPE_RECIPE_TYPE, JEI_GrindingCatagory.getAllRecipes());
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
        return ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, "_jei_plugin");
    }
}
