package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutSetSlot;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutSetSlot extends PacketWrapper {

    public WrappedPlayOutSetSlot(Packet<?> instance) {
        super(instance, PacketPlayOutSetSlot.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getA() {
        return get("a");
    }

    public int getB() {
        return get("a");
    }

    public ItemStack getItemStack() {
        return get("c");
    }
}
