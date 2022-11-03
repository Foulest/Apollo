package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutOpenWindow;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutOpenWindow extends PacketWrapper {

    public WrappedPlayOutOpenWindow(Packet<?> instance) {
        super(instance, PacketPlayOutOpenWindow.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getA() {
        return get("a");
    }

    public String getB() {
        return get("b");
    }

    public IChatBaseComponent getC() {
        return get("c");
    }

    public int getD() {
        return get("d");
    }

    public int getE() {
        return get("e");
    }
}
