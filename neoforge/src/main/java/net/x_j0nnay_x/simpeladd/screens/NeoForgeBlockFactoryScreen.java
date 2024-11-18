package net.x_j0nnay_x.simpeladd.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;
import net.x_j0nnay_x.simpeladd.menu.NeoForgeBlockFactoryMenu;
import net.x_j0nnay_x.simpeladd.network.NeoForgeMessageSlotChange;
import net.x_j0nnay_x.simpeladd.network.NeoForgeNetworkMessage;

public class NeoForgeBlockFactoryScreen extends AbstractContainerScreen<NeoForgeBlockFactoryMenu> {

    private static final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, "textures/screens/blockfactory_gui.png");
    private int ButtonY = 166;
    private int ButtonSize = 12;
    private int buttonPosX = 56;
    private int buttonPosY = 57;
    private Button button;
    BlockPos pos = new BlockPos(this.menu.blockEntity.getBlockPos().getX(),this.menu.blockEntity.getBlockPos().getY(),this.menu.blockEntity.getBlockPos().getZ());

    public NeoForgeBlockFactoryScreen(NeoForgeBlockFactoryMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int x, int y) {
       super.renderTooltip(guiGraphics, x, y);
       renderButtonToolTip(guiGraphics, x, y);
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
        renderlava(pGuiGraphics, x, y);
        renderwater(pGuiGraphics, x, y);
        renderButton(pGuiGraphics, x, y);
        pGuiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.disableBlend();
    }

    private void  renderButtonToolTip(GuiGraphics guiGraphics, int x, int y){
        int hoverPositionX = x - leftPos;
        int hoverPositionY = y - topPos;
        if (hoverPositionX > this.buttonPosX
                && hoverPositionX < this.buttonPosX + this.ButtonSize
                && hoverPositionY > this.buttonPosY
                && hoverPositionY < this.buttonPosY + this.ButtonSize) {
            guiGraphics.renderTooltip(font, Component.translatable("gui.simpeladdmod.blockfactory.button.tooltip"),x ,y + 12);
        }
    }

    public void renderButton(GuiGraphics guiGraphics, int x, int y) {
        funtionButton(x,y);
        guiGraphics.blit(texture, x + this.buttonPosX,y + this.buttonPosY, this.menu.getButtonPosX(), this.ButtonY, this.ButtonSize, this.ButtonSize);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(texture, x + 82, y + 59, 177, 62, 10, menu.getScalledProgress());
        }
    }

    private void renderwater(GuiGraphics guiGraphics, int x, int y) {
        if(menu.hasWater()) {
            guiGraphics.blit(texture, x + 12, y + 72 - menu.getScalledwater(), 191, 0, 13, menu.getScalledwater());
        }
    }

    private void renderlava(GuiGraphics guiGraphics, int x, int y) {
        if(menu.hasLava()) {
            guiGraphics.blit(texture, x + 152, y + 72 - menu.getScalledlava(), 177, 0, 13, menu.getScalledlava());
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, Component.translatable("gui.simpeladdmod.blockfactory.label1"), 40, 9, -12829636, false);
        guiGraphics.drawString(this.font, Component.translatable("gui.simpeladdmod.blockfactory.label2"), 105, 9, -12829636, false);
    }

    private void  funtionButton(int x, int y){

        this.button = addWidget(ImageButton.builder(
                    Component.literal(""),
                    this::handleButton)
                        .bounds(x + this.buttonPosX , y + this.buttonPosY, 12 , 12)
                        .tooltip(Tooltip.create(Component.literal("")))
                        .build());
    }

    private void handleButton(Button button){
        int cereentslot = this.menu.getOutPutSlot();
        if (cereentslot == 4) {
            NeoForgeNetworkMessage.sendToServer(new NeoForgeMessageSlotChange(pos.getX(), pos.getY(), pos.getZ(), 8, 0));
            NeoForgeNetworkMessage.sendToServer(new NeoForgeMessageSlotChange(pos.getX(), pos.getY(), pos.getZ(),6, 0));
        }
        if (cereentslot == 0) {
            NeoForgeNetworkMessage.sendToServer(new NeoForgeMessageSlotChange(pos.getX(), pos.getY(), pos.getZ(),8, 1));
            NeoForgeNetworkMessage.sendToServer(new NeoForgeMessageSlotChange(pos.getX(), pos.getY(), pos.getZ(),6, 1));
        }
        if (cereentslot == 1) {
            NeoForgeNetworkMessage.sendToServer(new NeoForgeMessageSlotChange(pos.getX(), pos.getY(), pos.getZ(),6, 2));
        }
        if (cereentslot == 2) {
            NeoForgeNetworkMessage.sendToServer(new NeoForgeMessageSlotChange(pos.getX(), pos.getY(), pos.getZ(),6, 3));
        }
        if (cereentslot == 3) {
            NeoForgeNetworkMessage.sendToServer(new NeoForgeMessageSlotChange(pos.getX(), pos.getY(), pos.getZ(),6, 4));
        }
    }
}