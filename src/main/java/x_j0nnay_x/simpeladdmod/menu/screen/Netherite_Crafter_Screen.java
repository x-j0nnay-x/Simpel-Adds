package x_j0nnay_x.simpeladdmod.menu.screen;


import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import x_j0nnay_x.simpeladdmod.init.Mod_Constants;
import x_j0nnay_x.simpeladdmod.menu.Netherite_Crafter_Menu;

public class Netherite_Crafter_Screen extends AbstractContainerScreen<Netherite_Crafter_Menu> {
    private static final Identifier GUI_TEXTURE = Mod_Constants.id("textures/screens/netherite_crafter_gui.png");

    public Netherite_Crafter_Screen(Netherite_Crafter_Menu menu, Inventory inventory, Component title) {
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
        renderBlazeUses(graphics, x, y);
    }

    private void renderProgressArrow(GuiGraphicsExtractor guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,x + 43, y + 59, 0, 168, menu.getScaledArrowProgress(), 6, 256, 256);
        }
    }

    private void renderBlazeUses(GuiGraphicsExtractor guiGraphics, int x, int y) {
        int posisionOffset = 18 - menu.getBlazeLevel();
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,  x + 107 , y + 34 + posisionOffset, 176, posisionOffset, 4, menu.getBlazeLevel(), 256, 256);

    }

}