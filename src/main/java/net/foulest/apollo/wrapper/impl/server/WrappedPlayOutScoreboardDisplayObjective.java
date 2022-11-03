package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardDisplayObjective;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutScoreboardDisplayObjective extends PacketWrapper {

    public WrappedPlayOutScoreboardDisplayObjective(Packet<?> instance) {
        super(instance, PacketPlayOutScoreboardDisplayObjective.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getA() {
        return get("a");
    }

    public String getB() {
        return get("b");
    }
}
