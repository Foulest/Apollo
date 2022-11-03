package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutCamera;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutCamera extends PacketWrapper {

    public WrappedPlayOutCamera(Packet<?> instance) {
        super(instance, PacketPlayOutCamera.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getA() {
        return get("a");
    }
}
