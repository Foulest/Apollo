package net.foulest.apollo.check.impl.inventory;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInSetCreativeSlot;
import org.bukkit.GameMode;

// Detects sending invalid SetCreativeSlot packets
@CheckData(name = "Inventory (E)")
public class InventoryE extends PacketCheck {

    public InventoryE(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInSetCreativeSlot) {
            if (playerData.getBukkitPlayer().getGameMode() != GameMode.CREATIVE) {
                playerData.kick(getCheckName(), "Not in Creative");
            }
        }
    }
}
