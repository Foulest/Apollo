package net.foulest.apollo.check.impl.inventory;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInWindowClick;

@CheckData(name = "Inventory (K)")
public final class InventoryK extends PacketCheck {

    public boolean pressed;
    public int slot;
    public int button;

    public InventoryK(PlayerData playerData) {
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
            int button = packet.getButton();

            if (packet.getWindowId() != 0 && packet.getClickType() == 2) {
                if (pressed && this.slot != slot && this.button == button) {
                    playerData.kick(getCheckName(), "");
                }

                pressed = true;
            }

            this.slot = slot;
            this.button = button;

        } else if (object instanceof WrappedPlayInFlying) {
            pressed = false;
        }
    }
}
