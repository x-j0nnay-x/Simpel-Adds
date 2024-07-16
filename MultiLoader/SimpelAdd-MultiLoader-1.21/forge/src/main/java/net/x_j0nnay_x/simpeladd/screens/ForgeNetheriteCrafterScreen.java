package net.x_j0nnay_x.simpeladd.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.x_j0nnay_x.simpeladd.SimpelAddModForge;
import net.x_j0nnay_x.simpeladd.menu.ForgeNetheriteCrafterMenu;



public class ForgeNetheriteCrafterScreen extends AbstractContainerScreen<ForgeNetheriteCrafterMenu> {
    private static final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(SimpelAddModForge.MODID, "textures/screens/netheritecrafter_gui.png");
    public ForgeNetheriteCrafterScreen(ForgeNetheriteCrafterMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.title.contains(Component.translatable("gui.simpeladdmod.netherite_crafter_gui"));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);


    }
    @Override
    public void init() {
        super.init();

    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        pGuiGraphics.blit(texture, x, y, 0, 0, imageWidth, imageHeight);
        renderProgressArrow(pGuiGraphics, x, y);
        renderBlazeUses(pGuiGraphics, x, y);
        RenderSystem.disableBlend();
    }
    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()){
            guiGraphics.blit(texture,  x + 43, y + 59, 0, 168, menu.getScalledProgress(), 6);
        }
    }
    private void renderBlazeUses(GuiGraphics guiGraphics, int x, int y) {

            guiGraphics.blit(texture,  x + 107 , y + 34, 176, 0, 4, menu.getBlazeLevel());

    }


}