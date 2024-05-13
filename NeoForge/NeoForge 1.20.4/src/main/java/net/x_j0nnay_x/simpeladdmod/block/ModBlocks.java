package net.x_j0nnay_x.simpeladdmod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.block.custom.*;
import net.x_j0nnay_x.simpeladdmod.block.entity.GrinderBlockEntity;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Simpeladd.MOD_ID);

    public static final DeferredBlock<Block> DEEPSLATE_DEBRI_ORE = registerBlock("deepslate_debri_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 6), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> UNOBTANIUM_ORE = registerBlock("unobtanium_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> GRINDER_BLOCK = registerBlock("grinder_block",
            () -> new GrinderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));
    public static final DeferredBlock<Block> GRINDER_BLOCK_UP = registerBlock("grinder_block_up",
            () -> new GrinderBlock_upgrade(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));
    public static final DeferredBlock<Block> BLOCK_FACTORY = registerBlock("blockfactory_block",
            () -> new BlockFactoryBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));
    public static final DeferredBlock<Block> CHILLER = registerBlock("chiller_block",
            () -> new ChillerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));
    public static final DeferredBlock<Block> NETHERITE_CRAFTER = registerBlock("netherite_crafter_block",
            () -> new NetheriteCrafterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));
    public static final DeferredBlock<Block> STONE_SHIFTER = registerBlock("stone_sifter_block",
            () -> new StoneSifterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));


    public static DeferredBlock<Block> registerBlock(
            String name, Supplier<Block> block) {
        DeferredBlock<Block> blockReg = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(blockReg.get(), new Item.Properties()));
        return blockReg;
    }

    public static void registerModBlocks(){
        Simpeladd.LOGGER.info("Registering Mod Blocks for " + Simpeladd.MOD_ID);
    }

}
