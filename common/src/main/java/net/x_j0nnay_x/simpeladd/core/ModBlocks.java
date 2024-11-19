package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.x_j0nnay_x.simpeladd.blocks.*;


public class ModBlocks {

    public static final Block DEEPSLATE_DEBRI_ORE = new SimpelXPBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops(), UniformInt.of(2, 6));
    public static final Block UNOBTANIUM_ORE = new SimpelBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops());
    public static final Block SIMPELFARMLAND = new SimpelFarmLand();
    public static final Block CHUNKTOURCH = new ChunkTourch();

}
