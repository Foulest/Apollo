package net.foulest.apollo;

import org.bukkit.plugin.java.JavaPlugin;

public final class ApolloPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Apollo.INSTANCE.start(this);
    }

    @Override
    public void onDisable() {
        Apollo.INSTANCE.stop(this);
    }
}
