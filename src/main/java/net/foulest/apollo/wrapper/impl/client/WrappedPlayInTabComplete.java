package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInTabComplete;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayInTabComplete extends PacketWrapper {

    public WrappedPlayInTabComplete(Packet<?> instance) {
        super(instance, PacketPlayInTabComplete.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public String getMessage() {
        return get("a");
    }

    public BlockPosition getBlockPosition() {
        return get("b");
    }
}
