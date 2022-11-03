package net.foulest.apollo.check.impl.pingspoof;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInKeepAlive;

// Detects sending multiple negative KeepAlive packets in a row
@CheckData(name = "PingSpoof (D)")
public final class PingSpoofD extends PacketCheck {

    public int streak;

    public PingSpoofD(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (object instanceof WrappedPlayInKeepAlive) {
            WrappedPlayInKeepAlive packet = (WrappedPlayInKeepAlive) object;

            if (packet.getId() == -1) {
                ++streak;
            } else {
                streak = 0;
            }

            if (streak >= 5) {
                playerData.kick(getCheckName(), "Negative");
            }
        }
    }
}
