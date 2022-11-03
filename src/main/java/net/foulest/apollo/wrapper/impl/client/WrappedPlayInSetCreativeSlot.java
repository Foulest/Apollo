package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInSetCreativeSlot;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayInSetCreativeSlot extends PacketWrapper {

    public WrappedPlayInSetCreativeSlot(Packet<?> instance) {
        super(instance, PacketPlayInSetCreativeSlot.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getSlot() {
        return get("slot");
    }

    public ItemStack getItemStack() {
        return get("b");
    }
}
