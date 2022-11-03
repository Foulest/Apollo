package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardObjective;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutScoreboardObjective extends PacketWrapper {

    public WrappedPlayOutScoreboardObjective(Packet<?> instance) {
        super(instance, PacketPlayOutScoreboardObjective.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public String getA() {
        return get("a");
    }

    public String getB() {
        return get("b");
    }

    public IScoreboardCriteria.EnumScoreboardHealthDisplay getHealthDisplay() {
        return get("c");
    }

    public int getD() {
        return get("d");
    }
}
