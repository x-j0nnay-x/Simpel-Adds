package net.x_j0nnay_x.simpeladd.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.x_j0nnay_x.simpeladd.SimpelAddModFabric;
import net.x_j0nnay_x.simpeladd.menu.FabricGrinderMenu;

public class FabricGrinderScreen extends AbstractContainerScreen<FabricGrinderMenu> {

    private static final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, "textures/screens/grinder_gui.png");

    public FabricGrinderScreen(FabricGrinderMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
        if (mouseX > leftPos + 28 && mouseX < leftPos + 52 && mouseY > topPos + 40 && mouseY < topPos + 64)
            guiGraphics.renderTooltip(font, Component.translatable("gui.simpeladdmod.grinder_gui.Grindables"), mouseX, mouseY-16);
    }
    @Override
    public void init() {
        super.init();
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        renderBoostslot(pGuiGraphics, x, y);
        renderGrindsLeft(pGuiGraphics, x, y);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        pGuiGraphics.blit(texture, x, y, 0, 0, imageWidth, imageHeight);
        renderProgressArrow(pGuiGraphics, x, y);
        RenderSystem.disableBlend();
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()){
            guiGraphics.blit(texture,  x + 60, y + 44, 177, 0, menu.getScalledProgress(), 16);
        }
    }

    private void  renderGrindsLeft(GuiGraphics guiGraphics, int x, int y){
        String gl = Integer.toString(menu.getGrindsLeft());
        String title = Component.translatable("gui.simpeladdmod.grinder_gui.grinds_left").getString();
        guiGraphics.drawString(font, title + gl, x + 103,  y +28, ChatFormatting.DARK_GRAY.getColor());
    }

    private void renderBoostslot(GuiGraphics guiGraphics, int x, int y){
        String eff = Integer.toString(menu.getGrinderEff());
        String title = Component.translatable("gui.simpeladdmod.grinder_gui.grinds_eff").getString();
        if (menu.hasEffUpgrade()){
            guiGraphics.drawString(font,  title + eff, x + 103, y + 6, ChatFormatting.DARK_GRAY.getColor());
        }
    }
}
