package net.x_j0nnay_x.simpeladdmod.screen.grinder_up;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;

import java.awt.*;

public class GrinderScreen_up extends HandledScreen<GrinderMenu_up> {
    private static final Identifier texture = new Identifier(Simpeladd.MOD_ID, "textures/screens/grinder_gui_upgrade.png");
    public GrinderScreen_up(GrinderMenu_up pMenu, PlayerInventory pPlayerInventory, Text pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(DrawContext guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.drawMouseoverTooltip(guiGraphics, mouseX, mouseY);


    }
    @Override
    public void init() {
        super.init();
    }

    @Override
    protected void drawBackground(DrawContext pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        pGuiGraphics.drawTexture(texture, x, y, 0, 0, backgroundWidth, backgroundHeight);
        renderProgressArrow1(pGuiGraphics, x, y);
        renderProgressArrow2(pGuiGraphics, x, y);
        renderProgressArrow3(pGuiGraphics, x, y);
        renderProgressArrow4(pGuiGraphics, x, y);
        renderBoostslot(pGuiGraphics, x, y);
        renderGrindsLeft(pGuiGraphics, x, y);
        RenderSystem.disableBlend();
    }

    private void renderProgressArrow1(DrawContext guiGraphics, int x, int y) {
        if(handler.isCrafting1()){
            guiGraphics.drawTexture(texture,  x + 49, y + 37, 177, 0, 4 ,handler.getScalledProgress1());
        }
    }
    private void renderProgressArrow2(DrawContext guiGraphics, int x, int y) {
        if(handler.isCrafting2()){
            guiGraphics.drawTexture(texture,  x + 67, y + 37, 177, 0, 4, handler.getScalledProgress2());
        }
    }
    private void renderProgressArrow3(DrawContext guiGraphics, int x, int y) {
        if(handler.isCrafting3()){
            guiGraphics.drawTexture(texture,  x + 85, y + 37, 177, 0, 4, handler.getScalledProgress3());
        }
    }
    private void renderProgressArrow4(DrawContext guiGraphics, int x, int y) {
        if(handler.isCrafting4()){
            guiGraphics.drawTexture(texture,  x + 103, y + 37, 177, 0, 4, handler.getScalledProgress4());
        }
    }
    private void  renderGrindsLeft(DrawContext guiGraphics, int x, int y){
        String gl = Integer.toString(handler.getGrindsLeft());
        String title = Text.translatable("gui.simpeladdmod.grinder_gui.grinds_left").getString();
        guiGraphics.drawText(this.textRenderer, title + gl, x +124, y +5, TextColor.fromRgb(Colors.GRAY).getRgb(), false);
    }
    private void renderBoostslot(DrawContext guiGraphics, int x, int y){
        String eff = Integer.toString(handler.getGrinderEff());
        String title = Text.translatable("gui.simpeladdmod.grinder_gui.grinds_eff").getString();
        if (handler.hasEffUpgrade()){
            guiGraphics.drawText(this.textRenderer, title + eff, x + 124, y + 28, TextColor.fromRgb(Colors.GRAY).getRgb(), false);
        }
    }

}
