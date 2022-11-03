package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInHeldItemSlot;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayInHeldItemSlot extends PacketWrapper {

    public WrappedPlayInHeldItemSlot(Packet<?> instance) {
        super(instance, PacketPlayInHeldItemSlot.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getSlot() {
        return get("itemInHandIndex");
    }
}
