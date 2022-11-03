package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.EnumDirection;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityPainting;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutSpawnEntityPainting extends PacketWrapper {

    public WrappedPlayOutSpawnEntityPainting(Packet<?> instance) {
        super(instance, PacketPlayOutSpawnEntityPainting.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getA() {
        return get("a");
    }

    public BlockPosition getBlockPosition() {
        return get("b");
    }

    public EnumDirection getDirection() {
        return get("c");
    }

    public String getD() {
        return get("d");
    }
}
