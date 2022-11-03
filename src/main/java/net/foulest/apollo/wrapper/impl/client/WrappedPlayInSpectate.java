package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInSpectate;
import net.foulest.apollo.wrapper.PacketWrapper;

import java.util.UUID;

public final class WrappedPlayInSpectate extends PacketWrapper {

    public WrappedPlayInSpectate(Packet<?> instance) {
        super(instance, PacketPlayInSpectate.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public UUID getUUID() {
        return get("a");
    }
}
