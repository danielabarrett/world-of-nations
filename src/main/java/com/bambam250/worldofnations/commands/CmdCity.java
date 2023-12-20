package com.bambam250.worldofnations.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import com.bambam250.worldofnations.WorldOfNations;

public class CmdCity implements CommandExecutor {

    private WorldOfNations plugin;
    public CmdCity(WorldOfNations pl) {
        this.plugin = pl;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        switch (args[0]) {
            case "create":
                return cmdCreate(sender, args);
            default:
                return false;
        }
    }
    
    public boolean cmdCreate(CommandSender sender, String[] args) {
        return false;
    }
}
