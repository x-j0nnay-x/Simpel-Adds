package net.x_j0nnay_x.simpeladd.core;


import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.x_j0nnay_x.simpeladd.blocks.*;
import net.x_j0nnay_x.simpeladd.SimpelAddModFabric;


public class ModBlockRegFabric {

    public static final Block DEEPSLATE_DEBRI_ORE = registerBlock("deepslate_debri_ore", ModBlocks.DEEPSLATE_DEBRI_ORE);

    public static final Block UNOBTANIUM_ORE = registerBlock("unobtanium_ore", ModBlocks.UNOBTANIUM_ORE);

    public static final Block BLOCK_FACTORY = registerBlock("blockfactory_block",
            new FabricBlockFactoryBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));
    public static final Block CHILLER = registerBlock("chiller_block",
            new FabricChillerBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));
    public static final Block GRINDER_BLOCK = registerBlock("grinder_block",
            new FabricGrinderBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));
    public static final Block GRINDER_BLOCK_UP = registerBlock("grinder_block_up",
            new FabricGrinderBlock_Up(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));
    public static final Block NETHERITE_CRAFTER = registerBlock("netherite_crafter_block",
            new FabricNetheriteCraftingBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));
    public static final Block UPGRADED_FURNACE = registerBlock("upgraded_furnace_block",
            new FabricFurnaceBlock_Up(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK,  ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, name), block);
    }
    private static Item registerBlockItem(String name, Block block){
        return Registry.register(BuiltInRegistries.ITEM,  ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, name), new BlockItem(block, new Item.Properties()));
    }

    public static void registerModBlocks(){
        SimpelAddModFabric.LOGGER.info("Registering Blocks for " + SimpelAddModFabric.MODID);
    }
}
