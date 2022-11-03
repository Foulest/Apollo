package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockAction;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutBlockAction extends PacketWrapper {

    public WrappedPlayOutBlockAction(Packet<?> instance) {
        super(instance, PacketPlayOutBlockAction.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public BlockPosition getBlockPosition() {
        return get("a");
    }

    public int getB() {
        return get("b");
    }

    public int getC() {
        return get("c");
    }

    public Block getBlock() {
        return get("d");
    }
}
