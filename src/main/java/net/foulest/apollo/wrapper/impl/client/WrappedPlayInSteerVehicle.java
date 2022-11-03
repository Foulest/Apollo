package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInSteerVehicle;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayInSteerVehicle extends PacketWrapper {

    public WrappedPlayInSteerVehicle(Packet<?> instance) {
        super(instance, PacketPlayInSteerVehicle.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public float getForward() {
        return get("a");
    }

    public float getSide() {
        return get("b");
    }

    public boolean getC() {
        return get("c");
    }

    public boolean getD() {
        return get("d");
    }
}
