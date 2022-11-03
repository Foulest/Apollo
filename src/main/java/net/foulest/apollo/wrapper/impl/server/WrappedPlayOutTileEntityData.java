package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutTileEntityData;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutTileEntityData extends PacketWrapper {

    public WrappedPlayOutTileEntityData(Packet<?> instance) {
        super(instance, PacketPlayOutTileEntityData.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getB() {
        return get("b");
    }

    public NBTTagCompound getNBTTagCompound() {
        return get("c");
    }
}
