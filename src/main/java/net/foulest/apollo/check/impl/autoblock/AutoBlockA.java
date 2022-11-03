package net.foulest.apollo.check.impl.autoblock;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInArmAnimation;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInBlockDig;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;

// Detects basic AutoBlock
@CheckData(name = "AutoBlock (A)")
public final class AutoBlockA extends PacketCheck {

    public int stage;

    public AutoBlockA(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInBlockDig) {
            WrappedPlayInBlockDig packet = (WrappedPlayInBlockDig) object;

            if (packet.getDigType() == PacketPlayInBlockDig.EnumPlayerDigType.RELEASE_USE_ITEM) {
                if (stage == 0) {
                    ++stage;
                }
            }

        } else if (object instanceof WrappedPlayInArmAnimation) {
            if (stage == 1) {
                ++stage;
            }

        } else if (object instanceof WrappedPlayInUseEntity) {
            WrappedPlayInUseEntity packet = (WrappedPlayInUseEntity) object;

            if (packet.getAction() == PacketPlayInUseEntity.EnumEntityUseAction.ATTACK) {
                if (stage == 2) {
                    ++stage;
                }
            }

        } else if (object instanceof WrappedPlayInFlying) {
            WrappedPlayInFlying packet = (WrappedPlayInFlying) object;

            // Ignores Look packets.
            if (!(packet.hasLook() && !packet.hasPos())) {
                stage = 0;
            }
        }

        if (stage == 3) {
            playerData.kick(getCheckName(), "");
        }
    }
}
