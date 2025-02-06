package net.x_j0nnay_x.simpeladd.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.SimpelAddModFabric;
import net.x_j0nnay_x.simpeladd.menu.FabricChillerMenu;

import java.text.NumberFormat;

public class FabricChillerScreen extends AbstractContainerScreen<FabricChillerMenu> {

    private int waterPosX = 74;
    private int waterPosY = 11;
    private int tankWidth = 15;
    private int tankHeight = 58;

    private static final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, "textures/screens/chiller_gui.png");

    public FabricChillerScreen(FabricChillerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
        renderWaterToolOverlay(guiGraphics, mouseX, mouseY);
        if (mouseX > leftPos + 11 && mouseX < leftPos + 35 && mouseY > topPos + 48 && mouseY < topPos + 72)
            guiGraphics.renderTooltip(font, Component.translatable("gui.simpeladdmod.chiller_block_gui.Chilling"), mouseX, mouseY-16);
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
        renderwater(pGuiGraphics, x, y);
        renderSnow(pGuiGraphics, x, y);
        RenderSystem.disableBlend();
        pGuiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, Component.translatable("gui.simpeladdmod.chiller_block_gui.label"), 114, 9, -12829636, false);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()){
            guiGraphics.blit(texture,  x + 99, y + 42, 177, 62, menu.getScalledProgress(), 16);
        }
    }

    private void renderwater(GuiGraphics guiGraphics, int x, int y) {
        if(menu.hasWater()) {
            guiGraphics.blit(texture, x + 73, y + 69 - menu.getScalledwater() , 177, 0, 16, menu.getScalledwater());
        }
    }
    public void renderWaterToolOverlay(GuiGraphics guiGraphics, int x, int y) {
        int hoverPositionX = x - leftPos;
        int hoverPositionY = y - topPos;
        if (hoverPositionX > this.waterPosX
                && hoverPositionX < this.waterPosX + this.tankWidth
                && hoverPositionY > this.waterPosY
                && hoverPositionY < this.waterPosY + this.tankHeight) {
            NumberFormat format = NumberFormat.getInstance();
            String waterLevel = format.format(this.menu.getWaterLever());
            guiGraphics.renderTooltip(font, Component.literal(waterLevel), x ,y + 12);
        }
    }

    private void renderSnow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.hasSnow()) {
            guiGraphics.blit(texture, x + 38, y + 69 - menu.getScalledsnow(), 195, 1, 7, menu.getScalledsnow());
        }
    }
}
