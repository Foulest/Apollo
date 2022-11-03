package net.foulest.apollo.wrapper.impl.server;

import net.foulest.apollo.wrapper.PacketWrapper;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldBorder;

public final class WrappedPlayOutWorldBorder extends PacketWrapper {

    public WrappedPlayOutWorldBorder(Packet<?> instance) {
        super(instance, PacketPlayOutWorldBorder.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public PacketPlayOutWorldBorder.EnumWorldBorderAction getAction() {
        return get("a");
    }

    public int getB() {
        return get("b");
    }

    public double getC() {
        return get("c");
    }

    public double getD() {
        return get("d");
    }

    public double getE() {
        return get("e");
    }

    public double getF() {
        return get("f");
    }

    public long getG() {
        return get("g");
    }

    public int getH() {
        return get("h");
    }

    public int getI() {
        return get("i");
    }
}
