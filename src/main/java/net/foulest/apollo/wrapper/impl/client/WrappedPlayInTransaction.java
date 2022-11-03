package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInTransaction;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayInTransaction extends PacketWrapper {

    public WrappedPlayInTransaction(Packet<?> instance) {
        super(instance, PacketPlayInTransaction.class);
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
