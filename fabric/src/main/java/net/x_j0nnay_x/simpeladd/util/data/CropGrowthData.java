package net.x_j0nnay_x.simpeladd.util.data;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record CropGrowthData(boolean empty) {
    public static final StreamCodec<RegistryFriendlyByteBuf, CropGrowthData> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            CropGrowthData::empty,
            CropGrowthData::new
    );
}
