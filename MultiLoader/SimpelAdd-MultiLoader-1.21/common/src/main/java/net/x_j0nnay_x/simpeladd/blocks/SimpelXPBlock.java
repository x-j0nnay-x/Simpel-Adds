package net.x_j0nnay_x.simpeladd.blocks;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.DropExperienceBlock;


public class SimpelXPBlock extends DropExperienceBlock {
    public static final MapCodec<SimpelXPBlock> CODEC = RecordCodecBuilder.mapCodec(($$0) -> {
        return $$0.group(IntProvider.codec(0, 10).fieldOf("experience").forGetter(($$0x) -> {
            return $$0x.xpRange;
        }), propertiesCodec()).apply($$0, SimpelXPBlock::new);
    });
    public MapCodec<? extends SimpelXPBlock> codec() {
        return CODEC;
    }
    private final IntProvider xpRange;
    public SimpelXPBlock(IntProvider $$0, Properties $$1) {
        super($$0, $$1);
        this.xpRange = $$0;
    }


}
