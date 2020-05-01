package net.xiler.bungeenauncer.utils;

import net.md_5.bungee.api.ChatColor;

import javax.security.auth.login.Configuration;

public class chat {
    public static String[] listColor(String[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = ChatColor.translateAlternateColorCodes('&', data[i]);
        }
        return data;
    }

    public static String[] listFormat(String[] data) {
        data = listColor(data);
        for (int i = 0; i < data.length; i++) {
            data[i] = ChatColor.translateAlternateColorCodes('&', data[i]);
        }
        return data;
    }

    public static String[] listConfig(Configuration cfg, String msg) {
        String[] data = new String[] {"test"};
        for (int i = 0; i < data.length; i++) {
            data[i] = ChatColor.translateAlternateColorCodes('&', data[i]);
        }
        return data;
    }

    public static String stringColor(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String stringConfig(net.md_5.bungee.config.Configuration cfg, String path) {
        return ChatColor.translateAlternateColorCodes('&',
                cfg.getString("prefix") + cfg.getString(path));
    }
}
