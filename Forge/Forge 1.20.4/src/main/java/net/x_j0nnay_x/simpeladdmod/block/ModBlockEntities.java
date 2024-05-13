package net.x_j0nnay_x.simpeladdmod.block;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.x_j0nnay_x.simpeladdmod.block.entity.*;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;


public class ModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Simpeladd.MOD_ID);


	public static final RegistryObject<BlockEntityType<Upgrade_Furnace_BlockEntity>> UPGRADED_FURNACE = BLOCK_ENTITY.register("upgraded_furnace", () ->
			BlockEntityType.Builder.of(Upgrade_Furnace_BlockEntity::new, ModBlocks.UPGRADED_FURNACE.get()).build(null));
	public static final RegistryObject<BlockEntityType<GrinderBlockEntity>> GRINDER = BLOCK_ENTITY.register("grinder", () ->
		 BlockEntityType.Builder.of(GrinderBlockEntity::new, ModBlocks.GRINDER_BLOCK.get()).build(null));
	public static final RegistryObject<BlockEntityType<GrinderBlockEntity_upgrade>> GRINDER_UP = BLOCK_ENTITY.register("grinder_up", () ->
			BlockEntityType.Builder.of(GrinderBlockEntity_upgrade::new, ModBlocks.GRINDER_BLOCK_UP.get()).build(null));
	public static final RegistryObject<BlockEntityType<BlockFactoryBlockEntity>> BLOCK_FACTORY = BLOCK_ENTITY.register("block_factory", () ->
			BlockEntityType.Builder.of(BlockFactoryBlockEntity::new, ModBlocks.BLOCK_FACTORY.get()).build(null));
	public static final RegistryObject<BlockEntityType<ChillerBlockEntity>> CHILLER = BLOCK_ENTITY.register("chiller", () ->
			BlockEntityType.Builder.of(ChillerBlockEntity::new, ModBlocks.CHILLER.get()).build(null));
	public static final RegistryObject<BlockEntityType<NetheriteCrafterBlockEntity>> NETHERITE_CRAFTER = BLOCK_ENTITY.register("netherite_crafter", () ->
			BlockEntityType.Builder.of(NetheriteCrafterBlockEntity::new, ModBlocks.NETHERITE_CRAFTER.get()).build(null));
	public static final RegistryObject<BlockEntityType<StoneSifterBlockEntity>> STONE_SIFTER = BLOCK_ENTITY.register("stone_sifter", () ->
			BlockEntityType.Builder.of(StoneSifterBlockEntity::new, ModBlocks.STONE_SHIFTER.get()).build(null));


	public static void register(IEventBus eventBus) {
		BLOCK_ENTITY.register(eventBus);
	}
	public static void registerModBlockEntities(){
		Simpeladd.LOGGER.info("Registering Mod Blocks Entities for " + Simpeladd.MOD_ID);
	}
}
