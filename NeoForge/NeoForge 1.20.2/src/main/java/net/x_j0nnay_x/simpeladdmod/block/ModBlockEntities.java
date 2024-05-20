package net.x_j0nnay_x.simpeladdmod.block;


import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladdmod.block.entity.*;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;


public class ModBlockEntities {
	public static final DeferredRegister.Blocks BLOCK_ENTITY = DeferredRegister.createBlocks(Simpeladd.MOD_ID + "_BlockEntity");


	public static final DeferredBlock<BlockEntityType<Upgrade_Furnace_BlockEntity>> UPGRADED_FURNACE = BLOCK_ENTITY.register("upgraded_furnace", () ->
			BlockEntityType.Builder.of(Upgrade_Furnace_BlockEntity::new, ModBlocks.UPGRADED_FURNACE.get()).build(null));
	public static final DeferredBlock<BlockEntityType<GrinderBlockEntity>> GRINDER = BLOCK_ENTITY.register("grinder", () ->
		 BlockEntityType.Builder.of(GrinderBlockEntity::new, ModBlocks.GRINDER_BLOCK.get()).build(null));
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


	public static void register(IEventBus eventBus) {
		BLOCK_ENTITY.register(eventBus);
	}
	public static void registerModBlockEntities(){
		Simpeladd.LOGGER.info("Registering Mod Blocks Entities for " + Simpeladd.MOD_ID);
	}
}
