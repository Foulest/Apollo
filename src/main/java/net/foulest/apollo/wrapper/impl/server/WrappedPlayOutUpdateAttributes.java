package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutUpdateAttributes;
import net.foulest.apollo.wrapper.PacketWrapper;

import java.util.List;

public final class WrappedPlayOutUpdateAttributes extends PacketWrapper {

    public WrappedPlayOutUpdateAttributes(Packet<?> instance) {
        super(instance, PacketPlayOutUpdateAttributes.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getA() {
        return get("a");
    }

    public List<PacketPlayOutUpdateAttributes.AttributeSnapshot> getAttributeSnapshots() {
        return get("b");
    }
}
