package net.x_j0nnay_x.simpeladdmod.block;


import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladdmod.block.entity.*;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;

import java.util.function.Supplier;


public class ModBlockEntities {

	public static final DeferredRegister.Blocks BLOCK_ENTITY = DeferredRegister.createBlocks(Simpeladd.MOD_ID);

	public static final DeferredBlock<BlockEntityType<GrinderBlockEntity>> GRINDER = BLOCK_ENTITY.register("grinder", () ->
		 BlockEntityType.Builder.of(GrinderBlockEntity::new, ModBlocks.GRINDER_BLOCK).build(null));
	public static final DeferredBlock<BlockEntityType<GrinderBlockEntity_upgrade>> GRINDER_UP = BLOCK_ENTITY.register("grinder_up", () ->
			BlockEntityType.Builder.of(GrinderBlockEntity_upgrade::new, ModBlocks.GRINDER_BLOCK_UP.get()).build(null));
	public static final DeferredBlock<BlockEntityType<BlockFactoryBlockEntity>> BLOCK_FACTORY = BLOCK_ENTITY.register("block_factory", () ->
			BlockEntityType.Builder.of(BlockFactoryBlockEntity::new, ModBlocks.BLOCK_FACTORY.get()).build(null));
	public static final DeferredBlock<BlockEntityType<ChillerBlockEntity>> CHILLER = BLOCK_ENTITY.register("chiller", () ->
			BlockEntityType.Builder.of(ChillerBlockEntity::new, ModBlocks.CHILLER.get()).build(null));
	public static final DeferredBlock<BlockEntityType<NetheriteCrafterBlockEntity>> NETHERITE_CRAFTER = BLOCK_ENTITY.register("netherite_crafter", () ->
			BlockEntityType.Builder.of(NetheriteCrafterBlockEntity::new, ModBlocks.NETHERITE_CRAFTER.get()).build(null));
	public static final DeferredBlock<BlockEntityType<StoneSifterBlockEntity>> STONE_SIFTER = BLOCK_ENTITY.register("stone_sifter", () ->
			BlockEntityType.Builder.of(StoneSifterBlockEntity::new, ModBlocks.STONE_SHIFTER.get()).build(null));

	public static DeferredBlock<Block> registerBlock(
			String name, Supplier<Block> block) {
		DeferredBlock<Block> blockReg = BLOCK_ENTITY.register(name, block);
		ModItems.ITEMS.register(name, () -> new BlockItem(blockReg.get(), new Item.Properties()));
		return blockReg;
	}
	public static void register(IEventBus eventBus) {
		BLOCK_ENTITY.register(eventBus);
	}
	public static void registerModBlockEntities(){
		Simpeladd.LOGGER.info("Registering Mod Blocks Entities for " + Simpeladd.MOD_ID);
	}
}
