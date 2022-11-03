package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInKeepAlive;
import net.foulest.apollo.wrapper.PacketWrapper;

// TODO: Make setters for all wrappers
public final class WrappedPlayInKeepAlive extends PacketWrapper {

    public WrappedPlayInKeepAlive(Packet<?> instance) {
        super(instance, PacketPlayInKeepAlive.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getId() {
        return Math.abs(get("a"));
    }

    public void setId(int id) {
        set("a", id);
    }
}
