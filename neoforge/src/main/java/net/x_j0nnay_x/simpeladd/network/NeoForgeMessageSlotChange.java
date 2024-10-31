package net.x_j0nnay_x.simpeladd.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;
import net.x_j0nnay_x.simpeladd.blocks.entity.NeoForgeBlockFactoryBlockEntity;
import java.util.Objects;

public record NeoForgeMessageSlotChange(int x, int y, int z, int index,  int set) implements CustomPacketPayload {

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(SimpelAddModNeoForge.MODID, "blockfactory_slot_packet");
    public static final CustomPacketPayload.Type<NeoForgeMessageSlotChange> TYPE = new Type<>(ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, NeoForgeMessageSlotChange> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, NeoForgeMessageSlotChange::x,
            ByteBufCodecs.INT, NeoForgeMessageSlotChange::y,
            ByteBufCodecs.INT, NeoForgeMessageSlotChange::z,
            ByteBufCodecs.INT, NeoForgeMessageSlotChange::index,
            ByteBufCodecs.INT, NeoForgeMessageSlotChange::set,
            NeoForgeMessageSlotChange::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static NeoForgeMessageSlotChange create(int x, int y, int z, int index, int set) {
        return new NeoForgeMessageSlotChange(x, y, z, index, set);
    }

    public void handle(IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Player player = ctx.player();
            BlockPos pos = new BlockPos(x,y,z);
            NeoForgeBlockFactoryBlockEntity blockEntity = (NeoForgeBlockFactoryBlockEntity) player.level().getBlockEntity(pos);
            assert blockEntity != null;
            if (player.level().isLoaded(pos)) {
                    blockEntity.setData(index, set);
                    Objects.requireNonNull(blockEntity.getLevel()).markAndNotifyBlock(pos, player.level().getChunkAt(pos), blockEntity.getLevel().getBlockState(pos).getBlock().defaultBlockState(), blockEntity.getLevel().getBlockState(pos), 2, 0);
                    blockEntity.setChanged();
           }
        });
    }
}
