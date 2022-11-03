package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutExperience;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutExperience extends PacketWrapper {

    public WrappedPlayOutExperience(Packet<?> instance) {
        super(instance, PacketPlayOutExperience.class);
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

    public int getC() {
        return get("c");
    }
}
