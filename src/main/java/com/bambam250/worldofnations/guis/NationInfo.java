package com.bambam250.worldofnations.guis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.bambam250.worldofnations.objects.Nation;

import net.kyori.adventure.text.Component;

public class NationInfo {
    public static Inventory openNationInfo(Nation nation, Player player) {
        Inventory inv = Bukkit.createInventory(null, 18, Component.text(nation.getName()));
        inv.setItem(0, nation.getFlag());
        

        return inv;
    }
}
