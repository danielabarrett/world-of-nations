package com.bambam250.worldofnations.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import com.bambam250.worldofnations.Util;
import com.bambam250.worldofnations.WorldOfNations;
import com.bambam250.worldofnations.objects.Nation;

public class CmdNation implements CommandExecutor {

    private WorldOfNations plugin;
    public CmdNation(WorldOfNations pl) {
        this.plugin = pl;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        switch (args[0].toLowerCase()) {
            case "create":
                return cmdCreate(sender, args);
            case "info":
                return cmdInfo(sender, args);
            default:
                return false;
        }
    }

    public boolean cmdCreate(CommandSender sender, String[] args) {
        if (args.length != 2) return false;
        
        String name = args[1];
        UUID owner = ((Player) sender).getUniqueId();
        Nation nation = new Nation(name, owner);
        plugin.db.addNation(nation);
        Bukkit.getConsoleSender().sendMessage(Util.consolePrefix + "Craeted new nation " + nation.getName());
        return true;
    }

    public boolean cmdInfo(CommandSender sender, String[] args) {
        if (args.length == 1) {
            for (Nation n : plugin.nations) {
                sender.sendMessage(n.getName() + ", owner=" + Bukkit.getOfflinePlayer(n.getOwner()));
            }
        } else if (args.length == 2) {

        }
        
        return false;
    }
    
}
