package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.*;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutRespawn extends PacketWrapper {

    public WrappedPlayOutRespawn(Packet<?> instance) {
        super(instance, PacketPlayOutRespawn.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getEntityId() {
        return get("a");
    }

    public EnumDifficulty getDifficulty() {
        return get("b");
    }

    public WorldSettings.EnumGamemode getGamemode() {
        return get("c");
    }

    public WorldType getWorldType() {
        return get("d");
    }
}
