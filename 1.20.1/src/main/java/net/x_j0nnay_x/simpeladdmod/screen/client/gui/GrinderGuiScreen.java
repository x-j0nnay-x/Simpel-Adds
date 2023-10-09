package net.x_j0nnay_x.simpeladdmod.screen.client.gui;

import net.x_j0nnay_x.simpeladdmod.screen.client.procedures.Grinder.GrinderProgressProcedure;
import net.x_j0nnay_x.simpeladdmod.world.inventory.GrinderGuiMenu;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class GrinderGuiScreen extends AbstractContainerScreen<GrinderGuiMenu> {
	private final static HashMap<String, Object> guistate = GrinderGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public GrinderGuiScreen(GrinderGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("simpeladdmod:textures/screens/grinder_gui.png");

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
		if (GrinderProgressProcedure.progress1(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/grinder_extras/grinder_gui_arrow_1.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (GrinderProgressProcedure.progress2(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/grinder_extras/grinder_gui_arrow_2.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (GrinderProgressProcedure.progress3(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/grinder_extras/grinder_gui_arrow_3.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (GrinderProgressProcedure.progress4(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/grinder_extras/grinder_gui_arrow_4.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (GrinderProgressProcedure.progress5(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/grinder_extras/grinder_gui_arrow_5.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (GrinderProgressProcedure.progress6(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/grinder_extras/grinder_gui_arrow_6.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (GrinderProgressProcedure.progress7(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/grinder_extras/grinder_gui_arrow_7.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (GrinderProgressProcedure.progress8(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/grinder_extras/grinder_gui_arrow_8.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (GrinderProgressProcedure.progress9(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("simpeladdmod:textures/screens/grinder_extras/grinder_gui_arrow_9.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
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
