package x_j0nnay_x.simpeladdmod.menu.screen;


import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import x_j0nnay_x.simpeladdmod.menu.Grinder_Menu;
import x_j0nnay_x.simpeladdmod.init.Mod_Constants;

public class Grinder_Screen  extends AbstractContainerScreen<Grinder_Menu> {
    private static final Identifier GUI_TEXTURE = Mod_Constants.id("textures/screens/grinder_gui.png");

    public Grinder_Screen(Grinder_Menu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelX = 5;
        this.titleLabelX = 5;
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
        renderProgressArrow(graphics, x, y);
    }

    private void renderProgressArrow(GuiGraphicsExtractor guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,x + 60, y + 44, 177, 0, menu.getScaledArrowProgress(), 16, 256, 256);
        }
    }


}