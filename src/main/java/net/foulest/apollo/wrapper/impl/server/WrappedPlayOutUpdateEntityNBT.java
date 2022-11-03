package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutUpdateEntityNBT;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayOutUpdateEntityNBT extends PacketWrapper {

    public WrappedPlayOutUpdateEntityNBT(Packet<?> instance) {
        super(instance, PacketPlayOutUpdateEntityNBT.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public int getA() {
        return get("a");
    }

    public NBTTagCompound getNBTTagCompound() {
        return get("b");
    }
}
