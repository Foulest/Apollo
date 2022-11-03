package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedSoundEffect;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutNamedSoundEffect extends PacketWrapper {

    public WrappedPlayOutNamedSoundEffect(Packet<?> instance) {
        super(instance, PacketPlayOutNamedSoundEffect.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public String getA() {
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

    public float getE() {
        return get("d");
    }

    public int getF() {
        return get("f");
    }
}
