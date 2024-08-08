package net.x_j0nnay_x.simpeladd.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.x_j0nnay_x.simpeladd.SimpelAddModFabric;
import net.x_j0nnay_x.simpeladd.menu.FabricBlockFactoryMenu;

import java.util.function.Supplier;


public class FabricBlockFactoryScreen extends AbstractContainerScreen<FabricBlockFactoryMenu> {
    private static final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, "textures/screens/blockfactory_gui.png");
    private Button button;

    public FabricBlockFactoryScreen(FabricBlockFactoryMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);

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
        //renderButton(x,y);
        pGuiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.disableBlend();
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

    private void  renderButton(int x, int y){

        this.button =

                addRenderableWidget(
                        ImageButton.builder(
               // Button.builder(
                                Component.literal(Integer.toString(menu.getOutPutSlot())),
                                this::handleButton

                        )
                        .bounds(x + 56 , y + 59, 12 , 12)
                        .tooltip(Tooltip.create(Component.translatable("gui.simpeladdmod.blockfactory.button.tooltip")))
                        .build()
        );

    }

    private void handleButton(Button button){
        int cereentslot = this.menu.getOutPutSlot();
         if (cereentslot == 4) {
             this.menu.setOutPutSlot(1);
         }
         if (cereentslot == 1) {
             this.menu.setOutPutSlot(2);
         }
         if (cereentslot == 2) {
             this.menu.setOutPutSlot(3);
         }
         if (cereentslot == 3) {
             this.menu.setOutPutSlot(4);
         }



    }
}
