package net.foulest.apollo.check.impl.inventory;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInArmAnimation;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInWindowClick;

// Detects swinging & clicking in your inventory at the same time
@CheckData(name = "Inventory (I)")
public final class InventoryI extends PacketCheck {

    public boolean click;
    public boolean swing;

    public InventoryI(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInArmAnimation) {
            swing = true;

        } else if (object instanceof WrappedPlayInWindowClick) {
            click = true;

        } else if (object instanceof WrappedPlayInFlying) {
            if (swing && click) {
                playerData.kick(getCheckName(), "");
            }

            swing = false;
            click = false;
        }
    }
}
