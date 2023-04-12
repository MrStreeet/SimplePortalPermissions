package mrstreeet.simpleportalpermissions.events;

import mrstreeet.simpleportalpermissions.SimplePortalPermissions;
import mrstreeet.simpleportalpermissions.utils.Files;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class FallDamage implements Listener {

    SimplePortalPermissions plugin;

    public FallDamage(SimplePortalPermissions plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFall (EntityDamageEvent event) {
        String fallpath = "Config.Disable-Fall-Damage";
        Player player = (Player) event.getEntity();
        if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL) && Files.config.getBoolean(fallpath) && plugin.getNamePlayers().contains(player)) {
            event.setCancelled(true);
            plugin.getNamePlayers().remove(player);
        }
    }
}
