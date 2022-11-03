package net.foulest.apollo.check.impl.autoblock;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInBlockDig;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig.EnumPlayerDigType;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

// Detects sending multiple ReleaseUseItem packets in the same tick
@CheckData(name = "AutoBlock (C)")
public final class AutoBlockC extends PacketCheck {

    private int count = 0;

    public AutoBlockC(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInBlockDig) {
            WrappedPlayInBlockDig wrapper = (WrappedPlayInBlockDig) object;

            if (wrapper.getDigType() == EnumPlayerDigType.RELEASE_USE_ITEM) {
                if (++count > 1) {
                    playerData.kick(getCheckName(), "Invalid Use Item");
                }
            }

        } else if (object instanceof WrappedPlayInFlying) {
            count = 0;
        }
    }
}