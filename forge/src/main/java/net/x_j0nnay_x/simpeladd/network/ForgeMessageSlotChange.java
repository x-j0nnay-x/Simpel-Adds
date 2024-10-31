package net.x_j0nnay_x.simpeladd.network;

import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.blocks.entity.Abst_BlockFactoryBlockEntity;
import net.x_j0nnay_x.simpeladd.data.OutPutSlotChange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class ForgeMessageSlotChange {

    protected static final Logger log = LogManager.getLogger(SimpelAddMod.LOGNAME);
    protected final BlockPos blockPos;
    protected final OutPutSlotChange slotChange;

    public ForgeMessageSlotChange(BlockPos blockPos, OutPutSlotChange slotMode) {
        this.blockPos = blockPos;
        this.slotChange = slotMode;
    }

    public static ForgeMessageSlotChange decode(final FriendlyByteBuf buffer) {
        BlockPos blockPos = buffer.readBlockPos();
        OutPutSlotChange putSlotChange = buffer.readEnum(OutPutSlotChange.class);
        return new ForgeMessageSlotChange(blockPos, putSlotChange);
    }

    public static void encode(final ForgeMessageSlotChange message, final FriendlyByteBuf buffer) {
        buffer.writeBlockPos(message.getBlockPos());
        buffer.writeEnum(message.getSlotMode());
    }

    public static void handle(ForgeMessageSlotChange message, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> handlePacket(message, context));
        context.setPacketHandled(true);
    }

    public static void handlePacket(
            ForgeMessageSlotChange message, CustomPayloadEvent.Context context) {
        ServerPlayer serverPlayer = context.getSender();
        if (serverPlayer == null) {
            return;
        }
        BlockPos blockPos = message.getBlockPos();
        if (blockPos == null) {
            log.error("Received invalid block position for slot change!");
            return;
        }
        BlockEntity blockEntity = serverPlayer.level().getBlockEntity(blockPos);
        if (blockEntity == null) {
            log.error("Received invalid block entity for slot change!");
            return;
        }
        if (!(blockEntity instanceof Abst_BlockFactoryBlockEntity)) {
            log.error("Received invalid block entity type for slot change!");
            return;
        }
        OutPutSlotChange slotMode = message.getSlotMode();
        if (slotMode == null) {
            log.error("Received invalid slot for slot change!");
            return;
        }
        Abst_BlockFactoryBlockEntity blockFactory = (Abst_BlockFactoryBlockEntity) blockEntity;
        log.debug("Change Slot for {} to {}", blockFactory, slotMode);
        blockFactory.setSlotOutPut(slotMode);
    }

    public OutPutSlotChange getSlotMode() {
        return this.slotChange;
    }

    public BlockPos getBlockPos() {
        return this.blockPos;
    }
}
