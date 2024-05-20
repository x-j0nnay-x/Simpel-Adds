package net.x_j0nnay_x.simpeladdmod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.block.custom.*;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
    DeferredRegister.createBlocks(Simpeladd.MOD_ID);

    public static final DeferredBlock<Block> DEEPSLATE_DEBRI_ORE = registerBlock("deepslate_debri_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE)
                    .requiresCorrectToolForDrops(), UniformInt.of(2, 6)));
    public static final DeferredBlock<Block> UNOBTANIUM_ORE = registerBlock("unobtanium_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> UPGRADED_FURNACE = registerBlock("upgraded_furnace_block",
            () -> new Upgrade_Furnace(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> GRINDER_BLOCK = registerBlock("grinder_block",
            () -> new GrinderBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));
    public static final DeferredBlock<Block> GRINDER_BLOCK_UP = registerBlock("grinder_block_up",
            () -> new GrinderBlock_upgrade(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));
    public static final DeferredBlock<Block> BLOCK_FACTORY = registerBlock("blockfactory_block",
            () -> new BlockFactoryBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));
    public static final DeferredBlock<Block> CHILLER = registerBlock("chiller_block",
            () -> new ChillerBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));
    public static final DeferredBlock<Block> NETHERITE_CRAFTER = registerBlock("netherite_crafter_block",
            () -> new NetheriteCrafterBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));
    public static final DeferredBlock<Block> STONE_SHIFTER = registerBlock("stone_sifter_block",
            () -> new StoneSifterBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));
    private static  <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static  <T extends Block> DeferredItem<Item> registerBlockItem(String name, DeferredBlock<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void registerModBlocks(){
        Simpeladd.LOGGER.info("Registering Mod Blocks for " + Simpeladd.MOD_ID);
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
