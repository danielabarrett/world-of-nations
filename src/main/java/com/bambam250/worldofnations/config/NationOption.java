package com.bambam250.worldofnations.config;

public enum NationOption {
    ALLOWDUALCITIZENSHIP (boolean.class, "Allow dual citizenship", "Determines if a player should be allowed to join this nation if they are already in another"),
    INVITEONLY (boolean.class, "Invite only", "Determines if a player should be able to join without an invite");

    private final Class<?> configType;
    private final String title;
    private final String description;

    NationOption(Class<?> configType, String title, String description) {
        this.configType = configType;
        this.title = title;
        this.description = description;
    }

    public Class<?> getConfigType() {
        return configType;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
