package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutEntityEquipment extends PacketWrapper {

    public WrappedPlayOutEntityEquipment(Packet<?> instance) {
        super(instance, PacketPlayOutEntityEquipment.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getEntityId() {
        return get("a");
    }

    public int getB() {
        return get("b");
    }

    public ItemStack getItemStack() {
        return get("c");
    }
}
