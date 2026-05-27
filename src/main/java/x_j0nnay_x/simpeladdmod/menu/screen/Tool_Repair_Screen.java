package x_j0nnay_x.simpeladdmod.menu.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import x_j0nnay_x.simpeladdmod.init.Mod_Constants;
import x_j0nnay_x.simpeladdmod.menu.Tool_Repair_Menu;

public class Tool_Repair_Screen extends AbstractContainerScreen<Tool_Repair_Menu> {
    private static final Identifier GUI_TEXTURE = Mod_Constants.id("textures/screens/tool_repair_gui.png");

    public Tool_Repair_Screen(Tool_Repair_Menu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelX = 5;
        this.titleLabelX = 5;
    }
    @Override
    protected void extractTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        super.extractTooltip(graphics, mouseX, mouseY);
        renderCopperToolTip(graphics,mouseX,mouseY);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
        graphics.text(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, -12566464, false);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x -22, y + 68, 229, 68, 27, 98, 256, 256);
        renderCopper(graphics, x, y);
    }

    private void renderCopper(GuiGraphicsExtractor guiGraphics, int x, int y) {
        int posisionOffset = 37 - menu.getCopperLevel();
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x + 110, y + 12 + posisionOffset, 176, posisionOffset, 4, menu.getCopperLevel(), 256, 256);
    }

    private void renderCopperToolTip(GuiGraphicsExtractor guiGraphics, int x, int y){
        int hoverPositionX = x - leftPos;
        int hoverPositionY = y - topPos;
        if (hoverPositionX > 109 && hoverPositionX < 114 && hoverPositionY > 11 && hoverPositionY < 50) {
            Component componentCopper = MutableComponent.create(this.menu.getCopperName().getContents())
                    .append(" (%s/%s)".formatted(this.menu.getCoperValue(), this.menu.getMaxCopper()));
            guiGraphics.setTooltipForNextFrame(font, componentCopper,x ,y + 12);
        }
    }

}