package com.opannapo.googlebooks.domain;

/**
 * Created by napouser on 23,February,2020
 */
public class IndustryIdentifiers {
    private String identifier;

    private String type;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ClassPojo [identifier = " + identifier + ", type = " + type + "]";
    }
}