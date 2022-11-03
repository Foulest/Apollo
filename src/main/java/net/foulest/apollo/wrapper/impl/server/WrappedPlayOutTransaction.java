package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutTransaction;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutTransaction extends PacketWrapper {

    public WrappedPlayOutTransaction(Packet<?> instance) {
        super(instance, PacketPlayOutTransaction.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getId() {
        return get("a");
    }

    public short getHash() {
        return get("b");
    }

    public boolean isAccepted() {
        return get("c");
    }
}
