package net.foulest.apollo.listener;

import io.netty.channel.ChannelPipeline;
import net.foulest.apollo.Apollo;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.packet.PacketHandler;
import net.foulest.apollo.util.NmsUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = Apollo.INSTANCE.getPlayerDataManager().getData(player);
        int ticks = playerData.getTicks().get();
        ChannelPipeline channelPipeline = NmsUtil.getPlayerPipeline(player);

        playerData.getActionManager().onTeleport();
        playerData.getJoined().set(ticks);

        Apollo.INSTANCE.getExecutorPacket().execute(() -> channelPipeline.addBefore("packet_handler", "apollo_packet_handler", new PacketHandler(playerData)));
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = Apollo.INSTANCE.getPlayerDataManager().getData(player);

        /*
         * There's a bug with Minecraft where the START_DIGGING packet will never be sent, making it impossible
         * for us to know if the player is digging a block or not. Thankfully, the spigot itself does the raytrace
         * for us meaning we don't have to waste any performance by doing it ourselves.
         */
        block:
        {
            if (event.getAction() != Action.LEFT_CLICK_BLOCK) {
                break block;
            }

            playerData.getActionManager().onBukkitDig();
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        ChannelPipeline channelPipeline = NmsUtil.getPlayerPipeline(player);

        /*
         * We need to remove the player's pipeline in the case it does not get auto-removed
         * by the channel. Some spigots who have messed with pipelines might have a bug where the
         * pipeline is never removed creating a memory leak. We're handling that here.
         */
        removal:
        {
            if (channelPipeline.get("apollo_packet_handler") == null) {
                break removal;
            }

            Apollo.INSTANCE.getExecutorPacket().execute(() -> channelPipeline.remove("apollo_packet_handler"));
        }

        /*
         * To prevent any memory leaks from showing up, we have to remove the player from
         * the map we have inside. We don't need to keep the data inside the memory if we have
         * no use for it anymore. This can be abused, but if needed, one can make a caching system,
         */
        Apollo.INSTANCE.getPlayerDataManager().remove(player);
    }
}
