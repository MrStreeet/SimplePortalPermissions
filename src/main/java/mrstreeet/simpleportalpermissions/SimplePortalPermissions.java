package mrstreeet.simpleportalpermissions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimplePortalPermissions extends JavaPlugin {

    PluginDescriptionFile pdffile = getDescription();
    public String version = pdffile.getVersion();
    public String name = ChatColor.GOLD + "[" + pdffile.getName() + "]";
    public String configPath;
    public static SimplePortalPermissions plugin;
    private static Plugin instance;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(name + ChatColor.YELLOW + " Plugin it's loading...");

        plugin = this;
        instance = this;

        Bukkit.getConsoleSender().sendMessage(name + ChatColor.GREEN + " It's now working correctly!.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
