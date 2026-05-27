package x_j0nnay_x.simpeladdmod.menu.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import x_j0nnay_x.simpeladdmod.menu.Grinder_Netherite_Menu;
import x_j0nnay_x.simpeladdmod.init.Mod_Constants;

public class Grinder_Netherite_Screen extends AbstractContainerScreen<Grinder_Netherite_Menu> {
    private static final Identifier GUI_TEXTURE = Mod_Constants.id("textures/screens/grinder_netherite_gui.png");

    public Grinder_Netherite_Screen(Grinder_Netherite_Menu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelX = 8;
        this.inventoryLabelY = 73;
        this.titleLabelX = 45;
        this.titleLabelY = 6;
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
        super.extractLabels(graphics, xm, ym);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
        renderProgressArrow1(graphics, x, y);
        renderProgressArrow2(graphics, x, y);
        renderProgressArrow3(graphics, x, y);
        renderProgressArrow4(graphics, x, y);
    }

    private void renderProgressArrow1(GuiGraphicsExtractor guiGraphics, int x, int y) {
        if(menu.isCrafting1()) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,x + 60, y + 37, 177, 0, 4, menu.getScaledArrowProgress1(), 256, 256);
        }
    }
    private void renderProgressArrow2(GuiGraphicsExtractor guiGraphics, int x, int y) {
        if(menu.isCrafting2()) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,x + 78, y + 37, 177, 0, 4, menu.getScaledArrowProgress2(), 256, 256);
        }
    }
    private void renderProgressArrow3(GuiGraphicsExtractor guiGraphics, int x, int y) {
        if(menu.isCrafting3()) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,x + 96, y + 37, 177, 0, 4, menu.getScaledArrowProgress3(), 256, 256);
        }
    }
    private void renderProgressArrow4(GuiGraphicsExtractor guiGraphics, int x, int y) {
        if(menu.isCrafting4()) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,x + 114, y + 37, 177, 0, 4, menu.getScaledArrowProgress4(), 256, 256);
        }
    }


}