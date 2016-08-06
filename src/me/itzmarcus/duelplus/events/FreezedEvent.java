package me.itzmarcus.duelplus.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Lavet af ItzMarcus!
 */
public class FreezedEvent implements Listener {

    public static ArrayList<String> freezed = new ArrayList<>();

    @EventHandler
    public void move(PlayerMoveEvent e) {
        if(freezed.contains(e.getPlayer().getName())) {
            Location to = e.getTo();
            Location from = e.getFrom();
            e.getPlayer().teleport(from);
            e.setCancelled(true);
        }
    }
}
