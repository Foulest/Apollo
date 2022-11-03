package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutMapChunk;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutMapChunk extends PacketWrapper {

    public WrappedPlayOutMapChunk(Packet<?> instance) {
        super(instance, PacketPlayOutMapChunk.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getA() {
        return get("a");
    }

    public int getB() {
        return get("b");
    }

    public PacketPlayOutMapChunk.ChunkMap getChunkMap() {
        return get("c");
    }

    public boolean getD() {
        return get("d");
    }
}
