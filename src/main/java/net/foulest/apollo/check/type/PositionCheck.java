package net.foulest.apollo.check.type;

import net.foulest.apollo.update.PositionUpdate;
import net.foulest.apollo.check.Check;
import net.foulest.apollo.data.PlayerData;

public class PositionCheck extends Check<PositionUpdate> {

    public PositionCheck(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(PositionUpdate positionUpdate) {
    }
}
