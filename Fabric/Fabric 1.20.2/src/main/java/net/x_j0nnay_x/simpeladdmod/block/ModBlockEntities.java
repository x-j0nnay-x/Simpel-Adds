package net.x_j0nnay_x.simpeladdmod.block;


import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.block.entity.*;



public class ModBlockEntities {


	public static final BlockEntityType<BlockFactoryBlockEntity> BLOCK_FACTORY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
			new Identifier(Simpeladd.MOD_ID, "block_factory"),
			FabricBlockEntityTypeBuilder.create(BlockFactoryBlockEntity::new, ModBlocks.BLOCK_FACTORY).build());
	public static final BlockEntityType<ChillerBlockEntity> CHILLER = Registry.register(Registries.BLOCK_ENTITY_TYPE,
			new Identifier(Simpeladd.MOD_ID, "chiller"),
			FabricBlockEntityTypeBuilder.create(ChillerBlockEntity::new, ModBlocks.CHILLER).build());
	public static final BlockEntityType<GrinderBlockEntity> GRINDER = Registry.register(Registries.BLOCK_ENTITY_TYPE,
			new Identifier(Simpeladd.MOD_ID, "grinder"),
			FabricBlockEntityTypeBuilder.create(GrinderBlockEntity::new, ModBlocks.GRINDER_BLOCK).build());
	public static final BlockEntityType<GrinderBlockEntity_upgrade> GRINDER_UP = Registry.register(Registries.BLOCK_ENTITY_TYPE,
			new Identifier(Simpeladd.MOD_ID, "grinder_up"),
			FabricBlockEntityTypeBuilder.create(GrinderBlockEntity_upgrade::new, ModBlocks.GRINDER_BLOCK_UP).build());
	public static final BlockEntityType<NetheriteCrafterBlockEntity> NETHERITE_CRAFTER = Registry.register(Registries.BLOCK_ENTITY_TYPE,
			new Identifier(Simpeladd.MOD_ID, "netherite_crafter"),
			FabricBlockEntityTypeBuilder.create(NetheriteCrafterBlockEntity::new, ModBlocks.NETHERITE_CRAFTER).build());
	public static final BlockEntityType<Upgrade_Furnace_BlockEntity> UPGRADED_FURNACE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
			new Identifier(Simpeladd.MOD_ID, "upgraded_furnace"),
			FabricBlockEntityTypeBuilder.create(Upgrade_Furnace_BlockEntity::new, ModBlocks.UPGRADED_FURNACE).build());




	public static void registerBlockEntities() {
		Simpeladd.LOGGER.info("Registering Block Entities for " + Simpeladd.MOD_ID);
	}
}
