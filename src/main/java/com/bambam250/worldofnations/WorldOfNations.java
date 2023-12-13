package com.bambam250.worldofnations;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public final class World_of_nations extends JavaPlugin {
    public static String consolePrefix = ChatColor.GRAY + "[" + ChatColor.GOLD + "WoN" + ChatColor.GRAY + "]" + ChatColor.WHITE;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(consolePrefix + "Starting Plugin");
        
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage(consolePrefix + "Stopping Plugin");
    }
}
