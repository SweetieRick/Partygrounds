package dev.sweetierick.plugins.partygrounds;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Partygrounds extends JavaPlugin implements Listener {
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        // Adding default configuration
        config.addDefault("version", "v0.1");
        config.addDefault("welcome-message", true);
        config.addDefault("welcome-message-content", "§6 Welcome to our Event Server! Ready to party ");
        config.addDefault("broadcast-to-chat", false);
        config.addDefault("broadcast-message", "§8[§a+§8]§r ");
        config.options().copyDefaults(true);
        saveConfig();

        // Adding plugin loading logic
        getLogger().info("************************************************");
        getLogger().info("**  Partygrounds loaded successfully!");
        getLogger().info("**  Plugin Version: " + getConfig().getString("version"));
        getLogger().info("**  ");
        getLogger().info("**  Author: SweetieRick");
        getLogger().info("************************************************");

        // Enables the class to get and run on trigger event onPlayerJoin()
        getServer().getPluginManager().registerEvents(this, this);

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Retrieving current joined player and welcome message
        Player player = event.getPlayer();
        // If also the broadcast message is enabled, we broadcast it to everyone
        if (config.getBoolean("broadcast-to-chat")) {
            // We broadcast the message to everyone to inform the chat that someone joined
            // As many people use other, cooler plugins with fancy prefixes, only if the
            // condition is cleared the broadcast will run
            String broadcastmessage = config.getString("broadcast-message") + player.getName();
            for (Player everyone : Bukkit.getOnlinePlayers()) {
                everyone.sendMessage(broadcastmessage);
            }
        }
        // If the welcome message is enabled, we broadcast it to the player
        if (config.getBoolean("welcome-message")) {
            // We send a message to the player that joined
            String welcomemessage = config.getString("welcome-message") + player.getName() + "?";
            player.sendMessage(welcomemessage);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
