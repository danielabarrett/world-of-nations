package com.bambam250.worldofnations.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TabCompleteNation implements TabCompleter {

    public ArrayList<String> cmdList = new ArrayList<>();

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        
        if (cmdList.isEmpty()) {
            cmdList.add("create");
            cmdList.add("info");
            cmdList.add("list");
            cmdList.add("delete");
        }
        
        
        
        return Collections.singletonList(sender.getName());
    }
    
}
