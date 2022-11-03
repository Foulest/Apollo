package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayInBlockPlace extends PacketWrapper {

    public WrappedPlayInBlockPlace(Packet<?> instance) {
        super(instance, PacketPlayInBlockPlace.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public long getPacketTimestamp() {
        return get("timestamp");
    }

    public BlockPosition getBlockPosition() {
        return get("b");
    }

    public int getFace() {
        return get("c");
    }

    public ItemStack getItemStack() {
        return get("d");
    }

    public float getE() {
        return get("e");
    }

    public float getF() {
        return get("f");
    }

    public float getG() {
        return get("g");
    }
}
