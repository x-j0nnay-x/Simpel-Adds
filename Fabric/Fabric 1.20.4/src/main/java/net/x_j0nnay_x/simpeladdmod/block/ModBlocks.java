package net.x_j0nnay_x.simpeladdmod.block;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.block.custom.*;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import java.util.function.Supplier;

public class ModBlocks {


    public static final Block DEEPSLATE_DEBRI_ORE = registerBlock("deepslate_debri_ore",
             new ExperienceDroppingBlock(UniformIntProvider.create(2,6), FabricBlockSettings.copyOf(Blocks.DEEPSLATE_DIAMOND_ORE).nonOpaque()
                    .requiresTool()));

    public static final Block UNOBTANIUM_ORE = registerBlock("unobtanium_ore",
             new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_DIAMOND_ORE).nonOpaque()
                    .requiresTool()));
    public static final Block BLOCK_FACTORY = registerBlock("blockfactory_block",
             new BlockFactoryBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));
    public static final Block CHILLER = registerBlock("chiller_block",
             new ChillerBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));
    public static final Block GRINDER_BLOCK = registerBlock("grinder_block",
            new GrinderBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));
    public static final Block GRINDER_BLOCK_UP = registerBlock("grinder_block_up",
            new GrinderBlock_upgrade(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));

    public static final Block NETHERITE_CRAFTER = registerBlock("netherite_crafter_block",
             new NetheriteCrafterBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));
    public static final Block STONE_SHIFTER = registerBlock("stone_sifter_block",
             new StoneSifterBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()));


   private static Block registerBlock(String name, Block block){
       registerBlockItem(name, block);
       return Registry.register(Registries.BLOCK, new Identifier(Simpeladd.MOD_ID, name), block);
   }
   private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(Simpeladd.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
   }

    public static void registerModBlocks(){
        Simpeladd.LOGGER.info("Registering Blocks for " + Simpeladd.MOD_ID);
    }

}
