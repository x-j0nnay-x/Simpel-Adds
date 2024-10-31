package net.x_j0nnay_x.simpeladd.util.data;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record Grind_FactoryData(boolean empty) {
    public static final StreamCodec<RegistryFriendlyByteBuf, Grind_FactoryData> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            Grind_FactoryData::empty,
            Grind_FactoryData::new
    );
}
