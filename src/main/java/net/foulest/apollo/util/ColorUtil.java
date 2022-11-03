package net.foulest.apollo.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

@UtilityClass
public class ColorUtil {

    // & to paragraph symbol
    public String format(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
