package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutPlayerListHeaderFooter extends PacketWrapper {

    public WrappedPlayOutPlayerListHeaderFooter(Packet<?> instance) {
        super(instance, PacketPlayOutPlayerListHeaderFooter.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public IChatBaseComponent getHeader() {
        return get("a");
    }

    public IChatBaseComponent getFooter() {
        return get("b");
    }
}
