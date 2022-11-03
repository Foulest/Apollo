package net.foulest.apollo.check.impl.killaura;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInArmAnimation;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInUseEntity;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInWindowClick;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;

// Detects Dortware's InfiniteDurability
@CheckData(name = "KillAura (J)")
public final class KillAuraJ extends PacketCheck {

    public int stage;

    public KillAuraJ(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInArmAnimation) {
            if (stage == 0) {
                ++stage;
            }

        } else if (object instanceof WrappedPlayInWindowClick) {
            WrappedPlayInWindowClick packet = (WrappedPlayInWindowClick) object;

            if (packet.getWindowId() == 0 && packet.getClickType() == 2) {
                if (stage == 1 || stage == 3) {
                    ++stage;
                }
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

        if (stage == 4) {
            playerData.kick(getCheckName(), "");
        }
    }
}
