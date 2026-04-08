package x_j0nnay_x.simpeladdmod.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import x_j0nnay_x.simpeladdmod.Constants;
import x_j0nnay_x.simpeladdmod.blocks.Fuel_Chunks;
import x_j0nnay_x.simpeladdmod.services.Services;
import x_j0nnay_x.simpeladdmod.services.util.BlockWithItemRegistryHandle;


public class Mod_Blocks {
    public static void load(){}

    public static final BlockWithItemRegistryHandle<Block> FUEL_CHUNKS = Services.REGISTRY.registerBlockWithItem(Constants.Blocks.FUEL_CHUNKS,
            properties -> new Fuel_Chunks(properties.strength(0.5F).sound(SoundType.STONE)));


    public static final BlockWithItemRegistryHandle<Block> RAW_DEBRI_SHARD_BLOCK = Services.REGISTRY.registerBlockWithItem(Constants.getRawOreBlockName(Constants.Types.DEBRI),
            properties -> new Block(properties.strength(5.0F, 6.0F).sound(SoundType.IRON).requiresCorrectToolForDrops()));
    public static final BlockWithItemRegistryHandle<Block> RAW_DIAMOND_SHARD_BLOCK =  Services.REGISTRY.registerBlockWithItem(Constants.getRawOreBlockName(Constants.Types.DIAMOND),
            properties -> new Block(properties.strength(5.0F, 6.0F).sound(SoundType.IRON).requiresCorrectToolForDrops()));
    public static final BlockWithItemRegistryHandle<Block> RAW_EMERALD_SHARD_BLOCK =  Services.REGISTRY.registerBlockWithItem(Constants.getRawOreBlockName(Constants.Types.EMERALD),
            properties -> new Block(properties.strength(5.0F, 6.0F).sound(SoundType.IRON).requiresCorrectToolForDrops()));
}
