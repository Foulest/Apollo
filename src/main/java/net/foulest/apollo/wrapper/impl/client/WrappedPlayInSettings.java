package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInSettings;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayInSettings extends PacketWrapper {

    public WrappedPlayInSettings(Packet<?> instance) {
        super(instance, PacketPlayInSettings.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public String getLocale() {
        return get("a");
    }

    public int getViewDistance() {
        return get("b");
    }

    public EntityHuman.EnumChatVisibility getChatVisibility() {
        return get("c");
    }

    public boolean getD() {
        return get("d");
    }

    public int getE() {
        return get("e");
    }
}
