package net.foulest.apollo;

import lombok.Getter;
import net.foulest.apollo.data.type.PlayerDataManager;
import net.foulest.apollo.listener.PlayerListener;
import net.foulest.apollo.processor.ProcessorManager;
import net.foulest.apollo.tick.TickManager;
import org.bukkit.Bukkit;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Getter
public enum Apollo {
    INSTANCE;

    private final ProcessorManager processorManager = new ProcessorManager();
    private final PlayerDataManager playerDataManager = new PlayerDataManager();
    private final TickManager tickManager = new TickManager();
    private final Executor executorAlert = Executors.newSingleThreadExecutor();
    private final Executor executorPacket = Executors.newSingleThreadExecutor();
    private ApolloPlugin plugin;

    public void start(ApolloPlugin plugin) {
        this.plugin = plugin;
        tickManager.start();
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), plugin);
    }

    public void stop(ApolloPlugin plugin) {
        this.plugin = plugin;
        tickManager.stop();
    }
}
