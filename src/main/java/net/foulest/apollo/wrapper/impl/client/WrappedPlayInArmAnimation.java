package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayInArmAnimation extends PacketWrapper {

    public WrappedPlayInArmAnimation(Packet<?> instance) {
        super(instance, PacketPlayInArmAnimation.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public long getPacketTimestamp() {
        return get("timestamp");
    }
}
