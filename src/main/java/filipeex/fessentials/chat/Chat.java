package filipeex.fessentials.chat;

import org.bukkit.ChatColor;

public class Chat {

    public static String color(String message) {

        return ChatColor.translateAlternateColorCodes('&', message);

    }

}
