package me.itzmarcus.duelplus;

import me.itzmarcus.duelplus.commands.CommandClass;
import me.itzmarcus.duelplus.events.DeathEvent;
import me.itzmarcus.duelplus.events.FreezedEvent;
import me.itzmarcus.duelplus.files.MyConfig;
import me.itzmarcus.duelplus.files.MyConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Lavet af ItzMarcus!
 */
public class Core extends JavaPlugin implements Listener {

    private void command(String command, CommandExecutor commandExecutor) {
        getCommand(command).setExecutor(commandExecutor);
    }

    private void listener(Listener... listener) {
        for(Listener l : listener) {
            Bukkit.getServer().getPluginManager().registerEvents(l, this);
        }
    }

    public static MyConfigManager manager;
    public static MyConfig spawnPoints;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        manager = new MyConfigManager(this);
        spawnPoints = manager.getNewConfig("spawns.yml");
        command("duel", new CommandClass());
        listener(new FreezedEvent(), new DeathEvent());
    }

    public void onDisable() {

    }
}
