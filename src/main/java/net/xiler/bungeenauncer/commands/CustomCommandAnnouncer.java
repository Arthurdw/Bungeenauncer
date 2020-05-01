package net.xiler.bungeenauncer.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.xiler.bungeenauncer.Main;
import net.xiler.bungeenauncer.utils.chat;

import java.util.Collection;

public class CustomCommandAnnouncer extends Command {

    private final Main plugin;
    private final String prefix;
    private final String see;

    public CustomCommandAnnouncer(Main plugin, String call, String prefix, String permission, String see) {  // String[] aliases
        super(call, permission);
        this.plugin = plugin;
        this.prefix = prefix;
        this.see = see;
    }

    @Override
    public void execute(CommandSender sender, String[] str) {
        if (str == null || str.length == 0) sender.sendMessage(chat.stringConfig(this.plugin.getConfig(), "missing"));
        else {
            Collection<ProxiedPlayer> members = plugin.getProxy().getPlayers();  // removeIf(player -> !(player.hasPermission(this.see)))
            for (ProxiedPlayer player : members)
                if (player.hasPermission(this.see)) {
                    player.sendMessage(chat.stringColor(this.prefix.replace("{member}", sender.getName()) + String.join(" ", str)));
                }
        }
        return;
    }
}