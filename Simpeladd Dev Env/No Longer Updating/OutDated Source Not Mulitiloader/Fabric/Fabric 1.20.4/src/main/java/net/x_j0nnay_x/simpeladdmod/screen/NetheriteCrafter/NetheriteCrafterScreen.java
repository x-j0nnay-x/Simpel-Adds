package net.x_j0nnay_x.simpeladdmod.screen.NetheriteCrafter;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;


public class NetheriteCrafterScreen extends HandledScreen<NetheriteCrafterMenu> {
    private static final Identifier texture = new Identifier(Simpeladd.MOD_ID, "textures/screens/netheritecrafter_gui.png");
    public NetheriteCrafterScreen(NetheriteCrafterMenu pMenu, PlayerInventory pPlayerInventory, Text pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.title.contains(Text.translatable("gui.simpeladdmod.netherite_crafter_gui"));
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
        renderProgressArrow(pGuiGraphics, x, y);
        renderBlazeUses(pGuiGraphics, x, y);
        RenderSystem.disableBlend();
    }
    private void renderProgressArrow(DrawContext guiGraphics, int x, int y) {
        if(handler.isCrafting()){
            guiGraphics.drawTexture(texture,  x + 43, y + 59, 0, 168, handler.getScalledProgress(), 6);
        }
    }
    private void renderBlazeUses(DrawContext guiGraphics, int x, int y) {

            guiGraphics.drawTexture(texture,  x + 107 , y + 34, 176, 0, 4, handler.getBlazeLevel());

    }


}
