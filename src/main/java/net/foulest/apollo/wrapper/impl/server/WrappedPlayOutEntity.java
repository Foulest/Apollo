package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutEntity extends PacketWrapper {

    public WrappedPlayOutEntity(Packet<?> instance) {
        super(instance, PacketPlayOutEntity.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getEntityId() {
        return get("a");
    }

    public double getDeltaX() {
        return ((byte) get("b")) / 32.d;
    }

    public double getDeltaY() {
        return (byte) (get("c")) / 32.d;
    }

    public double getDeltaZ() {
        return ((byte) get("d")) / 32.d;
    }

    public double getE() {
        return (byte) (get("e")) / 32.d;
    }

    public double getF() {
        return ((byte) get("f")) / 32.d;
    }

    public boolean getG() {
        return get("g");
    }

    public boolean getH() {
        return get("h");
    }
}
