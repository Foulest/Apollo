package net.foulest.apollo.wrapper.impl.server;

import net.foulest.apollo.wrapper.PacketWrapper;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutWindowItems;

public final class WrappedPlayOutWindowItems extends PacketWrapper {

    public WrappedPlayOutWindowItems(Packet<?> instance) {
        super(instance, PacketPlayOutWindowItems.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getA() {
        return get("a");
    }

    public ItemStack[] getItemStacks() {
        return get("b");
    }
}
