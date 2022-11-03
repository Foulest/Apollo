package net.foulest.apollo.check.impl.inventory;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInBlockPlace;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInHeldItemSlot;

@CheckData(name = "Inventory (H)")
public final class InventoryH extends PacketCheck {

    public int stage;

    public InventoryH(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInHeldItemSlot) {
            if (stage == 2) {
                playerData.kick(getCheckName(), "");
            }

            stage = 1;

        } else if (object instanceof WrappedPlayInBlockPlace) {
            WrappedPlayInBlockPlace packet = (WrappedPlayInBlockPlace) object;

            if (stage == 1) {
                stage = 2;
            }

            if (packet.getItemStack() != null) {
                stage = 0;
            }

        } else if (object instanceof WrappedPlayInFlying) {
            WrappedPlayInFlying packet = (WrappedPlayInFlying) object;

            // Ignores Look packets.
            if (!(packet.hasLook() && !packet.hasPos())) {
                stage = 0;
            }
        }
    }
}
