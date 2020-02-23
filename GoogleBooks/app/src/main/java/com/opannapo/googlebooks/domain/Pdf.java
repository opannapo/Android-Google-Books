package com.opannapo.googlebooks.domain;

/**
 * Created by napouser on 23,February,2020
 */
public class Pdf
{
    private String isAvailable;

    public String getIsAvailable ()
    {
        return isAvailable;
    }

    public void setIsAvailable (String isAvailable)
    {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [isAvailable = "+isAvailable+"]";
    }
}