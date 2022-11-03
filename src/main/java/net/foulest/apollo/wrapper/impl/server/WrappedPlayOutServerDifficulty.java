package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.EnumDifficulty;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutServerDifficulty;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutServerDifficulty extends PacketWrapper {

    public WrappedPlayOutServerDifficulty(Packet<?> instance) {
        super(instance, PacketPlayOutServerDifficulty.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public EnumDifficulty getDifficulty() {
        return get("a");
    }

    public boolean getB() {
        return get("b");
    }
}
