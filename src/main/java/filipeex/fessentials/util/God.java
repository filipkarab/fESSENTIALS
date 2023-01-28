package filipeex.fessentials.util;

import filipeex.fessentials.Main;
import filipeex.fessentials.config.Database;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class God {

    public static void set(OfflinePlayer p, boolean x) {

        Database.get("goddb").set(p.getUniqueId().toString(), x);
        Database.save("goddb");

    }

    public static void toggle(OfflinePlayer p) {

        boolean state = Database.get("goddb").getBoolean(p.getUniqueId().toString());

        Database.get("goddb").set(p.getUniqueId().toString(), !state);
        Database.save("goddb");

    }

    public static boolean get(OfflinePlayer p) {

        return Database.get("goddb").getBoolean(p.getUniqueId().toString());

    }

}
