package net.foulest.apollo.processor.type;

import net.foulest.apollo.data.PlayerData;

public interface Processor<T> {

    void process(PlayerData playerData, T t);
}
