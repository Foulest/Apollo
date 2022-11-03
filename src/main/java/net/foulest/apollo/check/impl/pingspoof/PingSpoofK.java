package net.foulest.apollo.check.impl.pingspoof;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInTransaction;
import net.foulest.apollo.wrapper.impl.server.WrappedPlayOutTransaction;

// Detects receiving more Transaction packets than sent
@CheckData(name = "PingSpoof (K)")
public final class PingSpoofK extends PacketCheck {

    public int packetsSent;
    public int packetsReceived;

    public PingSpoofK(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (object instanceof WrappedPlayOutTransaction) {
            ++packetsSent;

        } else if (object instanceof WrappedPlayInTransaction) {
            if (++packetsReceived > packetsSent) {
                playerData.kick(getCheckName(), "SENT=" + packetsSent + " RECEIVED=" + packetsReceived);
            }
        }
    }
}
