package net.x_j0nnay_x.simpeladd.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.menu.NeoForgeTickAcceleratorMenu;


public class NeoForgeTickAcceleratorScreen extends AbstractContainerScreen<NeoForgeTickAcceleratorMenu> {

    private static final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, "textures/screens/tick_accelerator_gui.png");

    public NeoForgeTickAcceleratorScreen(NeoForgeTickAcceleratorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int i, int j) {
        super.renderTooltip(guiGraphics, i, j);
        this.renderCopperToolTip(guiGraphics, i, j);
        this.renderBurnToolTip(guiGraphics, i, j);

    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int i, int j) {
        guiGraphics.drawString(this.font, Component.literal(""), this.titleLabelX, this.titleLabelY, 4210752, false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
    private void renderCopperToolTip(GuiGraphics guiGraphics, int x, int y){
        int hoverPositionX = x - leftPos;
        int hoverPositionY = y - topPos;
        if (hoverPositionX > 106 && hoverPositionX < 111 && hoverPositionY > 11 && hoverPositionY < 50) {
            Component componentCopper = MutableComponent.create(this.menu.getCopperName().getContents())
                    .append(" (%s/%s)".formatted(this.menu.getCoperValue(), this.menu.getMaxCopper()));
            guiGraphics.renderTooltip(font, componentCopper,x ,y + 12);
        }
    }
    private void renderBurnToolTip(GuiGraphics guiGraphics, int x, int y){
        int hoverPositionX = x - leftPos;
        int hoverPositionY = y - topPos;
        if (hoverPositionX > 113 && hoverPositionX < 118 && hoverPositionY > 11 && hoverPositionY < 50) {
            Component componentCopper = MutableComponent.create(this.menu.getburnName().getContents())
                    .append(" (%s/%s)".formatted(this.menu.getBurnValue(), 3600));
            guiGraphics.renderTooltip(font, componentCopper,x ,y + 12);
        }
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        pGuiGraphics.blit(texture, x, y, 0, 0, imageWidth, imageHeight);
        renderCopper(pGuiGraphics, x, y);
        renderBurn(pGuiGraphics, x, y);
        RenderSystem.disableBlend();
    }

    private void renderCopper(GuiGraphics guiGraphics, int x, int y) {
        int posisionOffset = 37 - menu.getCopperLevel();
        guiGraphics.blit(texture, x + 107, y + 12 + posisionOffset, 176, posisionOffset, 4, menu.getCopperLevel());
    }

    private void renderBurn(GuiGraphics guiGraphics, int x, int y) {
        int posisionOffset = 35 - menu.getBunrLevel();
        guiGraphics.blit(texture, x + 115, y + 13 + posisionOffset, 181, posisionOffset, 2, menu.getBunrLevel());
    }
}
