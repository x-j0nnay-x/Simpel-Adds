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

    public static final Block DEEPSLATE_DEBRI_ORE = registerBlock(ModNames.Blocks.DEEPSLATE_DEBRI_ORE, ModBlocks.DEEPSLATE_DEBRI_ORE);

    public static final Block UNOBTANIUM_ORE = registerBlock(ModNames.Blocks.UNOBTANIUM_ORE, ModBlocks.UNOBTANIUM_ORE);

    public static final Block SIMPEL_FARM_LAND = registerBlock(ModNames.Blocks.SIMPEL_FARM_LAND, ModBlocks.SIMPELFARMLAND);

    public static final Block BLOCK_FACTORY = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.BLOCKFACTORY),
            new FabricBlockFactoryBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));

    public static final Block CHILLER = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.CHILLER),
            new FabricChillerBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));

    public static final Block GRINDER_BLOCK = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.GRINDER),
            new FabricGrinderBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));

    public static final Block GRINDER_BLOCK_UP = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.GRINDER_UP),
            new FabricGrinderBlock_Up(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));

    public static final Block GRIND_FACTORY_BLOCK = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.GRINDFACTORY),
            new FabricGrindFactoryBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));

    public static final Block NETHERITE_CRAFTER = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.NETHERITE_CRAFTER),
            new FabricNetheriteCraftingBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));

    public static final Block UPGRADED_FURNACE = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.UPGRADED_FURNACE),
            new FabricFurnaceBlock_Up(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));

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
