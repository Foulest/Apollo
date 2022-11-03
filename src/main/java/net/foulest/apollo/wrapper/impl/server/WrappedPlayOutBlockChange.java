package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockChange;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutBlockChange extends PacketWrapper {

    public WrappedPlayOutBlockChange(Packet<?> instance) {
        super(instance, PacketPlayOutBlockChange.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public BlockPosition getBlockPosition() {
        return get("a");
    }

    public IBlockData getBlockData() {
        return get("block");
    }
}
