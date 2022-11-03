package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInEnchantItem;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayInEnchantItem extends PacketWrapper {

    public WrappedPlayInEnchantItem(Packet<?> instance) {
        super(instance, PacketPlayInEnchantItem.class);
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
}