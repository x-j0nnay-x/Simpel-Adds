package net.x_j0nnay_x.simpeladd.util.data;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record TickAcceleratorData(boolean empty) {
    public static final StreamCodec<RegistryFriendlyByteBuf, TickAcceleratorData> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            TickAcceleratorData::empty,
            TickAcceleratorData::new
    );
}
