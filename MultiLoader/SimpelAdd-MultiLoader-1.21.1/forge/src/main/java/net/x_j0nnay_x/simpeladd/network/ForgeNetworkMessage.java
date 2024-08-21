package net.x_j0nnay_x.simpeladd.network;

import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.data.OutPutSlotChange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.core.BlockPos;

public class ForgeNetworkMessage {
    protected static final Logger log = LogManager.getLogger(SimpelAddMod.LOGNAME);

    protected ForgeNetworkMessage() {}

    public static void sendSlotChangeToServer(BlockPos blockPos, OutPutSlotChange slotMode) {
        if (blockPos != null && slotMode != null) {
            ForgeNetworkHandler.sendToServer(new ForgeMessageSlotChange(blockPos, slotMode));
        }
    }
}
