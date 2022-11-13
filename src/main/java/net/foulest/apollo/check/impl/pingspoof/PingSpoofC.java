package net.foulest.apollo.check.impl.pingspoof;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInKeepAlive;
import net.foulest.apollo.wrapper.impl.server.WrappedPlayOutKeepAlive;

// Detects receiving more KeepAlive packets than sent
@CheckData(name = "PingSpoof (C)")
public final class PingSpoofC extends PacketCheck {

    public int packetsSent;
    public int packetsReceived;

    public PingSpoofC(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (object instanceof WrappedPlayOutKeepAlive) {
            ++packetsSent;

        } else if (object instanceof WrappedPlayInKeepAlive) {
            if (++packetsReceived > packetsSent) {
                if (packetsSent == 0 && packetsReceived == 1) {
                    return;
                }

                playerData.kick(getCheckName(), "SENT=" + packetsSent + " RECEIVED=" + packetsReceived);
            }
        }
    }
}
