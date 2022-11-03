package net.foulest.apollo.wrapper.impl.server;

import net.foulest.apollo.wrapper.PacketWrapper;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutWindowData;

public final class WrappedPlayOutWindowData extends PacketWrapper {

    public WrappedPlayOutWindowData(Packet<?> instance) {
        super(instance, PacketPlayOutWindowData.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getA() {
        return get("a");
    }

    public int getB() {
        return get("b");
    }

    public int getC() {
        return get("c");
    }
}
