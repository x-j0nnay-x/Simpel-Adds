package net.x_j0nnay_x.simpeladd.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.menu.NeoForgeCropGrowthMenu;

import java.text.NumberFormat;

public class NeoForgeCropGrowthScreen extends AbstractContainerScreen<NeoForgeCropGrowthMenu> {


    private static final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, "textures/screens/cropgrowthmachine.png");

    public NeoForgeCropGrowthScreen(NeoForgeCropGrowthMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
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
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        pGuiGraphics.blit(texture, x, y, 0, 0, imageWidth, imageHeight);
        renderProgress(pGuiGraphics, x, y);
        renderCopper(pGuiGraphics, x, y);
        RenderSystem.disableBlend();
        pGuiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int x, int y) {
        super.renderTooltip(guiGraphics, x, y);
        this.renderCopperToolTip(guiGraphics, x, y);
        this.renderBurnToolTip(guiGraphics, x, y);
    }


    private void renderProgress(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()){
            int posisionOffset = 43 - menu.getScalledProgress();
            guiGraphics.blit(texture,  x + 166, y + 26 + posisionOffset, 183, posisionOffset, 2 , menu.getScalledProgress());
        }
    }
    private void renderCopper(GuiGraphics guiGraphics, int x, int y) {
        int posisionOffset = 46 - menu.getCopperLevel();
        guiGraphics.blit(texture, x + 158, y + 25 + posisionOffset, 177, posisionOffset, 4, menu.getCopperLevel());
    }
    private void renderCopperToolTip(GuiGraphics guiGraphics, int x, int y){
        int hoverPositionX = x - leftPos;
        int hoverPositionY = y - topPos;
        if (hoverPositionX > 157 && hoverPositionX < 163 && hoverPositionY > 24 && hoverPositionY < 72) {
            Component componentCopper = MutableComponent.create(this.menu.getCopperName().getContents())
                    .append(" (%s/%s)".formatted(this.menu.getCoperValue(), 100));
            guiGraphics.renderTooltip(font, componentCopper,x ,y + 12);
        }
    }
    private void renderBurnToolTip(GuiGraphics guiGraphics, int x, int y){
        int hoverPositionX = x - leftPos;
        int hoverPositionY = y - topPos;
        if (hoverPositionX > 165 && hoverPositionX < 169 && hoverPositionY > 25 && hoverPositionY < 70) {
            Component componentCopper = MutableComponent.create(this.menu.getburnName().getContents())
                    .append(" (%s/%s)".formatted(this.menu.getBurnValue(), this.menu.getMaxBurn()));
            guiGraphics.renderTooltip(font, componentCopper,x ,y + 12);
        }
    }

}