package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.EnumDirection;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayInBlockDig extends PacketWrapper {

    public WrappedPlayInBlockDig(Packet<?> instance) {
        super(instance, PacketPlayInBlockDig.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public BlockPosition getBlockPosition() {
        return get("a");
    }

    public EnumDirection getDirection() {
        return get("b");
    }

    public PacketPlayInBlockDig.EnumPlayerDigType getDigType() {
        return get("c");
    }
}
