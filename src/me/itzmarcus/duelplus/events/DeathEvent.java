package me.itzmarcus.duelplus.events;

import me.itzmarcus.duelplus.utils.ChatUtilities;
import me.itzmarcus.duelplus.utils.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by marcus on 06-08-2016.
 */
public class DeathEvent implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player dead = e.getEntity().getPlayer();
        Player killer = e.getEntity().getKiller();
        if(GameManager.isPlayerPlaying(dead.getName())) {
            GameManager.setIsPlaying(dead, false);
        }
        if(GameManager.isPlayerPlaying(killer.getName())) {
            GameManager.setIsPlaying(killer, false);
        }
        Bukkit.getServer().broadcastMessage(ChatUtilities.prefix + "§3" + killer.getName() + " §cdefeated §3" + dead.getName() + " §ain a duel.");
        GameManager.teleportToLobby(dead, killer);
        // Add winning effects and a delay for the teleport.
    }
}
