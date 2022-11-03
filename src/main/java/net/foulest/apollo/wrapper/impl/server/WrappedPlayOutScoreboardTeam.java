package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardTeam;
import net.foulest.apollo.wrapper.PacketWrapper;

import java.util.Collection;

public final class WrappedPlayOutScoreboardTeam extends PacketWrapper {

    public WrappedPlayOutScoreboardTeam(Packet<?> instance) {
        super(instance, PacketPlayOutScoreboardTeam.class);
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

    public String getC() {
        return get("c");
    }

    public String getD() {
        return get("d");
    }

    public String getE() {
        return get("e");
    }

    public int getF() {
        return get("f");
    }

    public Collection<String> getG() {
        return get("g");
    }

    public int getH() {
        return get("h");
    }

    public int getI() {
        return get("i");
    }
}
