package net.foulest.apollo.wrapper.impl.server;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutStatistic;
import net.minecraft.server.v1_8_R3.Statistic;
import net.foulest.apollo.wrapper.PacketWrapper;

import java.util.Map;

public final class WrappedPlayOutStatistic extends PacketWrapper {

    public WrappedPlayOutStatistic(Packet<?> instance) {
        super(instance, PacketPlayOutStatistic.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public Map<Statistic, Integer> getStatisticsMap() {
        return get("a");
    }
}
