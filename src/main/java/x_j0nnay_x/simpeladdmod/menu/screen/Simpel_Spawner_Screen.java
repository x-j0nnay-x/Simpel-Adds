package x_j0nnay_x.simpeladdmod.menu.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import x_j0nnay_x.simpeladdmod.init.Mod_Constants;
import x_j0nnay_x.simpeladdmod.menu.Simpel_Spawner_Menu;

public class Simpel_Spawner_Screen extends AbstractContainerScreen<Simpel_Spawner_Menu> {
    private static final Identifier GUI_TEXTURE = Mod_Constants.id("textures/screens/simpel_spawner_gui.png");

    public Simpel_Spawner_Screen(Simpel_Spawner_Menu menu, Inventory inventory, Component title) {
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
        renderCopper(graphics, x, y);
    }
    private void renderProgressArrow(GuiGraphicsExtractor guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            int posisionOffset = 43 - menu.getScalledProgress();
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,x + 166, y + 26 + posisionOffset, 183, posisionOffset, 2, menu.getScalledProgress(), 256, 256);
        }
    }
    @Override
    protected void extractTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        super.extractTooltip(graphics, mouseX, mouseY);
        renderBurnToolTip(graphics, mouseX, mouseY);
        renderCopperToolTip(graphics,mouseX,mouseY);
    }

    private void renderCopper(GuiGraphicsExtractor guiGraphics, int x, int y) {
        int posisionOffset = 46 - menu.getCopperLevel();
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,x + 158, y + 25 + posisionOffset, 177, posisionOffset,  4, menu.getCopperLevel(), 256, 256);

    }
    private void renderCopperToolTip(GuiGraphicsExtractor guiGraphics, int x, int y){
        int hoverPositionX = x - leftPos;
        int hoverPositionY = y - topPos;
        if (hoverPositionX > 157 && hoverPositionX < 163 && hoverPositionY > 24 && hoverPositionY < 72) {
            Component componentCopper = MutableComponent.create(this.menu.getCopperName().getContents())
                    .append(" (%s/%s)".formatted(this.menu.getCoperValue(), 100));
            guiGraphics.setTooltipForNextFrame(font, componentCopper,x ,y + 12);
        }
    }
    private void renderBurnToolTip(GuiGraphicsExtractor guiGraphics, int x, int y){
        int hoverPositionX = x - leftPos;
        int hoverPositionY = y - topPos;
        if (hoverPositionX > 165 && hoverPositionX < 169 && hoverPositionY > 25 && hoverPositionY < 70) {
            Component componentCopper = MutableComponent.create(this.menu.getburnName().getContents())
                    .append(" (%s/%s)".formatted(this.menu.getBurnValue(), this.menu.getMaxBurn()));
            guiGraphics.setTooltipForNextFrame(font, componentCopper,x ,y + 12);
        }
    }

}