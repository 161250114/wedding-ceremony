package com.wedding.model.po;

public class Date_standard {
    private Integer id;

    private Integer agemin;

    private Integer agemax;

    private Integer heightmin;

    private Integer heightmax;

    private String salary;

    private String education;

    private String address;

    private Byte marrige;

    private Integer userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgemin() {
        return agemin;
    }

    public void setAgemin(Integer agemin) {
        this.agemin = agemin;
    }

    public Integer getAgemax() {
        return agemax;
    }

    public void setAgemax(Integer agemax) {
        this.agemax = agemax;
    }

    public Integer getHeightmin() {
        return heightmin;
    }

    public void setHeightmin(Integer heightmin) {
        this.heightmin = heightmin;
    }

    public Integer getHeightmax() {
        return heightmax;
    }

    public void setHeightmax(Integer heightmax) {
        this.heightmax = heightmax;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary == null ? null : salary.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Byte getMarrige() {
        return marrige;
    }

    public void setMarrige(Byte marrige) {
        this.marrige = marrige;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}