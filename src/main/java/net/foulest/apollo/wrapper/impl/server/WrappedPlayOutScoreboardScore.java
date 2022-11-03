package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutScoreboardScore extends PacketWrapper {

    public WrappedPlayOutScoreboardScore(Packet<?> instance) {
        super(instance, PacketPlayOutScoreboardScore.class);
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

    public int getC() {
        return get("c");
    }

    public PacketPlayOutScoreboardScore.EnumScoreboardAction getAction() {
        return get("d");
    }
}
