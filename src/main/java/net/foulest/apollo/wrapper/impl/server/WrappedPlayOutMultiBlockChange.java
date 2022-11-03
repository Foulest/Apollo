package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.ChunkCoordIntPair;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutMultiBlockChange;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutMultiBlockChange extends PacketWrapper {

    public WrappedPlayOutMultiBlockChange(Packet<?> instance) {
        super(instance, PacketPlayOutMultiBlockChange.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public ChunkCoordIntPair getA() {
        return get("a");
    }

    public PacketPlayOutMultiBlockChange.MultiBlockChangeInfo[] getB() {
        return get("b");
    }
}
