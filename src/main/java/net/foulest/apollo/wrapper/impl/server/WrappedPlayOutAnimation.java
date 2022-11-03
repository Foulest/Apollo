package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutAnimation extends PacketWrapper {

    public WrappedPlayOutAnimation(Packet<?> instance) {
        super(instance, PacketPlayOutAnimation.class);
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
}
