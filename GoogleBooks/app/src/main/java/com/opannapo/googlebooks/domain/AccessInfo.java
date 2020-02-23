package com.opannapo.googlebooks.domain;

/**
 * Created by napouser on 23,February,2020
 */
public class AccessInfo
{
    private String accessViewStatus;

    private String country;

    private String viewability;

    private Pdf pdf;

    private String webReaderLink;

    private Epub epub;

    private String publicDomain;

    private String quoteSharingAllowed;

    private String embeddable;

    private String textToSpeechPermission;

    public String getAccessViewStatus ()
    {
        return accessViewStatus;
    }

    public void setAccessViewStatus (String accessViewStatus)
    {
        this.accessViewStatus = accessViewStatus;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getViewability ()
    {
        return viewability;
    }

    public void setViewability (String viewability)
    {
        this.viewability = viewability;
    }

    public Pdf getPdf ()
    {
        return pdf;
    }

    public void setPdf (Pdf pdf)
    {
        this.pdf = pdf;
    }

    public String getWebReaderLink ()
    {
        return webReaderLink;
    }

    public void setWebReaderLink (String webReaderLink)
    {
        this.webReaderLink = webReaderLink;
    }

    public Epub getEpub ()
    {
        return epub;
    }

    public void setEpub (Epub epub)
    {
        this.epub = epub;
    }

    public String getPublicDomain ()
    {
        return publicDomain;
    }

    public void setPublicDomain (String publicDomain)
    {
        this.publicDomain = publicDomain;
    }

    public String getQuoteSharingAllowed ()
    {
        return quoteSharingAllowed;
    }

    public void setQuoteSharingAllowed (String quoteSharingAllowed)
    {
        this.quoteSharingAllowed = quoteSharingAllowed;
    }

    public String getEmbeddable ()
    {
        return embeddable;
    }

    public void setEmbeddable (String embeddable)
    {
        this.embeddable = embeddable;
    }

    public String getTextToSpeechPermission ()
    {
        return textToSpeechPermission;
    }

    public void setTextToSpeechPermission (String textToSpeechPermission)
    {
        this.textToSpeechPermission = textToSpeechPermission;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [accessViewStatus = "+accessViewStatus+", country = "+country+", viewability = "+viewability+", pdf = "+pdf+", webReaderLink = "+webReaderLink+", epub = "+epub+", publicDomain = "+publicDomain+", quoteSharingAllowed = "+quoteSharingAllowed+", embeddable = "+embeddable+", textToSpeechPermission = "+textToSpeechPermission+"]";
    }
}