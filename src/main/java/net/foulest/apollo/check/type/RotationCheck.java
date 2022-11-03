package net.foulest.apollo.check.type;

import net.foulest.apollo.update.RotationUpdate;
import net.foulest.apollo.check.Check;
import net.foulest.apollo.data.PlayerData;

public class RotationCheck extends Check<RotationUpdate> {

    public RotationCheck(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(RotationUpdate rotationUpdate) {
    }
}
