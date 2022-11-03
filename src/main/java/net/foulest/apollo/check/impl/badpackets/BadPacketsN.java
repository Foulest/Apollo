package net.foulest.apollo.check.impl.badpackets;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInClientCommand;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;

// Detects sending invalid Respawn packets
@CheckData(name = "BadPackets (N)")
public class BadPacketsN extends PacketCheck {

    public boolean sentRespawn;

    public BadPacketsN(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInClientCommand) {
            WrappedPlayInClientCommand packet = (WrappedPlayInClientCommand) object;

            if (packet.getCommand() == PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN) {
                if (!playerData.getBukkitPlayer().isDead()) {
                    playerData.kick(getCheckName(), "Respawn (Impossible)");
                }

                if (sentRespawn) {
                    playerData.kick(getCheckName(), "Respawn (Sent)");
                }

                sentRespawn = true;
            }

        } else if (object instanceof WrappedPlayInFlying) {
            sentRespawn = false;
        }
    }
}
