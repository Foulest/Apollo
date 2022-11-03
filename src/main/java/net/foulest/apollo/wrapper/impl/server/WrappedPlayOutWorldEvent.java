package net.foulest.apollo.wrapper.impl.server;

import net.foulest.apollo.wrapper.PacketWrapper;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldEvent;

public final class WrappedPlayOutWorldEvent extends PacketWrapper {

    public WrappedPlayOutWorldEvent(Packet<?> instance) {
        super(instance, PacketPlayOutWorldEvent.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getA() {
        return get("a");
    }

    public BlockPosition getB() {
        return get("b");
    }

    public int getC() {
        return get("c");
    }

    public boolean getD() {
        return get("d");
    }
}
