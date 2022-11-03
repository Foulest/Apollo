package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutKeepAlive;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutKeepAlive extends PacketWrapper {

    public WrappedPlayOutKeepAlive(Packet<?> instance) {
        super(instance, PacketPlayOutKeepAlive.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getId() {
        return get("a");
    }

    public void setId(int time) {
        set("a", time);
    }
}
