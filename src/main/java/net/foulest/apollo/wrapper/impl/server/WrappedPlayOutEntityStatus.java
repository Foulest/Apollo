package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityStatus;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutEntityStatus extends PacketWrapper {

    public WrappedPlayOutEntityStatus(Packet<?> instance) {
        super(instance, PacketPlayOutEntityStatus.class);
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
