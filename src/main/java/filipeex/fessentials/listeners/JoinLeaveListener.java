package filipeex.fessentials.listeners;

import filipeex.fessentials.config.Config;
import filipeex.fessentials.config.Database;
import filipeex.fessentials.util.Fly;
import filipeex.fessentials.util.God;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        if (!Database.get("uuiddb").contains(p.getUniqueId().toString())) {
            firstJoin(p);
        } else {
            normalJoin(p);
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeave(PlayerQuitEvent e) {

        Player p = e.getPlayer();

        // KEEP-FLY
        if (!p.hasPermission("fessentials.fly.keep")) {
            Fly.set(p, false);
        }

        // KEEP-GOD
        if (!p.hasPermission("fessentials.god.keep")) {
            God.set(p, false);
        }

    }

    // ANY OTHER JOIN THAT'S NOT FIRST
    private void normalJoin(Player p) {

        // KEEP-FLY
        if (p.hasPermission("fessentials.fly.keep")) {
            Fly.set(p, Fly.get(p));
        } else {
            Fly.set(p, false);
        }

        // KEEP-GOD
        if (!p.hasPermission("fessentials.god.keep")) {
            God.set(p, false);
        }

    }

    // FIRST JOIN
    private void firstJoin(Player p) {

        // WRITE UUID AND IP INTO THE DATABASES
        Database.get("uuiddb").set(p.getUniqueId().toString(), p.getName());
        Database.save("uuiddb");
        Database.get("ipdb").set(p.getUniqueId().toString(), p.getAddress().getAddress().getHostAddress());
        Database.save("ipdb");

        // FIRST FLY FALSE DATABASE INITIATION
        Fly.set(p, false);

        // FIRST GOD FALSE DATABASE INITIATION
        God.set(p, false);

    }

}
