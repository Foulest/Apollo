package net.foulest.apollo.check.impl.killaura;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

@CheckData(name = "KillAura (G)")
public final class KillAuraG extends PacketCheck {

    public KillAuraG(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInFlying) {
            boolean attacking = playerData.getActionManager().getAttacking().get();
            boolean swinging = playerData.getActionManager().getSwinging().get();

            if (attacking && !swinging) {
                playerData.kick(getCheckName(), "");
            }
        }
    }
}
