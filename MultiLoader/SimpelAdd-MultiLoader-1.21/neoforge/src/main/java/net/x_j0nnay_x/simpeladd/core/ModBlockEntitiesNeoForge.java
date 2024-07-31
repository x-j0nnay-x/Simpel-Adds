package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;
import net.x_j0nnay_x.simpeladd.blocks.entity.*;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ModBlockEntitiesNeoForge {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, SimpelAddModNeoForge.MODID);

	public static final Supplier<BlockEntityType<NeoForgeBlockFactoryBlockEntity>> BLOCK_FACTORY = BLOCK_ENTITY.register("block_factory", () ->
			BlockEntityType.Builder.of(NeoForgeBlockFactoryBlockEntity::new, ModBlockRegNeoForge.BLOCK_FACTORY.get()).build(null));

	public static final Supplier<BlockEntityType<NeoForgeChillerBlockEntity>> CHILLER = BLOCK_ENTITY.register("chiller", () ->
			BlockEntityType.Builder.of(NeoForgeChillerBlockEntity::new, ModBlockRegNeoForge.CHILLER.get()).build(null));

	public static final Supplier<BlockEntityType<NeoForgeGrinderBlockEntity>> GRINDER = BLOCK_ENTITY.register("grinder", () ->
			BlockEntityType.Builder.of(NeoForgeGrinderBlockEntity::new, ModBlockRegNeoForge.GRINDER_BLOCK.get()).build(null));

	public static final Supplier<BlockEntityType<NeoForgeGrinderBlockEntity_Up>> GRINDER_UP = BLOCK_ENTITY.register("grinder_up", () ->
			BlockEntityType.Builder.of(NeoForgeGrinderBlockEntity_Up::new, ModBlockRegNeoForge.GRINDER_BLOCK_UP.get()).build(null));

	public static final Supplier<BlockEntityType<NeoForgeGrindFactoryBlockEntity>> GRIND_FACTORY = BLOCK_ENTITY.register("grinder_factory", () ->
			BlockEntityType.Builder.of(NeoForgeGrindFactoryBlockEntity::new, ModBlockRegNeoForge.GRIND_FACTORY_BLOCK.get()).build(null));

	public static final Supplier<BlockEntityType<NeoForgeNetheriteCrafterBlockEntity>> NETHERITE_CRAFTER = BLOCK_ENTITY.register("netherite_crafter", () ->
			BlockEntityType.Builder.of(NeoForgeNetheriteCrafterBlockEntity::new, ModBlockRegNeoForge.NETHERITE_CRAFTER.get()).build(null));

	public static final Supplier<BlockEntityType<NeoForgeFurnaceBlockEntity_Up>> UPGRADED_FURNACE = BLOCK_ENTITY.register("upgraded_furnace", () ->
			BlockEntityType.Builder.of(NeoForgeFurnaceBlockEntity_Up::new, ModBlockRegNeoForge.UPGRADED_FURNACE.get()).build(null));


	public static void register(IEventBus eventBus) {
		BLOCK_ENTITY.register(eventBus);
	}

	public static void registerModBlockEntities(){
		SimpelAddModNeoForge.LOGGER.info("Registering Mod Blocks Entities for " + SimpelAddModNeoForge.MODID);
	}

}
