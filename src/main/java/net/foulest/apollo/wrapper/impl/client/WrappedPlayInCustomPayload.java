package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload;
import net.foulest.apollo.wrapper.PacketWrapper;

import java.nio.charset.StandardCharsets;

public final class WrappedPlayInCustomPayload extends PacketWrapper {

    public WrappedPlayInCustomPayload(Packet<?> instance) {
        super(instance, PacketPlayInCustomPayload.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public String getPayload() {
        return get("a");
    }

    public String getData() {
        return new String(((PacketDataSerializer) get("b")).array(), StandardCharsets.UTF_8);
    }

    public byte[] getDataBytes() {
        return ((PacketDataSerializer) get("b")).a();
    }
}
