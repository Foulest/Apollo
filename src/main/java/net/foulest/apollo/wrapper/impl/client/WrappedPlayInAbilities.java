package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInAbilities;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayInAbilities extends PacketWrapper {

    public WrappedPlayInAbilities(Packet<?> instance) {
        super(instance, PacketPlayInAbilities.class);
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

    public boolean isFlightAllowed() {
        return get("c");
    }

    public boolean isInCreative() {
        return get("d");
    }

    public float getFlySpeed() {
        return get("e");
    }

    public float getFovModifier() {
        return get("f");
    }
}
