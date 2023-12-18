package com.bambam250.worldofnations;

import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.bambam250.worldofnations.commands.CmdNation;
import com.bambam250.worldofnations.database.Database;
import com.bambam250.worldofnations.listeners.PlayerJoin;
import com.bambam250.worldofnations.objects.City;
import com.bambam250.worldofnations.objects.Nation;

public final class WorldOfNations extends JavaPlugin {
    
    public Database db;
    public ArrayList<Nation> nations;
    public ArrayList<City> cities;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(Util.consolePrefix + "Starting Plugin");

        // Event listeners
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);

        // Command executors
        this.getCommand("nation").setExecutor(new CmdNation(this));

        // Database setup
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }

            db = new Database(getDataFolder().getAbsolutePath()+ "/nations.db", this);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Failed to connect to the database! " + ex.getMessage());
            Bukkit.getPluginManager().disablePlugin(this);
        }

        // Nation and city initialization
        nations = db.getAllNations();
        // cities = db.getAllCities();
        
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        try {
            db.closeConn();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        Bukkit.getConsoleSender().sendMessage(Util.consolePrefix + "Stopping Plugin");
    }

    public void updateData() {
        nations = db.getAllNations();
        // cities = db.getAllCities();
    }
}
