package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutTitle extends PacketWrapper {

    public WrappedPlayOutTitle(Packet<?> instance) {
        super(instance, PacketPlayOutTitle.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public PacketPlayOutTitle.EnumTitleAction getAction() {
        return get("a");
    }

    public IChatBaseComponent getMessage() {
        return get("b");
    }

    public int getC() {
        return get("c");
    }

    public int getD() {
        return get("d");
    }

    public int getE() {
        return get("e");
    }
}
