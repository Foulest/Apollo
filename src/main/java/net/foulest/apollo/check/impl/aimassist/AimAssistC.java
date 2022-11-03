package net.foulest.apollo.check.impl.aimassist;

import net.foulest.apollo.check.type.RotationCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.update.RotationUpdate;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.util.MathUtil;

@CheckData(name = "AimAssist (C)", threshold = 8)
public final class AimAssistC extends RotationCheck {

    private double buffer = 0.0;
    private float lastDeltaYaw = 0.0f;
    private float lastDeltaPitch = 0.0f;

    public AimAssistC(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(RotationUpdate update) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        // Get the delta yaw/pitch from the rotation update
        float deltaYaw = update.getDeltaYaw();
        float deltaPitch = update.getDeltaPitch();

        // Make sure the rotation is valid and that both of the rotations are not big
        if (deltaYaw > 0.0 && deltaPitch > 0.0 && deltaYaw < 30.d && deltaPitch < 20.d) {
            // Expand the current and the previous yaw
            long expandedYaw = (long) (deltaYaw * MathUtil.EXPANDER);
            long previousExpandedYaw = (long) (lastDeltaYaw * MathUtil.EXPANDER);

            // Expand the current and the previous pitch
            long expandedPitch = (long) (deltaPitch * MathUtil.EXPANDER);
            long previousExpandedPitch = (long) (lastDeltaPitch * MathUtil.EXPANDER);

            // Get the divisors of the yaw and the pitch
            double divisorPitch = MathUtil.getGcd(expandedPitch, previousExpandedPitch);
            double divisorYaw = MathUtil.getGcd(expandedYaw, previousExpandedYaw);

            // Make sure the player isn't using cinematic camera
            boolean cinematic = playerData.getCinematic().get();

            // Make sure both of them are bigger than 0
            if (divisorYaw > 0.0 && divisorPitch > 0.0 && !cinematic) {
                // This is the usual minimum GCD
                final double threshold = 131072;

                // Make sure one of the rotations isn't valid
                if (divisorYaw < threshold || divisorPitch < threshold) {
                    // Get their delta to compare
                    double deltaDivisor = Math.abs(divisorYaw - divisorPitch);

                    // It's seemingly impossible to do this and only clients that have a one way match for gcd flag for this.
                    boolean invalid = deltaDivisor > 700d;
                    boolean attacked = playerData.getActionManager().getAttacking().get();

                    // If the rotation is invalid and he attacked, flag
                    if (invalid && attacked) {
                        if (++buffer > 7) {
                            playerData.kick(getCheckName(), "");
                        }
                    }
                } else {
                    buffer = Math.max(buffer - 2.5, 0);
                }
            } else {
                buffer = 0;
            }
        }

        // Parse to the previous values
        lastDeltaYaw = deltaYaw;
        lastDeltaPitch = deltaPitch;
    }
}

