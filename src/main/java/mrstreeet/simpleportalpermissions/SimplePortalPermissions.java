package mrstreeet.simpleportalpermissions;

import mrstreeet.simpleportalpermissions.commands.MainCommand;
import mrstreeet.simpleportalpermissions.events.PortalJoin;
import mrstreeet.simpleportalpermissions.utils.Files;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
    }*/

    @Override
    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage(name + ChatColor.GREEN + " It's now working correctly!.");

        plugin = this;
        instance = this;
        Files.createFiles(this);

        registerCommand();
        registerEvents();

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
        //pm.registerEvents(new FallDamage(this), this);
    }

}
