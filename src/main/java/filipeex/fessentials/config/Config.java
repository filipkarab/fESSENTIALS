package filipeex.fessentials.config;

import filipeex.fessentials.Main;
import filipeex.fessentials.console.Debug;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Config {

    private static HashMap<File, FileConfiguration> cnfgs = new HashMap<File, FileConfiguration>();

    public static FileConfiguration get(String cnfgName) {
        return findCnfg(cnfgName);
    }

    public static void create(String cnfgName) {

        File file = new File(Main.i.getDataFolder(), cnfgName + ".yml");

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            Main.i.saveResource(cnfgName + ".yml", true);
        }

        load(cnfgName);

    }

    public static void load(String cnfgName) {

        FileConfiguration config = new YamlConfiguration();
        try {
            config.load(new File(Main.i.getDataFolder(), cnfgName + ".yml"));
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            Debug.error("An error occured while trying to load the %x configuration file!".replace("%x", cnfgName + ".yml"));
        }

        cnfgs.put(new File(Main.i.getDataFolder(), cnfgName + ".yml"), config);

    }

    public static void save(String cnfgName) {

        FileConfiguration cnfg = findCnfg(cnfgName);
        try {
            cnfg.save(new File(Main.i.getDataFolder(), cnfgName + ".yml"));
        } catch (IOException e) {
            e.printStackTrace();
            Debug.error("An error has occured while trying to save the %x configuration file!".replace("%x", cnfgName + ".yml"));
        }

    }

    public static void unload(String cnfgName) {

        cnfgs.remove(new File(Main.i.getDataFolder(), cnfgName + ".yml"));

    }

    public static FileConfiguration findCnfg(String cnfgName) {

        for (Map.Entry<File, FileConfiguration> x : cnfgs.entrySet()) {
            if (x.getKey().getName().equalsIgnoreCase(cnfgName + ".yml")) {
                return x.getValue();
            }
        }

        return null;

    }

}
