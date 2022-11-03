package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.MapIcon;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutMap;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutMap extends PacketWrapper {

    public WrappedPlayOutMap(Packet<?> instance) {
        super(instance, PacketPlayOutMap.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getA() {
        return get("a");
    }

    public double getB() {
        return ((byte) get("b")) / 32.d;
    }

    public MapIcon[] getMapIcons() {
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

    public byte[] getH() {
        return get("h");
    }
}
