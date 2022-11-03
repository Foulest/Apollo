package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutRemoveEntityEffect;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutRemoveEntityEffect extends PacketWrapper {

    public WrappedPlayOutRemoveEntityEffect(Packet<?> instance) {
        super(instance, PacketPlayOutRemoveEntityEffect.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getEntityId() {
        return get("a");
    }

    public int getEffectId() {
        return get("b");
    }
}
