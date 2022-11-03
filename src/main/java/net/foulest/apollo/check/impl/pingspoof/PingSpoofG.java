package net.foulest.apollo.check.impl.pingspoof;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.wrapper.impl.server.WrappedPlayOutTransaction;

// Detects modifying Transaction packets
@CheckData(name = "PingSpoof (G)")
public final class PingSpoofG extends PacketCheck {

    public int lastId;

    public PingSpoofG(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (object instanceof WrappedPlayOutTransaction) {
            WrappedPlayOutTransaction packet = (WrappedPlayOutTransaction) object;
            int diff = packet.getId() - lastId;

            if (Math.abs(diff) >= 7 && lastId != 0 && Math.abs(packet.getId()) >= 30) {
                playerData.kick(getCheckName(), "MODIFY (DIFF=" + diff + ", ID=" + packet.getId() + ")");
                return;
            }

            lastId = packet.getId();
        }
    }
}
