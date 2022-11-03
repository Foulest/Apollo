package net.foulest.apollo.check.impl.badpackets;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

// Detects sending Post packets
@CheckData(name = "BadPackets (A)")
public final class BadPacketsA extends PacketCheck {

    public long lastFlying;
    public long lastPacket;
    public double buffer = 0.0;
    private boolean sent = false;

    public BadPacketsA(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (isPost(object) && !(object instanceof WrappedPlayInFlying)) {
            playerData.kick(getCheckName(), object.getClass().getName());
        }
    }

    public boolean isPost(Object object) {
        if (object.getClass() == WrappedPlayInFlying.class) {
            long now = System.currentTimeMillis();
            long delay = now - lastPacket;

            if (sent) {
                if (delay > 40L && delay < 100L) {
                    buffer += 0.25;

                    if (buffer > 0.5) {
                        return true;
                    }
                } else {
                    buffer = Math.max(buffer - 0.025, 0);
                }

                sent = false;
            }

            lastFlying = now;

        } else {
            long now = System.currentTimeMillis();
            long delay = now - lastFlying;

            if (delay < 10L) {
                lastPacket = now;
                sent = true;
            } else {
                buffer = Math.max(buffer - 0.025, 0.0);
            }
        }

        return false;
    }
}
