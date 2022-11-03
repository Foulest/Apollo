package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutKickDisconnect;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutKickDisconnect extends PacketWrapper {

    public WrappedPlayOutKickDisconnect(Packet<?> instance) {
        super(instance, PacketPlayOutKickDisconnect.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public IChatBaseComponent getMessage() {
        return get("a");
    }
}
