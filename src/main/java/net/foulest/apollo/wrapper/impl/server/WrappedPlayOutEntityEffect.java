package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEffect;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutEntityEffect extends PacketWrapper {

    public WrappedPlayOutEntityEffect(Packet<?> instance) {
        super(instance, PacketPlayOutEntityEffect.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getEntityId() {
        return get("a");
    }

    public double getB() {
        return ((byte) get("b")) / 32.d;
    }

    public double getC() {
        return ((byte) get("c")) / 32.d;
    }

    public int getD() {
        return get("d");
    }

    public double getE() {
        return ((byte) get("e")) / 32.d;
    }
}
