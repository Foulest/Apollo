package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutUpdateTime;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutUpdateTime extends PacketWrapper {

    public WrappedPlayOutUpdateTime(Packet<?> instance) {
        super(instance, PacketPlayOutUpdateTime.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public long getA() {
        return get("a");
    }

    public long getB() {
        return get("b");
    }
}
