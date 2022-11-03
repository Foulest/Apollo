package net.foulest.apollo.check.impl.autoheal;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInBlockPlace;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInHeldItemSlot;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

// Detects this AutoHeal pattern:
// HeldItemSlot, BlockPlace, HeldItemSlot
@CheckData(name = "AutoHeal (C)")
public final class AutoHealC extends PacketCheck {

    public long start;
    public int stage;

    public AutoHealC(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInHeldItemSlot) {
            if (stage == 0 || stage == 2) {
                if (stage == 0) {
                    start = System.currentTimeMillis();
                }

                ++stage;
            }

        } else if (object instanceof WrappedPlayInBlockPlace) {
            WrappedPlayInBlockPlace packet = (WrappedPlayInBlockPlace) object;

            if (packet.getItemStack() != null) {
                ItemStack itemStack = CraftItemStack.asBukkitCopy(packet.getItemStack());

                if (itemStack.getType() == Material.MUSHROOM_SOUP
                        || itemStack.getType() == Material.POTION
                        || itemStack.getType() == Material.BOWL) {
                    // TODO: Should be OTHER
                    if (packet.getFace() == 255) {
                        if (stage == 1) {
                            ++stage;
                        }
                    }
                }
            }

        } else if (object instanceof WrappedPlayInFlying) {
            WrappedPlayInFlying packet = (WrappedPlayInFlying) object;

            if (!packet.hasPos() && !packet.hasLook()) {
                stage = 0;
            }
        }

        long timeDiff = System.currentTimeMillis() - start;

        if (stage == 3 && timeDiff < 99) {
            playerData.kick(getCheckName(), "TIME=" + (System.currentTimeMillis() - start));
        }
    }
}
