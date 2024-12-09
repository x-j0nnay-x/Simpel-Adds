package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;
import net.x_j0nnay_x.simpeladd.blocks.entity.*;
import java.util.function.Supplier;

public class ModBlockEntitiesNeoForge {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, SimpelAddMod.MOD_ID);

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

	public static final Supplier<BlockEntityType<NeoForgeTickAcceleratorBlockEntity>> TICK_ACCELERATOR = BLOCK_ENTITY.register(ModNames.Blocks.TICK_ACCELERATOR, () ->
			BlockEntityType.Builder.of(NeoForgeTickAcceleratorBlockEntity::new, ModBlockRegNeoForge.TICK_ACCELERATOR.get()).build(null));

	public static final Supplier<BlockEntityType<NeoForgeToolRepairBlockEntity>> TOOL_REPAIR = BLOCK_ENTITY.register(ModNames.Blocks.TOOLREPAIR, () ->
			BlockEntityType.Builder.of(NeoForgeToolRepairBlockEntity::new, ModBlockRegNeoForge.TOOLREPAIR.get()).build(null));
	public static void register(IEventBus eventBus) {
		SimpelAddMod.modBlockEntRegText();
		BLOCK_ENTITY.register(eventBus);
	}


}
