package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayInEntityAction extends PacketWrapper {

    public WrappedPlayInEntityAction(Packet<?> instance) {
        super(instance, PacketPlayInEntityAction.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public PacketPlayInEntityAction.EnumPlayerAction getAction() {
        return get("animation");
    }
}