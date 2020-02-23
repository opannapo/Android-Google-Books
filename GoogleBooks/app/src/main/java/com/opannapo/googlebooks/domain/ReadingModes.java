package com.opannapo.googlebooks.domain;

/**
 * Created by napouser on 23,February,2020
 */
public class ReadingModes
{
    private String image;

    private String text;

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [image = "+image+", text = "+text+"]";
    }
}