package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayInClientCommand extends PacketWrapper {

    public WrappedPlayInClientCommand(Packet<?> instance) {
        super(instance, PacketPlayInClientCommand.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public PacketPlayInClientCommand.EnumClientCommand getCommand() {
        return get("a");
    }
}
