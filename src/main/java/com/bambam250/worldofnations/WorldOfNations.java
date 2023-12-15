package com.bambam250.worldofnations;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.bambam250.worldofnations.database.Database;
import com.bambam250.worldofnations.listeners.PlayerJoin;

import net.md_5.bungee.api.ChatColor;

public final class WorldOfNations extends JavaPlugin {
    public static String consolePrefix = ChatColor.GRAY + "[" + ChatColor.GOLD + "WoN" + ChatColor.GRAY + "] " + ChatColor.WHITE;
    // public static SQL sql = new SQL();
    public Database db;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(consolePrefix + "Starting Plugin");
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);

        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }

            db = new Database(getDataFolder().getAbsolutePath()+ "/nations.db");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Failed to connect to the database! " + ex.getMessage());
            Bukkit.getPluginManager().disablePlugin(this);
        }
        
        
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        try {
            db.closeConn();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        Bukkit.getConsoleSender().sendMessage(consolePrefix + "Stopping Plugin");
    }
}
