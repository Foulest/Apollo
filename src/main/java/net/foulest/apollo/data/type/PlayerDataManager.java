package net.foulest.apollo.data.type;

import com.google.common.collect.Maps;
import org.bukkit.entity.Player;
import net.foulest.apollo.data.PlayerData;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public final class PlayerDataManager {
    private final Map<UUID, PlayerData> playerDataMap = Maps.newConcurrentMap();

    public PlayerData getData(Player player) {
        return playerDataMap.computeIfAbsent(player.getUniqueId(), uuid -> new PlayerData(player));
    }

    public PlayerData remove(Player player) {
        UUID uuid = player.getUniqueId();

        return playerDataMap.remove(uuid);
    }

    public Collection<PlayerData> getEntries() {
        return playerDataMap.values();
    }
}
