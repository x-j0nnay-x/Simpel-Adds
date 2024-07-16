package net.x_j0nnay_x.simpeladd.util;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record BlockFactroyData(boolean empty) {
    public static final StreamCodec<RegistryFriendlyByteBuf, BlockFactroyData> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            BlockFactroyData::empty,
            BlockFactroyData::new
    );

}
