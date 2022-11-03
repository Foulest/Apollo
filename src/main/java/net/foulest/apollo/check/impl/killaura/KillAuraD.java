package net.foulest.apollo.check.impl.killaura;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

@CheckData(name = "KillAura (D)")
public final class KillAuraD extends PacketCheck {

    private int streak = 0;

    public KillAuraD(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInUseEntity) {
            WrappedPlayInUseEntity wrapper = (WrappedPlayInUseEntity) object;

            if (wrapper.getAction() == PacketPlayInUseEntity.EnumEntityUseAction.ATTACK) {
                // Player swung and attacked
                if (!playerData.getActionManager().getSwinging().get()) {
                    if (++streak > 2) {
                        playerData.kick(getCheckName(), "");
                    }
                } else {
                    streak = 0;
                }
            }
        }
    }
}
