package com.helloitsmeadm.chatmention;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MentionCommand implements CommandExecutor {
    final ChatMention chatMention;

    public MentionCommand(ChatMention chatMention) {
        this.chatMention = chatMention;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equalsIgnoreCase("toggle")) {
            if (chatMention.database.hasMentionEnabled(sender.getName())) {
                chatMention.database.toggleMention(sender.getName(), false);
                sender.sendMessage(chatMention.getConfig().getString("prefix") + chatMention.getConfig().getString("toggled-off"));
            } else {
                chatMention.database.toggleMention(sender.getName(), true);
                sender.sendMessage(chatMention.getConfig().getString("prefix") + chatMention.getConfig().getString("toggled-on"));
            }
        } else {
            sender.sendMessage(chatMention.getConfig().getString("prefix") + chatMention.getConfig().getString("error-wrong-usage"));
        }
        return true;
    }
}
