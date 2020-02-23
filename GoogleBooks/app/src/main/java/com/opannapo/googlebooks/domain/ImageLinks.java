package com.opannapo.googlebooks.domain;

/**
 * Created by napouser on 23,February,2020
 */
public class ImageLinks
{
    private String thumbnail;

    private String smallThumbnail;

    public String getThumbnail ()
    {
        return thumbnail;
    }

    public void setThumbnail (String thumbnail)
    {
        this.thumbnail = thumbnail;
    }

    public String getSmallThumbnail ()
    {
        return smallThumbnail;
    }

    public void setSmallThumbnail (String smallThumbnail)
    {
        this.smallThumbnail = smallThumbnail;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [thumbnail = "+thumbnail+", smallThumbnail = "+smallThumbnail+"]";
    }
}