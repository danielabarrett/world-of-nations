package com.bambam250.worldofnations;

import java.util.UUID;

import net.md_5.bungee.api.ChatColor;

public class Util {
    public static String consolePrefix = ChatColor.GRAY + "[" + ChatColor.GOLD + "WoN" + ChatColor.GRAY + "] " + ChatColor.WHITE;

    public static UUID stou(String uuidStr) {
        if (uuidStr.isEmpty() || uuidStr.isBlank() || uuidStr == null || uuidStr.equals("null")) return null;
        return UUID.fromString(uuidStr);
    }
}
