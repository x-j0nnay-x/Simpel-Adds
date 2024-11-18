package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;
import net.x_j0nnay_x.simpeladd.blocks.*;
import java.util.function.Supplier;

public class ModBlockRegNeoForge {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(SimpelAddMod.MOD_ID);

    public static final DeferredBlock<Block> DEEPSLATE_DEBRI_ORE = registerBlock(ModNames.Blocks.DEEPSLATE_DEBRI_ORE,() -> ModBlocks.DEEPSLATE_DEBRI_ORE);

    public static final DeferredBlock<Block> UNOBTANIUM_ORE = registerBlock(ModNames.Blocks.UNOBTANIUM_ORE,() -> ModBlocks.UNOBTANIUM_ORE);

    public static final DeferredBlock<Block> SIMPEL_FARM_LAND = registerBlock(ModNames.Blocks.SIMPEL_FARM_LAND,() -> ModBlocks.SIMPELFARMLAND);

    public static final DeferredBlock<Block> BLOCK_FACTORY = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.BLOCKFACTORY),
            () -> new NeoForgeBlockFactoryBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> CHILLER = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.CHILLER),
            () -> new NeoForgeChillerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> GRINDER_BLOCK = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.GRINDER),
            () -> new NeoForgeGrinderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> GRINDER_BLOCK_UP = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.GRINDER_UP),
            () -> new NeoForgeGrinderBlock_Up(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> GRIND_FACTORY_BLOCK = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.GRINDFACTORY),
            () -> new NeoForgeGrindFactoryBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> NETHERITE_CRAFTER = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.NETHERITE_CRAFTER),
            () -> new NeoForgeNetheriteCraftingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> UPGRADED_FURNACE = registerBlock(ModNames.Blocks.getBlockNameForEntity(ModNames.Blocks.UPGRADED_FURNACE),
            () -> new NeoForgeFurnaceBlock_Up(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));

    private static  <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static  <T extends Block> Supplier<Item> registerBlockItem(String name, DeferredBlock<T> block){
        return ModItemRegNeoForge.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        SimpelAddMod.modBlockRegText();
        BLOCKS.register(eventBus);
    }
}
