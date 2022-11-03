package net.foulest.apollo.check.impl.badpackets;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInSpectate;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;

// Detects sending invalid Spectate packets
@CheckData(name = "BadPackets (J)")
public class BadPacketsJ extends PacketCheck {

    public BadPacketsJ(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInSpectate) {
            WrappedPlayInSpectate packet = (WrappedPlayInSpectate) object;

            // Detects sending packets while not being in spectator mode.
            if (playerData.getBukkitPlayer().getGameMode() != GameMode.SPECTATOR) {
                playerData.kick(getCheckName(), "Not in Spectator");
            }

            // Detects spectating invalid targets.
            if (Bukkit.getServer().getPlayer(packet.getUUID()) == null) {
                playerData.kick(getCheckName(), "Invalid Target");
            }
        }
    }
}
