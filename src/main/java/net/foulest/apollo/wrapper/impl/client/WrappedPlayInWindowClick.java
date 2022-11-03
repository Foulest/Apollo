package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInWindowClick;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayInWindowClick extends PacketWrapper {

    public WrappedPlayInWindowClick(Packet<?> instance) {
        super(instance, PacketPlayInWindowClick.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getWindowId() {
        return get("a");
    }

    public int getSlot() {
        return get("slot");
    }

    public int getButton() {
        return get("button");
    }

    public int getShift() {
        return get("shift");
    }

    public short getClickType() {
        return get("d");
    }

    public ItemStack getItemStack() {
        return get("item");
    }
}
