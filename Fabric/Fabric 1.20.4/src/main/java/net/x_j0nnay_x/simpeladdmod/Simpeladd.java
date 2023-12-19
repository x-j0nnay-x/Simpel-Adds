package net.x_j0nnay_x.simpeladdmod;

import net.fabricmc.api.ModInitializer;

import net.x_j0nnay_x.simpeladdmod.World.Gen.ModWorldGeneration;
import net.x_j0nnay_x.simpeladdmod.block.ModBlockEntities;
import net.x_j0nnay_x.simpeladdmod.block.ModBlocks;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.recipe.ModRecipes;
import net.x_j0nnay_x.simpeladdmod.screen.ModMenuType;
import net.x_j0nnay_x.simpeladdmod.until.ModCreativeTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Simpeladd implements ModInitializer {
	public static final String MOD_ID = "simpeladdmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world! from" + MOD_ID);
		ModCreativeTab.registerCreativeTab();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModMenuType.registerScreenHandlers();
		ModRecipes.registerRecipes();
		ModWorldGeneration.generateModWorldGen();
	}
}