package net.x_j0nnay_x.simpeladd.util.data;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record ToolRepairData(boolean empty) {
    public static final StreamCodec<RegistryFriendlyByteBuf, ToolRepairData> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            ToolRepairData::empty,
            ToolRepairData::new
    );
}
