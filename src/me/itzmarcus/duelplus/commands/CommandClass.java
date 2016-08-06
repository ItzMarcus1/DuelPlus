package me.itzmarcus.duelplus.commands;

import me.itzmarcus.duelplus.Core;
import me.itzmarcus.duelplus.utils.ChatUtilities;
import me.itzmarcus.duelplus.utils.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

/**
 * Lavet af ItzMarcus!
 */
public class CommandClass implements CommandExecutor {

    JavaPlugin plugin = JavaPlugin.getPlugin(Core.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Players only.");
            return true;
        }
        if (command.getName().equalsIgnoreCase("duel")) {
            Player p = (Player) sender;
            switch (args.length) {
                case 0: p.sendMessage(ChatUtilities.prefix + "§cTo duel a player use /duel <player>");
                    return true;
                case 1:
                    if(args[0].equalsIgnoreCase("setspawn1")) {
                        Core.spawnPoints.set("1", p.getLocation().getWorld().getName() + ":" + p.getLocation().getX() + ":"
                                + p.getLocation().getY() + ":"
                                + p.getLocation().getZ() + ":"
                                + p.getLocation().getPitch() + ":"
                                + p.getLocation().getYaw());
                        Core.spawnPoints.saveConfig();
                        p.sendMessage(ChatUtilities.prefix + "§aYou have set the first spawn for the duel arena.");
                        return true;
                    } else if(args[0].equalsIgnoreCase("setspawn2")) {
                        Core.spawnPoints.set("2",
                                p.getLocation().getWorld().getName() + ":" +
                                p.getLocation().getX() + ":"
                                + p.getLocation().getY() + ":"
                                        + p.getLocation().getZ() + ":"
                                        + p.getLocation().getPitch() + ":"
                                        + p.getLocation().getYaw());
                        Core.spawnPoints.saveConfig();
                        p.sendMessage(ChatUtilities.prefix + "§aYou have set the second spawn for the duel arena.");
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("accept")) {
                        if(!GameManager.hasPendingInvite(p)) {
                            p.sendMessage(ChatUtilities.prefix + "§cYou do not have any pending requests.");
                            return true;
                        }
                        GameManager.teleportPlayers(p);
                        return true;
                    }
                    String player = args[0];
                    String sendingPlayer = p.getName();
                    if(Bukkit.getPlayer(player) == null) {
                        p.sendMessage(ChatUtilities.prefix + "§cInvalid Player.");
                        return true;
                    }
                    if(GameManager.isPlayerPlaying(sendingPlayer)) {
                        p.sendMessage(ChatUtilities.prefix + "§cYou cannot be in a duel, when sending a invitation.");
                        return true;
                    } else if(GameManager.isPlayerPlaying(player)) {
                        p.sendMessage(ChatUtilities.prefix + "§c" + player + " is already in a duel.");
                        return true;
                    } else {
                        GameManager.sendInvitation(p, Bukkit.getPlayer(player));
                        GameManager.setIsPlaying(p, true);
                        GameManager.setIsPlaying(Bukkit.getPlayer(player), true);
                        return true;
                    }
                    default: p.sendMessage("Error");
            }
        }
        return false;
    }
}