package net.x_j0nnay_x.simpeladdmod.screen.Furnace_Up;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;

public class FurnaceScreen_up extends AbstractContainerScreen<FurnaceMenu_up> {
    private static final ResourceLocation texture = new ResourceLocation(Simpeladd.MOD_ID, "textures/screens/upgrade_furnace_gui.png");
    public FurnaceScreen_up(FurnaceMenu_up pMenu, Inventory pPlayerInventory, Component pTitle) {
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
        renderFuelLeft(pGuiGraphics, x, y);
        renderXPlevle(pGuiGraphics, x, y);
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
            guiGraphics.blit(texture,  x + 69, y + 37, 177, 0, 4 ,menu.getScalledProgress1());
        }
    }
    private void renderProgressArrow2(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting2()){
            guiGraphics.blit(texture,  x + 87, y + 37, 177, 0, 4, menu.getScalledProgress2());
        }
    }
    private void renderProgressArrow3(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting3()){
            guiGraphics.blit(texture,  x + 105, y + 37, 177, 0, 4, menu.getScalledProgress3());
        }
    }
    private void renderProgressArrow4(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting4()){
            guiGraphics.blit(texture,  x + 123, y + 37, 177, 0, 4, menu.getScalledProgress4());
        }
    }
    private void  renderFuelLeft(GuiGraphics guiGraphics, int x, int y){
        String gl = Integer.toString(menu.getFuleLeft());
        String title = Component.translatable("gui.simpeladdmod.upgrade_furnace_gui.fuel_left").getString();
        guiGraphics.drawString(font, title, x + 5,  y + 18, ChatFormatting.DARK_GRAY.getColor());
        guiGraphics.drawString(font, gl, x + 5,  y + 28, ChatFormatting.DARK_GRAY.getColor());
    }
    private void  renderXPlevle(GuiGraphics guiGraphics, int x, int y){
        String gl = Integer.toString(menu.getXPStored());
        String title = Component.translatable("gui.simpeladdmod.upgrade_furnace_gui.xp_stored").getString();
        guiGraphics.drawString(font, title, x + 68,  y + 73, ChatFormatting.DARK_GRAY.getColor());
        guiGraphics.drawString(font, gl, x + 84,  y + 73, ChatFormatting.DARK_GRAY.getColor());
    }

}
