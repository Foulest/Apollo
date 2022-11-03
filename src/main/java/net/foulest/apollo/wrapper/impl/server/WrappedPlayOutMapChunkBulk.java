package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutMapChunk;
import net.minecraft.server.v1_8_R3.PacketPlayOutMapChunkBulk;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutMapChunkBulk extends PacketWrapper {

    public WrappedPlayOutMapChunkBulk(Packet<?> instance) {
        super(instance, PacketPlayOutMapChunkBulk.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int[] getA() {
        return get("a");
    }

    public int[] getB() {
        return get("b");
    }

    public PacketPlayOutMapChunk.ChunkMap[] getChunkMaps() {
        return get("c");
    }

    public boolean getD() {
        return get("d");
    }

    public boolean getWorld() {
        return get("e");
    }
}
