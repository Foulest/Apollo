package net.foulest.apollo.check.type;

import net.foulest.apollo.check.Check;
import net.foulest.apollo.data.PlayerData;

public class PacketCheck extends Check<Object> {

    public PacketCheck(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object wrapper) {
        playerData.getCheckManager().getChecks()
                .stream().filter(PacketCheck.class::isInstance)
                .forEach(check -> check.process(wrapper));
    }
}
