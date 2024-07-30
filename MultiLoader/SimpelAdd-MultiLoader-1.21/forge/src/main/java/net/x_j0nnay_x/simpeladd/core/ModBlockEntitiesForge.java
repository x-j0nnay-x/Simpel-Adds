package net.x_j0nnay_x.simpeladd.core;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.world.level.block.entity.BlockEntityType;

import net.x_j0nnay_x.simpeladd.SimpelAddModForge;
import net.x_j0nnay_x.simpeladd.blocks.entity.*;



public class ModBlockEntitiesForge {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SimpelAddModForge.MODID);

	public static final RegistryObject<BlockEntityType<ForgeBlockFactoryBlockEntity>> BLOCK_FACTORY = BLOCK_ENTITY.register("block_factory", () ->
			BlockEntityType.Builder.of(ForgeBlockFactoryBlockEntity::new, ModBlockRegForge.BLOCK_FACTORY.get()).build(null));

	public static final RegistryObject<BlockEntityType<ForgeChillerBlockEntity>> CHILLER = BLOCK_ENTITY.register("chiller", () ->
			BlockEntityType.Builder.of(ForgeChillerBlockEntity::new, ModBlockRegForge.CHILLER.get()).build(null));

	public static final RegistryObject<BlockEntityType<ForgeGrinderBlockEntity>> GRINDER = BLOCK_ENTITY.register("grinder", () ->
			BlockEntityType.Builder.of(ForgeGrinderBlockEntity::new, ModBlockRegForge.GRINDER_BLOCK.get()).build(null));

	public static final RegistryObject<BlockEntityType<ForgeGrinderBlockEntity_Up>> GRINDER_UP = BLOCK_ENTITY.register("grinder_up", () ->
			BlockEntityType.Builder.of(ForgeGrinderBlockEntity_Up::new, ModBlockRegForge.GRINDER_BLOCK_UP.get()).build(null));

	public static final RegistryObject<BlockEntityType<ForgeGrindFactoryBlockEntity>> GRIND_FACTORY = BLOCK_ENTITY.register("grinder_factory", () ->
			BlockEntityType.Builder.of(ForgeGrindFactoryBlockEntity::new, ModBlockRegForge.GRIND_FACTORY_BLOCK.get()).build(null));

	public static final RegistryObject<BlockEntityType<ForgeNetheriteCrafterBlockEntity>> NETHERITE_CRAFTER = BLOCK_ENTITY.register("netherite_crafter", () ->
			BlockEntityType.Builder.of(ForgeNetheriteCrafterBlockEntity::new, ModBlockRegForge.NETHERITE_CRAFTER.get()).build(null));

	public static final RegistryObject<BlockEntityType<ForgeFurnaceBlockEntity_Up>> UPGRADED_FURNACE = BLOCK_ENTITY.register("upgraded_furnace", () ->
			BlockEntityType.Builder.of(ForgeFurnaceBlockEntity_Up::new, ModBlockRegForge.UPGRADED_FURNACE.get()).build(null));
	public static void register(IEventBus eventBus) {
		BLOCK_ENTITY.register(eventBus);
	}

	public static void registerModBlockEntities(){
		SimpelAddModForge.LOGGER.info("Registering Mod Blocks Entities for " + SimpelAddModForge.MODID);
	}
}
