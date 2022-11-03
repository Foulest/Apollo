package net.foulest.apollo.check.impl.inventory;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInWindowClick;

// Detects sending invalid WindowClick packets
@CheckData(name = "Inventory (F)")
public class InventoryF extends PacketCheck {

    public InventoryF(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInWindowClick) {
            WrappedPlayInWindowClick packet = (WrappedPlayInWindowClick) object;

            // Detects sending invalid slots.
            if (packet.getWindowId() == 0) {
                if (packet.getSlot() > 44 || (packet.getSlot() != -999 && packet.getSlot() < -1)) {
                    playerData.kick(getCheckName(), "Invalid Slot (" + packet.getSlot() + ")");
                    return;
                }
            }

            // Detects sending invalid buttons.
            switch (packet.getClickType()) {
                case 0:
                    if (packet.getButton() != 0 && packet.getButton() != 1) {
                        playerData.kick(getCheckName(), "Pickup (" + packet.getButton() + ")");
                    }
                    return;

                case 1:
                    if (packet.getButton() != 0 && packet.getButton() != 1) {
                        playerData.kick(getCheckName(), "Quick Move (" + packet.getButton() + ")");
                    }
                    return;

                case 2:
                    if (packet.getButton() != 0 && packet.getButton() != 1 && packet.getButton() != 2
                            && packet.getButton() != 8 && packet.getButton() != 40) {
                        playerData.kick(getCheckName(), "Swap (" + packet.getButton() + ")");
                    }
                    return;

                case 3:
                    if (packet.getButton() != 0 && packet.getButton() != 1 && packet.getButton() != 2) {
                        playerData.kick(getCheckName(), "Clone (" + packet.getButton() + ")");
                    }
                    return;

                case 4:
                    if (packet.getButton() != 0 && packet.getButton() != 1 && packet.getButton() != 2) {
                        playerData.kick(getCheckName(), "Throw (" + packet.getButton() + ")");
                    }
                    return;

                case 5:
                    if (packet.getButton() != 0 && packet.getButton() != 1 && packet.getButton() != 2
                            && packet.getButton() != 4 && packet.getButton() != 5 && packet.getButton() != 6
                            && packet.getButton() != 8 && packet.getButton() != 9 && packet.getButton() != 10) {
                        playerData.kick(getCheckName(), "Quick Craft (" + packet.getButton() + ")");
                    }
                    return;

                case 6:
                    if (packet.getButton() != 0 && packet.getButton() != 1) {
                        playerData.kick(getCheckName(), "Pickup All (" + packet.getButton() + ")");
                    }
            }
        }
    }
}
