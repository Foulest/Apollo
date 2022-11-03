package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.DataWatcher;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.foulest.apollo.wrapper.PacketWrapper;

import java.util.List;
import java.util.UUID;

public final class WrappedPlayOutNamedEntitySpawn extends PacketWrapper {

    public WrappedPlayOutNamedEntitySpawn(Packet<?> instance) {
        super(instance, PacketPlayOutNamedEntitySpawn.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getA() {
        return get("a");
    }

    public UUID getUUID() {
        return get("b");
    }

    public int getC() {
        return get("c");
    }

    public int getD() {
        return get("d");
    }

    public int getE() {
        return get("e");
    }

    public double getF() {
        return ((byte) get("f")) / 32.d;
    }

    public double getG() {
        return (byte) (get("g")) / 32.d;
    }

    public int getH() {
        return get("h");
    }

    public DataWatcher getDataWatcher() {
        return get("i");
    }

    public List<DataWatcher.WatchableObject> getWatchableObjects() {
        return get("j");
    }
}
