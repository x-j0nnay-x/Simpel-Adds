package net.x_j0nnay_x.simpeladd.util.data;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record Grinder_upData(boolean empty) {
    public static final StreamCodec<RegistryFriendlyByteBuf, Grinder_upData> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            Grinder_upData::empty,
            Grinder_upData::new
    );

}
