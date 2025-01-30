package net.x_j0nnay_x.simpeladd.core;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.SimpelAddModForge;
import net.x_j0nnay_x.simpeladd.blocks.entity.*;

public class ModBlockEntitiesForge {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SimpelAddMod.MOD_ID);

	public static final RegistryObject<BlockEntityType<ForgeBlockFactoryBlockEntity>> BLOCK_FACTORY = BLOCK_ENTITY.register(ModNames.Blocks.BLOCKFACTORY, () ->
			BlockEntityType.Builder.of(ForgeBlockFactoryBlockEntity::new, ModBlockRegForge.BLOCK_FACTORY.get()).build(null));

	public static final RegistryObject<BlockEntityType<ForgeChillerBlockEntity>> CHILLER = BLOCK_ENTITY.register(ModNames.Blocks.CHILLER, () ->
			BlockEntityType.Builder.of(ForgeChillerBlockEntity::new, ModBlockRegForge.CHILLER.get()).build(null));

	public static final RegistryObject<BlockEntityType<ForgeGrinderBlockEntity>> GRINDER = BLOCK_ENTITY.register(ModNames.Blocks.GRINDER, () ->
			BlockEntityType.Builder.of(ForgeGrinderBlockEntity::new, ModBlockRegForge.GRINDER_BLOCK.get()).build(null));

	public static final RegistryObject<BlockEntityType<ForgeGrinderBlockEntity_Up>> GRINDER_UP = BLOCK_ENTITY.register(ModNames.Blocks.GRINDER_UP, () ->
			BlockEntityType.Builder.of(ForgeGrinderBlockEntity_Up::new, ModBlockRegForge.GRINDER_BLOCK_UP.get()).build(null));

	public static final RegistryObject<BlockEntityType<ForgeGrindFactoryBlockEntity>> GRIND_FACTORY = BLOCK_ENTITY.register(ModNames.Blocks.GRINDFACTORY, () ->
			BlockEntityType.Builder.of(ForgeGrindFactoryBlockEntity::new, ModBlockRegForge.GRIND_FACTORY_BLOCK.get()).build(null));

	public static final RegistryObject<BlockEntityType<ForgeNetheriteCrafterBlockEntity>> NETHERITE_CRAFTER = BLOCK_ENTITY.register(ModNames.Blocks.NETHERITE_CRAFTER, () ->
			BlockEntityType.Builder.of(ForgeNetheriteCrafterBlockEntity::new, ModBlockRegForge.NETHERITE_CRAFTER.get()).build(null));

	public static final RegistryObject<BlockEntityType<ForgeFurnaceBlockEntity_Up>> UPGRADED_FURNACE = BLOCK_ENTITY.register(ModNames.Blocks.UPGRADED_FURNACE, () ->
			BlockEntityType.Builder.of(ForgeFurnaceBlockEntity_Up::new, ModBlockRegForge.UPGRADED_FURNACE.get()).build(null));

	public static final RegistryObject<BlockEntityType<ForgeTickAcceleratorBlockEntity>> TICK_ACCELERATOR = BLOCK_ENTITY.register(ModNames.Blocks.TICK_ACCELERATOR, () ->
			BlockEntityType.Builder.of(ForgeTickAcceleratorBlockEntity::new, ModBlockRegForge.TICK_ACCELERATOR.get()).build(null));

	public static final RegistryObject<BlockEntityType<ForgeToolRepairBlockEntity>> TOOL_REPAIR = BLOCK_ENTITY.register(ModNames.Blocks.TOOLREPAIR, () ->
			BlockEntityType.Builder.of(ForgeToolRepairBlockEntity::new, ModBlockRegForge.TOOLREPAIR.get()).build(null));

	public static final RegistryObject<BlockEntityType<ForgeHarvesterBlockEntity>> HARVESTER = BLOCK_ENTITY.register(ModNames.Blocks.HARVESTER, () ->
			BlockEntityType.Builder.of(ForgeHarvesterBlockEntity::new, ModBlockRegForge.HARVESTER.get()).build(null));

	public static void register(IEventBus eventBus) {
		SimpelAddMod.modBlockEntRegText();
		BLOCK_ENTITY.register(eventBus);
	}

}
