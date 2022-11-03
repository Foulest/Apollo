package net.foulest.apollo.check.impl.badpackets;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInEntityAction;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

// Detects sending the same EntityAction in a row
@CheckData(name = "BadPackets (C)")
public final class BadPacketsC extends PacketCheck {

    private int count = 0;
    private PacketPlayInEntityAction.EnumPlayerAction lastAction;

    public BadPacketsC(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInEntityAction) {
            WrappedPlayInEntityAction wrapper = (WrappedPlayInEntityAction) object;

            if (++count > 1 && wrapper.getAction() == lastAction) {
                playerData.kick(getCheckName(), "Same Action");
            }

            lastAction = wrapper.getAction();

        } else if (object instanceof WrappedPlayInFlying) {
            count = 0;
        }
    }
}
