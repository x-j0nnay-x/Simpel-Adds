package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.x_j0nnay_x.simpeladd.blocks.*;


public class ModBlocks {

    public static final Block DEEPSLATE_DEBRI_ORE = new SimpelXPBlock(UniformInt.of(2, 6), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops());
    public static final Block NETHERRACK_DEBRI_ORE = new SimpelXPBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops());
    public static final Block END_DEBRI_ORE = new SimpelXPBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops());
    public static final Block NETHERRACK_COAL_ORE = new SimpelXPBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COAL_ORE).requiresCorrectToolForDrops());
    public static final Block END_COAL_ORE = new SimpelXPBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COAL_ORE).requiresCorrectToolForDrops());
    public static final Block NETHERRACK_COPPER_ORE = new SimpelXPBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE).requiresCorrectToolForDrops());
    public static final Block END_COPPER_ORE = new SimpelXPBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE).requiresCorrectToolForDrops());
    public static final Block NETHERRACK_IRON_ORE = new SimpelXPBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops());
    public static final Block END_IRON_ORE = new SimpelXPBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops());
    public static final Block NETHERRACK_GOLD_ORE = new SimpelXPBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_GOLD_ORE).requiresCorrectToolForDrops());
    public static final Block END_GOLD_ORE = new SimpelXPBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_GOLD_ORE).requiresCorrectToolForDrops());
    public static final Block NETHERRACK_DIAMOND_ORE = new SimpelXPBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops());
    public static final Block END_DIAMOND_ORE = new SimpelXPBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops());
    public static final Block NETHERRACK_EMERALD_ORE = new SimpelXPBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_EMERALD_ORE).requiresCorrectToolForDrops());
    public static final Block END_EMERALD_ORE = new SimpelXPBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_EMERALD_ORE).requiresCorrectToolForDrops());
    public static final Block NETHERRACK_LAPIS_ORE = new SimpelXPBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_LAPIS_ORE).requiresCorrectToolForDrops());
    public static final Block END_LAPIS_ORE = new SimpelXPBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_LAPIS_ORE).requiresCorrectToolForDrops());
    public static final Block NETHERRACK_REDSTONE_ORE = new SimpelXPBlockBlockState(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_REDSTONE_ORE).requiresCorrectToolForDrops());
    public static final Block END_REDSTONE_ORE = new SimpelXPBlockBlockState(UniformInt.of(4, 8), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_REDSTONE_ORE).requiresCorrectToolForDrops() );
    public static final Block UNOBTANIUM_ORE = new SimpelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops());

    public static final Block SIMPELFARMLAND = new SimpelFarmLand();
    public static final Block CHUNKTOURCH = new ChunkTourch(BlockSetType.STONE, 0, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON).lightLevel((state) -> {return 12;}));

    public static final Block RAW_DEBRI_SHARD_BLOCK = new SimpelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK));
    public static final Block RAW_DIAMOND_SHARD_BLOCK = new SimpelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK));
    public static final Block RAW_EMERALD_SHARD_BLOCK = new SimpelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK));

}
