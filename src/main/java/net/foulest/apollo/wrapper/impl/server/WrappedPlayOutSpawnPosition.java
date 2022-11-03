package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnPosition;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutSpawnPosition extends PacketWrapper {

    public WrappedPlayOutSpawnPosition(Packet<?> instance) {
        super(instance, PacketPlayOutSpawnPosition.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public BlockPosition getBlockPosition() {
        return get("a");
    }
}
