package com.opannapo.googlebooks.domain;

/**
 * Created by napouser on 23,February,2020
 */
public class SaleInfo
{
    private String country;

    private String isEbook;

    private String saleability;

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getIsEbook ()
    {
        return isEbook;
    }

    public void setIsEbook (String isEbook)
    {
        this.isEbook = isEbook;
    }

    public String getSaleability ()
    {
        return saleability;
    }

    public void setSaleability (String saleability)
    {
        this.saleability = saleability;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [country = "+country+", isEbook = "+isEbook+", saleability = "+saleability+"]";
    }
}