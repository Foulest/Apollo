package net.foulest.apollo.check.impl.badpackets;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInTabComplete;

// Detects sending invalid TabComplete packets
@CheckData(name = "BadPackets (L)")
public class BadPacketsL extends PacketCheck {

    public int count = 0;

    public BadPacketsL(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInTabComplete) {
            WrappedPlayInTabComplete packet = (WrappedPlayInTabComplete) object;
            String message = packet.getMessage();

            // Detects sending blank tab complete packets.
            if (message.equals("")) {
                playerData.kick(getCheckName(), "Empty Message");
            }

            // Detects sending multiple packets in the same tick.
            if (++count > 1) {
                playerData.kick(getCheckName(), "Count (" + count + ")");
            }

        } else if (object instanceof WrappedPlayInFlying) {
            count = 0;
        }
    }
}
