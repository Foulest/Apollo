package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutBed;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutBed extends PacketWrapper {

    public WrappedPlayOutBed(Packet<?> instance) {
        super(instance, PacketPlayOutBed.class);
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
}
