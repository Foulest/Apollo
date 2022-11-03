package net.foulest.apollo.wrapper.impl.server;

import net.md_5.bungee.api.chat.BaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutChat extends PacketWrapper {

    public WrappedPlayOutChat(Packet<?> instance) {
        super(instance, PacketPlayOutChat.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public IChatBaseComponent getBaseComponent() {
        return get("a");
    }

    public BaseComponent[] getComponents() {
        return get("components");
    }

    public byte getB() {
        return get("b");
    }
}
