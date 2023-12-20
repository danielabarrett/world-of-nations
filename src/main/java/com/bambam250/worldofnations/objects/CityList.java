package com.bambam250.worldofnations.objects;

import java.util.ArrayList;

public class CityList extends ArrayList<Nation> {
    
    ArrayList<City> cities;

    public CityList(ArrayList<City> cities) {
        this.cities = cities;
    }

    public City getCity(String name) {
        for (City c : cities) {
            if (c.getName().equalsIgnoreCase(name)) return c;
        }
        return null;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setNations(ArrayList<City> cities) {
        this.cities = cities;
    }
    
}
