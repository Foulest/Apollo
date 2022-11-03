package net.foulest.apollo.check.impl.pingspoof;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInKeepAlive;

// Detects cancelling KeepAlive packets
@CheckData(name = "PingSpoof (F)")
public final class PingSpoofF extends PacketCheck {

    public int flyingCount;
    public long lastReset;

    public PingSpoofF(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (object instanceof WrappedPlayInFlying) {
            ++flyingCount;

        } else if (object instanceof WrappedPlayInKeepAlive) {
            flyingCount = 0;
            lastReset = System.currentTimeMillis();
        }

        long timeSinceReset = System.currentTimeMillis() - lastReset;
        if (timeSinceReset == System.currentTimeMillis()) {
            return;
        }

        // Detects players sending 40 or more Flying packets
        // without sending a KeepAlive packet in 3 or more seconds.
        if (flyingCount >= 40 && timeSinceReset >= 3000) {
            playerData.kick(getCheckName(), "FLYING=" + flyingCount + " RESET=" + timeSinceReset);
        }
    }
}
