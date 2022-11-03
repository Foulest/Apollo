package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutSetCompression;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutSetCompression extends PacketWrapper {

    public WrappedPlayOutSetCompression(Packet<?> instance) {
        super(instance, PacketPlayOutSetCompression.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getA() {
        return get("a");
    }
}
