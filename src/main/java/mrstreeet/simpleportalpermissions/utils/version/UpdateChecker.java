package mrstreeet.simpleportalpermissions.utils.version;

import mrstreeet.simpleportalpermissions.SimplePortalPermissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {
    private SimplePortalPermissions plugin;
    private int resourceId;

    public UpdateChecker(SimplePortalPermissions plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getLatestVersion(Consumer<String> consumer) {


        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream();
                 Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                this.plugin.getLogger().info(plugin.name + ChatColor.RED + "Cannot look for updates: " + exception.getMessage());
            }
        });
    }
}
