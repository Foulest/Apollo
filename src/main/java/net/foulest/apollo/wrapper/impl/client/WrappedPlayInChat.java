package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInChat;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayInChat extends PacketWrapper {

    public WrappedPlayInChat(Packet<?> instance) {
        super(instance, PacketPlayInChat.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public String getMessage() {
        return get("a");
    }
}
