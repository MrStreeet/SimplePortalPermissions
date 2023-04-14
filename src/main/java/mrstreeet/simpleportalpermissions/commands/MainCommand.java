package mrstreeet.simpleportalpermissions.commands;

import jdk.jfr.internal.tool.Main;
import mrstreeet.simpleportalpermissions.SimplePortalPermissions;
import mrstreeet.simpleportalpermissions.utils.Files;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class MainCommand implements CommandExecutor {

    private SimplePortalPermissions plugin;

    public MainCommand(SimplePortalPermissions plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {


        if (args.length > 0){
            if (args[0].equalsIgnoreCase("reload")) {

                Files.reload();

                if (!(sender instanceof  Player)) {
                    Bukkit.getConsoleSender().sendMessage(plugin.name + ChatColor.GREEN + " Plugin reloaded correctly!");
                    return false;
                } else {
                    Player player = (Player) sender;
                    player.sendMessage(plugin.name + ChatColor.GREEN + " Plugin reloaded correctly!");
                    return true;
                }
            }
        }

        if (!(sender instanceof  Player)){
            Bukkit.getConsoleSender().sendMessage(plugin.name + ChatColor.RED + " Only players can perform this command!");
            return false;
        }else{
            Player player = (Player) sender;
            if (args.length > 0){
                if (args[0].equalsIgnoreCase("help")){
                    player.sendMessage(plugin.name + ChatColor.GREEN + " You are using SimplePortalPermissions " + ChatColor.WHITE + plugin.version);
                    player.sendMessage(ChatColor.GREEN + " These are all the available commands:");
                    player.sendMessage(ChatColor.WHITE + "/simpleportalpermissions or /spp " + ChatColor.YELLOW + "->" + ChatColor.GREEN + " Main command.");
                    player.sendMessage(ChatColor.WHITE + "/simplesetspawn help " + ChatColor.YELLOW + "->" + ChatColor.GREEN + " Se all available commands.");
                    player.sendMessage(ChatColor.WHITE + "/simplesetspawn reload " + ChatColor.YELLOW + "->" + ChatColor.GREEN + " Reload plugin files.");
                    player.sendMessage(ChatColor.GREEN + "Do you need more help?" + ChatColor.WHITE + "Ask me on:");
                    player.sendMessage(ChatColor.YELLOW + "https://github.com/MrStreeet/SimplePortalPermissions/issues");

                    return true;
                }
            }else{
                player.sendMessage(plugin.name + ChatColor.GREEN + " Do you need help? Use " );
                player.sendMessage(plugin.name + ChatColor.GREEN + "Use " + ChatColor.WHITE + "/simpleportalpermissions help" + ChatColor.GREEN + " to see all available commands!" );
            }
        }
        return false;
    }
}
