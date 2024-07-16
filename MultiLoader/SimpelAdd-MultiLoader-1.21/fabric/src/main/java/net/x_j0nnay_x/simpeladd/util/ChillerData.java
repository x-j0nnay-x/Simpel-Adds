package net.x_j0nnay_x.simpeladd.util;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record ChillerData(boolean empty) {
    public static final StreamCodec<RegistryFriendlyByteBuf, ChillerData> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            ChillerData::empty,
            ChillerData::new
    );

}
