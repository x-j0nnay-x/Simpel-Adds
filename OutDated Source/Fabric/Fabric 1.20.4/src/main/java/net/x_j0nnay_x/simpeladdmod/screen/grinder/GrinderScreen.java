package net.x_j0nnay_x.simpeladdmod.screen.grinder;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;


public class GrinderScreen extends HandledScreen<GrinderMenu> {
    private static final Identifier texture = new Identifier(Simpeladd.MOD_ID, "textures/screens/grinder_gui.png");
    public GrinderScreen(GrinderMenu pMenu, PlayerInventory pPlayerInventory, Text pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(DrawContext guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.drawMouseoverTooltip(guiGraphics, mouseX, mouseY);
        if (mouseX > x + 28 && mouseX < x + 52 && mouseY > y + 40 && mouseY < y + 64)
            guiGraphics.drawText(this.textRenderer, Text.translatable("gui.simpeladdmod.grinder_gui.Grindables"), mouseX, mouseY-16,-12829636, false);

    }
    @Override
    public void init() {
        super.init();
    }

    @Override
    protected void drawBackground(DrawContext pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, texture);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        pGuiGraphics.drawTexture(texture, x, y, 0, 0, backgroundWidth, backgroundHeight);
        renderProgressArrow(pGuiGraphics, x, y);
        renderGrindsLeft(pGuiGraphics, x,y);
        renderBoostslot(pGuiGraphics, x, y);
        RenderSystem.disableBlend();
    }

    private void renderProgressArrow(DrawContext guiGraphics, int x, int y) {
        if(handler.isCrafting()){
            guiGraphics.drawTexture(texture,  x + 60, y + 44, 177, 0, handler.getScalledProgress(), 16);
        }
    }
    private void  renderGrindsLeft(DrawContext guiGraphics, int x, int y){
        String gl = Integer.toString(handler.getGrindsLeft());
        String title = Text.translatable("gui.simpeladdmod.grinder_gui.grinds_left").getString();
        guiGraphics.drawText(this.textRenderer, title + gl, x + 103,  y +28, TextColor.fromRgb(Colors.GRAY).getRgb(), false);
    }
    private void renderBoostslot(DrawContext guiGraphics, int x, int y){
        String eff = Integer.toString(handler.getGrinderEff());
        String title = Text.translatable("gui.simpeladdmod.grinder_gui.grinds_eff").getString();
        if (handler.hasEffUpgrade()){
            guiGraphics.drawText(this.textRenderer,  title + eff, x + 103, y + 6, TextColor.fromRgb(Colors.GRAY).getRgb(), false);

        }
    }

}
