package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutHeldItemSlot;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutHeldItemSlot extends PacketWrapper {

    public WrappedPlayOutHeldItemSlot(Packet<?> instance) {
        super(instance, PacketPlayOutHeldItemSlot.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getSlot() {
        return get("a");
    }
}
