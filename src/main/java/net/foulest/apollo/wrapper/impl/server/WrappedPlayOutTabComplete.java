package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutTabComplete;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutTabComplete extends PacketWrapper {

    public WrappedPlayOutTabComplete(Packet<?> instance) {
        super(instance, PacketPlayOutTabComplete.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public String[] getMessage() {
        return get("a");
    }
}
