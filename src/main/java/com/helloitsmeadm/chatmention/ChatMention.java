package com.helloitsmeadm.chatmention;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ChatMention extends JavaPlugin {
    Database database;

    @Override
    public void onEnable() {
        // Save config
        saveDefaultConfig();

        // Enable Listeners
        Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);

        // Register command /mention
        Objects.requireNonNull(getCommand("mention")).setExecutor(new MentionCommand(this));

        // Create SQL database
        database = new Database(this);
        database.createTable();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
