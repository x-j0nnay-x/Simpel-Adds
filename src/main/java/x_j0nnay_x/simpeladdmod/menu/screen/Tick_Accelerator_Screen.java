package x_j0nnay_x.simpeladdmod.menu.screen;


import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import x_j0nnay_x.simpeladdmod.init.Mod_Constants;
import x_j0nnay_x.simpeladdmod.menu.Tick_Accelerator_Menu;

public class Tick_Accelerator_Screen extends AbstractContainerScreen<Tick_Accelerator_Menu> {
    private static final Identifier GUI_TEXTURE = Mod_Constants.id("textures/screens/tick_accelerator_gui.png");

    public Tick_Accelerator_Screen(Tick_Accelerator_Menu menu, Inventory inventory, Component title) {
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
        graphics.text(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, -12566464, false);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
    }
    @Override
    protected void extractTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        super.extractTooltip(graphics, mouseX, mouseY);
        renderBurnToolTip(graphics, mouseX, mouseY);
        renderCopperToolTip(graphics,mouseX,mouseY);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
        renderCopper(graphics, x, y);
        renderBurn(graphics, x, y);
    }

    private void renderCopper(GuiGraphicsExtractor guiGraphics, int x, int y) {
        int posisionOffset = 37 - menu.getCopperLevel();
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x + 107, y + 12 + posisionOffset, 176, posisionOffset, 4, menu.getCopperLevel(), 256, 256);
    }

    private void renderBurn(GuiGraphicsExtractor guiGraphics, int x, int y) {
        int posisionOffset = 35 - menu.getBunrLevel();
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x + 115, y + 13 + posisionOffset, 181, posisionOffset, 2, menu.getBunrLevel(), 256, 256);
    }

    private void renderCopperToolTip(GuiGraphicsExtractor guiGraphics, int x, int y){
        int hoverPositionX = x - leftPos;
        int hoverPositionY = y - topPos;
        if (hoverPositionX > 106 && hoverPositionX < 111 && hoverPositionY > 11 && hoverPositionY < 50) {
            Component componentCopper = MutableComponent.create(this.menu.getCopperName().getContents())
                    .append(" (%s/%s)".formatted(this.menu.getCoperValue(), this.menu.getMaxCopper()));
            guiGraphics.setTooltipForNextFrame(font, componentCopper,x ,y + 12);
        }
    }
    private void renderBurnToolTip(GuiGraphicsExtractor guiGraphics, int x, int y){
        int hoverPositionX = x - leftPos;
        int hoverPositionY = y - topPos;
        if (hoverPositionX > 113 && hoverPositionX < 118 && hoverPositionY > 11 && hoverPositionY < 50) {
            Component componentCopper = MutableComponent.create(this.menu.getburnName().getContents())
                    .append(" (%s/%s)".formatted(this.menu.getBurnValue(), 3600));
            guiGraphics.setTooltipForNextFrame(font, componentCopper,x ,y + 12);
        }
    }

}