package net.x_j0nnay_x.simpeladdmod.screen.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;


import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import net.x_j0nnay_x.simpeladdmod.screen.client.procedures.BlockFactory.*;
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


	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);


		if (BlockFactoryLavaLevelProcedure.level1(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/blockfactory_extras/cobble_gen_lava_1.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (BlockFactoryLavaLevelProcedure.level2(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/blockfactory_extras/cobble_gen_lava_2.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (BlockFactoryLavaLevelProcedure.level3(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/blockfactory_extras/cobble_gen_lava_3.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (BlockFactoryLavaLevelProcedure.level4(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/blockfactory_extras/cobble_gen_lava_4.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (BlockFactoryLavaLevelProcedure.level5(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/blockfactory_extras/cobble_gen_lava_5.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (BlockFactoryLavaLevelProcedure.level6(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/blockfactory_extras/cobble_gen_lava_6.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (BlockFactoryWaterLevelProcedure.level1(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/blockfactory_extras/cobble_gen_water_1.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (BlockFactoryWaterLevelProcedure.level2(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/blockfactory_extras/cobble_gen_water_2.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (BlockFactoryWaterLevelProcedure.level3(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/blockfactory_extras/cobble_gen_water_3.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (BlockFactoryWaterLevelProcedure.level4(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/blockfactory_extras/cobble_gen_water_4.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (BlockFactoryWaterLevelProcedure.level5(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/blockfactory_extras/cobble_gen_water_5.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (BlockFactoryWaterLevelProcedure.level6(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/blockfactory_extras/cobble_gen_water_6.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (BlockFactoryProgress.progress1(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/blockfactory_extras/blockfactory_progress_1.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (BlockFactoryProgress.progress2(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/blockfactory_extras/blockfactory_progress_2.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (BlockFactoryProgress.progress3(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/blockfactory_extras/blockfactory_progress_3.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (BlockFactoryProgress.progress4(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/blockfactory_extras/blockfactory_progress_4.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (BlockFactoryProgress.progress5(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/blockfactory_extras/blockfactory_progress_5.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
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
