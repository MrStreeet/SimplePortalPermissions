package mrstreeet.simpleportalpermissions.events;

import mrstreeet.simpleportalpermissions.SimplePortalPermissions;
import mrstreeet.simpleportalpermissions.utils.Files;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import java.util.HashMap;
import java.util.UUID;


public class PortalJoin implements Listener {

    private SimplePortalPermissions plugin;

    private final HashMap<UUID, Long> cooldown;

    public PortalJoin(SimplePortalPermissions plugin){
        this.cooldown = new HashMap<>();
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerTeleport(PlayerTeleportEvent event){
        Player player = event.getPlayer();
        //Nether cancellation
        if(event.getTo().getWorld().getEnvironment() == World.Environment.NETHER) {
            if (!player.hasPermission("spp.nether")) {
                event.setCancelled(true);
            }
        //End cancellation
        } else if (event.getTo().getWorld().getEnvironment() == World.Environment.THE_END && !player.hasPermission("spp.end")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void PlayerPortalEvent(PlayerTeleportEvent event){
        Player player = event.getPlayer();
        String audiopath = "Effects.Sounds";
        String msgpath = "Effects.Messages";
        String pushpath = "Effects.Push";

        //Nether cancellation
        if(event.getTo().getWorld().getEnvironment() == World.Environment.NETHER) {
            if (!player.hasPermission("spp.nether")) {
                event.setCancelled(true);
                //Push effect
                if (Files.config.getBoolean(pushpath)) {
                    player.setVelocity(player.getLocation().getDirection().multiply(Files.config.getInt("Push_Config.Nether-Velocity-Multiplier")));
                    player.setVelocity(player.getLocation().getDirection().setY(Files.config.getInt("Push_Config.Nether-Y-Velocity")));
                    plugin.onAir_players.add(player);
                }
                //Message effect
                if (Files.config.getBoolean(msgpath)) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Files.messages.getString("Deny-Entrance.Nether")));
                }
                //Sound effect
                if (Files.config.getBoolean(audiopath)) {
                        player.playSound(player.getLocation(), Sound.valueOf(Files.config.getString("Sound_Config.Sound")), Files.config.getInt("Sound_Config.Volume"), Files.config.getInt("Sound_Config.Pitch"));
                }
            }

        //End cancellation
        } else if (event.getTo().getWorld().getEnvironment() == World.Environment.THE_END && !player.hasPermission("spp.end")) {
            event.setCancelled(true);
            //Push effect
            if (Files.config.getBoolean(pushpath)) {
                player.setVelocity(player.getLocation().getDirection().multiply(Files.config.getInt("Push_Config.End-Velocity-Multiplier")));
                player.setVelocity(player.getLocation().getDirection().setY(Files.config.getInt("Push_Config.End-Y-Velocity")));
                plugin.onAir_players.add(player);
            }
            //Cooldown effects
            if (!this.cooldown.containsKey(player.getUniqueId())){
                this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                //Message effect
                if (Files.config.getBoolean(msgpath)) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Files.messages.getString("Deny-Entrance.End")));
                }
                //Audio effect
                if (Files.config.getBoolean(audiopath)) {
                    player.playSound(player.getLocation(), Sound.valueOf(Files.config.getString("Sound_Config.Sound")), Files.config.getInt("Sound_Config.Volume"), Files.config.getInt("Sound_Config.Pitch"));
                }
            }else{
                //In ms
                long timeElapsed = System.currentTimeMillis() - cooldown.get(player.getUniqueId());
                if (timeElapsed > 10000){ //10 seconds
                    this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                    //Message effect
                    if (Files.config.getBoolean(msgpath)) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Files.messages.getString("Deny-Entrance.End")));
                    }
                    //Audio effect
                    if (Files.config.getBoolean(audiopath)) {
                        player.playSound(player.getLocation(), Sound.valueOf(Files.config.getString("Sound_Config.Sound")), Files.config.getInt("Sound_Config.Volume"), Files.config.getInt("Sound_Config.Pitch"));
                    }
                }
            }
        }
    }
}
