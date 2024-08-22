package net.x_j0nnay_x.simpeladd.network;

import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.SimpelAddModForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

@EventBusSubscriber
public class ForgeNetworkHandler {

    protected static final Logger log = LogManager.getLogger(SimpelAddMod.LOGNAME);
    private static final int PROTOCOL_VERSION = 1;
    private static final SimpleChannel SIMPLE_CHANNEL =
            ChannelBuilder.named(ResourceLocation.fromNamespaceAndPath(SimpelAddModForge.MODID, "network"))
                    .networkProtocolVersion(PROTOCOL_VERSION)
                    .simpleChannel();
    private static int id = 0;

    public static void registerNetworkHandler(final FMLCommonSetupEvent event) {
        log.info(
                "{} Network Handler for {} with version {} ...",
                SimpelAddMod.LOG_REGISTER_PREFIX,
                SIMPLE_CHANNEL,
                PROTOCOL_VERSION);
        event.enqueueWork(
                () -> {
                    SIMPLE_CHANNEL
                            .messageBuilder(
                                    ForgeMessageSlotChange.class, id++, NetworkDirection.PLAY_TO_SERVER)
                            .decoder(ForgeMessageSlotChange::decode)
                            .encoder(ForgeMessageSlotChange::encode)
                            .consumerNetworkThread(ForgeMessageSlotChange::handle)
                            .add();
                });
    }
    public static <M> void sendToServer(M message) {
        try {
            SIMPLE_CHANNEL.send(message, PacketDistributor.SERVER.noArg());
        } catch (Exception e) {
            log.error("Failed to send {} to server, got error: {}", message, e.getMessage());
        }
    }
}
