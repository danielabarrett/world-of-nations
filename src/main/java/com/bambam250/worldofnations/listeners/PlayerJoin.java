package com.bambam250.worldofnations.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.bambam250.worldofnations.WorldOfNations;

public class PlayerJoin implements Listener {
    private WorldOfNations plugin;
    public PlayerJoin(WorldOfNations pl) {
        this.plugin = pl;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) {
        Bukkit.getConsoleSender().sendMessage("Player joined; adding to register");
        plugin.db.checkJoinedPlayer(ev.getPlayer().getUniqueId(), ev.getPlayer().getName());
    }
    
}
