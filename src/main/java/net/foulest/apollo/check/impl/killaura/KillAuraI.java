package net.foulest.apollo.check.impl.killaura;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInArmAnimation;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;

// Detects sending invalid EntityInteract packets
@CheckData(name = "KillAura (I)")
public class KillAuraI extends PacketCheck {

    public int attacks;

    public KillAuraI(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInUseEntity) {
            WrappedPlayInUseEntity packet = (WrappedPlayInUseEntity) object;

            // Detects interacting with yourself.
            if (packet.getEntityId() == playerData.getBukkitPlayer().getEntityId()) {
                playerData.kick(getCheckName(), "Self Interact");
            }

            // Detects attacking without swinging.
            if (packet.getAction() == PacketPlayInUseEntity.EnumEntityUseAction.ATTACK) {
                if (++attacks > 2) {
                    playerData.kick(getCheckName(), "No Swing");
                }
            }

        } else if (object instanceof WrappedPlayInArmAnimation) {
            attacks = 0;
        }
    }
}
