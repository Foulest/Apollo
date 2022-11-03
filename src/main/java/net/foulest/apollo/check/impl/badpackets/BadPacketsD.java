package net.foulest.apollo.check.impl.badpackets;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInBlockPlace;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

// Detects sending invalid BlockPlace packets
@CheckData(name = "BadPackets (D)")
public class BadPacketsD extends PacketCheck {

    public boolean sent;

    public BadPacketsD(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInBlockPlace) {
            WrappedPlayInBlockPlace packet = (WrappedPlayInBlockPlace) object;

            // Detects sending invalid blank UP packets.
            // TODO: Should be UP
            if (packet.getFace() == 0
                    && packet.getBlockPosition().getX() == 0.0
                    && packet.getBlockPosition().getY() == 0.0
                    && packet.getBlockPosition().getZ() == 0.0) {
                playerData.kick(getCheckName(), "Block Place");
            }
        }
    }
}
