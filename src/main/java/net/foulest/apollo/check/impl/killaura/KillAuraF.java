package net.foulest.apollo.check.impl.killaura;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.util.MathUtil;

@CheckData(name = "KillAura (F)")
public final class KillAuraF extends PacketCheck {

    private double lastPosX = 0.0d;
    private double lastPosZ = 0.0d;
    private double lastHorizontalDistance = 0.0d;
    private float lastYaw = 0L;
    private float lastPitch = 0L;

    public KillAuraF(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInFlying) {
            WrappedPlayInFlying wrapper = (WrappedPlayInFlying) object;

            if (!wrapper.hasLook() || !wrapper.hasPos()) {
                return;
            }

            double posX = wrapper.getX();
            double posZ = wrapper.getZ();
            float yaw = wrapper.getYaw();
            float pitch = wrapper.getPitch();
            double horizontalDistance = MathUtil.magnitude(posX - lastPosX, posZ - lastPosZ);

            // Player moved
            if (horizontalDistance > 0.0) {
                float deltaYaw = Math.abs(yaw - lastYaw);
                float deltaPitch = Math.abs(pitch - lastPitch);
                boolean attacking = playerData.getActionManager().getAttacking().get();
                double acceleration = Math.abs(horizontalDistance - lastHorizontalDistance);

                // Player made a large head rotation and didn't accelerate / decelerate which is impossible
                if (acceleration < 1e-02 && deltaYaw > 30.f && deltaPitch > 15.f && attacking) {
                    playerData.kick(getCheckName(), "");
                }
            }

            lastHorizontalDistance = horizontalDistance;
            lastYaw = yaw;
            lastPitch = pitch;
            lastPosX = posX;
            lastPosZ = posZ;
        }
    }
}
