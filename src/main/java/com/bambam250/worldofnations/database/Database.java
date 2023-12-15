package com.bambam250.worldofnations.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;

public class Database {
    private final Connection conn;
    
    public Database(String path)  throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqLite:" + path);

        try (Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS players (
                uuid TEXT PRIMARY KEY, 
                username TEXT NOT NULL, 
                points INTEGER NOT NULL DEFAULT 0)    
            """);
        }
    }

    public void closeConn() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    public ArrayList<UUID> getPlayerIds() {
        ArrayList<UUID> ids = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("""
                SELECT uuid FROM players
                """);
            while (rs.next()) {
                ids.add(UUID.fromString(rs.getString(1)));
                Bukkit.getConsoleSender().sendMessage("added new uuid: " + rs.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ids;
    }

    public void addPlayer(UUID uuid, String username) {
        if (getPlayerIds().contains(uuid)) return;
        Bukkit.getConsoleSender().sendMessage("NEW PLAYER JOINED");
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("""
                INSERT INTO players VALUES ('%s', '%s', 0)
                """.formatted(uuid.toString(), username));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
