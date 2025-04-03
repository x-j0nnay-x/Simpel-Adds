package net.x_j0nnay_x.simpeladd.core;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.blocks.entity.*;
import net.x_j0nnay_x.simpeladd.SimpelAddModFabric;

public class ModBlockEntitiesFabric {

	public static final BlockEntityType<FabricBlockFactoryBlockEntity> BLOCK_FACTORY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
			ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Blocks.BLOCKFACTORY),
			FabricBlockEntityTypeBuilder.create(FabricBlockFactoryBlockEntity::new, ModBlockRegFabric.BLOCK_FACTORY).build());

	public static final BlockEntityType<FabricChillerBlockEntity> CHILLER = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
			ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Blocks.CHILLER),
			FabricBlockEntityTypeBuilder.create(FabricChillerBlockEntity::new, ModBlockRegFabric.CHILLER).build());

	public static final BlockEntityType<FabricGrinderBlockEntity> GRINDER = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
			ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Blocks.GRINDER),
			FabricBlockEntityTypeBuilder.create(FabricGrinderBlockEntity::new, ModBlockRegFabric.GRINDER_BLOCK).build());

	public static final BlockEntityType<FabricGrinderBlockEntity_Up> GRINDER_UP = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
			ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Blocks.GRINDER_UP),
			FabricBlockEntityTypeBuilder.create(FabricGrinderBlockEntity_Up::new, ModBlockRegFabric.GRINDER_BLOCK_UP).build());

	public static final BlockEntityType<FabricGrindFactoryBlockEntity> GRIND_FACTORY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
			ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Blocks.GRINDFACTORY),
			FabricBlockEntityTypeBuilder.create(FabricGrindFactoryBlockEntity::new, ModBlockRegFabric.GRIND_FACTORY_BLOCK).build());

	public static final BlockEntityType<FabricNetheriteCrafterBlockEntity> NETHERITE_CRAFTER = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
			ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Blocks.NETHERITE_CRAFTER),
			FabricBlockEntityTypeBuilder.create(FabricNetheriteCrafterBlockEntity::new, ModBlockRegFabric.NETHERITE_CRAFTER).build());

	public static final BlockEntityType<FabricFurnaceBlockEntity_Up> UPGRADED_FURNACE = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
			ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Blocks.UPGRADED_FURNACE),
			FabricBlockEntityTypeBuilder.create(FabricFurnaceBlockEntity_Up::new, ModBlockRegFabric.UPGRADED_FURNACE).build());

	public static final BlockEntityType<FabricTickAcceleratorBlockEntity> TICK_ACCELERATOR = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
			ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Blocks.TICK_ACCELERATOR),
			FabricBlockEntityTypeBuilder.create(FabricTickAcceleratorBlockEntity::new, ModBlockRegFabric.TICK_ACCELERATOR).build());

	public static final BlockEntityType<FabricToolRepairBlockEntity> TOOLREPAIR = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
			ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Blocks.TOOLREPAIR),
			FabricBlockEntityTypeBuilder.create(FabricToolRepairBlockEntity::new, ModBlockRegFabric.TOOLREPAIR).build());

	public static final BlockEntityType<FabricHarvesterBlockEntity> HARVESTER = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
			ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Blocks.HARVESTER),
			FabricBlockEntityTypeBuilder.create(FabricHarvesterBlockEntity::new, ModBlockRegFabric.HARVESTER).build());

	public static final BlockEntityType<FabricCropGrowthBlockEntity> CROP_GROWTH = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
			ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.Blocks.CROP_GROWTH),
			FabricBlockEntityTypeBuilder.create(FabricCropGrowthBlockEntity::new, ModBlockRegFabric.CROP_GROWTH).build());


	public static void registerBlockEntities() {
		SimpelAddMod.modBlockEntRegText();
	}
}
