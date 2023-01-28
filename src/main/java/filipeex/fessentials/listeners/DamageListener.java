package filipeex.fessentials.listeners;

import filipeex.fessentials.util.God;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void damage(EntityDamageEvent e) {

        if (e.getEntity() instanceof Player p)
            if (God.get(p))
                e.setCancelled(true);

    }

}
