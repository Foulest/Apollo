package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutEntityDestroy extends PacketWrapper {

    public WrappedPlayOutEntityDestroy(Packet<?> instance) {
        super(instance, PacketPlayOutEntityDestroy.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int[] getEntityIds() {
        return get("a");
    }
}
