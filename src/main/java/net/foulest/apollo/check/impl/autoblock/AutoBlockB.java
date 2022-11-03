package net.foulest.apollo.check.impl.autoblock;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInBlockDig;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInBlockPlace;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;

// Detects sending BlockPlace and ReleaseUseItem in the same tick
@CheckData(name = "AutoBlock (B)")
public final class AutoBlockB extends PacketCheck {

    public boolean sent;

    public AutoBlockB(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS, ExemptType.VEHICLE)) {
            return;
        }

        if (object instanceof WrappedPlayInBlockPlace) {
            sent = true;

        } else if (object instanceof WrappedPlayInFlying) {
            sent = false;

        } else if (object instanceof WrappedPlayInBlockDig) {
            WrappedPlayInBlockDig packet = (WrappedPlayInBlockDig) object;

            if (sent && packet.getDigType() == PacketPlayInBlockDig.EnumPlayerDigType.RELEASE_USE_ITEM) {
                playerData.kick(getCheckName(), "");
            }
        }
    }
}
