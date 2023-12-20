package com.bambam250.worldofnations.objects;

import java.util.UUID;

public class City {
    UUID uuid;
    String name;
    Nation nation;
    
    public City(UUID uuid, String name, UUID nation, UUID owner) {

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

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    
}
