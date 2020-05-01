package net.xiler.bungeenauncer;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginDescription;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.xiler.bungeenauncer.commands.CustomCommandAnnouncer;
import net.xiler.bungeenauncer.commands.HelpCommand;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Main extends Plugin {

    private Configuration config;
    private PluginDescription desc;

    @Override
    public void onEnable() {
        desc = this.getDescription();
        getLogger().info("Initializing Bungeenauncer! (v" + desc.getVersion() + ")");
        if (!getDataFolder().exists())
            getDataFolder().mkdir();

        File file = new File(getDataFolder(), "config.yml");


        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        getProxy().getPluginManager().registerCommand(this, new HelpCommand(this));
        String[] customCommands = new String[getConfig().getList("announcers").size()];
        int count = 0;
        for (Object cmd: getConfig().getList("announcers")) {
            LinkedHashMap data = (LinkedHashMap) ((LinkedHashMap) cmd).get(((LinkedHashMap) cmd).keySet().toArray()[0]);
            ArrayList aliases = (ArrayList) data.get("aliases");
            getProxy().getPluginManager().registerCommand(this, new CustomCommandAnnouncer(this,
                    java.lang.String.valueOf(data.get("call")), java.lang.String.valueOf(data.get("prefix")),
                    java.lang.String.valueOf(data.get("permission")))); // (java.lang.String[]) (aliases.toArray(new String[aliases.size()]))
            customCommands[count] = java.lang.String.valueOf(data.get("call"));
            count++;
        }
        getLogger().info("Loaded the following command(s):");
        getLogger().info(String.join(", ", customCommands));
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling Bungeenauncer! (v" + desc.getVersion() + ")");
    }

    public Configuration getConfig() {
        return this.config;
    }

    public PluginDescription getDesc() {
        return this.desc;
    }
}

