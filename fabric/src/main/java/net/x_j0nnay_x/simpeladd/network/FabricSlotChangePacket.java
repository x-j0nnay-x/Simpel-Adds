package net.x_j0nnay_x.simpeladd.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.x_j0nnay_x.simpeladd.SimpelAddModFabric;

public record FabricSlotChangePacket(int index, int set) implements CustomPacketPayload  {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, "blockfactory_slot_packet");
    public static final CustomPacketPayload.Type<FabricSlotChangePacket> TYPE = new Type<>(ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, FabricSlotChangePacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, FabricSlotChangePacket::index,
            ByteBufCodecs.INT, FabricSlotChangePacket::set,
            FabricSlotChangePacket::new);

    public static FabricSlotChangePacket create(int index, int set) {
        return new FabricSlotChangePacket(index, set);
    }


    public static void receive(FabricSlotChangePacket payload, ServerPlayNetworking.Context context) {
        context.player().containerMenu.broadcastFullState();
        context.player().containerMenu.setData(payload.index, payload.set);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

}
