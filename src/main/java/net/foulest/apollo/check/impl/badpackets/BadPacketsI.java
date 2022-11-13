package net.foulest.apollo.check.impl.badpackets;

import net.minecraft.server.v1_8_R3.EnumDirection;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInBlockDig;

// Detects sending invalid BlockDig packets
@CheckData(name = "BadPackets (I)")
public class BadPacketsI extends PacketCheck {

    public boolean sentStart;
    public boolean sentStop;
    public boolean sentAbort;

    public BadPacketsI(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInBlockDig) {
            WrappedPlayInBlockDig packet = (WrappedPlayInBlockDig) object;

            if (packet.getDigType() == PacketPlayInBlockDig.EnumPlayerDigType.START_DESTROY_BLOCK) {
                sentStart = true;
                sentAbort = false;
                sentStop = false;
            }

            // Detects sending two CANCELLED_DIGGING packets in a row.
            if (packet.getDigType() == PacketPlayInBlockDig.EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
                if (sentAbort) {
                    playerData.kick(getCheckName(), "Sent Abort");
                }

                sentAbort = true;
                sentStart = false;
                sentStop = false;
            }

            // Detects sending two FINISHED_DIGGING packets in a row.
            if (packet.getDigType() == PacketPlayInBlockDig.EnumPlayerDigType.STOP_DESTROY_BLOCK) {
                if (sentStop) {
                    playerData.kick(getCheckName(), "Sent Stop");
                }

                sentStop = true;
                sentStart = false;
                sentAbort = false;
            }

            // Detects sending invalid RELEASE_USE_ITEM packets.
            if (packet.getDigType() == PacketPlayInBlockDig.EnumPlayerDigType.RELEASE_USE_ITEM
                /*&& player.getClientVersion().isNewerThanOrEquals(ClientVersion.V_1_8)*/) {
                if (packet.getDirection() != EnumDirection.DOWN
                        || packet.getBlockPosition().getX() != 0
                        || packet.getBlockPosition().getY() != 0
                        || packet.getBlockPosition().getZ() != 0) {
                    playerData.kick(getCheckName(), "RELEASE_USE_ITEM");
                }
            }
        }
    }
}
