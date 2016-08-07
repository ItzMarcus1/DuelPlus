package me.itzmarcus.duelplus.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

/**
 * Lavet af ItzMarcus!
 */
public class FreezedEvent implements Listener {

    public static ArrayList<String> freezed = new ArrayList<>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void move(PlayerMoveEvent e) {
        if(freezed.contains(e.getPlayer().getName())) {
            e.setTo(e.getFrom());
        }
    }
}
