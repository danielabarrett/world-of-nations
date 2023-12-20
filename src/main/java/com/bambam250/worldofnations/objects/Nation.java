package com.bambam250.worldofnations.objects;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.bambam250.worldofnations.config.NationOptionsContainer;

public class Nation {
    UUID uuid;
    String name;
    ItemStack flag;
    City capital;
    UUID owner;

    NationOptionsContainer options;

    ArrayList<City> cities;
    ArrayList<UUID> citizens;
    ArrayList<UUID> executives;

    
    /**
     * Nation constructor to be used by database class; initialize each parameter from data from the database
     * @param uuid Nation uuid
     * @param name Name string
     * @param flagBytes Formatted flag itemstack string
     * @param capital Capital City uuid
     * @param owner 
     */
    public Nation(UUID uuid, String name, byte[] flagBytes, UUID capital, UUID owner) {
        this.uuid = uuid;
        this.name = name;
        deserializeFlag(flagBytes);
        // this.capital
        this.owner = owner;
        
        // this.cities
    }


    /**
     * Nation constructor to be used when creating a new nation
     * @param name New nation name
     * @param owner New nation owner
     */
    public Nation(String name, UUID owner) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.owner = owner;
        this.capital = null;
        this.flag = new ItemStack(Material.WHITE_BANNER);
    }


    /**
     * Deserialize a string into a banner itemstack. Stores result in 'flag' object field
     * @param flagBytes Formatted flag itemstack byte array
     */
    public void deserializeFlag(byte[] flagBytes) {
        // String[] args = flagStr.split(";");
        // String[] patterns = args[2].split(",");
        // // Material
        // ItemStack flag = new ItemStack(Material.getMaterial(args[1]));
        // BannerMeta flagMeta = (BannerMeta) flag.getItemMeta();
        // flag.set
        // // Patterns
        // for (String pat : patterns) {
        //     String[] patsplit = pat.split("|");
        //     Pattern p = new Pattern(DyeColor.valueOf(patsplit[0]), PatternType.valueOf(patsplit[1]));
        //     flagMeta.addPattern(p);
        // }
        flag = ItemStack.deserializeBytes(flagBytes);

    }

    /**
     * Serialize the nation's flag into a byte array
     * <br><br>
    //  * Format: "<material>;<color1>|<pattern1>,<color2>|<pattern2>,...,<colorN>|<patternN>"
     * @param flagBytes Formatted flag itemstack string
     * @return Banner itemstack
     */
    public byte[] serializeFlag() {
        // String output = "";
        // output += flag.getType().toString() + ";";
        
        
        // return output;
        byte[] serial = flag.serializeAsBytes();
        return serial;
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

    public byte[] getFlagBytes() {
        return serializeFlag();
    }

    public void setFlag(byte[] flagBytes) {
        deserializeFlag(flagBytes);
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
