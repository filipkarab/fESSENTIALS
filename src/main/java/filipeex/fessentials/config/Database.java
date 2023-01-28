package filipeex.fessentials.config;

import filipeex.fessentials.Main;
import filipeex.fessentials.console.Debug;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Database {

    private static HashMap<File, FileConfiguration> dtbs = new HashMap<File, FileConfiguration>();

    public static FileConfiguration get(String dtbName) {
        return findDtb(dtbName);
    }

    public static void create(String dtbName) {

        File folder = new File(Main.i.getDataFolder(), "data");
        File file = new File(Main.i.getDataFolder() + File.separator + "data", dtbName + ".yml");

        if (!folder.exists()) {
            folder.mkdir();
        }

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            Main.i.saveResource("data" + File.separator + dtbName + ".yml", true);
        }

        load(dtbName);

    }

    public static void load(String dtbName) {

        FileConfiguration dtb = new YamlConfiguration();
        try {
            dtb.load(new File(Main.i.getDataFolder() + File.separator + "data", dtbName + ".yml"));
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            Debug.error("An error occured while trying to load the %x database file!".replace("%x", dtbName + ".yml"));
        }

        dtbs.put(new File(Main.i.getDataFolder() + File.separator + "data", dtbName + ".yml"), dtb);

    }

    public static void save(String dtbName) {

        FileConfiguration dtb = findDtb(dtbName);
        try {
            dtb.save(new File(Main.i.getDataFolder() + File.separator + "data", dtbName + ".yml"));
        } catch (IOException e) {
            e.printStackTrace();
            Debug.error("An error has occured while trying to save the %x database file!".replace("%x", dtbName + ".yml"));
        }

    }

    public static void unload(String dtbName) {

        dtbs.remove(new File(Main.i.getDataFolder() + File.separator + "data", dtbName + ".yml"));

    }

    public static FileConfiguration findDtb(String dtbName) {

        for (Map.Entry<File, FileConfiguration> x : dtbs.entrySet()) {
            if (x.getKey().getName().equalsIgnoreCase(dtbName + ".yml")) {
                return x.getValue();
            }
        }

        return null;

    }

}
