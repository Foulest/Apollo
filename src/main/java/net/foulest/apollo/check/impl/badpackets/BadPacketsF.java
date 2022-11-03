package net.foulest.apollo.check.impl.badpackets;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInCustomPayload;
import org.bukkit.inventory.ItemStack;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

// Detects sending invalid CustomPayload packets
@CheckData(name = "BadPackets (F)")
public class BadPacketsF extends PacketCheck {

    public BadPacketsF(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInCustomPayload) {
            WrappedPlayInCustomPayload packet = (WrappedPlayInCustomPayload) object;
            String payload = packet.getPayload();

            // Detects sending packets with invalid sizes.
            if (packet.getData().length() > 15000 || packet.getData().length() <= 0) {
                playerData.kick(getCheckName(), "SIZE=" + packet.getData().length());
                return;
            }

            // Detects sending invalid book-related channels.
            if (payload.equals("MC|BOpen") || payload.equals("MC|BEdit") || payload.equals("MC|BSign")) {
                ItemStack itemStack = playerData.getBukkitPlayer().getInventory().getItemInHand();

                if (itemStack != null && !itemStack.getType().toString().toLowerCase().contains("book")) {
                    playerData.kick(getCheckName(), "CHANNEL=" + payload);
                }
            }
        }
    }
}
