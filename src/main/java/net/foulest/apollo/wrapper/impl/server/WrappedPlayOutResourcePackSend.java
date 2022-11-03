package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutResourcePackSend;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutResourcePackSend extends PacketWrapper {

    public WrappedPlayOutResourcePackSend(Packet<?> instance) {
        super(instance, PacketPlayOutResourcePackSend.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public String getURL() {
        return get("a");
    }

    public String getHash() {
        return get("b");
    }
}
