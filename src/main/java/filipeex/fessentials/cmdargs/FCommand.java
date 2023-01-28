package filipeex.fessentials.cmdargs;

import com.sun.jdi.connect.Connector;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public abstract class FCommand implements TabExecutor {

    public abstract void command(CommandSender sender, Command cmd, String label, String[] args);
    public abstract ArgumentSet tab(CommandSender sender, Command cmd, String label, String[] args);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        command(commandSender, command, s, strings);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        ArgumentSet args = tab(commandSender, command, s, strings);
        return args.get();
    }

}
