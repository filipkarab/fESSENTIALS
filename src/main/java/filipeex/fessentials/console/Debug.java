package filipeex.fessentials.console;

import filipeex.fessentials.chat.Chat;
import org.bukkit.Bukkit;

public class Debug {

    public static String builtInPrefix = Chat.color("&x&4&7&4&7&f&b&lf&x&5&0&4&2&f&b&lE&x&5&9&3&e&f&c&lS&x&6&3&3&9&f&c&lS&x&6&c&3&5&f&d&lE&x&7&5&3&0&f&d&lN&x&7&e&2&b&f&d&lT&x&8&7&2&7&f&e&lI&x&9&1&2&2&f&e&lA&x&9&a&1&e&f&f&lL&x&a&3&1&9&f&f&lS &8>>");
    public static boolean doDebug = true;

    public static void debug(String message) {

        if (doDebug)
            Bukkit.getServer().getConsoleSender().sendMessage(builtInPrefix + Chat.color(" &b" + message));

    }

    public static void announce(String message) {

        Bukkit.getServer().getConsoleSender().sendMessage(builtInPrefix + Chat.color(" &b" + message));

    }

    public static void error(String message) {

        Bukkit.getServer().getConsoleSender().sendMessage(builtInPrefix + Chat.color(" &c" + message));

    }

    public static void warning(String message) {

        Bukkit.getServer().getConsoleSender().sendMessage(builtInPrefix + Chat.color(" &e" + message));

    }

}
