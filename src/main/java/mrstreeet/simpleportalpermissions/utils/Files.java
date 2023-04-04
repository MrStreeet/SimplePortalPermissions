package mrstreeet.simpleportalpermissions.utils;

import mrstreeet.simpleportalpermissions.SimplePortalPermissions;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;


public class Files {

    public static File configFile;
    public static FileConfiguration config;

    public static File messagesFile;
    public static FileConfiguration messages;

    public static void createFiles(SimplePortalPermissions files){
        if (!files.getDataFolder().exists()) {
            files.getDataFolder().mkdirs();
        }

        //Creates config.yml
        configFile = new File(files.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            files.saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(configFile);

        //Creates messages.yml
        messagesFile = new File(files.getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            files.saveResource("messages.yml", false);
        }
        messages = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public static void reload() {
        config = YamlConfiguration.loadConfiguration(configFile);
        messages = YamlConfiguration.loadConfiguration(messagesFile);
    }


}
