package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutUpdateHealth;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutUpdateHealth extends PacketWrapper {

    public WrappedPlayOutUpdateHealth(Packet<?> instance) {
        super(instance, PacketPlayOutUpdateHealth.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public float getA() {
        return get("a");
    }

    public int getB() {
        return get("b");
    }

    public float getC() {
        return get("c");
    }
}
