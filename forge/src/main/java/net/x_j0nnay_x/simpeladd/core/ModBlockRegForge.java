package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.blocks.*;
import net.x_j0nnay_x.simpeladd.SimpelAddModForge;
import java.util.function.Supplier;

public class ModBlockRegForge {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, SimpelAddMod.MOD_ID);

    public static final RegistryObject<Block> DEEPSLATE_DEBRI_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.DEBRI, ModNames.Blocks.Type.DEEP), () -> ModBlocks.DEEPSLATE_DEBRI_ORE);
    public static final RegistryObject<Block> NETHERRACK_DEBRI_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.DEBRI, ModNames.Blocks.Type.NETHER), () -> ModBlocks.NETHERRACK_DEBRI_ORE);
    public static final RegistryObject<Block> END_DEBRI_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.DEBRI, ModNames.Blocks.Type.END), () -> ModBlocks.END_DEBRI_ORE);
    public static final RegistryObject<Block> NETHERRACK_COAL_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.COAL, ModNames.Blocks.Type.NETHER), () -> ModBlocks.NETHERRACK_COAL_ORE);
    public static final RegistryObject<Block> END_COAL_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.COAL, ModNames.Blocks.Type.END), () -> ModBlocks.END_COAL_ORE);
    public static final RegistryObject<Block> NETHERRACK_COPPER_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.COPPER, ModNames.Blocks.Type.NETHER), () -> ModBlocks.NETHERRACK_COPPER_ORE);
    public static final RegistryObject<Block> END_COPPER_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.COPPER, ModNames.Blocks.Type.END), () -> ModBlocks.END_COPPER_ORE);
    public static final RegistryObject<Block> NETHERRACK_IRON_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.IRON, ModNames.Blocks.Type.NETHER), () -> ModBlocks.NETHERRACK_IRON_ORE);
    public static final RegistryObject<Block> END_IRON_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.IRON, ModNames.Blocks.Type.END), () -> ModBlocks.END_IRON_ORE);
    public static final RegistryObject<Block> NETHERRACK_GOLD_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.GOLD, ModNames.Blocks.Type.NETHER), () -> ModBlocks.NETHERRACK_GOLD_ORE);
    public static final RegistryObject<Block> END_GOLD_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.GOLD, ModNames.Blocks.Type.END), () -> ModBlocks.END_GOLD_ORE);
    public static final RegistryObject<Block> NETHERRACK_DIAMOND_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.DIAMOND, ModNames.Blocks.Type.NETHER), () -> ModBlocks.NETHERRACK_DIAMOND_ORE);
    public static final RegistryObject<Block> END_DIAMOND_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.DIAMOND, ModNames.Blocks.Type.END), () -> ModBlocks.END_DIAMOND_ORE);
    public static final RegistryObject<Block> NETHERRACK_EMERALD_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.EMERALD, ModNames.Blocks.Type.NETHER), () -> ModBlocks.NETHERRACK_EMERALD_ORE);
    public static final RegistryObject<Block> END_EMERALD_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.EMERALD, ModNames.Blocks.Type.END), () -> ModBlocks.END_EMERALD_ORE);
    public static final RegistryObject<Block> NETHERRACK_LAPIS_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.LAPIS, ModNames.Blocks.Type.NETHER), () -> ModBlocks.NETHERRACK_LAPIS_ORE);
    public static final RegistryObject<Block> END_LAPIS_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.LAPIS, ModNames.Blocks.Type.END), () -> ModBlocks.END_LAPIS_ORE);
    public static final RegistryObject<Block> NETHERRACK_REDSTONE_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.REDSTONE, ModNames.Blocks.Type.NETHER), () -> ModBlocks.NETHERRACK_REDSTONE_ORE);
    public static final RegistryObject<Block> END_REDSTONE_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.REDSTONE, ModNames.Blocks.Type.END), () -> ModBlocks.END_REDSTONE_ORE);

    public static final RegistryObject<Block> UNOBTANIUM_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.UNOBTANIUM_ORE, ModNames.Blocks.Type.END), () -> ModBlocks.UNOBTANIUM_ORE);

    public static final RegistryObject<Block> SIMPEL_FARM_LAND = registerBlock(ModNames.Blocks.SIMPEL_FARM_LAND, ()-> ModBlocks.SIMPELFARMLAND);
    public static final RegistryObject<Block> CHUNK_TOURCH = registerBlock(ModNames.Blocks.CHUNK_TOURCH, ()-> ModBlocks.CHUNKTOURCH);

    public static final RegistryObject<Block> BLOCK_FACTORY = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.BLOCKFACTORY),
            () -> new ForgeBlockFactoryBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CHILLER = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.CHILLER),
            () -> new ForgeChillerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRINDER_BLOCK = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.GRINDER),
            () -> new ForgeGrinderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRINDER_BLOCK_UP = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.GRINDER_UP),
            () -> new ForgeGrinderBlock_Up(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRIND_FACTORY_BLOCK = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.GRINDFACTORY),
            () -> new ForgeGrindFactoryBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> NETHERITE_CRAFTER = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.NETHERITE_CRAFTER),
            () -> new ForgeNetheriteCraftingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> UPGRADED_FURNACE = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.UPGRADED_FURNACE),
            () -> new ForgeFurnaceBlock_Up(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TICK_ACCELERATOR = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.TICK_ACCELERATOR),
            () -> new ForgeTickAcceleratorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TOOLREPAIR = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.TOOLREPAIR),
            () -> new ForgeToolRepairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));

    private static  <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static  <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItemRegForge.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus){
        SimpelAddMod.modBlockRegText();
        BLOCKS.register(eventBus);
    }
}
