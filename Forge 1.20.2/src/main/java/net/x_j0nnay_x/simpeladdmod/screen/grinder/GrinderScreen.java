package net.x_j0nnay_x.simpeladdmod.screen.grinder;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.x_j0nnay_x.simpeladdmod.simpeladdmod;

public class GrinderScreen extends AbstractContainerScreen<GrinderMenu> {
    private static final ResourceLocation texture = new ResourceLocation(simpeladdmod.MOD_ID, "textures/screens/grinder_gui.png");
   // private static final ResourceLocation progArrow = new ResourceLocation(simpeladdmod.MOD_ID,"textures/screens/sprites/grinder_prog.png");
    public GrinderScreen(GrinderMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
        if (mouseX > leftPos + 28 && mouseX < leftPos + 52 && mouseY > topPos + 40 && mouseY < topPos + 64)
            guiGraphics.renderTooltip(font, Component.translatable("gui.simpeladdmod.grinder_gui.Grindables"), mouseX, mouseY-16);

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
        RenderSystem.disableBlend();
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()){
            guiGraphics.blit(texture,  x + 60, y + 44, 177, 0, menu.getScalledProgress(), 16);
        }
    }

}
