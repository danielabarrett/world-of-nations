package com.bambam250.worldofnations.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;

import com.bambam250.worldofnations.Util;
import com.bambam250.worldofnations.WorldOfNations;
import com.bambam250.worldofnations.objects.City;
import com.bambam250.worldofnations.objects.Nation;

import net.md_5.bungee.api.ChatColor;

public class Database {
    private final Connection conn;
    private WorldOfNations plugin;
    
    public Database(String path, WorldOfNations plugin)  throws SQLException {
        this.plugin = plugin;
        conn = DriverManager.getConnection("jdbc:sqLite:" + path);

        try (Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS players (
                    uuid VARCHAR(127) PRIMARY KEY, 
                    username VARCHAR(255) NOT NULL
                )
            """);
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS nations (
                    uuid VARCHAR(127) PRIMARY KEY,
                    name VARCHAR(255) NOT NULL,
                    flag VARBINARY(65535),
                    capital VARCHAR(127),
                    owner VARCHAR(127)
                )
            """);
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS cities (
                    uuid VARCHAR(127) PRIMARY KEY,
                    name VARCHAR(255) NOT NULL
                )
            """);
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS city_player_join (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    city_uuid VARCHAR(127) REFERENCES city(uuid),
                    player_uuid VARCHAR(127) REFERENCES player(uuid),
                    role VARCHAR(127)
                )
            """);
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS nation_player_join (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nation_uuid VARCHAR(127) REFERENCES nation(uuid),
                    player_uuid VARCHAR(127) REFERENCES player(uuid),
                    role VARCHAR(127)
                )
            """);
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS nation_city_join (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nation_uuid VARCHAR(127) REFERENCES nation(uuid),
                    city_uuid VARCHAR(127) REFERENCES city(uuid)
                )
            """);
        }
    }

    public void closeConn() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    /**
     * Get a list of uuids of players that have joined the server from the database
     * @return ArrayList of UUIDs of players
     */
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


    /**
     * Compare a new player to the database of previously joined players. 
     * <br><br>
     * Called by the onPlayerJoin listener.
     * @param uuid uuid of the player that joined
     * @param username username of the player that joined
     */
    public void checkJoinedPlayer(UUID uuid, String username) {
        boolean newPlayer = true;
        if (getPlayerIds().contains(uuid)) {
            if (getPlayerName(uuid).matches(username)) {
                return;
            } else {
                newPlayer = false;
            }
        }
        Bukkit.getConsoleSender().sendMessage("NEW PLAYER JOINED");
        try (Statement stmt = conn.createStatement()) {
            if (newPlayer) {
                stmt.execute("""
                    INSERT INTO players VALUES ('%s', '%s', 0)
                """.formatted(uuid.toString(), username)
                );
            } else {
                stmt.execute("""
                    UPDATE players SET username = '%s' WHERE uuid = '%s'
                """.formatted(username, uuid.toString())
                );            
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Query the database to get a player's username.
     * @param uuid uuid of the player.
     * @return Player's username, or empty string if not found.
     */
    public String getPlayerName(UUID uuid) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("""
                SELECT username FROM players WHERE uuid = '%s'
            """.formatted(uuid.toString()));
            rs.next();
            String rString = rs.getString(1);
            Bukkit.getConsoleSender().sendMessage("Player name returned: " + rString);
            return rString;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }


    /**
     * Add nation to the database
     * @param nation Nation to be added
     */
    public void addNation(Nation nation) {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("""
                INSERT INTO nations (uuid, name, flag, capital, owner) VALUES ('%s', '%s', '%s', '%s', '%s')  
            """.formatted(nation.getUuid(), nation.getName(), nation.getFlagBytes(), nation.getCapitalUuid(), nation.getOwner()));
            plugin.updateData();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Create a nation object using the database
     * @param uuid Nation uuid
     * @return nation matching uuid or null if there's an error
     */
    public Nation getNation(UUID uuid) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("""
                SELECT * FROM nations WHERE uuid = '%s'
            """.formatted(uuid.toString()));
            rs.next();
            return new Nation(uuid, rs.getString(2), rs.getBytes(3), Util.stou(rs.getString(4)), Util.stou(rs.getString(5)));
        } catch (SQLException ex) {
            printErr("Error loading nation " + uuid + ": " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Create a city object using the database
     * @param uuid City uuid
     * @return city matching uuid or null if there's an error
     */
    public City getCity(UUID uuid) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("""
                SELECT * FROM cities WHERE uuid = '%s'
            """.formatted(uuid.toString()));
            rs.next();
            return new City(uuid, rs.getString(2), Util.stou(rs.getString(3)), Util.stou(rs.getString(4)));
        } catch (SQLException ex) {
            printErr("Error loading city " + uuid + ": " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    

    /**
     * Employs getNation function on all nations in the database
     * @return ArrayList of all nations in the database or null if there's an error
     */
    public ArrayList<Nation> loadNations() {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("""
                SELECT uuid FROM NATIONS
            """);
            ArrayList<Nation> nlist = new ArrayList<>();
            while (rs.next()) {
                nlist.add(getNation(Util.stou(rs.getString(1))));
            }
            return nlist;
        } catch (SQLException ex) {
            printErr("Error loading nations: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<City> loadCities() {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("""
                SELECT uuid FROM cities
            """);
            ArrayList<City> cList = new ArrayList<>();
            while (rs.next()) {
                cList.add(getCity(Util.stou(rs.getString(1))));
            }
        } catch (SQLException ex) {
            printErr("Error loading cities: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    



    private void printErr(String string) {
        Bukkit.getConsoleSender().sendMessage(Util.consolePrefix + ChatColor.RED + string);
    }
}
