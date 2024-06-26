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
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.block.custom.*;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Simpeladd.MOD_ID);

    public static final RegistryObject<Block> DEEPSLATE_DEBRI_ORE = registerBlock("deepslate_debri_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 6), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> UNOBTANIUM_ORE = registerBlock("unobtanium_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> UPGRADED_FURNACE = registerBlock("upgraded_furnace_block",
            () -> new Upgrade_Furnace(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRINDER_BLOCK = registerBlock("grinder_block",
            () -> new GrinderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));
    public static final RegistryObject<Block> GRINDER_BLOCK_UP = registerBlock("grinder_block_up",
            () -> new GrinderBlock_upgrade(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));
    public static final RegistryObject<Block> BLOCK_FACTORY = registerBlock("blockfactory_block",
            () -> new BlockFactoryBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));
    public static final RegistryObject<Block> CHILLER = registerBlock("chiller_block",
            () -> new ChillerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));
    public static final RegistryObject<Block> NETHERITE_CRAFTER = registerBlock("netherite_crafter_block",
            () -> new NetheriteCrafterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));

    private static  <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static  <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void registerModBlocks(){
        Simpeladd.LOGGER.info("Registering Mod Blocks for " + Simpeladd.MOD_ID);
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
