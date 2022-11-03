package net.foulest.apollo.check.impl.badpackets;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInUpdateSign;
import net.foulest.apollo.wrapper.impl.server.WrappedPlayOutBlockChange;
import net.foulest.apollo.wrapper.impl.server.WrappedPlayOutOpenSignEditor;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

// Detects sending invalid UpdateSign packets
@CheckData(name = "BadPackets (W)")
public class BadPacketsK extends PacketCheck {

    public boolean sentUpdateSign;
    public boolean sentSignEditor;
    public boolean sentBlockChange;

    public BadPacketsK(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayOutOpenSignEditor) {
            sentSignEditor = true;

        } else if (object instanceof WrappedPlayOutBlockChange) {
            sentBlockChange = true;

        } else if (object instanceof WrappedPlayInUpdateSign) {
            if (!sentSignEditor) {
                playerData.kick(getCheckName(), "No Sign Editor");
                return;
            }

            sentUpdateSign = true;
            sentSignEditor = false;

        } else if (object instanceof WrappedPlayInFlying) {
            if (sentUpdateSign && !sentBlockChange) {
                playerData.kick(getCheckName(), "No Block Change");
                return;
            }

            sentUpdateSign = false;
            sentBlockChange = false;
        }
    }
}
