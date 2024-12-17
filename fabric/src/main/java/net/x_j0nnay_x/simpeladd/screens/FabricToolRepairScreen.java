package net.x_j0nnay_x.simpeladd.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;

import net.x_j0nnay_x.simpeladd.menu.FabricToolRepairMenu;

public class FabricToolRepairScreen extends AbstractContainerScreen<FabricToolRepairMenu> {

    private static final ResourceLocation texture = new ResourceLocation(SimpelAddMod.MOD_ID, "textures/screens/toolrepair_gui.png");

    public FabricToolRepairScreen(FabricToolRepairMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int i, int j) {
        super.renderTooltip(guiGraphics, i, j);
        this.renderCopperToolTip(guiGraphics, i, j);

    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int i, int j) {
        guiGraphics.drawString(this.font, Component.literal(""), this.titleLabelX, this.titleLabelY, 4210752, false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
    private void renderCopperToolTip(GuiGraphics guiGraphics, int x, int y){
        int hoverPositionX = x - leftPos;
        int hoverPositionY = y - topPos;
        if (hoverPositionX > 109 && hoverPositionX < 114 && hoverPositionY > 1 && hoverPositionY < 50) {
            Component componentCopper = MutableComponent.create(this.menu.getCopperName().getContents())
                    .append(" (%s/%s Units)".formatted(this.menu.getCoperValue(), this.menu.getMaxCopper()));
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
        renderBlazeUses(pGuiGraphics, x, y);
        RenderSystem.disableBlend();
    }

    private void renderBlazeUses(GuiGraphics guiGraphics, int x, int y) {
        int posisionOffset = 35- menu.getCoperValue();
        guiGraphics.blit(texture,  x + 110 , y + 12 - posisionOffset, 176, posisionOffset, 4, menu.getCopperLevel());
    }
}
