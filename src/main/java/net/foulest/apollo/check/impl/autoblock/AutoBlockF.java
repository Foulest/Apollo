package net.foulest.apollo.check.impl.autoblock;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInBlockDig;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInBlockPlace;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

// Detects sending invalid ReleaseUseItem packets
@CheckData(name = "AutoBlock (F)")
public class AutoBlockF extends PacketCheck {

    public boolean sent;

    public AutoBlockF(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInBlockPlace) {
            WrappedPlayInBlockPlace packet = (WrappedPlayInBlockPlace) object;

            // TODO: Should be OTHER
            if (packet.getFace() == 255) {
                sent = true;
            }

        } else if (object instanceof WrappedPlayInBlockDig) {
            WrappedPlayInBlockDig packet = (WrappedPlayInBlockDig) object;

            if (packet.getDigType() == PacketPlayInBlockDig.EnumPlayerDigType.RELEASE_USE_ITEM && !sent) {
                playerData.kick(getCheckName(), "");
            }

            sent = false;
        }
    }
}
