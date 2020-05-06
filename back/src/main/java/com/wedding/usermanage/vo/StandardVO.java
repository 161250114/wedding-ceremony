package com.wedding.usermanage.vo;

import java.io.Serializable;

public class StandardVO implements Serializable {
    int ageMin;
    int ageMax;
    int heightMin;
    int heightMax;
    String salary;
    String education;
    String address;
    int marriage;

    public StandardVO(int ageMin, int ageMax, int heightMin, int heightMax, String salary, String education, String address, int marriage) {
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.heightMin = heightMin;
        this.heightMax = heightMax;
        this.salary = salary;
        this.education = education;
        this.address = address;
        this.marriage = marriage;
    }

    public StandardVO() {
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    public int getHeightMin() {
        return heightMin;
    }

    public void setHeightMin(int heightMin) {
        this.heightMin = heightMin;
    }

    public int getHeightMax() {
        return heightMax;
    }

    public void setHeightMax(int heightMax) {
        this.heightMax = heightMax;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMarriage() {
        return marriage;
    }

    public void setMarriage(int marriage) {
        this.marriage = marriage;
    }
}
