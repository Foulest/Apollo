package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.foulest.apollo.wrapper.PacketWrapper;

import java.util.List;

public final class WrappedPlayOutPlayerInfo extends PacketWrapper {

    public WrappedPlayOutPlayerInfo(Packet<?> instance) {
        super(instance, PacketPlayOutPlayerInfo.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public PacketPlayOutPlayerInfo.EnumPlayerInfoAction getAction() {
        return get("a");
    }

    public List<PacketPlayOutPlayerInfo.PlayerInfoData> getDataList() {
        return get("b");
    }
}
