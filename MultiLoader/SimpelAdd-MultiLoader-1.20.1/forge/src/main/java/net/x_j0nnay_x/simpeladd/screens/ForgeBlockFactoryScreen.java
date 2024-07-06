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
import net.x_j0nnay_x.simpeladd.menu.ForgeBlockFactoryMenu;


public class ForgeBlockFactoryScreen extends AbstractContainerScreen<ForgeBlockFactoryMenu> {
    private static final ResourceLocation texture = new ResourceLocation(SimpelAddModForge.MODID, "textures/screens/blockfactory_gui.png");


    public ForgeBlockFactoryScreen(ForgeBlockFactoryMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
        this.renderWaterToolOverlay(guiGraphics, mouseX, mouseY);
        this.renderLavaToolOverlay(guiGraphics, mouseX, mouseY);
    }
    private static int getFluidHeightW(IFluidTank tank) {
        return (int) (62 * ((float) tank.getFluidAmount() / tank.getCapacity()));
    }

    private int getFluidWY(int fluidHeight) {
        return this.topPos + 25 + (48 - fluidHeight);
    }
    private static int getFluidHeightL(IFluidTank tank) {
        return (int) (62 * ((float) tank.getFluidAmount() / tank.getCapacity()));
    }

    private int getFluidLY(int fluidHeight) {
        return this.topPos + 25 + (48 - fluidHeight);
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

        FluidTank tankW = this.menu.getBlockEntity().getFluidTankW();
        FluidStack fluidStackW = tankW.getFluid();
        if(fluidStackW.isEmpty())
            return;

        IClientFluidTypeExtensions fluidTypeExtensionsW = IClientFluidTypeExtensions.of(fluidStackW.getFluid());
        ResourceLocation stillTextureW = fluidTypeExtensionsW.getStillTexture(fluidStackW);
        if(stillTextureW == null)
            return;

        TextureAtlasSprite spriteW =
                this.minecraft.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(stillTextureW);
        int tintColorW = fluidTypeExtensionsW.getTintColor(fluidStackW);

        float alpha = ((tintColorW >> 24) & 0xFF) / 255f;
        float red = ((tintColorW >> 16) & 0xFF) / 255f;
        float green = ((tintColorW >> 8) & 0xFF) / 255f;
        float blue = (tintColorW & 0xFF) / 255f;

        pGuiGraphics.setColor(red, green, blue, alpha);

        int fluidHeightW = getFluidHeightW(tankW);
        pGuiGraphics.blit(
                this.leftPos + 12,
                getFluidWY(fluidHeightW),
                0,
                13,
                fluidHeightW,
                spriteW);

        pGuiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f);

        FluidTank tankL = this.menu.getBlockEntity().getFluidTankL();
        FluidStack fluidStackL = tankL.getFluid();
        if(fluidStackL.isEmpty())
            return;

        IClientFluidTypeExtensions fluidTypeExtensionsL = IClientFluidTypeExtensions.of(fluidStackL.getFluid());
        ResourceLocation stillTextureL = fluidTypeExtensionsL.getStillTexture(fluidStackL);
        if(stillTextureL == null)
            return;

        TextureAtlasSprite spriteL =
                this.minecraft.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(stillTextureL);
        int tintColorL = fluidTypeExtensionsL.getTintColor(fluidStackL);

        float alpha1 = ((tintColorL >> 24) & 0xFF) / 255f;
        float red1 = ((tintColorL >> 16) & 0xFF) / 255f;
        float green1 = ((tintColorL >> 8) & 0xFF) / 255f;
        float blue1 = (tintColorL & 0xFF) / 255f;

        pGuiGraphics.setColor(red1, green1, blue1, alpha1);

        int fluidHeightL = getFluidHeightL(tankL);
        pGuiGraphics.blit(
                this.leftPos + 152,
                getFluidLY(fluidHeightL),
                0,
                13,
                fluidHeightL,
                spriteL);

        pGuiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.disableBlend();
    }


    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(texture, x + 82, y + 59, 177, 62, 10, menu.getScalledProgress());
        }

    }
    public void renderWaterToolOverlay(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        FluidTank tankW = this.menu.getBlockEntity().getFluidTankW();
        FluidStack fluidStackW = tankW.getFluid();

        if(fluidStackW.isEmpty())
            return;
        int fluidHeightW = getFluidHeightW(tankW);
        if(!isHovering(10, getFluidWY(fluidHeightW) - this.topPos, 16, fluidHeightW, mouseX, mouseY))
            return;
        Component componentW = MutableComponent.create(fluidStackW.getDisplayName().getContents())
                .append("(%s/%s mB)".formatted(tankW.getFluidAmount(), tankW.getCapacity()));
        guiGraphics.renderTooltip(this.font, componentW, mouseX, mouseY);

    }
    public void renderLavaToolOverlay(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        FluidTank tankL = this.menu.getBlockEntity().getFluidTankL();
        FluidStack fluidStackL = tankL.getFluid();

        if(fluidStackL.isEmpty())
            return;
        int fluidHeightL = getFluidHeightL(tankL);
        if(!isHovering(150, getFluidLY(fluidHeightL) - this.topPos, 16, fluidHeightL, mouseX, mouseY))
            return;

        Component componentL = MutableComponent.create(fluidStackL.getDisplayName().getContents())
                .append(" (%s/%s mB)".formatted(tankL.getFluidAmount(), tankL.getCapacity()));
        guiGraphics.renderTooltip(this.font, componentL, mouseX, mouseY);



    }
    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, Component.translatable("gui.simpeladdmod.blockfactory.label1"), 40, 9, -12829636, false);
        guiGraphics.drawString(this.font, Component.translatable("gui.simpeladdmod.blockfactory.label2"), 105, 9, -12829636, false);
    }



}
