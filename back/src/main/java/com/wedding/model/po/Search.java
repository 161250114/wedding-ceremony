package com.wedding.model.po;

public class Search {
    private Integer id;

    private Integer sex;

    private Integer youngest;

    private Integer oldest;

    private String address;

    private Integer shortest;

    private Integer tallest;

    private Integer lowest;

    private Integer highest;

    private String education;

    private String profession;

    private Integer marrige;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getYoungest() {
        return youngest;
    }

    public void setYoungest(Integer youngest) {
        this.youngest = youngest;
    }

    public Integer getOldest() {
        return oldest;
    }

    public void setOldest(Integer oldest) {
        this.oldest = oldest;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getShortest() {
        return shortest;
    }

    public void setShortest(Integer shortest) {
        this.shortest = shortest;
    }

    public Integer getTallest() {
        return tallest;
    }

    public void setTallest(Integer tallest) {
        this.tallest = tallest;
    }

    public Integer getLowest() {
        return lowest;
    }

    public void setLowest(Integer lowest) {
        this.lowest = lowest;
    }

    public Integer getHighest() {
        return highest;
    }

    public void setHighest(Integer highest) {
        this.highest = highest;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession == null ? null : profession.trim();
    }

    public Integer getMarrige() {
        return marrige;
    }

    public void setMarrige(Integer marrige) {
        this.marrige = marrige;
    }
}