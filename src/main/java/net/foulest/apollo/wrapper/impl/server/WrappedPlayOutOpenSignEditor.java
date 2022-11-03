package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutOpenSignEditor;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutOpenSignEditor extends PacketWrapper {

    public WrappedPlayOutOpenSignEditor(Packet<?> instance) {
        super(instance, PacketPlayOutOpenSignEditor.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public BlockPosition getBlockPosition() {
        return get("a");
    }
}
