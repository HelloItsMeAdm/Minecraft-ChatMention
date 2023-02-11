package com.helloitsmeadm.chatmention;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.Objects;

public class ChatListener implements Listener {
    final ChatMention chatMention;

    public ChatListener(ChatMention chatMention) {
        this.chatMention = chatMention;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        // Set format, just for testing
        event.setFormat("§d§lDeveloper§r §f" + event.getPlayer().getName() + " §8» §d" + event.getMessage());

        // Create list of mentioned players
        ArrayList<String> mentionedPlayers = new ArrayList<>();

        // Check if message contains some name
        for (org.bukkit.entity.Player player : chatMention.getServer().getOnlinePlayers()) {
            if (event.getFormat().contains(player.getName()) && !event.getPlayer().getName().equals(player.getName())) {
                // Set the new format
                event.setFormat(event.getFormat().replace(player.getName(), Objects.requireNonNull(Objects.requireNonNull(chatMention.getConfig().getString("format")).replace("%name%", player.getName()))));

                if (!mentionedPlayers.contains(player.getName())) {
                    mentionedPlayers.add(player.getName());

                    if (chatMention.database.hasMentionEnabled(player.getName())) {
                        // Send title to mentioned player
                        player.sendTitle(chatMention.getConfig().getString("title"), Objects.requireNonNull(chatMention.getConfig().getString("subtitle")).replace("%name%", event.getPlayer().getName()), 10, 70, 20);

                        // Send sound to mentioned player - levelup from config
                        Sound sound = Sound.valueOf(chatMention.getConfig().getString("sound"));
                        player.playSound(player.getLocation(), sound, 1, 1);
                    }
                }
            }
        }

    }
}
