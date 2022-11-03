package net.foulest.apollo.check.impl.inventory;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInBlockPlace;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInSetCreativeSlot;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInWindowClick;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

// Detects placing blocks/items that don't exist
@CheckData(name = "Inventory (C)")
public final class InventoryC extends PacketCheck {

    public InventoryC(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInBlockPlace) {
            WrappedPlayInBlockPlace packet = (WrappedPlayInBlockPlace) object;

            try {
                if (packet.getItemStack() == null || playerData.getBukkitPlayer().getItemInHand() == null) {
                    return;
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {
                return;
            }

            ItemStack packetItem = CraftItemStack.asBukkitCopy(packet.getItemStack());
            ItemStack inventoryItem = playerData.getBukkitPlayer().getItemInHand();

            // Ignores eating mushroom stew.
            if (packetItem.getType() == Material.MUSHROOM_SOUP
                    && inventoryItem.getType() == Material.BOWL) {
                return;
            }
            
            check(packetItem, inventoryItem);

        } else if (object instanceof WrappedPlayInSetCreativeSlot) {
            WrappedPlayInSetCreativeSlot packet = (WrappedPlayInSetCreativeSlot) object;

            try {
                if (packet.getItemStack() == null || playerData.getBukkitPlayer().getItemInHand() == null) {
                    return;
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {
                return;
            }

            ItemStack packetItem = CraftItemStack.asBukkitCopy(packet.getItemStack());
            ItemStack inventoryItem = playerData.getBukkitPlayer().getItemInHand();

            // Ignores eating mushroom stew.
            if (packetItem.getType() == Material.MUSHROOM_SOUP
                    && inventoryItem.getType() == Material.BOWL) {
                return;
            }

            check(packetItem, inventoryItem);

        } else if (object instanceof WrappedPlayInWindowClick) {
            WrappedPlayInWindowClick packet = (WrappedPlayInWindowClick) object;
            Inventory inventory = playerData.getBukkitPlayer().getInventory();

            try {
                if (packet.getItemStack() == null || inventory.getItem(packet.getSlot()) == null) {
                    return;
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {
                return;
            }

            ItemStack packetItem = CraftItemStack.asBukkitCopy(packet.getItemStack());
            ItemStack inventoryItem = inventory.getItem(packet.getSlot());

            if (packet.getWindowId() == 0 && packet.getClickType() != 1
                    && packet.getClickType() != 2 && packet.getClickType() != 6) {
                check(packetItem, inventoryItem);
            }
        }
    }

    public void check(ItemStack packetItem, ItemStack inventoryItem) {
        if (packetItem.getType() != null && inventoryItem.getType() != null
                && packetItem.getType() != inventoryItem.getType()) {
            playerData.kick(getCheckName(), "Invalid Type");
        }

        if (packetItem.getItemMeta() != null && inventoryItem.getItemMeta() != null) {
            if (packetItem.getItemMeta().getEnchants() != null && inventoryItem.getItemMeta().getEnchants() != null
                    && packetItem.getItemMeta().getEnchants() != inventoryItem.getItemMeta().getEnchants()) {
                playerData.kick(getCheckName(), "Invalid ItemMeta (Enchants)");
            }

            if (packetItem.getItemMeta().getLore() != null && inventoryItem.getItemMeta().getLore() != null
                    && packetItem.getItemMeta().getLore() != inventoryItem.getItemMeta().getLore()) {
                playerData.kick(getCheckName(), "Invalid ItemMeta (Lore)");
            }
        }

        if (packetItem.getEnchantments() != null && inventoryItem.getEnchantments() != null
                && packetItem.getEnchantments() != inventoryItem.getEnchantments()) {
            playerData.kick(getCheckName(), "Invalid Enchantments");
        }

        if (packetItem.getData() != null && inventoryItem.getData() != null) {
            if (packetItem.getData().getItemType() != null && inventoryItem.getData().getItemType() != null
                    && packetItem.getData().getItemType() != inventoryItem.getData().getItemType()) {
                playerData.kick(getCheckName(), "Invalid Data (Type)");
            }

            if (packetItem.getData().getData() != inventoryItem.getData().getData()) {
                playerData.kick(getCheckName(), "Invalid Data (Data)");
            }

            if (packetItem.getData().getItemTypeId() != inventoryItem.getData().getItemTypeId()) {
                playerData.kick(getCheckName(), "Invalid Data (ItemTypeId)");
            }
        }

        if (packetItem.getDurability() != inventoryItem.getDurability()) {
            playerData.kick(getCheckName(), "Invalid Durability");
        }

        if (packetItem.getAmount() != inventoryItem.getAmount()) {
            playerData.kick(getCheckName(), "Invalid Amount");
        }

        if (packetItem.getMaxStackSize() != inventoryItem.getMaxStackSize()) {
            playerData.kick(getCheckName(), "Invalid MaxStackSize");
        }
    }
}
