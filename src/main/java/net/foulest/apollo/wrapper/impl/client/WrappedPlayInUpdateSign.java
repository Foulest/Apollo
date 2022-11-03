package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInUpdateSign;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayInUpdateSign extends PacketWrapper {

    public WrappedPlayInUpdateSign(Packet<?> instance) {
        super(instance, PacketPlayInUpdateSign.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public BlockPosition getBlockPosition() {
        return get("a");
    }

    public IChatBaseComponent[] getLines() {
        return get("b");
    }
}
