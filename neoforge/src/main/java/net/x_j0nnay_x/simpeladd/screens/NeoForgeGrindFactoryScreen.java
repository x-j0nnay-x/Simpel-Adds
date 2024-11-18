package net.x_j0nnay_x.simpeladd.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;
import net.x_j0nnay_x.simpeladd.menu.NeoForgeGrindFactoryMenu;
import java.text.DecimalFormat;

public class NeoForgeGrindFactoryScreen extends AbstractContainerScreen<NeoForgeGrindFactoryMenu> {

    private static final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, "textures/screens/grinder_factory_gui.png");

    public NeoForgeGrindFactoryScreen(NeoForgeGrindFactoryMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
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
        renderGrindsLeft(pGuiGraphics, x, y);
        renderFuelLeft(pGuiGraphics, x, y);
        renderXPlevle(pGuiGraphics, x, y);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        pGuiGraphics.blit(texture, x, y, 0, 0, imageWidth, imageHeight);
        renderGrindProgressArrow1(pGuiGraphics, x, y);
        renderGrindProgressArrow2(pGuiGraphics, x, y);
        renderGrindProgressArrow3(pGuiGraphics, x, y);
        renderGrindProgressArrow4(pGuiGraphics, x, y);
        renderFurnProgressArrow1(pGuiGraphics, x, y);
        renderFurnProgressArrow2(pGuiGraphics, x, y);
        renderFurnProgressArrow3(pGuiGraphics, x, y);
        renderFurnProgressArrow4(pGuiGraphics, x, y);
        RenderSystem.disableBlend();
    }

    private void renderGrindProgressArrow1(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isGrinding1()){
            guiGraphics.blit(texture,  x + 45, y + 29, 177, 0, menu.getGrindScalledProgress1(), 3);
        }
    }

    private void renderGrindProgressArrow2(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isGrinding2()){
            guiGraphics.blit(texture,  x + 63, y + 29, 177, 0,  menu.getGrindScalledProgress2(), 3);
        }
    }

    private void renderGrindProgressArrow3(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isGrinding3()){
            guiGraphics.blit(texture,  x + 81, y + 29, 177, 0, menu.getGrindScalledProgress3(), 3);
        }
    }

    private void renderGrindProgressArrow4(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isGrinding4()){
            guiGraphics.blit(texture,  x + 99, y + 29, 177, 0,  menu.getGrindScalledProgress4(), 3);
        }
    }

    private void renderFurnProgressArrow1(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isSmelting1()){
            guiGraphics.blit(texture,  x + 45, y + 50, 177, 3, menu.getFurnScalledProgress1(), 3);
        }
    }

    private void renderFurnProgressArrow2(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isSmelting2()){
            guiGraphics.blit(texture,  x + 63, y + 50, 177, 3,  menu.getFurnScalledProgress2(), 3);
        }
    }

    private void renderFurnProgressArrow3(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isSmelting3()){
            guiGraphics.blit(texture,  x + 81, y + 50, 177, 3,  menu.getFurnScalledProgress3(), 3);
        }
    }

    private void renderFurnProgressArrow4(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isSmelting4()){
            guiGraphics.blit(texture,  x + 99, y + 50, 177, 3, menu.getFurnScalledProgress4(), 3);
        }
    }

    private void  renderGrindsLeft(GuiGraphics guiGraphics, int x, int y){
        String gl = Integer.toString(menu.getGrindsLeft());
        String title = Component.translatable("gui.simpeladdmod.grinder_gui.grinds_left").getString();
        guiGraphics.drawString(font, title + gl, x + 124,  y + 5, ChatFormatting.DARK_GRAY.getColor());
    }

    private void  renderFuelLeft(GuiGraphics guiGraphics, int x, int y){
        int fuel = menu.getFuleLeft();
        DecimalFormat format = new DecimalFormat("#,###.#");
        String glp = format.format(fuel);
        String title = Component.translatable("gui.simpeladdmod.upgrade_furnace_gui.fuel_left").getString();
        guiGraphics.drawString(font, title, x + 124,  y + 28, ChatFormatting.DARK_GRAY.getColor());
        guiGraphics.drawString(font, glp, x + 124,  y + 38, ChatFormatting.DARK_GRAY.getColor());
    }

    private void  renderXPlevle(GuiGraphics guiGraphics, int x, int y){
        int xp = menu.getXPStored();
        DecimalFormat format = new DecimalFormat("#,###");
        String glp = format.format(xp);
        String title = Component.translatable("gui.simpeladdmod.upgrade_furnace_gui.xp_stored").getString();
        guiGraphics.drawString(font, title, x + 68,  y + 73, ChatFormatting.DARK_GRAY.getColor());
        guiGraphics.drawString(font, glp, x + 84,  y + 73, ChatFormatting.DARK_GRAY.getColor());
    }
    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int i, int j) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX - 2, this.titleLabelY - 3, 4210752, false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);
    }
}
