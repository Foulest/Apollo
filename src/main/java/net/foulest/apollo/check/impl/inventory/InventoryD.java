package net.foulest.apollo.check.impl.inventory;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInHeldItemSlot;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

// Detects sending invalid HeldItemSlot packets
@CheckData(name = "Inventory (D)")
public final class InventoryD extends PacketCheck {

    private int lastSlot = -1;

    public InventoryD(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInHeldItemSlot) {
            WrappedPlayInHeldItemSlot packet = (WrappedPlayInHeldItemSlot) object;

            if (packet.getSlot() == lastSlot) {
                playerData.kick(getCheckName(), "Same Slot");
            }

            if (packet.getSlot() < 0 || packet.getSlot() > 8) {
                playerData.kick(getCheckName(), "Invalid Slot");
            }

            lastSlot = packet.getSlot();
        }
    }
}
