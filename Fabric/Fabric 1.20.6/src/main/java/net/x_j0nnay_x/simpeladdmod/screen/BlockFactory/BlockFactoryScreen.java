package net.x_j0nnay_x.simpeladdmod.screen.BlockFactory;


import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;


public class BlockFactoryScreen extends HandledScreen<BlockFactoryMenu> {
    private static final Identifier texture = new Identifier (Simpeladd.MOD_ID, "textures/screens/blockfactory_gui.png");
    public BlockFactoryScreen(BlockFactoryMenu pMenu, PlayerInventory pPlayerInventory, Text pTitle) {
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
        titleY = 1000;
        playerInventoryTitleY = 1000;
    }

    @Override
    protected void drawBackground(DrawContext  pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, texture);
        int x = (width - backgroundWidth) / 2;
        int y = (height- backgroundHeight) / 2;
        pGuiGraphics.drawTexture(texture, x, y, 0, 0, backgroundWidth, backgroundHeight);
        pGuiGraphics.drawText(this.textRenderer, Text.translatable("gui.simpeladdmod.blockfactory.label1"), x + 40, y +9, -12829636, false);
        pGuiGraphics.drawText(this.textRenderer, Text.translatable("gui.simpeladdmod.blockfactory.label2"), x + 105, y + 9, -12829636, false);
        renderProgressArrow(pGuiGraphics, x, y);
        renderlava(pGuiGraphics, x, y);
        renderwater(pGuiGraphics, x, y);
        RenderSystem.disableBlend();
    }

    private void renderProgressArrow(DrawContext guiGraphics, int x, int y) {
        if(handler.isCrafting()) {
            guiGraphics.drawTexture(texture, x + 82, y + 59, 177, 62, 10, handler.getScalledProgress());
        }

    }
    private void renderwater(DrawContext guiGraphics, int x, int y) {
        if(handler.hasWater()) {
            guiGraphics.drawTexture(texture, x + 12, y + 72 - handler.getScalledwater(), 191, 0, 13, handler.getScalledwater());
        }
    }
    private void renderlava(DrawContext guiGraphics, int x, int y) {
        if(handler.hasLava()) {
            guiGraphics.drawTexture(texture, x + 152, y + 72 - handler.getScalledlava(), 177, 0, 13, handler.getScalledlava());
        }
    }

    @Override
    public Text getTitle() {
        return super.getTitle();
    }



}
