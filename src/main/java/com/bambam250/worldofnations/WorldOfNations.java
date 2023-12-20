package com.bambam250.worldofnations;

import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.bambam250.worldofnations.commands.CmdCity;
import com.bambam250.worldofnations.commands.CmdNation;
import com.bambam250.worldofnations.commands.TabCompleteCity;
import com.bambam250.worldofnations.commands.TabCompleteNation;
import com.bambam250.worldofnations.database.Database;
import com.bambam250.worldofnations.listeners.PlayerJoin;
import com.bambam250.worldofnations.objects.City;
import com.bambam250.worldofnations.objects.Nation;
import com.bambam250.worldofnations.objects.NationList;

public final class WorldOfNations extends JavaPlugin {
    
    public Database db;
    public NationList nations;
    public ArrayList<City> cities;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(Util.consolePrefix + "Starting Plugin");

        // Event listeners
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);

        // Command executors
        this.getCommand("nation").setExecutor(new CmdNation(this));
        this.getCommand("city").setExecutor(new CmdCity(this));
        this.getCommand("nation").setTabCompleter(new TabCompleteNation());
        this.getCommand("city").setTabCompleter(new TabCompleteCity());

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
        nations = new NationList(db.loadNations());
        cities = db.loadCities();
        
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
        nations = new NationList(db.loadNations());
        cities = db.loadCities();
    }
}
