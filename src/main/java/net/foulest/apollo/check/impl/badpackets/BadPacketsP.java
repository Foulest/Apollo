package net.foulest.apollo.check.impl.badpackets;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInAbilities;
import org.bukkit.GameMode;

// Detects sending invalid Abilities packets
@CheckData(name = "BadPackets (P)")
public class BadPacketsP extends PacketCheck {

    public BadPacketsP(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInAbilities) {
            WrappedPlayInAbilities packet = (WrappedPlayInAbilities) object;
            boolean isFlying = packet.isFlying();
            boolean isInvulnerable = packet.isInvulnerable();
            boolean isFlightAllowed = packet.isFlightAllowed();
            boolean isInCreative = packet.isInCreative();

            // Detects sending invalid data for every ability.
            if (isFlying == playerData.getFlying().get()
                    && isInvulnerable == playerData.getInvulnerable().get()
                    && isFlightAllowed == playerData.getFlightAllowed().get()
                    && isInCreative == playerData.getInCreative().get()
                    && !isFlying && !isInvulnerable && !isFlightAllowed && !isInCreative) {
                playerData.kick(getCheckName(), "Abilities (All)");
                return;
            }

            // Detects sending invalid Flight Allowed data.
            if (isFlying && !playerData.getFlightAllowed().get()) {
                playerData.kick(getCheckName(), "Abilities (Flight Allowed)");
                return;
            }

            if (playerData.getBukkitPlayer().getGameMode() != GameMode.CREATIVE) {
                // Detects sending invalid God Mode data.
                if (isInvulnerable != playerData.getInvulnerable().get()) {
                    playerData.kick(getCheckName(), "Abilities (Invulnerable)");
                    return;
                }

                // Detects sending invalid Flying data.
                if (isFlying && !playerData.getFlying().get()) {
                    playerData.kick(getCheckName(), "Abilities (Flying)");
                    return;
                }

                // Detects sending invalid Creative data.
                if (isInCreative && !playerData.getInCreative().get()) {
                    playerData.kick(getCheckName(), "Abilities (Creative)");
                }
            }
        }
    }
}
