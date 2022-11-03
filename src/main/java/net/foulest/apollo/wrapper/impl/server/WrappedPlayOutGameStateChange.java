package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutGameStateChange extends PacketWrapper {

    public WrappedPlayOutGameStateChange(Packet<?> instance) {
        super(instance, PacketPlayOutGameStateChange.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getB() {
        return get("b");
    }

    public float getC() {
        return get("c");
    }
}
