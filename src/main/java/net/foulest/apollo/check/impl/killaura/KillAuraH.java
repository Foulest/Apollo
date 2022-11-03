package net.foulest.apollo.check.impl.killaura;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInArmAnimation;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

// Detects attacking without swinging
@CheckData(name = "KillAura (H)")
public class KillAuraH extends PacketCheck {

    private boolean swung;

    public KillAuraH(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInUseEntity) {
            WrappedPlayInUseEntity wrapper = (WrappedPlayInUseEntity) object;

            if (wrapper.getAction() != PacketPlayInUseEntity.EnumEntityUseAction.ATTACK) {
                return;
            }

            if (!swung) {
                playerData.kick(getCheckName(), "No Swing");
            }

        } else if (object instanceof WrappedPlayInArmAnimation) {
            swung = true;

        } else if (object instanceof WrappedPlayInFlying) {
            swung = false;
        }
    }
}
