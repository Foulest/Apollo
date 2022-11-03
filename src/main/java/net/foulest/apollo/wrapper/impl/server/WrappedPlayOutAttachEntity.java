package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutAttachEntity;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutAttachEntity extends PacketWrapper {

    public WrappedPlayOutAttachEntity(Packet<?> instance) {
        super(instance, PacketPlayOutAttachEntity.class);
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
}
