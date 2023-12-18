package com.bambam250.worldofnations.objects;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Nation {
    UUID uuid;
    String name;
    ItemStack flag;
    City capital;
    UUID owner;

    ArrayList<City> cities;
    ArrayList<UUID> citizens;
    ArrayList<UUID> executives;

    
    /**
     * Nation constructor to be used by database class; initialize each parameter from data from the database
     * @param uuid Nation uuid
     * @param name Name string
     * @param flagStr Formatted flag itemstack string
     * @param capital Capital City uuid
     * @param owner 
     */
    public Nation(UUID uuid, String name, String flagStr, UUID capital, UUID owner) {
        this.uuid = uuid;
        this.name = name;
        this.owner = owner;
        // this.capital
        this.flag = parseFlagStr(flagStr);
        // this.cities
    }

    public Nation(String name, UUID owner) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.owner = owner;
        this.capital = null;
        this.flag = new ItemStack(Material.WHITE_BANNER);
    }


    /**
     * Parse a string into a banner itemstack
     * @param flagStr Formatted flag itemstack string
     * @return Banner itemstack
     */
    public ItemStack parseFlagStr(String flagStr) {
        
        
        return null;
    }


    public UUID getUuid() {
        return uuid;
    }


    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public ItemStack getFlag() {
        return flag;
    }


    public void setFlag(ItemStack flag) {
        this.flag = flag;
    }

    public String getFlagStr() {
        return flag.toString();
    }

    public void setFlagStr(String flagStr) {
        this.flag = parseFlagStr(flagStr);
    }


    public City getCapital() {
        return capital;
    }

    public UUID getCapitalUuid() {
        if (capital == null) return null;
        return capital.getUuid();
    }


    public void setCapital(City capital) {
        this.capital = capital;
    }


    public UUID getOwner() {
        return owner;
    }


    public void setOwner(UUID owner) {
        this.owner = owner;
    }


    public ArrayList<City> getCities() {
        return cities;
    }


    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public void addCity(City city) {
        this.cities.add(city);
    }


    public ArrayList<UUID> getCitizens() {
        return citizens;
    }


    public void setCitizens(ArrayList<UUID> citizens) {
        this.citizens = citizens;
    }

    public void addCitizen(UUID citizen) {
        this.citizens.add(citizen);
    }


    public ArrayList<UUID> getExecutives() {
        return executives;
    }


    public void setExecutives(ArrayList<UUID> executives) {
        this.executives = executives;
    }

    public void addExecutive(UUID executive) {
        this.executives.add(executive);
    }

    
}
