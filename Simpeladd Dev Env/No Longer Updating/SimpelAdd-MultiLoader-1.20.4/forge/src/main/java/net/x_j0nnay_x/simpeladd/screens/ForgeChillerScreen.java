package net.x_j0nnay_x.simpeladd.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.x_j0nnay_x.simpeladd.SimpelAddModForge;
import net.x_j0nnay_x.simpeladd.menu.ForgeChillerMenu;


public class ForgeChillerScreen extends AbstractContainerScreen<ForgeChillerMenu> {
    private static final ResourceLocation texture = new ResourceLocation(SimpelAddModForge.MODID, "textures/screens/chiller_gui.png");
    public ForgeChillerScreen(ForgeChillerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
        this.renderWaterToolOverlay(guiGraphics, mouseX, mouseY);
        if (mouseX > leftPos + 11 && mouseX < leftPos + 35 && mouseY > topPos + 48 && mouseY < topPos + 72)
            guiGraphics.renderTooltip(font, Component.translatable("gui.simpeladdmod.chiller_block_gui.Chilling"), mouseX, mouseY-16);



    }
    @Override
    public void init() {
        super.init();

    }
    private static int getFluidHeight(IFluidTank tank) {
        return (int) (58 * ((float) tank.getFluidAmount() / tank.getCapacity()));
    }

    private int getFluidY(int fluidHeight) {
        return this.topPos + 21 + (48 - fluidHeight);
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
        renderSnow(pGuiGraphics, x, y);
        RenderSystem.disableBlend();

        FluidTank tank = this.menu.getBlockEntity().getFluidTank();
        FluidStack fluidStack = tank.getFluid();
        if(fluidStack.isEmpty())
            return;

        IClientFluidTypeExtensions fluidTypeExtensionsW = IClientFluidTypeExtensions.of(fluidStack.getFluid());
        ResourceLocation stillTextureW = fluidTypeExtensionsW.getStillTexture(fluidStack);
        if(stillTextureW == null)
            return;

        TextureAtlasSprite spriteW =
                this.minecraft.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(stillTextureW);
        int tintColorW = fluidTypeExtensionsW.getTintColor(fluidStack);

        float alpha = ((tintColorW >> 24) & 0xFF) / 255f;
        float red = ((tintColorW >> 16) & 0xFF) / 255f;
        float green = ((tintColorW >> 8) & 0xFF) / 255f;
        float blue = (tintColorW & 0xFF) / 255f;

        pGuiGraphics.setColor(red, green, blue, alpha);

        int fluidHeight = getFluidHeight(tank);
        pGuiGraphics.blit(
                this.leftPos + 74,
                getFluidY(fluidHeight),
                0,
                15,
                fluidHeight,
                spriteW);

        pGuiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }
    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, Component.translatable("gui.simpeladdmod.chiller_block_gui.label"), 114, 9, -12829636, false);
    }
    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()){
            guiGraphics.blit(texture,  x + 99, y + 42, 177, 62, menu.getScalledProgress(), 16);
        }
    }
    private void renderSnow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.hasSnow()) {
            guiGraphics.blit(texture, x + 38, y + 69 - menu.getScalledsnow(), 195, 1, 7, menu.getScalledsnow());
        }
    }
    public void renderWaterToolOverlay(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        FluidTank tankW = this.menu.getBlockEntity().getFluidTank();
        FluidStack fluidStackW = tankW.getFluid();

        if(fluidStackW.isEmpty())
            return;
        int fluidHeightW = getFluidHeight(tankW);
        if(!isHovering(72, getFluidY(fluidHeightW) - this.topPos, 16, fluidHeightW, mouseX, mouseY))
            return;
        Component componentW = MutableComponent.create(fluidStackW.getDisplayName().getContents())
                .append(" (%s/%s mB)".formatted(tankW.getFluidAmount(), tankW.getCapacity()));
        guiGraphics.renderTooltip(this.font, componentW, mouseX, mouseY);

    }

}
