package net.x_j0nnay_x.simpeladdmod.block;


import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;

import net.x_j0nnay_x.simpeladdmod.block.entity.BlockFactoryBlockEntity;
import net.x_j0nnay_x.simpeladdmod.block.entity.ChillerBlockEntity;
import net.x_j0nnay_x.simpeladdmod.block.entity.GrinderBlockEntity;

import net.x_j0nnay_x.simpeladdmod.simpeladdmod;


public class ModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, simpeladdmod.MOD_ID);



	public static final RegistryObject<BlockEntityType<GrinderBlockEntity>> GRINDER = BLOCK_ENTITY.register("grinder", () ->
		 BlockEntityType.Builder.of(GrinderBlockEntity::new, ModBlocks.GRINDER_BLOCK.get()).build(null));

	public static final RegistryObject<BlockEntityType<BlockFactoryBlockEntity>> BLOCK_FACTORY = BLOCK_ENTITY.register("block_factory", () ->
			BlockEntityType.Builder.of(BlockFactoryBlockEntity::new, ModBlocks.BLOCK_FACTORY.get()).build(null));
	public static final RegistryObject<BlockEntityType<ChillerBlockEntity>> CHILLER = BLOCK_ENTITY.register("chiller", () ->
			BlockEntityType.Builder.of(ChillerBlockEntity::new, ModBlocks.CHILLER.get()).build(null));

	public static void register(IEventBus eventBus) {
		BLOCK_ENTITY.register(eventBus);
	}
}