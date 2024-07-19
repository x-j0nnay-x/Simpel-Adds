package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;
import net.x_j0nnay_x.simpeladd.blocks.*;


import java.util.function.Supplier;

public class ModBlockRegNeoForge {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(SimpelAddModNeoForge.MODID);

    public static final DeferredBlock<Block> DEEPSLATE_DEBRI_ORE = registerBlock("deepslate_debri_ore",() -> ModBlocks.DEEPSLATE_DEBRI_ORE);
    public static final DeferredBlock<Block> UNOBTANIUM_ORE = registerBlock("unobtanium_ore",() -> ModBlocks.UNOBTANIUM_ORE);

    public static final DeferredBlock<Block> BLOCK_FACTORY = registerBlock("blockfactory_block",
            () -> new NeoForgeBlockFactoryBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> CHILLER = registerBlock("chiller_block",
            () -> new NeoForgeChillerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> GRINDER_BLOCK = registerBlock("grinder_block",
            () -> new NeoForgeGrinderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> GRINDER_BLOCK_UP = registerBlock("grinder_block_up",
            () -> new NeoForgeGrinderBlock_Up(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> NETHERITE_CRAFTER = registerBlock("netherite_crafter_block",
            () -> new NeoForgeNetheriteCraftingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> UPGRADED_FURNACE = registerBlock("upgraded_furnace_block",
            () -> new NeoForgeFurnaceBlock_Up(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));

    private static  <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static  <T extends Block> Supplier<Item> registerBlockItem(String name, DeferredBlock<T> block){
        return ModItemRegNeoForge.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void registerModBlocks(){
        SimpelAddModNeoForge.LOGGER.info("Registering Mod Blocks for " + SimpelAddModNeoForge.MODID);
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
