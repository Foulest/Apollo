package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInCloseWindow;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayInCloseWindow extends PacketWrapper {

    public WrappedPlayInCloseWindow(Packet<?> instance) {
        super(instance, PacketPlayInCloseWindow.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getWindowId() {
        return get("id");
    }

    public boolean isInventory() {
        return getWindowId() == 0;
    }
}
