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
import net.x_j0nnay_x.simpeladdmod.screen.client.procedures.Chiller.ChillerProgressProcedure;
import net.x_j0nnay_x.simpeladdmod.screen.client.procedures.Chiller.ChillerSnowlevProcedure;
import net.x_j0nnay_x.simpeladdmod.screen.client.procedures.Chiller.ChillerWaterLevel;
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


		if (ChillerSnowlevProcedure.level1(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_1.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerSnowlevProcedure.level2(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_2.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerSnowlevProcedure.level3(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_3.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerSnowlevProcedure.level4(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_4.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerSnowlevProcedure.level5(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_5.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerSnowlevProcedure.level6(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_6.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerSnowlevProcedure.level7(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_7.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerSnowlevProcedure.level8(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_8.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerSnowlevProcedure.level9(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_9.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerSnowlevProcedure.level10(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_10.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerSnowlevProcedure.level11(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_11.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerSnowlevProcedure.level12(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_12.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerSnowlevProcedure.level13(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_13.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerSnowlevProcedure.level14(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_14.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerSnowlevProcedure.level15(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_15.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerSnowlevProcedure.level16(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_16.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerSnowlevProcedure.level17(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_17.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerSnowlevProcedure.level18(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_18.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerSnowlevProcedure.level19(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_19.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerSnowlevProcedure.level20(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_snow_20.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerWaterLevel.level1(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_water1.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerWaterLevel.level2(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_water2.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerWaterLevel.level3(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_water3.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerWaterLevel.level4(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_water4.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerWaterLevel.level5(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_water5.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerWaterLevel.level6(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_water6.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerProgressProcedure.progress1(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_progress1.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerProgressProcedure.progress2(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_progress2.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerProgressProcedure.progress3(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_progress3.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerProgressProcedure.progress4(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_progress4.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerProgressProcedure.progress5(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_progress5.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerProgressProcedure.progress6(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_progress6.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (ChillerProgressProcedure.progress7(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/chiller_extras/chiller_block_gui_progress7.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
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
