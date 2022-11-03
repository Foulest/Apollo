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

@CheckData(name = "Inventory (J)")
public final class InventoryJ extends PacketCheck {

    public boolean was;
    public boolean open;

    public InventoryJ(PlayerData playerData) {
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

        if (object instanceof WrappedPlayInCloseWindow) {
            if (was) {
                playerData.kick(getCheckName(), "Close");
            }

        } else if (object instanceof WrappedPlayInClientCommand) {
            WrappedPlayInClientCommand packet = (WrappedPlayInClientCommand) object;

            if (packet.getCommand() == PacketPlayInClientCommand.EnumClientCommand.OPEN_INVENTORY_ACHIEVEMENT) {
                open = true;
            }

        } else if (object instanceof WrappedPlayInWindowClick) {
            was = open;

            if (open) {
                playerData.kick(getCheckName(), "Click");
                open = false;
            }

        } else if (object instanceof WrappedPlayInFlying) {
            open = false;
            was = false;
        }
    }
}
