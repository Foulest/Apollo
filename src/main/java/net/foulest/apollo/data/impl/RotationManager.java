package net.foulest.apollo.data.impl;

import lombok.RequiredArgsConstructor;
import net.foulest.apollo.check.type.RotationCheck;
import net.foulest.apollo.update.RotationUpdate;
import net.foulest.apollo.data.PlayerData;

@RequiredArgsConstructor
public final class RotationManager {

    private final PlayerData playerData;
    private float lastYaw;
    private float lastPitch;

    public void handle(float yaw, float pitch) {
        float deltaYaw = Math.abs(yaw - lastYaw);
        float deltaPitch = Math.abs(pitch - lastPitch);
        RotationUpdate rotationUpdate = new RotationUpdate(deltaYaw, deltaPitch);

        playerData.getCheckManager().getChecks().stream()
                .filter(RotationCheck.class::isInstance)
                .forEach(check -> check.process(rotationUpdate));

        lastYaw = yaw;
        lastPitch = pitch;
    }
}
