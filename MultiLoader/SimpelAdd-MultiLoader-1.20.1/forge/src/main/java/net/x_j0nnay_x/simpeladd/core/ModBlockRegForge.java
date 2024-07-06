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
import net.x_j0nnay_x.simpeladd.blocks.*;
import net.x_j0nnay_x.simpeladd.SimpelAddModForge;
import java.util.function.Supplier;

public class ModBlockRegForge {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, SimpelAddModForge.MODID);

    public static final RegistryObject<Block> DEEPSLATE_DEBRI_ORE = registerBlock("deepslate_debri_ore",() -> ModBlocks.DEEPSLATE_DEBRI_ORE);
    public static final RegistryObject<Block> UNOBTANIUM_ORE = registerBlock("unobtanium_ore",() -> ModBlocks.UNOBTANIUM_ORE);

    public static final RegistryObject<Block> BLOCK_FACTORY = registerBlock("blockfactory_block",
            () -> new ForgeBlockFactoryBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CHILLER = registerBlock("chiller_block",
            () -> new ForgeChillerBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRINDER_BLOCK = registerBlock("grinder_block",
            () -> new ForgeGrinderBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRINDER_BLOCK_UP = registerBlock("grinder_block_up",
            () -> new ForgeGrinderBlock_Up(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> NETHERITE_CRAFTER = registerBlock("netherite_crafter_block",
            () -> new ForgeNetheriteCraftingBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> UPGRADED_FURNACE = registerBlock("upgraded_furnace_block",
            () -> new ForgeFurnaceBlock_Up(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));

    private static  <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static  <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItemRegForge.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void registerModBlocks(){
        SimpelAddModForge.LOGGER.info("Registering Mod Blocks for " + SimpelAddModForge.MODID);
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
