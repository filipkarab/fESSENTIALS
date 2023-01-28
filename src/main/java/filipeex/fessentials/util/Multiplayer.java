package filipeex.fessentials.util;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Multiplayer {

    public static Player findOnlinePlayer(String playerName) {

        for (Player p : Bukkit.getOnlinePlayers())
            if (playerName.equalsIgnoreCase(p.getName()))
                return p;

        return null;
    }

    public static OfflinePlayer findOfflinePlayer(String playerName) {

        for (OfflinePlayer p : Bukkit.getOfflinePlayers())
            if (playerName.equalsIgnoreCase(p.getName()))
                return p;

        return null;
    }

    public static Object findOnlineOrOfflinePlayer(String playerName) {

        if (findOnlinePlayer(playerName) == null)
            return findOfflinePlayer(playerName);
        else
            return findOnlinePlayer(playerName);

    }

}
