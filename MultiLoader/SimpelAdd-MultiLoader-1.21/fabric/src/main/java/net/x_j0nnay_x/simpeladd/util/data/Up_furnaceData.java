package net.x_j0nnay_x.simpeladd.util.data;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record Up_furnaceData(boolean empty) {
    public static final StreamCodec<RegistryFriendlyByteBuf, Up_furnaceData> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            Up_furnaceData::empty,
            Up_furnaceData::new
    );

}
