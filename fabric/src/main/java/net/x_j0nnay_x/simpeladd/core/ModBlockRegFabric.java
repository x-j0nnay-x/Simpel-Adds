package net.x_j0nnay_x.simpeladd.core;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.blocks.*;
import net.x_j0nnay_x.simpeladd.SimpelAddModFabric;


public class ModBlockRegFabric {

//Ores
    public static final Block DEEPSLATE_DEBRI_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.DEBRI, ModNames.Blocks.Type.DEEP), ModBlocks.DEEPSLATE_DEBRI_ORE);
    public static final Block NETHERRACK_DEBRI_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.DEBRI, ModNames.Blocks.Type.NETHER), ModBlocks.NETHERRACK_DEBRI_ORE);
    public static final Block END_DEBRI_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.DEBRI, ModNames.Blocks.Type.END), ModBlocks.END_DEBRI_ORE);
    public static final Block NETHERRACK_COAL_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.COAL, ModNames.Blocks.Type.NETHER), ModBlocks.NETHERRACK_COAL_ORE);
    public static final Block END_COAL_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.COAL, ModNames.Blocks.Type.END), ModBlocks.END_COAL_ORE);
    public static final Block NETHERRACK_COPPER_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.COPPER, ModNames.Blocks.Type.NETHER), ModBlocks.NETHERRACK_COPPER_ORE);
    public static final Block END_COPPER_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.COPPER, ModNames.Blocks.Type.END), ModBlocks.END_COPPER_ORE);
    public static final Block NETHERRACK_IRON_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.IRON, ModNames.Blocks.Type.NETHER), ModBlocks.NETHERRACK_IRON_ORE);
    public static final Block END_IRON_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.IRON, ModNames.Blocks.Type.END), ModBlocks.END_IRON_ORE);
    public static final Block NETHERRACK_GOLD_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.GOLD, ModNames.Blocks.Type.NETHER), ModBlocks.NETHERRACK_GOLD_ORE);
    public static final Block END_GOLD_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.GOLD, ModNames.Blocks.Type.END), ModBlocks.END_GOLD_ORE);
    public static final Block NETHERRACK_DIAMOND_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.DIAMOND, ModNames.Blocks.Type.NETHER), ModBlocks.NETHERRACK_DIAMOND_ORE);
    public static final Block END_DIAMOND_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.DIAMOND, ModNames.Blocks.Type.END), ModBlocks.END_DIAMOND_ORE);
    public static final Block NETHERRACK_EMERALD_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.EMERALD, ModNames.Blocks.Type.NETHER), ModBlocks.NETHERRACK_EMERALD_ORE);
    public static final Block END_EMERALD_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.EMERALD, ModNames.Blocks.Type.END), ModBlocks.END_EMERALD_ORE);
    public static final Block NETHERRACK_LAPIS_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.LAPIS, ModNames.Blocks.Type.NETHER), ModBlocks.NETHERRACK_LAPIS_ORE);
    public static final Block END_LAPIS_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.LAPIS, ModNames.Blocks.Type.END), ModBlocks.END_LAPIS_ORE);
    public static final Block NETHERRACK_REDSTONE_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.REDSTONE, ModNames.Blocks.Type.NETHER), ModBlocks.NETHERRACK_REDSTONE_ORE);
    public static final Block END_REDSTONE_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.REDSTONE, ModNames.Blocks.Type.END), ModBlocks.END_REDSTONE_ORE);
    public static final Block UNOBTANIUM_ORE = registerBlock(ModNames.Blocks.getBlockNameForOre(ModNames.Blocks.UNOBTANIUM_ORE, ModNames.Blocks.Type.END), ModBlocks.UNOBTANIUM_ORE);
//other
    public static final Block SIMPEL_FARM_LAND = registerBlock(ModNames.Blocks.SIMPEL_FARM_LAND, ModBlocks.SIMPELFARMLAND);
    public static final Block CHUNK_TOURCH = registerBlock(ModNames.Blocks.CHUNK_TOURCH, ModBlocks.CHUNKTOURCH);
//block entitys blocks
    public static final Block BLOCK_FACTORY = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.BLOCKFACTORY), new FabricBlockFactoryBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));
    public static final Block CHILLER = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.CHILLER), new FabricChillerBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));
    public static final Block GRINDER_BLOCK = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.GRINDER), new FabricGrinderBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));
    public static final Block GRINDER_BLOCK_UP = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.GRINDER_UP), new FabricGrinderBlock_Up(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));
    public static final Block GRIND_FACTORY_BLOCK = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.GRINDFACTORY), new FabricGrindFactoryBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));
    public static final Block NETHERITE_CRAFTER = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.NETHERITE_CRAFTER), new FabricNetheriteCraftingBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));
    public static final Block UPGRADED_FURNACE = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.UPGRADED_FURNACE), new FabricFurnaceBlock_Up(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));
    public static final Block TICK_ACCELERATOR = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.TICK_ACCELERATOR), new FabricTickAcceleratorBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(SimpelAddMod.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block){
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(SimpelAddMod.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks(){
        SimpelAddMod.modBlockRegText();
    }
}
