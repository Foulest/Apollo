package net.foulest.apollo.check.impl.badpackets;

import net.foulest.apollo.wrapper.impl.client.WrappedPlayInChat;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;

// Detects sending invalid Chat packets
@CheckData(name = "BadPackets (B)")
public class BadPacketsB extends PacketCheck {

    public BadPacketsB(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInChat) {
            WrappedPlayInChat packet = (WrappedPlayInChat) object;

            if (packet.getMessage().equals("")) {
                playerData.kick(getCheckName(), "Empty Message");
            }
        }
    }
}
