package me.itzmarcus.duelplus.utils;

import me.itzmarcus.duelplus.Core;
import me.itzmarcus.duelplus.events.FreezedEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Lavet af ItzMarcus!
 */
public class GameManager {

    public static HashMap<String, Boolean> isPlaying = new HashMap<>();
    public static HashMap<Player, Player> duelRequest = new HashMap<>();
    public static HashMap<Player, Inventory> inv = new HashMap<>();


    public static void setIsPlaying(Player player, Boolean status) {
        isPlaying.put(player.getName(), status);
    }

    public static boolean isPlayerPlaying(String player) {
        if(isPlaying.containsKey(player) && isPlaying.get(player)) {
            return true;
        } else {
            return false;
        }
    }

    public static void sendInvitation(Player sender, Player receiver) {
        if(sender == receiver) {
            sender.sendMessage(ChatUtilities.prefix + "§cYou cannot invite yourself.");
            return;
        }
        duelRequest.put(sender, receiver);
        sender.sendMessage(ChatUtilities.prefix + "§aYou have invited " + receiver.getName() + " to a duel.");
        receiver.sendMessage(ChatUtilities.prefix + "§a" + sender.getName() + " has invited you to a duel.");
    }

    public static boolean hasPendingInvite(Player player) {
        if(duelRequest.containsKey(player)) {
            return true;
        } else {
            return false;
        }
    }

    public static void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void teleportPlayers(Player player) {
        if(hasPendingInvite(player)) {
            Player playerone = player;
            Player playertwo = duelRequest.get(player);

            FreezedEvent.freezed.add(playerone.getName());
            FreezedEvent.freezed.add(playertwo.getName());

            playerone.sendMessage(ChatUtilities.prefix + "§aYou accepted " + playertwo.getName() + "'s duel request " +
                    "and will be teleport soon.");
            playertwo.sendMessage(ChatUtilities.prefix + "§a" + playerone.getName() + " accepted your duel request.");
            playerone.playSound(playerone.getLocation(), Sound.NOTE_BASS, 1, 2);
            playertwo.playSound(playertwo.getLocation(), Sound.NOTE_BASS, 1, 2);

            // Denne "part" bliver gjort mere overskuelig med et loop eller ligende.
            delay(1000);
            playerone.sendMessage(ChatUtilities.prefix + "§bYou will be teleported in 10 seconds.");
            playertwo.sendMessage(ChatUtilities.prefix + "§bYou will be teleported in 10 seconds.");
            playerone.playSound(playerone.getLocation(), Sound.NOTE_BASS, 1, 2);
            playertwo.playSound(playertwo.getLocation(), Sound.NOTE_BASS, 1, 2);

            delay(1000);
            playerone.sendMessage(ChatUtilities.prefix + "§bYou will be teleported in 5 seconds.");
            playertwo.sendMessage(ChatUtilities.prefix + "§bYou will be teleported in 5 seconds.");
            playerone.playSound(playerone.getLocation(), Sound.NOTE_BASS, 1, 2);
            playertwo.playSound(playertwo.getLocation(), Sound.NOTE_BASS, 1, 2);

            delay(1000);
            playerone.sendMessage(ChatUtilities.prefix + "§bYou will be teleported in 4 seconds.");
            playertwo.sendMessage(ChatUtilities.prefix + "§bYou will be teleported in 4 seconds.");
            playerone.playSound(playerone.getLocation(), Sound.NOTE_BASS, 1, 2);
            playertwo.playSound(playertwo.getLocation(), Sound.NOTE_BASS, 1, 2);

            delay(1000);
            playerone.sendMessage(ChatUtilities.prefix + "§bYou will be teleported in 3 seconds.");
            playertwo.sendMessage(ChatUtilities.prefix + "§bYou will be teleported in 3 seconds.");
            playerone.playSound(playerone.getLocation(), Sound.NOTE_BASS, 1, 2);
            playertwo.playSound(playertwo.getLocation(), Sound.NOTE_BASS, 1, 2);

            delay(1000);
            playerone.sendMessage(ChatUtilities.prefix + "§bYou will be teleported in 2 seconds.");
            playertwo.sendMessage(ChatUtilities.prefix + "§bYou will be teleported in 2 seconds.");
            playerone.playSound(playerone.getLocation(), Sound.NOTE_BASS, 1, 2);
            playertwo.playSound(playertwo.getLocation(), Sound.NOTE_BASS, 1, 2);

            delay(1000);
            playerone.sendMessage(ChatUtilities.prefix + "§bYou will be teleported in 1 seconds.");
            playertwo.sendMessage(ChatUtilities.prefix + "§bYou will be teleported in 1 seconds.");
            playerone.playSound(playerone.getLocation(), Sound.NOTE_BASS, 1, 2);
            playertwo.playSound(playertwo.getLocation(), Sound.NOTE_BASS, 1, 2);
            delay(1000);

            playerone.playSound(playerone.getLocation(), Sound.NOTE_BASS, 1, 2);
            playertwo.playSound(playertwo.getLocation(), Sound.NOTE_BASS, 1, 2);

            String dataString = Core.spawnPoints.getString("1");
            String dataStringTwo = Core.spawnPoints.getString("2");

            String[] data = dataString.split(":");
            String[] dataTwo = dataStringTwo.split(":");

            World worldOne = Bukkit.getWorld(data[0]);
            World worldTwo = Bukkit.getWorld(dataTwo[0]);

            double xOne = Double.valueOf(data[1]);
            double yOne = Double.valueOf(data[2]);
            double zOne = Double.valueOf(data[3]);
            double xTwo = Double.valueOf(dataTwo[1]);
            double yTwo = Double.valueOf(dataTwo[2]);
            double zTwo = Double.valueOf(dataTwo[3]);

            float pitchOne = Float.valueOf(data[4]);
            float yawOne = Float.valueOf(data[5]);
            float pitchTwo = Float.valueOf(dataTwo[4]);
            float yawTwo = Float.valueOf(dataTwo[5]);

            Location locationForPlayerOne = new Location(worldOne, xOne, yOne, zOne, yawOne, pitchOne);
            Location locationForPlayerTwo = new Location(worldTwo, xTwo, yTwo, zTwo, yawTwo, pitchTwo);

            playerone.teleport(locationForPlayerOne);
            playertwo.teleport(locationForPlayerTwo);

            playerone.sendMessage(ChatUtilities.prefix + "§bThe Duel will start in 5 seconds.");
            playertwo.sendMessage(ChatUtilities.prefix + "§bThe Duel will start in 5 seconds.");

            playerone.playSound(playerone.getLocation(), Sound.NOTE_BASS, 1, 2);
            playertwo.playSound(playertwo.getLocation(), Sound.NOTE_BASS, 1, 2);

            delay(5000);
            FreezedEvent.freezed.remove(playerone.getName());
            FreezedEvent.freezed.remove(playertwo.getName());
        }
    }
}
