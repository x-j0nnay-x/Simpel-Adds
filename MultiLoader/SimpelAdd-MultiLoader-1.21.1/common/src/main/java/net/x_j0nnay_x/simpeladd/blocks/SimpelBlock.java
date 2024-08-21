package net.x_j0nnay_x.simpeladd.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;

public class SimpelBlock extends Block {

    public static final MapCodec<SimpelBlock> CODEC = simpleCodec(SimpelBlock::new);

    public SimpelBlock(Properties $$0) {
        super($$0);
    }

    protected MapCodec<? extends SimpelBlock> codec() {
        return CODEC;
    }
}
