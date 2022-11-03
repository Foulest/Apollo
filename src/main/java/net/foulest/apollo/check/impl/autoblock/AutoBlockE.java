package net.foulest.apollo.check.impl.autoblock;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInBlockDig;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInBlockPlace;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

// Detects sending invalid BlockPlace and ReleaseUseItem order
@CheckData(name = "AutoBlock (E)")
public class AutoBlockE extends PacketCheck {

    public int placeCount;
    public int releaseCount;

    public AutoBlockE(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInBlockPlace) {
            WrappedPlayInBlockPlace packet = (WrappedPlayInBlockPlace) object;

            if (packet.getItemStack() == null) {
                return;
            }

            ItemStack itemStack = CraftItemStack.asBukkitCopy(packet.getItemStack());

            if ((itemStack.getType().equals(Material.BOW)
                    || itemStack.getType().toString().toLowerCase().contains("sword")
                    || (itemStack.getType().isEdible() && playerData.getBukkitPlayer().getFoodLevel() < 20))) {
                ++placeCount;
                releaseCount = 0;
                check();
            }

        } else if (object instanceof WrappedPlayInBlockDig) {
            WrappedPlayInBlockDig packet = (WrappedPlayInBlockDig) object;

            if (packet.getDigType() == PacketPlayInBlockDig.EnumPlayerDigType.RELEASE_USE_ITEM) {
                ++releaseCount;
                placeCount = 0;
                check();
            }
        }
    }

    // Detects sending multiple PLACE or RELEASE packets in a row.
    public void check() {
        if (placeCount > 3) {
            playerData.kick(getCheckName(), "PLACE=" + placeCount);
        }

        if (releaseCount > 1) {
            playerData.kick(getCheckName(), "RELEASE=" + releaseCount);
        }
    }
}
