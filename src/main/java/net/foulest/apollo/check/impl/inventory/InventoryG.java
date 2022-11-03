package net.foulest.apollo.check.impl.inventory;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInClientCommand;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInCloseWindow;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInWindowClick;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;

// Detects InvCleaner
// OpenInventory, WindowClick (Quick Move/Throw), CloseWindow
@CheckData(name = "Inventory (G)")
public final class InventoryG extends PacketCheck {

    public int stage;

    public InventoryG(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInClientCommand) {
            WrappedPlayInClientCommand packet = (WrappedPlayInClientCommand) object;

            if (packet.getCommand() == PacketPlayInClientCommand.EnumClientCommand.OPEN_INVENTORY_ACHIEVEMENT) {
                if (stage == 0) {
                    ++stage;
                }
            }

        } else if (object instanceof WrappedPlayInWindowClick) {
            WrappedPlayInWindowClick packet = (WrappedPlayInWindowClick) object;

            if (packet.getWindowId() == 0 && (packet.getClickType() == 1 || packet.getClickType() == 4)) {
                if (stage == 1) {
                    ++stage;
                }
            }

        } else if (object instanceof WrappedPlayInCloseWindow) {
            WrappedPlayInCloseWindow packet = (WrappedPlayInCloseWindow) object;

            if (packet.getWindowId() == 0) {
                if (stage == 2) {
                    ++stage;
                }
            }

        } else if (object instanceof WrappedPlayInFlying) {
            WrappedPlayInFlying packet = (WrappedPlayInFlying) object;

            // Ignores Look packets.
            if (!(packet.hasLook() && !packet.hasPos())) {
                stage = 0;
            }
        }

        if (stage == 3) {
            playerData.kick(getCheckName(), "");
        }
    }
}
