package com.wedding.usermanage.vo;

import java.io.Serializable;

public class BaseInfoVO implements Serializable {
    String fullname;
    int sex;
    String birthday;
    int height;
    String username;
    String phone;
    String address;
    String education;
    int marriage;
    String profession;
    String salary;

    public BaseInfoVO() {
    }

    public BaseInfoVO(String fullname, int sex, String birthday, int height, String username, String phone, String address, String education, int marriage, String profession, String salary) {
        this.fullname = fullname;
        this.sex = sex;
        this.birthday = birthday;
        this.height = height;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.education = education;
        this.marriage = marriage;
        this.profession = profession;
        this.salary = salary;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public int getMarriage() {
        return marriage;
    }

    public void setMarriage(int marriage) {
        this.marriage = marriage;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
