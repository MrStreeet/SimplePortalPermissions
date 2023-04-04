package mrstreeet.simpleportalpermissions.events;

import mrstreeet.simpleportalpermissions.SimplePortalPermissions;
import mrstreeet.simpleportalpermissions.utils.Files;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FallDamage implements Listener {

    SimplePortalPermissions plugin;

    public FallDamage(SimplePortalPermissions plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFall (EntityDamageEvent event) {
        String fallpath = "Config.Disable-Fall-Damage";
        if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL) && Files.config.getBoolean(fallpath)) {
            if (plugin.onAir_players.contains(event.getEntity())) {
                plugin.onAir_players.remove(event.getEntity());
                event.setCancelled(true);
            }
        }
    }
}
