package net.x_j0nnay_x.simpeladd.util.data;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record HarvesterData(boolean empty) {
    public static final StreamCodec<RegistryFriendlyByteBuf, HarvesterData> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            HarvesterData::empty,
            HarvesterData::new
    );
}
