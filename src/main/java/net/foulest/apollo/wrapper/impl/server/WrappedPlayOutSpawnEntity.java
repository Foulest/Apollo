package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutSpawnEntity extends PacketWrapper {

    public WrappedPlayOutSpawnEntity(Packet<?> instance) {
        super(instance, PacketPlayOutSpawnEntity.class);
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

    public int getC() {
        return get("c");
    }

    public int getD() {
        return get("d");
    }

    public int getE() {
        return get("e");
    }

    public int getF() {
        return get("f");
    }

    public int getG() {
        return get("g");
    }

    public int getH() {
        return get("h");
    }

    public int getI() {
        return get("i");
    }

    public int getJ() {
        return get("j");
    }

    public int getK() {
        return get("k");
    }
}
