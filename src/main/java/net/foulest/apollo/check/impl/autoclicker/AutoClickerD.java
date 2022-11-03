package net.foulest.apollo.check.impl.autoclicker;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInArmAnimation;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

@CheckData(name = "AutoClicker (D)")
public final class AutoClickerD extends PacketCheck {

    private int movements = 0;
    private int clicks = 0;

    public AutoClickerD(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInArmAnimation) {
            boolean valid = movements < 100 && !playerData.getActionManager().getDigging().get();

            // If the player has clicked recently and the player isn't digging
            if (valid) {
                ++clicks;
            }

            // 20 movements = 1 second
            if (movements == 20) {
                boolean flag = clicks > 20;

                // Sent an extra swing in a tick
                if (flag) {
                    playerData.kick(getCheckName(), "");
                }

                // Reset the movements
                movements = 0;
            }

        } else if (object instanceof WrappedPlayInFlying) {
            ++movements;
        }
    }
}
