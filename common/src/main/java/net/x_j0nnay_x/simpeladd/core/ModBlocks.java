package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.x_j0nnay_x.simpeladd.blocks.*;


public class ModBlocks {

    public static final Block DEEPSLATE_DEBRI_ORE = new SimpelXPBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops(), UniformInt.of(2, 6));
    public static final Block NETHERRACK_DEBRI_ORE = new SimpelXPBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops(), UniformInt.of(3, 7));
    public static final Block END_DEBRI_ORE = new SimpelXPBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops(), UniformInt.of(4, 8));
    public static final Block NETHERRACK_COAL_ORE = new SimpelXPBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COAL_ORE).requiresCorrectToolForDrops(), UniformInt.of(3, 7));
    public static final Block END_COAL_ORE = new SimpelXPBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COAL_ORE).requiresCorrectToolForDrops(), UniformInt.of(4, 8));
    public static final Block NETHERRACK_COPPER_ORE = new SimpelXPBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE).requiresCorrectToolForDrops(), UniformInt.of(3, 7));
    public static final Block END_COPPER_ORE = new SimpelXPBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE).requiresCorrectToolForDrops(), UniformInt.of(4, 8));
    public static final Block NETHERRACK_IRON_ORE = new SimpelXPBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), UniformInt.of(3, 7));
    public static final Block END_IRON_ORE = new SimpelXPBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), UniformInt.of(4, 8));
    public static final Block NETHERRACK_GOLD_ORE = new SimpelXPBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_GOLD_ORE).requiresCorrectToolForDrops(), UniformInt.of(3, 7));
    public static final Block END_GOLD_ORE = new SimpelXPBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_GOLD_ORE).requiresCorrectToolForDrops(), UniformInt.of(4, 8));
    public static final Block NETHERRACK_DIAMOND_ORE = new SimpelXPBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops(), UniformInt.of(3, 7));
    public static final Block END_DIAMOND_ORE = new SimpelXPBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops(), UniformInt.of(4, 8));
    public static final Block NETHERRACK_EMERALD_ORE = new SimpelXPBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_EMERALD_ORE).requiresCorrectToolForDrops(), UniformInt.of(3, 7));
    public static final Block END_EMERALD_ORE = new SimpelXPBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_EMERALD_ORE).requiresCorrectToolForDrops(), UniformInt.of(4, 8));
    public static final Block NETHERRACK_LAPIS_ORE = new SimpelXPBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_LAPIS_ORE).requiresCorrectToolForDrops(), UniformInt.of(3, 7));
    public static final Block END_LAPIS_ORE = new SimpelXPBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_LAPIS_ORE).requiresCorrectToolForDrops(), UniformInt.of(4, 8));
    public static final Block NETHERRACK_REDSTONE_ORE = new SimpelXPBlockBlockState(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_REDSTONE_ORE).requiresCorrectToolForDrops(), UniformInt.of(3, 7));
    public static final Block END_REDSTONE_ORE = new SimpelXPBlockBlockState(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_REDSTONE_ORE).requiresCorrectToolForDrops(), UniformInt.of(4, 8));

    public static final Block UNOBTANIUM_ORE = new SimpelBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops());

    public static final Block SIMPELFARMLAND = new SimpelFarmLand();
    public static final Block CHUNKTOURCH = new ChunkTourch();

}
