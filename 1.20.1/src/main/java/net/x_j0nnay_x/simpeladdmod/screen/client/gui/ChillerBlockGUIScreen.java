package net.x_j0nnay_x.simpeladdmod.screen.client.gui;

import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;



import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import net.x_j0nnay_x.simpeladdmod.block.custom.ChillerBlockBlock;
import net.x_j0nnay_x.simpeladdmod.world.inventory.ChillerBlockGUIMenu;

public class ChillerBlockGUIScreen extends AbstractContainerScreen<ChillerBlockGUIMenu> {
	private final static HashMap<String, Object> guistate = ChillerBlockGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public ChillerBlockGUIScreen(ChillerBlockGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("simpeladdmod:textures/screens/chiller_block_gui.png");
	private static final ResourceLocation progress = new ResourceLocation("simpeladdmod:textures/screens/sprites/chiller_prog.png");
	private static final ResourceLocation snowLev = new ResourceLocation("simpeladdmod:textures/screens/sprites/chiller_snow_lev.png");
	private static final ResourceLocation WaterLev = new ResourceLocation("simpeladdmod:textures/screens/sprites/chiller_water_lev.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 11 && mouseX < leftPos + 35 && mouseY > topPos + 48 && mouseY < topPos + 72)
			guiGraphics.renderTooltip(font, Component.translatable("gui.simpeladdmod.chiller_block_gui.Chilling"), mouseX, mouseY-16);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		int prog = Mth.ceil(ChillerBlockBlock.PROGRESS * 0.46f);
		int snow = Mth.ceil(ChillerBlockBlock.SNOW * 2.9f);
		int water = Mth.ceil(ChillerBlockBlock.WATER * 9.83f);

		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(progress, this.leftPos + 99, this.topPos + 43, 0, 0, prog, 11, 28, 11);
		guiGraphics.blit(snowLev, this.leftPos + 38, this.topPos + 69 - snow, 0, 0, 7, snow, 7, 58);
		guiGraphics.blit(WaterLev, this.leftPos + 73, this.topPos+ 70 - water, 0, 0, 16, water, 16, 59);


		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.simpeladdmod.chiller_block_gui.label"), 114, 9, -12829636, false);
		}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
	}
}
