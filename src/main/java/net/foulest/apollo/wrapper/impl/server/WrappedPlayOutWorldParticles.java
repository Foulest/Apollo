package net.foulest.apollo.wrapper.impl.server;

import net.foulest.apollo.wrapper.PacketWrapper;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public final class WrappedPlayOutWorldParticles extends PacketWrapper {

    public WrappedPlayOutWorldParticles(Packet<?> instance) {
        super(instance, PacketPlayOutWorldParticles.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public EnumParticle getParticle() {
        return get("a");
    }

    public float getB() {
        return get("b");
    }

    public float getC() {
        return get("c");
    }

    public float getD() {
        return get("d");
    }

    public float getE() {
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

    public int getI() {
        return get("i");
    }

    public boolean getJ() {
        return get("j");
    }

    public int[] getK() {
        return get("k");
    }
}
