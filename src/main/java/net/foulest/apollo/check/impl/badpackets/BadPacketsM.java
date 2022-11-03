package net.foulest.apollo.check.impl.badpackets;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInResourcePackStatus;
import net.foulest.apollo.wrapper.impl.server.WrappedPlayOutResourcePackSend;
import net.minecraft.server.v1_8_R3.PacketPlayInResourcePackStatus;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

// Detects sending invalid ResourcePackStatus packets
@CheckData(name = "BadPackets (M)")
public class BadPacketsM extends PacketCheck {

    public boolean accepted;
    public int packetsSent;
    public int packetsReceived;

    public BadPacketsM(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayOutResourcePackSend) {
            ++packetsSent;

        } else if (object instanceof WrappedPlayInResourcePackStatus) {
            WrappedPlayInResourcePackStatus packet = (WrappedPlayInResourcePackStatus) object;

            // Keeps track of packets received.
            if (packet.getStatus() != PacketPlayInResourcePackStatus.EnumResourcePackStatus.ACCEPTED) {
                ++packetsReceived;
            }

            // Detects receiving more packets than sent.
            if (packetsReceived > packetsSent) {
                playerData.kick(getCheckName(), "SENT=" + packetsSent + " RECEIVED=" + packetsReceived);
            }

            // Detects sending two ACCEPTED packets in a row.
            switch (packet.getStatus()) {
                case DECLINED:
                case SUCCESSFULLY_LOADED:
                case FAILED_DOWNLOAD:
                    accepted = false;
                    break;

                case ACCEPTED:
                    if (accepted) {
                        playerData.kick(getCheckName(), "Accepted");
                        return;
                    }

                    accepted = true;
            }
        }
    }
}
