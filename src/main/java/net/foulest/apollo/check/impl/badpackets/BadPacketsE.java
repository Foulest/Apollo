package net.foulest.apollo.check.impl.badpackets;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

// Detects sending the same yaw & pitch in a row
@CheckData(name = "BadPackets (E)")
public final class BadPacketsE extends PacketCheck {

    public BadPacketsE(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInFlying) {
            WrappedPlayInFlying wrapper = (WrappedPlayInFlying) object;

            if (wrapper.hasLook()) {
                float pitch = wrapper.getPitch();

                if (Math.abs(pitch) > 90) {
                    playerData.kick(getCheckName(), "Invalid Pitch");
                }
            }
        }
    }
}
