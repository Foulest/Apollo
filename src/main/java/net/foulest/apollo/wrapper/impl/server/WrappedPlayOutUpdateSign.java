package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.*;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutUpdateSign extends PacketWrapper {

    public WrappedPlayOutUpdateSign(Packet<?> instance) {
        super(instance, PacketPlayOutUpdateSign.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public World getWorld() {
        return get("a");
    }

    public BlockPosition getBlockPosition() {
        return get("b");
    }

    public IChatBaseComponent[] getLines() {
        return get("c");
    }
}
