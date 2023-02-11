package com.helloitsmeadm.chatmention;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Database {
    private static YamlConfiguration config;
    final File file = new File("plugins/ChatMention/database.yml");
    final ChatMention chatMention;

    public Database(ChatMention chatMention) {
        Database.config = YamlConfiguration.loadConfiguration(file);
        this.chatMention = chatMention;
    }

    public void createTable() {
        Database.config.addDefault("players", new HashMap<>());
        Database.config.options().copyDefaults(true);
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void toggleMention(String nickname, boolean enabled) {
        Database.config.set("players." + nickname + ".enabled", enabled);
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hasMentionEnabled(String nickname) {
        return Database.config.getBoolean("players." + nickname + ".enabled", true);
    }
}
