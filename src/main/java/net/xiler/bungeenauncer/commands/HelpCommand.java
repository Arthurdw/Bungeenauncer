package net.xiler.bungeenauncer.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.xiler.bungeenauncer.Main;
import net.xiler.bungeenauncer.utils.chat;

public class HelpCommand extends Command {

    private final Main plugin;
    private final String[][] menu;

    public HelpCommand(Main plugin) {
        super("bn");
        this.plugin = plugin;
        this.menu = new String[][] {
                {
                        "&8--- &7+ &8--- &aBungeenauncer &7&o(&f&ov" + plugin.getDesc().getVersion() + "&7&o) &8--- &7+ &8---",
                        " &ebn&7 - &6Bungeenauncer help command!",
                        "                &8--- &7Page 1 &8---"
                },
//                {
//                        "&8--- &7+ &8--- &aBungeenauncer &7&o(&f&ov" + plugin.getDesc().getVersion() + "&7&o) &8--- &7+ &8---",
//                        "&ebn&7 - &6Bungeenauncer help command!",
//                        "&8--- &7Page 2 &8---"
//                }
        };
    }

    @Override
    public void execute(CommandSender sender, String[] str) {
        try {
            for (String msg : chat.listColor(this.menu[Integer.parseInt(str[0])])) {
                sender.sendMessage(msg);
            }
        } catch (Exception e) {
            for (String msg : chat.listColor(this.menu[0])) {
                sender.sendMessage(msg);
            }
        }
    }
}
