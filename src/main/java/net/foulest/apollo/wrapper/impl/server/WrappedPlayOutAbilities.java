package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutAbilities;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutAbilities extends PacketWrapper {

    public WrappedPlayOutAbilities(Packet<?> instance) {
        super(instance, PacketPlayOutAbilities.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public boolean isInvulnerable() {
        return get("a");
    }

    public boolean isFlying() {
        return get("b");
    }

    public boolean canFly() {
        return get("c");
    }

    public boolean canInstantBuild() {
        return get("d");
    }

    public float getFlySpeed() {
        return get("e");
    }

    public float getFovModifier() {
        return get("f");
    }
}
