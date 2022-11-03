package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.DataWatcher;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.foulest.apollo.wrapper.PacketWrapper;

import java.util.List;

public final class WrappedPlayOutEntityMetadata extends PacketWrapper {

    public WrappedPlayOutEntityMetadata(Packet<?> instance) {
        super(instance, PacketPlayOutEntityMetadata.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getEntityId() {
        return get("a");
    }

    public List<DataWatcher.WatchableObject> getB() {
        return get("a");
    }
}
