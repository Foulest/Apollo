package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutCustomPayload extends PacketWrapper {

    public WrappedPlayOutCustomPayload(Packet<?> instance) {
        super(instance, PacketPlayOutCustomPayload.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public String getPayload() {
        return get("a");
    }

    public PacketDataSerializer getPacketDataSerializer() {
        return get("b");
    }
}
