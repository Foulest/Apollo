package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityHeadRotation;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutEntityHeadRotation extends PacketWrapper {

    public WrappedPlayOutEntityHeadRotation(Packet<?> instance) {
        super(instance, PacketPlayOutEntityHeadRotation.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getEntityId() {
        return get("a");
    }

    public double getB() {
        return ((byte) get("b")) / 32.d;
    }
}
