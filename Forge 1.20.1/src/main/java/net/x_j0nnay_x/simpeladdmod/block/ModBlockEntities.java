package net.x_j0nnay_x.simpeladdmod.block;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;
import net.x_j0nnay_x.simpeladdmod.block.entity.BlockFactoryBlockEntity;
import net.x_j0nnay_x.simpeladdmod.block.entity.ChillerBlockBlockEntity;
import net.x_j0nnay_x.simpeladdmod.block.entity.GrinderBlockEntity;

import net.x_j0nnay_x.simpeladdmod.simpeladdmod;


public class ModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, simpeladdmod.MOD_ID);
	public static final RegistryObject<BlockEntityType<?>> GRINDER = register("grinder", ModBlocks.GRINDER_BLOCK, GrinderBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> BLOCK_FACTORY_BLOCK = register("block_factory_block", ModBlocks.BLOCK_FACTORY_BLOCK, BlockFactoryBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> CHILLER_BLOCK = register("chiller_block", ModBlocks.CHILLER_BLOCK, ChillerBlockBlockEntity::new);


	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return BLOCK_ENTITY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}

	public static void register(IEventBus eventBus) {
		BLOCK_ENTITY.register(eventBus);
	}
}
