package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;
import net.x_j0nnay_x.simpeladd.blocks.entity.*;
import java.util.function.Supplier;

public class ModBlockEntitiesNeoForge {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, SimpelAddModNeoForge.MODID);

	public static final Supplier<BlockEntityType<NeoForgeBlockFactoryBlockEntity>> BLOCK_FACTORY = BLOCK_ENTITY.register(ModNames.Blocks.BLOCKFACTORY, () ->
			BlockEntityType.Builder.of(NeoForgeBlockFactoryBlockEntity::new, ModBlockRegNeoForge.BLOCK_FACTORY.get()).build(null));

	public static final Supplier<BlockEntityType<NeoForgeChillerBlockEntity>> CHILLER = BLOCK_ENTITY.register(ModNames.Blocks.CHILLER, () ->
			BlockEntityType.Builder.of(NeoForgeChillerBlockEntity::new, ModBlockRegNeoForge.CHILLER.get()).build(null));

	public static final Supplier<BlockEntityType<NeoForgeGrinderBlockEntity>> GRINDER = BLOCK_ENTITY.register(ModNames.Blocks.GRINDER, () ->
			BlockEntityType.Builder.of(NeoForgeGrinderBlockEntity::new, ModBlockRegNeoForge.GRINDER_BLOCK.get()).build(null));

	public static final Supplier<BlockEntityType<NeoForgeGrinderBlockEntity_Up>> GRINDER_UP = BLOCK_ENTITY.register(ModNames.Blocks.GRINDER_UP, () ->
			BlockEntityType.Builder.of(NeoForgeGrinderBlockEntity_Up::new, ModBlockRegNeoForge.GRINDER_BLOCK_UP.get()).build(null));

	public static final Supplier<BlockEntityType<NeoForgeGrindFactoryBlockEntity>> GRIND_FACTORY = BLOCK_ENTITY.register(ModNames.Blocks.GRINDFACTORY, () ->
			BlockEntityType.Builder.of(NeoForgeGrindFactoryBlockEntity::new, ModBlockRegNeoForge.GRIND_FACTORY_BLOCK.get()).build(null));

	public static final Supplier<BlockEntityType<NeoForgeNetheriteCrafterBlockEntity>> NETHERITE_CRAFTER = BLOCK_ENTITY.register(ModNames.Blocks.NETHERITE_CRAFTER, () ->
			BlockEntityType.Builder.of(NeoForgeNetheriteCrafterBlockEntity::new, ModBlockRegNeoForge.NETHERITE_CRAFTER.get()).build(null));

	public static final Supplier<BlockEntityType<NeoForgeFurnaceBlockEntity_Up>> UPGRADED_FURNACE = BLOCK_ENTITY.register(ModNames.Blocks.UPGRADED_FURNACE, () ->
			BlockEntityType.Builder.of(NeoForgeFurnaceBlockEntity_Up::new, ModBlockRegNeoForge.UPGRADED_FURNACE.get()).build(null));

	public static void register(IEventBus eventBus) {
		BLOCK_ENTITY.register(eventBus);
	}

	public static void registerModBlockEntities(){
		SimpelAddModNeoForge.LOGGER.info("Registering Mod Blocks Entities for " + SimpelAddModNeoForge.MODID);
	}
}
