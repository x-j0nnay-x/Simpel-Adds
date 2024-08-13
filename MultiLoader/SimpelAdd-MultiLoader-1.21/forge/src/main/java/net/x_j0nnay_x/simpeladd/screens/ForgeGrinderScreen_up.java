package net.x_j0nnay_x.simpeladd.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.x_j0nnay_x.simpeladd.SimpelAddModForge;
import net.x_j0nnay_x.simpeladd.menu.ForgeGrinderMenu_up;

public class ForgeGrinderScreen_up extends AbstractContainerScreen<ForgeGrinderMenu_up> {

    private static final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(SimpelAddModForge.MODID, "textures/screens/grinder_gui_upgrade.png");

    public ForgeGrinderScreen_up(ForgeGrinderMenu_up pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
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
        renderBoostslot(pGuiGraphics, x, y);
        renderGrindsLeft(pGuiGraphics, x, y);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        pGuiGraphics.blit(texture, x, y, 0, 0, imageWidth, imageHeight);
        renderProgressArrow1(pGuiGraphics, x, y);
        renderProgressArrow2(pGuiGraphics, x, y);
        renderProgressArrow3(pGuiGraphics, x, y);
        renderProgressArrow4(pGuiGraphics, x, y);
        RenderSystem.disableBlend();
    }

    private void renderProgressArrow1(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting1()){
            guiGraphics.blit(texture,  x + 49, y + 37, 177, 0, 4 ,menu.getScalledProgress1());
        }
    }

    private void renderProgressArrow2(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting2()){
            guiGraphics.blit(texture,  x + 67, y + 37, 177, 0, 4, menu.getScalledProgress2());
        }
    }

    private void renderProgressArrow3(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting3()){
            guiGraphics.blit(texture,  x + 85, y + 37, 177, 0, 4, menu.getScalledProgress3());
        }
    }

    private void renderProgressArrow4(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting4()){
            guiGraphics.blit(texture,  x + 103, y + 37, 177, 0, 4, menu.getScalledProgress4());
        }
    }

    private void  renderGrindsLeft(GuiGraphics guiGraphics, int x, int y){
        String gl = Integer.toString(menu.getGrindsLeft());
        String title = Component.translatable("gui.simpeladdmod.grinder_gui.grinds_left").getString();
        guiGraphics.drawString(font, title + gl, x + 124,  y + 5, ChatFormatting.DARK_GRAY.getColor());
    }

    private void renderBoostslot(GuiGraphics guiGraphics, int x, int y){
        String eff = Integer.toString(menu.getGrinderEff());
        String title = Component.translatable("gui.simpeladdmod.grinder_gui.grinds_eff").getString();
        if (menu.hasEffUpgrade()){
            guiGraphics.drawString(font,  title + eff, x + 124, y + 28, ChatFormatting.DARK_GRAY.getColor());
        }
    }
}
