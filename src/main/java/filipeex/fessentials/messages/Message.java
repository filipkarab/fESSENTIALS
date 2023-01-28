package filipeex.fessentials.messages;

import filipeex.fessentials.chat.Chat;
import filipeex.fessentials.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Message {

    // ********************************************************************
    //
    //          PLAYER, NO REPLACEMENT SET
    //
    // ********************************************************************

    public static void send(Player p, String messageName) {

        String rawMessage = Config.get("messages").getString(messageName);
        List<String> rawMessageArray = Config.get("messages").getStringList(messageName);

        // IF THE MESSAGE IS ONE-LINE
        if (rawMessage != null && !rawMessage.equals("")) {

            String message = rawMessage;

            ReplacementSet replacementSet = new ReplacementSet();
            replacementSet.add("%player%", p.getDisplayName());
            for (String x : Config.get("messages").getKeys(true))
                if (!x.equalsIgnoreCase(messageName))
                    replacementSet.add("%" + x + "%", Config.get("messages").getString(x));
            // TODO: PLACEHOLDERAPI AUTOMATIC ALL REPLACEMENTS

            for (Replacement x : replacementSet.get())
                message = message.replace(x.getPlaceholder(), x.getValue());

            message = Chat.color(message);

            p.sendMessage(message);


        } else { // IF THE MESSAGE IS MULTI-LINED

            ArrayList<String> message = new ArrayList<String>(rawMessageArray);

            ReplacementSet replacementSet = new ReplacementSet();
            replacementSet.add("%player%", p.getDisplayName());
            for (String x : Config.get("messages").getKeys(true))
                if (!x.equalsIgnoreCase(messageName))
                    replacementSet.add("%" + x + "%", Config.get("messages").getString(x));
            // TODO: PLACEHOLDERAPI AUTOMATIC ALL REPLACEMENTS

            for (Replacement x : replacementSet.get())
                for (String line : message) {
                    message.remove(line);
                    line = line.replace(x.getPlaceholder(), x.getValue());
                    message.add(line);
                }

            for (String x : message) {
                message.remove(x);
                message.add(Chat.color(x));
            }

            for (String x : message)
                p.sendMessage(x);

        }

    }

    // ********************************************************************
    //
    //          PLAYER, WITH REPLACEMENT SET
    //
    // ********************************************************************

    public static void send(Player p, String messageName, ReplacementSet replacementSet) {

        String rawMessage = Config.get("messages").getString(messageName);
        List<String> rawMessageArray = Config.get("messages").getStringList(messageName);

        // IF THE MESSAGE IS ONE-LINE
        if (rawMessage != null && !rawMessage.equals("")) {

            String message = rawMessage;

            if (!replacementSet.contains("%player%"))
                replacementSet.add("%player%", p.getDisplayName());
            for (String x : Config.get("messages").getKeys(true))
                if (!x.equalsIgnoreCase(messageName))
                    replacementSet.add("%" + x + "%", Config.get("messages").getString(x));
            // TODO: PLACEHOLDERAPI AUTOMATIC ALL REPLACEMENTS

            for (Replacement x : replacementSet.get())
                message = message.replace(x.getPlaceholder(), x.getValue());

            message = Chat.color(message);

            p.sendMessage(message);


        } else { // IF THE MESSAGE IS MULTI-LINED

            ArrayList<String> message = new ArrayList<String>(rawMessageArray);

            replacementSet.add("%player%", p.getDisplayName());
            for (String x : Config.get("messages").getKeys(true))
                if (!x.equalsIgnoreCase(messageName))
                    replacementSet.add("%" + x + "%", Config.get("messages").getString(x));
            // TODO: PLACEHOLDERAPI AUTOMATIC ALL REPLACEMENTS

            for (Replacement x : replacementSet.get())
                for (String line : message) {
                    message.remove(line);
                    line = line.replace(x.getPlaceholder(), x.getValue());
                    message.add(line);
                }

            for (String x : message) {
                message.remove(x);
                message.add(Chat.color(x));
            }

            for (String x : message)
                p.sendMessage(x);

        }

    }

    // ********************************************************************
    //
    //          CONSOLE, NO REPLACEMENT SET
    //
    // ********************************************************************

    public static void send(CommandSender console, String messageName) {

        String rawMessage = Config.get("messages").getString(messageName);
        List<String> rawMessageArray = Config.get("messages").getStringList(messageName);

        // IF THE MESSAGE IS ONE-LINE
        if (rawMessage != null && !rawMessage.equals("")) {

            String message = rawMessage;

            ReplacementSet replacementSet = new ReplacementSet();
            for (String x : Config.get("messages").getKeys(true))
                if (!x.equalsIgnoreCase(messageName))
                    replacementSet.add("%" + x + "%", Config.get("messages").getString(x));

            for (Replacement x : replacementSet.get())
                message = message.replace(x.getPlaceholder(), x.getValue());

            message = Chat.color(message);

            console.sendMessage(message);


        } else { // IF THE MESSAGE IS MULTI-LINED

            ArrayList<String> message = new ArrayList<String>(rawMessageArray);

            ReplacementSet replacementSet = new ReplacementSet();
            for (String x : Config.get("messages").getKeys(true))
                if (!x.equalsIgnoreCase(messageName))
                    replacementSet.add("%" + x + "%", Config.get("messages").getString(x));

            for (Replacement x : replacementSet.get())
                for (String line : message) {
                    message.remove(line);
                    line = line.replace(x.getPlaceholder(), x.getValue());
                    message.add(line);
                }

            for (String x : message) {
                message.remove(x);
                message.add(Chat.color(x));
            }

            for (String x : message)
                console.sendMessage(x);

        }

    }

    // ********************************************************************
    //
    //          CONSOLE, WITH REPLACEMENT SET
    //
    // ********************************************************************

    public static void send(CommandSender console, String messageName, ReplacementSet replacementSet) {

        String rawMessage = Config.get("messages").getString(messageName);
        List<String> rawMessageArray = Config.get("messages").getStringList(messageName);

        // IF THE MESSAGE IS ONE-LINE
        if (rawMessage != null && !rawMessage.equals("")) {

            String message = rawMessage;

            for (String x : Config.get("messages").getKeys(true))
                if (!x.equalsIgnoreCase(messageName))
                    replacementSet.add("%" + x + "%", Config.get("messages").getString(x));

            for (Replacement x : replacementSet.get())
                message = message.replace(x.getPlaceholder(), x.getValue());

            message = Chat.color(message);

            console.sendMessage(message);


        } else { // IF THE MESSAGE IS MULTI-LINED

            ArrayList<String> message = new ArrayList<String>(rawMessageArray);

            for (String x : Config.get("messages").getKeys(true))
                if (!x.equalsIgnoreCase(messageName))
                    replacementSet.add("%" + x + "%", Config.get("messages").getString(x));

            for (Replacement x : replacementSet.get())
                for (String line : message) {
                    message.remove(line);
                    line = line.replace(x.getPlaceholder(), x.getValue());
                    message.add(line);
                }

            for (String x : message) {
                message.remove(x);
                message.add(Chat.color(x));
            }

            for (String x : message)
                console.sendMessage(x);

        }

    }

}
