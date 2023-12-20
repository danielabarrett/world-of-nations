package com.bambam250.worldofnations.config;

import java.util.EnumMap;

public class NationOptionsContainer {
    EnumMap<NationOption, Object> menu = new EnumMap<NationOption, Object>(NationOption.class);

    public void put(NationOption option, Object value) {
        menu.put(option, value);
    }

    public Object getValue(NationOption option) {
        if (menu.containsKey(option)) {
            return menu.get(option);
        }
        return null;
    }

    public Integer getInt(NationOption option) {
        if (menu.containsKey(option)) {
            return (Integer) menu.get(option);
        }
        return null;
    }

    public Boolean getBoolean(NationOption option) {
        if (menu.containsKey(option)) {
            return (Boolean) menu.get(option);
        }
        return null;
    }

    public String getString(NationOption option) {
        if (menu.containsKey(option)) {
            return (String) menu.get(option);
        }
        return null;
    }
}
