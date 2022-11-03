package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutPosition;
import net.foulest.apollo.wrapper.PacketWrapper;

import java.util.Set;

public final class WrappedPlayOutPosition extends PacketWrapper {

    public WrappedPlayOutPosition(Packet<?> instance) {
        super(instance, PacketPlayOutPosition.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public double getX() {
        return (double) (get("a")) / 32.d;
    }

    public double getY() {
        return (double) (get("b")) / 32.d;
    }

    public double getZ() {
        return (double) (get("c")) / 32.d;
    }

    public float getD() {
        return get("d");
    }

    public float getE() {
        return get("e");
    }

    public Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> getTeleportFlags() {
        return get("f");
    }
}
