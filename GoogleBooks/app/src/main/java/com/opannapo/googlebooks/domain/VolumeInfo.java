package com.opannapo.googlebooks.domain;

/**
 * Created by napouser on 23,February,2020
 */
public class VolumeInfo {
    private IndustryIdentifiers[] industryIdentifiers;

    private String pageCount;

    private String printType;

    private ReadingModes readingModes;

    private String previewLink;

    private String canonicalVolumeLink;

    private String language;

    private String title;

    private ImageLinks imageLinks;

    private String subtitle;

    private float averageRating;

    private String publisher;

    private String ratingsCount;

    private String publishedDate;

    private String[] categories;

    private String maturityRating;

    private String allowAnonLogging;

    private String contentVersion;

    private String[] authors;

    private String infoLink;

    public IndustryIdentifiers[] getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(IndustryIdentifiers[] industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public ReadingModes getReadingModes() {
        return readingModes;
    }

    public void setReadingModes(ReadingModes readingModes) {
        this.readingModes = readingModes;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getCanonicalVolumeLink() {
        return canonicalVolumeLink;
    }

    public void setCanonicalVolumeLink(String canonicalVolumeLink) {
        this.canonicalVolumeLink = canonicalVolumeLink;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(String ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String getMaturityRating() {
        return maturityRating;
    }

    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    public String getAllowAnonLogging() {
        return allowAnonLogging;
    }

    public void setAllowAnonLogging(String allowAnonLogging) {
        this.allowAnonLogging = allowAnonLogging;
    }

    public String getContentVersion() {
        return contentVersion;
    }

    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    @Override
    public String toString() {
        return "ClassPojo [industryIdentifiers = " + industryIdentifiers + ", pageCount = " + pageCount + ", printType = " + printType + ", readingModes = " + readingModes + ", previewLink = " + previewLink + ", canonicalVolumeLink = " + canonicalVolumeLink + ", language = " + language + ", title = " + title + ", imageLinks = " + imageLinks + ", subtitle = " + subtitle + ", averageRating = " + averageRating + ", publisher = " + publisher + ", ratingsCount = " + ratingsCount + ", publishedDate = " + publishedDate + ", categories = " + categories + ", maturityRating = " + maturityRating + ", allowAnonLogging = " + allowAnonLogging + ", contentVersion = " + contentVersion + ", authors = " + authors + ", infoLink = " + infoLink + "]";
    }
}