package net.foulest.apollo.timings;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.foulest.apollo.Apollo;

@RequiredArgsConstructor
@Getter
public enum Timings {
    TICKS(Apollo.INSTANCE.getTickManager().getTicks()),
    TPS(MinecraftServer.getServer().recentTps[0]);

    private final double number;
}
