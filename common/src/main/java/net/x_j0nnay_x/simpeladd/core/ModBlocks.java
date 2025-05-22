package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.x_j0nnay_x.simpeladd.blocks.*;

public class ModBlocks {
    public static final Block DEEPSLATE_DEBRI_ORE = new SimpelXPBlock(UniformInt.of(2, 6), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE));
    public static final Block UNOBTANIUM_ORE = new SimpelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE));
    public static final FarmBlock SIMPELFARMLAND = new SimpelFarmLand();
}
