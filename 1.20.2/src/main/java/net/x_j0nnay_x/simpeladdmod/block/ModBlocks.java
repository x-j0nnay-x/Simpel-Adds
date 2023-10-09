package net.x_j0nnay_x.simpeladdmod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import net.x_j0nnay_x.simpeladdmod.block.custom.BlockFactoryBlockBlock;
import net.x_j0nnay_x.simpeladdmod.block.custom.ChillerBlockBlock;
import net.x_j0nnay_x.simpeladdmod.block.custom.GrinderBlock;

import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.simpeladdmod;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, simpeladdmod.MOD_ID);

    public static final RegistryObject<Block> DEEPSLATE_DEBRI_ORE = registerBlock("deepslate_debri_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE)
                    .requiresCorrectToolForDrops(), UniformInt.of(2, 6)));
    public static final RegistryObject<Block> GRINDER_BLOCK = registerBlock("grinder_block",
            () -> new GrinderBlock());
    public static final RegistryObject<Block> BLOCK_FACTORY_BLOCK = registerBlock("blockfactory_block",
            () -> new BlockFactoryBlockBlock());
    public static final RegistryObject<Block> CHILLER_BLOCK = registerBlock("chiller_block",
            () -> new ChillerBlockBlock());

    private static  <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static  <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
