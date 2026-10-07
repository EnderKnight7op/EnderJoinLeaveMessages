package me.enderlabs;

import net.kyori.adventure.text.Component;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.clip.placeholderapi.PlaceholderAPI;

public class LeaveListener implements Listener {

    private final EnderJoinQuitMessages plugin;
    private final MessageParser messageParser;

    public LeaveListener(EnderJoinQuitMessages plugin) {
        this.plugin = plugin;
        this.messageParser = new MessageParser(plugin);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {

        String message = plugin.getConfig().getString("leave-message");

        if (message == null) {
            return;
        }

        message = message.replace("%player%", event.getPlayer().getName());

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            message = PlaceholderAPI.setPlaceholders(event.getPlayer(), message);
        }

        Component component = messageParser.parseMessage(message);

        event.quitMessage(component);
    }
}