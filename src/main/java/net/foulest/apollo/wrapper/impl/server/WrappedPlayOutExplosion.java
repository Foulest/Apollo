package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutExplosion;
import net.foulest.apollo.wrapper.PacketWrapper;

import java.util.List;

public final class WrappedPlayOutExplosion extends PacketWrapper {

    public WrappedPlayOutExplosion(Packet<?> instance) {
        super(instance, PacketPlayOutExplosion.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public double getA() {
        return get("a");
    }

    public double getB() {
        return get("b");
    }

    public double getC() {
        return get("c");
    }

    public float getD() {
        return get("d");
    }

    public List<BlockPosition> getBlockPositions() {
        return get("e");
    }

    public float getF() {
        return get("f");
    }

    public float getG() {
        return get("g");
    }

    public float getH() {
        return get("h");
    }
}
