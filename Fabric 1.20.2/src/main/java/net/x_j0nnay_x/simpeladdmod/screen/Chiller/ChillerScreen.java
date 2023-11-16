package net.x_j0nnay_x.simpeladdmod.screen.Chiller;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;


public class ChillerScreen extends HandledScreen<ChillerMenu> {
    private static final Identifier texture = new Identifier(Simpeladd.MOD_ID, "textures/screens/chiller_gui.png");
    public ChillerScreen(ChillerMenu pMenu, PlayerInventory pPlayerInventory, Text pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(DrawContext guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.drawMouseoverTooltip(guiGraphics, mouseX, mouseY);
        if (mouseX > x + 11 && mouseX < x + 35 && mouseY > y + 48 && mouseY < y + 72)
            guiGraphics.drawText(this.textRenderer, Text.translatable("gui.simpeladdmod.chiller_block_gui.Chilling"), mouseX, mouseY-16, -12829636, false);

    }
    @Override
    public void init() {
        super.init();
        titleX = 100;
        playerInventoryTitleY = 1000;
    }

    @Override
    protected void drawBackground(DrawContext pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, texture);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        pGuiGraphics.drawText(this.textRenderer, Text.translatable("gui.simpeladdmod.chiller_block_gui.label"), x + 114, y + 9, -12829636, false);
        pGuiGraphics.drawTexture(texture, x, y, 0, 0, backgroundWidth, backgroundHeight);
        renderProgressArrow(pGuiGraphics, x, y);
        renderwater(pGuiGraphics, x, y);
        renderSnow(pGuiGraphics, x, y);
        RenderSystem.disableBlend();
    }
    private void renderProgressArrow(DrawContext guiGraphics, int x, int y) {
        if(handler.isCrafting()){
            guiGraphics.drawTexture(texture,  x + 99, y + 42, 177, 62, handler.getScalledProgress(), 16);
        }
    }
    private void renderwater(DrawContext guiGraphics, int x, int y) {
        if(handler.hasWater()) {
            guiGraphics.drawTexture(texture, x + 73, y + 69 - handler.getScalledwater() , 177, 0, 16, handler.getScalledwater());
        }
    }
    private void renderSnow(DrawContext guiGraphics, int x, int y) {
        if(handler.hasSnow()) {
            guiGraphics.drawTexture(texture, x + 38, y + 69 - handler.getScalledsnow(), 195, 1, 7, handler.getScalledsnow());
        }
    }

}
