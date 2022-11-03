package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutCloseWindow;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutCloseWindow extends PacketWrapper {

    public WrappedPlayOutCloseWindow(Packet<?> instance) {
        super(instance, PacketPlayOutCloseWindow.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getWindowId() {
        return get("a");
    }
}
