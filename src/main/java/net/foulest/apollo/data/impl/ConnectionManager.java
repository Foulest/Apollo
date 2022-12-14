package net.foulest.apollo.data.impl;

import lombok.RequiredArgsConstructor;
import net.foulest.apollo.data.PlayerData;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public final class ConnectionManager {

    private final PlayerData playerData;

    public void onTransaction(short actionNumber, long now) {
        Optional<Long> entry = getTransactionTime(actionNumber);
        entry.ifPresent(time -> playerData.getTransactionPing().set(now - time));
    }

    public void onKeepAlive(int identification, long now) {
        Optional<Long> entry = getKeepAliveTime(identification);
        entry.ifPresent(time -> playerData.getKeepAlivePing().set(now - time));
    }

    public Optional<Long> getTransactionTime(short actionNumber) {
        Map<Short, Long> entries = playerData.getTransactionUpdates();

        if (entries.containsKey(actionNumber)) {
            return Optional.of(entries.get(actionNumber));
        }

        return Optional.empty();
    }

    public Optional<Long> getKeepAliveTime(int identification) {
        Map<Integer, Long> entries = playerData.getKeepAliveUpdates();

        if (entries.containsKey(identification)) {
            return Optional.of(entries.get(identification));
        }

        return Optional.empty();
    }
}
