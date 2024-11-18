package net.x_j0nnay_x.simpeladd.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;

public class NeoForgeNetworkMessage {

    @SubscribeEvent
    public static void onRegisterPayloadHandler(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(SimpelAddMod.MOD_ID)
                .versioned("1.0")
                .optional();
        registrar.playToServer(NeoForgeMessageSlotChange.TYPE, NeoForgeMessageSlotChange.CODEC, NeoForgeMessageSlotChange::handle);

    }

    public static <MSG extends CustomPacketPayload> void sendToServer(MSG message) {
        PacketDistributor.sendToServer(message);
    }

    public static <MSG extends CustomPacketPayload> void sendToPlayer(MSG message, ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, message);
    }
}