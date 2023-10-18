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
import net.x_j0nnay_x.simpeladdmod.block.custom.BlockFactoryBlock;
import net.x_j0nnay_x.simpeladdmod.world.inventory.BlockfactoryMenu;

public class BlockfactoryScreen extends AbstractContainerScreen<BlockfactoryMenu> {
	private final static HashMap<String, Object> guistate = BlockfactoryMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public BlockfactoryScreen(BlockfactoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("simpeladdmod:textures/screens/blockfactory.png");
	private static final ResourceLocation progress = new ResourceLocation("simpeladdmod:textures/screens/sprites/blockfactory_prog.png");
	private static final ResourceLocation lavaLevel = new ResourceLocation("simpeladdmod:textures/screens/sprites/blockfactory_lava_level.png");
	private static final ResourceLocation waterLevel = new ResourceLocation("simpeladdmod:textures/screens/sprites/blockfactory_water_level.png");


	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		int prog = Mth.ceil(BlockFactoryBlock.progress * 0.4f);
		int lava = Mth.ceil(BlockFactoryBlock.lavaLev * 10.16f);
		int water = Mth.ceil(BlockFactoryBlock.waterLev * 10.16f);


		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(progress, this.leftPos + 82, this.topPos + 59, 0, 0, 10, prog, 10, 12);
		guiGraphics.blit(lavaLevel, this.leftPos + 152, this.topPos+ 72 - lava, 0, 0, 13, lava, 13, 61);
		guiGraphics.blit(waterLevel, this.leftPos + 12, this.topPos+ 72 - water, 0, 0, 13, water, 13, 61);

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
		guiGraphics.drawString(this.font, Component.translatable("gui.simpeladdmod.blockfactory.label1"), 40, 9, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.simpeladdmod.blockfactory.label2"), 105, 9, -12829636, false);
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
