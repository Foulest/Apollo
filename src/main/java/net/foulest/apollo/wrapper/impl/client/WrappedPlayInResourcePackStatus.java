package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInResourcePackStatus;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayInResourcePackStatus extends PacketWrapper {

    public WrappedPlayInResourcePackStatus(Packet<?> instance) {
        super(instance, PacketPlayInResourcePackStatus.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getHash() {
        return get("a");
    }

    public PacketPlayInResourcePackStatus.EnumResourcePackStatus getStatus() {
        return get("b");
    }
}
