package filipeex.fessentials.util;

import filipeex.fessentials.Main;
import filipeex.fessentials.config.Database;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Fly {

    public static void set(OfflinePlayer p, boolean x) {

        if (p.isOnline()) {
            Player onlinePlayer = (Player) p;
            onlinePlayer.setAllowFlight(x);
            if (x) {
                onlinePlayer.setVelocity(new Vector(0, 0.3, 0));
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        onlinePlayer.setFlying(true);
                    }
                }.runTaskLater(Main.i, 4);
            } else
                onlinePlayer.setFlying(false);

        }

        Database.get("flydb").set(p.getUniqueId().toString(), x);
        Database.save("flydb");

    }

    public static void toggle(OfflinePlayer p) {

        boolean state = Database.get("flydb").getBoolean(p.getUniqueId().toString());

        if (p.isOnline()) {
            Player onlinePlayer = (Player) p;
            onlinePlayer.setAllowFlight(!state);
            if (!state) {
                onlinePlayer.setVelocity(new Vector(0, 0.3, 0));
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        onlinePlayer.setFlying(true);
                    }
                }.runTaskLater(Main.i, 4);
            } else
                onlinePlayer.setFlying(false);
        }

        Database.get("flydb").set(p.getUniqueId().toString(), !state);
        Database.save("flydb");

    }

    public static boolean get(OfflinePlayer p) {

        return Database.get("flydb").getBoolean(p.getUniqueId().toString());

    }

}
