package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutCombatEvent;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutCombatEvent extends PacketWrapper {

    public WrappedPlayOutCombatEvent(Packet<?> instance) {
        super(instance, PacketPlayOutCombatEvent.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public PacketPlayOutCombatEvent.EnumCombatEventType getType() {
        return get("a");
    }

    public int getB() {
        return get("b");
    }

    public int getC() {
        return get("c");
    }

    public int getD() {
        return get("d");
    }

    public String getE() {
        return get("e");
    }
}
