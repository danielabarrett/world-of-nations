package com.bambam250.worldofnations.objects;

import java.util.ArrayList;

public class NationList extends ArrayList<Nation> {
    
    ArrayList<Nation> nations;

    public NationList(ArrayList<Nation> nations) {
        this.nations = nations;
    }

    public Nation getNation(String name) {
        for (Nation n : nations) {
            if (n.getName().equalsIgnoreCase(name)) return n;
        }
        return null;
    }

    public ArrayList<Nation> getNations() {
        return nations;
    }

    public void setNations(ArrayList<Nation> nations) {
        this.nations = nations;
    }
    
}
