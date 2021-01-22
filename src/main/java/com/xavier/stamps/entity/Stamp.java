package com.xavier.stamps.entity;

public class Stamp {
    private String id;
    private String country;
    private String name;
    private String series;
    private String codes;
    private String themes;
    private String issuedOn;
    private String expiryDate;
    private String dimension;
    private String colors;
    private String emission;
    private String perforation;
    private String printing;
    private String paper;
    private String faceValue;
    private Long printRun;
    private String score;
    private String links;
    private byte[] img;
    private String imgLink;
    private String imgName;
    private String collectingDate;

    @Override
    public String toString() {
        return "Stamp{" +
                "id='" + id + '\'' +
                ", country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", series='" + series + '\'' +
                ", codes='" + codes + '\'' +
                ", themes='" + themes + '\'' +
                ", issuedOn='" + issuedOn + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", dimension='" + dimension + '\'' +
                ", colors='" + colors + '\'' +
                ", emission='" + emission + '\'' +
                ", perforation='" + perforation + '\'' +
                ", printing='" + printing + '\'' +
                ", paper='" + paper + '\'' +
                ", faceValue='" + faceValue + '\'' +
                ", printRun=" + printRun +
                ", score='" + score + '\'' +
                ", links='" + links + '\'' +
                ", imgLink='" + imgLink + '\'' +
                ", imgName='" + imgName + '\'' +
                ", collectingDate='" + collectingDate + '\'' +
                '}';
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getThemes() {
        return themes;
    }

    public void setThemes(String themes) {
        this.themes = themes;
    }

    public String getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(String issuedOn) {
        this.issuedOn = issuedOn;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getEmission() {
        return emission;
    }

    public void setEmission(String emission) {
        this.emission = emission;
    }

    public String getPerforation() {
        return perforation;
    }

    public void setPerforation(String perforation) {
        this.perforation = perforation;
    }

    public String getPrinting() {
        return printing;
    }

    public void setPrinting(String printing) {
        this.printing = printing;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public String getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(String faceValue) {
        this.faceValue = faceValue;
    }

    public Long getPrintRun() {
        return printRun;
    }

    public void setPrintRun(Long printRun) {
        this.printRun = printRun;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getCollectingDate() {
        return collectingDate;
    }

    public void setCollectingDate(String collectingDate) {
        this.collectingDate = collectingDate;
    }
}
