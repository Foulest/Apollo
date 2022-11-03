package net.foulest.apollo.wrapper.impl.client;

import net.foulest.apollo.wrapper.PacketWrapper;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;

public final class WrappedPlayInFlying extends PacketWrapper {

    public WrappedPlayInFlying(Packet<?> instance) {
        super(instance, PacketPlayInFlying.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public double getX() {
        return get("x");
    }

    public double getY() {
        return get("y");
    }

    public double getZ() {
        return get("z");
    }

    public float getYaw() {
        return get("yaw");
    }

    public float getPitch() {
        return get("pitch");
    }

    public boolean hasPos() {
        return get("hasPos");
    }

    public boolean hasLook() {
        return get("hasLook");
    }

    public boolean isOnGround() {
        return get("f");
    }
}
