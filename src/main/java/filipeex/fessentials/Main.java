package filipeex.fessentials;

import filipeex.fessentials.commands.FlyCMD;
import filipeex.fessentials.commands.GodCMD;
import filipeex.fessentials.config.Config;
import filipeex.fessentials.config.Database;
import filipeex.fessentials.console.Debug;
import filipeex.fessentials.listeners.DamageListener;
import filipeex.fessentials.listeners.JoinLeaveListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {

    public static Main i;

    @Override
    public void onLoad() {

        Debug.announce("Loading the fESSENTIALS plugin...");
        //
        Debug.announce("Successfully loaded the fESSENTIALS plugin!");

    }

    @Override
    public void onEnable() {

        Debug.announce("Enabling the fESSENTIALS plugin...");

        initiateMainInstance();
        createConfigurationFiles();
        createDatabaseFiles();
        toggleDebugOutput();
        registerListeners();
        registerCommands();

        Debug.announce("Successfully enabled the fESSENTIALS plugin!");

    }

    @Override
    public void onDisable() {

        Debug.announce("Disabling the fESSENTIALS plugin...");
        //
        Debug.announce("Successfully disabled the fESSENTIALS plugin!");

    }

    private void initiateMainInstance() {

        Debug.debug("Initiating the main instanace...");
        i = this;
        Debug.debug("The main instance initiated.");

    }

    private void createConfigurationFiles() {

        Debug.debug("Loading the settings.yml configuration file...");
        Config.create("settings");
        Debug.debug("Successfully loaded the settings.yml configuration file!");

        Debug.debug("Loading the messages.yml configuration file...");
        Config.create("messages");
        Debug.debug("Successfully loaded the messages.yml configuration file!");


    }

    private void createDatabaseFiles() {

        Debug.debug("Loading the uuiddb.yml database file...");
        Database.create("uuiddb");
        Debug.debug("Successfully loaded the uuiddb.yml database file!");

        Debug.debug("Loading the ipdb.yml database file...");
        Database.create("ipdb");
        Debug.debug("Successfully loaded the ipdb.yml database file!");

        Debug.debug("Loading the flydb.yml database file...");
        Database.create("flydb");
        Debug.debug("Successfully loaded the flydb.yml database file!");

        Debug.debug("Loading the goddb.yml database file...");
        Database.create("goddb");
        Debug.debug("Successfully loaded the goddb.yml database file!");

    }

    private void toggleDebugOutput() {

        Debug.announce("Scanning the settings.yml configuration file to figure out if debug output is enabled...");

        if (Config.get("settings").getBoolean("debug")) {

            Debug.doDebug = true;
            Debug.announce("Debug output is enabled, enjoy your poor decision making and observe the console be destroyed by useless messages from the plugin (joke)!");

        } else {

            Debug.doDebug = false;
            Debug.announce("Debug output is disabled, good choice, from now on you can clearly see through the non-spammed console!");

        }

    }

    private void registerListeners() {

        Debug.debug("Getting the pluginmanager for registering the listeners...");
        PluginManager pm = getServer().getPluginManager();

        Debug.debug("Registering JoinLeaveListener.java...");
        pm.registerEvents(new JoinLeaveListener(), this);
        Debug.debug("Successfully registered JoinLeaveListener.java!");

        Debug.debug("Registering DamageListener.java...");
        pm.registerEvents(new DamageListener(), this);
        Debug.debug("Successfully registered DamageListener.java!");

    }

    private void registerCommands() {

        Debug.debug("Registering /fly command...");
        Objects.requireNonNull(getCommand("fly")).setExecutor(new FlyCMD());
        Debug.debug("Successfully registered /fly command!");

        Debug.debug("Registering /god command...");
        Objects.requireNonNull(getCommand("god")).setExecutor(new GodCMD());
        Debug.debug("Successfully registered /god command!");

    }

}
