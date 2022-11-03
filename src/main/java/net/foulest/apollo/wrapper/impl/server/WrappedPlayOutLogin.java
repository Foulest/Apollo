package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.*;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutLogin extends PacketWrapper {

    public WrappedPlayOutLogin(Packet<?> instance) {
        super(instance, PacketPlayOutLogin.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getA() {
        return get("a");
    }

    public boolean getB() {
        return get("b");
    }

    public WorldSettings.EnumGamemode getGamemode() {
        return get("c");
    }

    public int getD() {
        return get("d");
    }

    public EnumDifficulty getDifficulty() {
        return get("e");
    }

    public int getF() {
        return get("f");
    }

    public WorldType getWorldType() {
        return get("g");
    }

    public boolean getH() {
        return get("h");
    }
}
