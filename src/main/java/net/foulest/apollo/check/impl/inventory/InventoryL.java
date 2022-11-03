package net.foulest.apollo.check.impl.inventory;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInWindowClick;

import java.util.Objects;

@CheckData(name = "Inventory (L)")
public final class InventoryL extends PacketCheck {

    public int stage;
    public int slot;

    public InventoryL(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

//        if (player.getClientVersion().isNewerThanOrEquals(ClientVersion.V_1_9)) {
//            return;
//        }

        if (object instanceof WrappedPlayInWindowClick) {
            WrappedPlayInWindowClick packet = (WrappedPlayInWindowClick) object;
            int slot = packet.getSlot();

            if (packet.getClickType() == 1 && packet.getButton() == 0) {
                if (stage == 0 && packet.getItemStack() == null) {
                    stage = 1;
                } else if (stage == 1 && packet.getItemStack() == null && Objects.equals(this.slot, slot)) {
                    playerData.kick(getCheckName(), "");
                    stage = 0;
                } else {
                    stage = 0;
                }

            } else {
                stage = 0;
            }

            this.slot = slot;

        } else if (object instanceof WrappedPlayInFlying) {
            stage = 0;
        }
    }
}
