package filipeex.fessentials.commands;

import filipeex.fessentials.cmdargs.ArgumentSet;
import filipeex.fessentials.cmdargs.FCommand;
import filipeex.fessentials.config.Config;
import filipeex.fessentials.messages.Message;
import filipeex.fessentials.messages.ReplacementSet;
import filipeex.fessentials.util.Fly;
import filipeex.fessentials.util.Multiplayer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCMD extends FCommand {

    @Override
    public void command(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("fessentials.fly")) {
            Message.send(sender, "no-perms");
            return;
        }

        // //
        // 0 ARGS (/fly)
        // //
        if (args.length == 0) {

            if (!(sender instanceof Player p)) {
                Message.send(sender, "player_only");
                Message.send(sender, "fly_usage");
                return;
            }

            Fly.toggle(p);

            ReplacementSet r = new ReplacementSet();
            r.add("%state%", Config.get("messages").getString("state_" + Fly.get(p)));
            Message.send(p, "fly_self", r);

        }

        // //
        // 1 ARG (/fly [true/false/player_name]) TODO: WHAT IF THE ONE ARGUMENT IS A STAR (**) or EVERYONE
        // //
        if (args.length == 1) {

            String arg = args[0];

            // PLAYER NAME (/fly [player])
            if (!arg.equalsIgnoreCase("true") && !arg.equalsIgnoreCase("false") && !arg.equalsIgnoreCase("toggle")) {

                    if (!sender.hasPermission("fessentials.fly.others")) {
                        Message.send(sender, "no_perms");
                        return;
                    }

                    OfflinePlayer target = (OfflinePlayer) Multiplayer.findOnlineOrOfflinePlayer(arg);
                    if (target == null) {
                        Message.send(sender, "player_not_found");
                        return;
                    }

                    Fly.toggle(target);

                    ReplacementSet r = new ReplacementSet();
                    r.add("%player%", target.getName());
                    r.add("%status%", Config.get("messages").getString("status_" + target.isOnline()));
                    r.add("%state%", Config.get("messages").getString("state_" + Fly.get(target)));
                    Message.send(sender, "fly_other", r);

            } else if (arg.equalsIgnoreCase("true") || arg.equalsIgnoreCase("false")) { // STATE (/fly [true/false])

                if (!(sender instanceof Player p)) {
                    Message.send(sender, "player_only");
                    Message.send(sender, "fly_usage");
                    return;
                }

                boolean x;
                x = arg.equalsIgnoreCase("true");

                Fly.set(p, x);

                ReplacementSet r = new ReplacementSet();
                r.add("%state%", Config.get("messages").getString("state_" + Fly.get(p)));
                Message.send(p, "fly_self", r);

            } else if (arg.equalsIgnoreCase("toggle")) { // REVERSED-STATE (/fly toggle)

                if (!(sender instanceof Player p)) {
                    Message.send(sender, "player_only");
                    Message.send(sender, "fly_usage");
                    return;
                }

                Fly.toggle(p);

                ReplacementSet r = new ReplacementSet();
                r.add("%state%", Config.get("messages").getString("state_" + Fly.get(p)));
                Message.send(p, "fly_self", r);

            }

        }

        // //
        // 2 ARGS (/fly [playerName/*/**] [true/false/toggle])
        // //
        if (args.length == 2) {

            String arg1 = args[0];
            String arg2 = args[1];

            if (!arg1.equalsIgnoreCase("*") && !arg1.equalsIgnoreCase("**")) { // PLAYER NAME (/fly [playerName] [true/false/toggle])

                if (!sender.hasPermission("fessentials.fly.others")) {
                    Message.send(sender, "no_perms");
                    return;
                }

                OfflinePlayer target = (OfflinePlayer) Multiplayer.findOnlineOrOfflinePlayer(arg1);
                if (target == null) {
                    Message.send(sender, "player_not_found");
                    return;
                }

                if (!arg2.equalsIgnoreCase("true") &&
                    !arg2.equalsIgnoreCase("false") &&
                    !arg2.equalsIgnoreCase("toggle"))
                {
                    Message.send(sender, "fly_usage");
                    return;
                } else if (arg2.equalsIgnoreCase("toggle")) { // /fly [playerName] toggle

                    Fly.toggle(target);

                } else { // /fly [playerName] [true/false]

                    boolean x = arg2.equalsIgnoreCase("true");
                    Fly.set(target, x);

                }

                ReplacementSet r = new ReplacementSet();
                r.add("%player%", target.getName());
                r.add("%status%", "%status_" + target.isOnline() + "%");
                r.add("%state%", "%state_" + Fly.get(target) + "%");
                Message.send(sender, "fly_other", r);

            } else if (arg1.equalsIgnoreCase("*")) { // ALL ONLINE PLAYERS (/fly * [true/false/toggle])

                if (!sender.hasPermission("fessentials.fly.others")) {
                    Message.send(sender, "no_perms");
                    return;
                }

                int onlinePlayerCount = 0;
                String statePlaceholder = "";

                if (!arg2.equalsIgnoreCase("true") &&
                    !arg2.equalsIgnoreCase("false") &&
                    !arg2.equalsIgnoreCase("toggle"))
                {
                    Message.send(sender, "fly_usage");
                    return;
                } else if (arg2.equalsIgnoreCase("toggle")) {

                    for (Player p : Bukkit.getOnlinePlayers()) {
                        Fly.toggle(p);
                        onlinePlayerCount++;
                    }
                    statePlaceholder = "%state_toggled%";

                } else {

                    boolean x = arg2.equalsIgnoreCase("true");
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        Fly.set(p, x);
                        onlinePlayerCount++;
                    }
                    statePlaceholder = "%state_" + x + "%";

                }

                ReplacementSet r = new ReplacementSet();
                r.add("%total%", onlinePlayerCount + "");
                r.add("%state%", statePlaceholder);
                Message.send(sender, "fly_all_online", r);


            } else if (arg1.equalsIgnoreCase("**")) { // ALL ONLINE AND OFFLINE PLAYERS (/fly ** [true/false/toggle])

                if (!sender.hasPermission("fessentials.fly.others")) {
                    Message.send(sender, "no_perms");
                    return;
                }

                int onlinePlayerCount = 0;
                int offlinePlayerCount = 0;
                String statePlaceholder = "";

                if (!arg2.equalsIgnoreCase("true") &&
                        !arg2.equalsIgnoreCase("false") &&
                        !arg2.equalsIgnoreCase("toggle"))
                {
                    Message.send(sender, "fly_usage");
                    return;
                } else if (arg2.equalsIgnoreCase("toggle")) {

                    for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
                        Fly.toggle(p);
                        if (p.isOnline())
                            onlinePlayerCount++;
                        else
                            offlinePlayerCount++;
                    }
                    statePlaceholder = "%state_toggled%";

                } else {

                    boolean x = arg2.equalsIgnoreCase("true");
                    for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
                        Fly.set(p, x);
                        if (p.isOnline())
                            onlinePlayerCount++;
                        else
                            offlinePlayerCount++;
                    }
                    statePlaceholder = "%state_" + x + "%";

                }

                ReplacementSet r = new ReplacementSet();
                r.add("%total%", onlinePlayerCount + offlinePlayerCount + "");
                r.add("%online%", onlinePlayerCount + "");
                r.add("%offline%", offlinePlayerCount + "");
                r.add("%state%", statePlaceholder);
                Message.send(sender, "fly_all", r);

            }

        }

        if (args.length >= 3) {
            Message.send(sender, "fly_usage");
        }

    }

    @Override
    public ArgumentSet tab(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 1) {

            ArgumentSet result = new ArgumentSet();
            for (Player p : Bukkit.getOnlinePlayers())
                result.add(p.getName());
            result.add("true");
            result.add("false");
            result.add("toggle");

            return result;
        }

        if (args.length == 2) {

            ArgumentSet result = new ArgumentSet();

            if (Multiplayer.findOnlineOrOfflinePlayer(args[0]) != null) { // /fly [playerName] ...
                result.add("true");
                result.add("false");
                result.add("toggle");
            } else if (args[0].equalsIgnoreCase("*") || args[0].equalsIgnoreCase("**")) {
                result.add("true");
                result.add("false");
                result.add("toggle");
            }

        }

        return new ArgumentSet();
    }

}
