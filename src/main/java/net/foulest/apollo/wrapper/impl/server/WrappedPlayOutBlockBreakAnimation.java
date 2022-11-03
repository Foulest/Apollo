package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockBreakAnimation;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutBlockBreakAnimation extends PacketWrapper {

    public WrappedPlayOutBlockBreakAnimation(Packet<?> instance) {
        super(instance, PacketPlayOutBlockBreakAnimation.class);
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

    public int getC() {
        return get("c");
    }
}
