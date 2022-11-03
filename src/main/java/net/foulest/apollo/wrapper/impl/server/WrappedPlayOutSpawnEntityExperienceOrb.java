package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityExperienceOrb;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutSpawnEntityExperienceOrb extends PacketWrapper {

    public WrappedPlayOutSpawnEntityExperienceOrb(Packet<?> instance) {
        super(instance, PacketPlayOutSpawnEntityExperienceOrb.class);
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
}
