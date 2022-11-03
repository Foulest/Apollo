package net.foulest.apollo.check.impl.aimassist;

import net.foulest.apollo.check.type.RotationCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.update.RotationUpdate;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

@CheckData(name = "AimAssist (B)")
public final class AimAssistB extends RotationCheck {

    private int lastRoundedYaw = 0;
    private int lastRoundedPitch = 0;
    private int streak = 0;

    public AimAssistB(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(RotationUpdate rotationUpdate) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        // Get the current system time to account for attacks
        long now = System.currentTimeMillis();

        // Get the deltas from the rotation update
        float deltaYaw = rotationUpdate.getDeltaYaw();
        float deltaPitch = rotationUpdate.getDeltaPitch();

        // Round up the rotations to get their first digit
        int roundedYaw = Math.round(deltaYaw);
        int roundedPitch = Math.round(deltaPitch);
        int roundedDelta = Math.abs(roundedPitch - lastRoundedPitch);

        // Make sure the player is attacking, isn't using cinematic and the rotations had the same first number
        boolean attacking = now - playerData.getActionManager().getLastAttack() < 500L;
        boolean cinematic = playerData.getCinematic().get();
        boolean identical = roundedYaw == lastRoundedYaw;

        // If the rotation was not proper, and the player wasn't spamming their aim, flag.
        if (identical && roundedDelta < 5 && deltaYaw < 20.f && deltaPitch < 20.f && attacking && cinematic) {
            if (++streak > 7) {
                playerData.kick(getCheckName(), "");
            }
        } else {
            streak = 0;
        }

        lastRoundedYaw = roundedYaw;
        lastRoundedPitch = roundedPitch;
    }
}
