package net.xiler.bungeenauncer.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.xiler.bungeenauncer.Main;
import net.xiler.bungeenauncer.utils.chat;

public class CustomCommandAnnouncer extends Command {

    private final Main plugin;
    private final String prefix;

    public CustomCommandAnnouncer(Main plugin, String call, String prefix, String permission) {  // String[] aliases
        super(call, permission);
        this.plugin = plugin;
        this.prefix = prefix;
//        for (String str: aliases) plugin.getLogger().info(str);
    }

    @Override
    public void execute(CommandSender sender, String[] str) {
        if (str == null || str.length == 0) sender.sendMessage(chat.stringConfig(this.plugin.getConfig(), "missing"));
        else sender.sendMessage(chat.stringColor(this.prefix + String.join(" ", str)));

        return;
    }
}