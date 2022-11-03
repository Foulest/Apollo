package net.foulest.apollo.check.impl.inventory;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInClientCommand;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

@CheckData(name = "Inventory (B)", threshold = 3)
public final class InventoryB extends PacketCheck {

    private long lastFlying = System.currentTimeMillis();
    private boolean inventory = false;
    private int buffer = 0;

    public InventoryB(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInClientCommand) {
            WrappedPlayInClientCommand wrapper = (WrappedPlayInClientCommand) object;

            achievement:
            {
                if (wrapper.getCommand() != PacketPlayInClientCommand.EnumClientCommand.OPEN_INVENTORY_ACHIEVEMENT) {
                    break achievement;
                }

                inventory = true;
            }

            if (inventory) {
                long now = System.currentTimeMillis();
                boolean lagging = now - lastFlying > 60L;
                boolean attacking = playerData.getActionManager().getAttacking().get();
                boolean swinging = playerData.getActionManager().getSwinging().get();

                if (!lagging && (attacking || swinging)) {
                    if (++buffer > 2) {
                        playerData.kick(getCheckName(), "");
                    }
                } else {
                    buffer = 0;
                }
            }

        } else if (object instanceof WrappedPlayInFlying) {
            lastFlying = System.currentTimeMillis();
            inventory = false;
        }
    }
}
