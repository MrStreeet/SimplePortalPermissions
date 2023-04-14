package mrstreeet.simpleportalpermissions;

import mrstreeet.simpleportalpermissions.commands.MainCommand;
import mrstreeet.simpleportalpermissions.events.PortalJoin;
import mrstreeet.simpleportalpermissions.utils.Files;
import mrstreeet.simpleportalpermissions.utils.version.ReleaseData;
import mrstreeet.simpleportalpermissions.utils.version.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimplePortalPermissions extends JavaPlugin {

    PluginDescriptionFile pdffile = getDescription();
    public String version = pdffile.getVersion();
    public String name = ChatColor.GOLD + "[" + pdffile.getName() + "]";
    public String configPath;
    public static SimplePortalPermissions plugin;
    private static Plugin instance;

    //-----------------FALL DAMAGE SYSTEM WIP-----------------//
    /*private final Set<String> namePlayers = new HashSet<>();
    public Set<String> getNamePlayers() {
        return this.namePlayers;
    }
    public boolean addPlayer(final Player player) {
        return this.namePlayers.add(player.getName());
    }

    @Override
    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage(name + ChatColor.GREEN + " It's now working correctly!.");

        plugin = this;
        instance = this;
        Files.createFiles(this);

        registerCommand();
        registerEvents();

        if (System.currentTimeMillis() > ReleaseData.RELEASE_TIME + 21600000L) {
            updateChecker();
        }


    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(name + ChatColor.RED + " Session closed correctly. See you!");
    }

    public void registerCommand(){
        this.getCommand("simpleportalpermissions").setExecutor(new MainCommand(this));
    }

    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PortalJoin(this), this);
    }

    public void updateChecker(){
        new UpdateChecker(this, 109279).getLatestVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                Bukkit.getConsoleSender().sendMessage(name + ChatColor.GREEN + " It's up to date (Running version: " + ChatColor.WHITE +  version + ")");
            }else{
                Bukkit.getConsoleSender().sendMessage(name + ChatColor.RED + " You are running an old version of the plugin, please update to the latest version.");
            }
        });
    }

}
