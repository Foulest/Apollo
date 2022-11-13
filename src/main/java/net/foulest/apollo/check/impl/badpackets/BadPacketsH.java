package net.foulest.apollo.check.impl.badpackets;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInSteerVehicle;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

// Detects ignoring the Position idle packet
// Same check as PingSpoof H
@CheckData(name = "BadPackets (H)")
public final class BadPacketsH extends PacketCheck {

    private int streak = 0;

    public BadPacketsH(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInFlying) {
            WrappedPlayInFlying wrapper = (WrappedPlayInFlying) object;

            if (!wrapper.hasPos() && playerData.getBukkitPlayer().getVehicle() == null) {
                if (++streak > 20) {
                    fail("", true);
                    playerData.kick(getCheckName(), "Idle Packet");
                }
            } else {
                streak = 0;
            }

        } else if (object instanceof WrappedPlayInSteerVehicle) {
            streak = 0;
        }
    }
}
